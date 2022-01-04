// SCRAPPER.js
const cheerio = require('cheerio')
const fetch = require('node-fetch')
const fetchRetry = require('fetch-retry')(fetch, { retries: 5, retryDelay: 800 })
const request = require('request')

async function linksParse (url) {
  const response = await fetchRetry(url)
  const body = await response.text()
  const links = cheerio('tr > td', body)
  const linksArr = []
  for (let i = 0; i < links.length; i++) {
    linksArr[i] = links[i].children[0].data
  }
  return {
    links: linksArr
  }
}
async function getHtml (url) {
  const response = await fetchRetry(url)
  const body = await response.text()
  return body
}

async function getCinemaTimes (url) {
  const response = await fetchRetry(encodeURI(url))
  const body = await response.json()
  return body
}

async function getDinnerTimes (url) {
  const login = await loginRestaurant(url)

  const response = await fetch(login.url, {
    headers: { Cookie: login.cookie }
  })

  const body = await response.text()
  return body
}

const restaurantUsername = 'zeke'
const restaurantPass = 'coys'
async function loginRestaurant (url) {
  const html = await getHtml(url)
  const urla = cheerio('form', html)
  const actionUrl = urla[0].attribs.action
  const posOfLastSlash = actionUrl.lastIndexOf('/')
  const postURL = actionUrl.substring(posOfLastSlash, actionUrl.length)

  return new Promise(resolve => {
    request(
      {
        method: 'POST',
        url: url + postURL,
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        form: {
          username: restaurantUsername,
          password: restaurantPass,
          submit: 'login'
        }
      }, (error, response) => {
        if (error) {
          console.log(error, 'Error with Login')
          process.exit()
        }
        const cookies = response.headers['set-cookie'][0]
        resolve({ url: url + '/' + response.headers.location, cookie: cookies })
      })
  })
}

module.exports.linksParse = linksParse
module.exports.getCinemaTimes = getCinemaTimes
module.exports.getHtml = getHtml
module.exports.getDinnerTimes = getDinnerTimes
module.exports.loginRestaurant = loginRestaurant
