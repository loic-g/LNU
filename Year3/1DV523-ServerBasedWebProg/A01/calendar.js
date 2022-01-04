const cheerio = require('cheerio')
const scrapper = require('./scrapper.js')

class Calendar {
  start (url) {
    return scrapper.getHtml(url).then(function (html) {
      const resp = cheerio('li > a', html)
      const urls = []
      for (let i = 0; i < resp.length; i++) {
        urls[i] = resp[i].attribs.href
      }
      return Promise.all(
        urls.map(async function (urlPers) {
          return await scrapper.linksParse(url + '/' + urlPers)
        })
      )
    }).then(function (test) {
      const responseBool = []
      for (let i = 0; i < test.length; i++) {
        let isAvailable = true
        for (let j = 0; j < test[i].links.length; j++) {
          if (test[j].links[i].toLowerCase() !== 'ok') {
            isAvailable = false
            break
          }
        }
        if (isAvailable) {
          responseBool.push(true)
        } else {
          responseBool.push(false)
        }
      }
      const response = []

      // Change bool into numerical string
      for (let j = 0; j < responseBool.length; j++) {
        if (responseBool[j] === true) {
          response.push('0' + (j + 5))
        }
      }
      return response
    }).catch(function (err) {
      console.log(err)
    })
  }
}

module.exports = {
  Calendar
}
