'use strict'
const fetch = require('node-fetch')
const fetchRetry = require('fetch-retry')(fetch, { retries: 5, retryDelay: 800 })
const dotenv = require('dotenv').config();

const controller = {}
module.exports = controller

controller.fetchIssues = async (req, res)=>{
  try{
    const test = await fetchRetry(`https://gitlab.lnu.se/api/v4/projects/13859/issues?state=opened&private_token=${process.env.GIT_TOKEN}`)
    const issuesFetched = await test.json()

    let issues = []
    for(let i=0;i<issuesFetched.length;i++){
      let notes = await fetchRetry(`https://gitlab.lnu.se/api/v4/projects/13859/issues/${issuesFetched[i].iid}/notes?private_token=${process.env.GIT_TOKEN}`)
      let notesJson = await notes.json()

      let issuesCreation = {
        id: issuesFetched[i].id,
        title:issuesFetched[i].title,
        description: issuesFetched[i].description,
        state: issuesFetched[i].state,
        created_at: issuesFetched[i].created_at,
        updated_at: issuesFetched[i].updated_at,
        comments: notesJson.length ,
        link: issuesFetched[i].web_url
      }

      if(issuesCreation){
        issues.push(issuesCreation)
      }
    }
    res.render('index',{issues: issues})
  }catch(error){
    console.error(error)
    res.render('index')
  }

}

controller.webHook = async (req,res,next)=> {
  const secret = req.headers['x-gitlab-token']
  if(secret === process.env.SECRET){
    if(req.body.event_type === 'issue' ){
      if(!req.body.changes.description){
        req.body.changes.description = {}
        req.body.changes.description.previous = null
        req.body.changes.description.current = null
      }
      let eventToSend = {
        id: req.body.object_attributes.id,
        title: req.body.object_attributes.title ,
        description: req.body.object_attributes.description,
        state: req.body.object_attributes.state,
        created_at: req.body.object_attributes.created_at,
        updated_at: req.body.object_attributes.updated_at,
        comments: 0 ,
        link: req.body.object_attributes.url,
        currentDesc: req.body.changes.description.previous,
        newDesc: req.body.changes.description.current,
        type: req.body.event_type
      }
      res.locals.event = eventToSend
      return next()
    }else if(req.body.event_type === 'note'){
      let eventToSend = {
        id: req.body.issue.id,
        title: req.body.issue.title,
        description: req.body.object_attributes.description,
        created_at: req.body.object_attributes.created_at,
        updated_at: req.body.object_attributes.updated_at,
        link: req.body.object_attributes.url,
        type: req.body.event_type
      }
      res.locals.event = eventToSend
      return next()
    }
    return res.sendStatus(500)
  }
}



