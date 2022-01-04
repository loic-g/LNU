
const socketClient = io();

socketClient.on('webhook-event',function (event){
  if(event.type === 'note'){
    notifHandler(event, "New Comment on Issue")
    changeCommentHandler(event)
  }else if(event.type === 'issue'){
    if(event.state === 'closed'){
      notifHandler(event,"Closed Issue")
      removeIssue(event)
    }else {
      notifHandler(event,"New Issue")
      newIssueHandler(event)
    }
  }
})

const notifHandler = (event, type)=>{
  const notification = document.querySelector('.notification')

  let bubble = document.createElement("div")
  bubble.setAttribute('class','notificationBubble')
  //Header creation
  let header = document.createElement('header')
  header.setAttribute('class','notificationHeader')
  header.innerHTML = `<h2>${type}</h2>`
  bubble.append(header)

  let content = document.createElement('div')
  content.setAttribute('class','notificationContent')

  let updatedDate= document.createElement('p')
  updatedDate.innerText = "Updated Date: " +event.updated_at

  let link = document.createElement('a')
  link.setAttribute('href',event.link)
  link.innerText = "Link to the issue"

  let title  = document.createElement('p')
  title.innerText = "Title: "+event.title

  content.append(title)
  content.append(updatedDate)
  content.append(link)

  bubble.append(content)
  notification.append(bubble)
}

const newIssueHandler = (event) => {
  const firstissues = document.querySelector('.issueBubble')
  const issue = document.querySelector('.issueBubble').parentNode
  let bubble = document.createElement("div")
  bubble.setAttribute('class','issueBubble')
  bubble.setAttribute('id',event.id)
  //Header creation
  let header = document.createElement('header')
  header.setAttribute('class','issueHeader')
  header.innerHTML = `<h2>${event.title}</h2>`
  bubble.append(header)

  let content = document.createElement('div')
  content.setAttribute('class','issueContent')

  let desc = document.createElement('p')
  desc.innerText = "Description: " +event.description

  let state = document.createElement('p')
  state.innerText = "State:" +event.state

  let createDate = document.createElement('p')
  createDate.innerText = "Create date:" +event.created_at

  let updatedDate= document.createElement('p')
  updatedDate.innerText = "Updated Date: " +event.updated_at

  let comments= document.createElement('p')
  comments.innerText = "Updated Date: " +event.comments

  let link = document.createElement('a')
  link.setAttribute('href',event.link)
  link.innerText = "Link to the issue"

  content.append(desc)
  content.append(state)
  content.append(createDate)
  content.append(updatedDate)
  content.append(comments)
  content.append(state)
  content.append(link)

  bubble.append(content)

  issue.insertBefore(bubble,firstissues)
}

const removeIssue = (event) => {
  document.getElementById(event.id).remove()
}

const changeCommentHandler = (event) =>{
  let issue = document.getElementById(event.id)
  let comments = issue.querySelector("#comments")
  let update = issue.querySelector("#udate")
  const newCommentsNumber = Number(comments.textContent.slice(-1)) + 1
  comments.textContent = "Nb of Comments: "+ newCommentsNumber
  update.textContent = "Update date: "+ event.updated_at
}

