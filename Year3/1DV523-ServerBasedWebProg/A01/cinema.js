const cheerio = require('cheerio')
const scrapper = require('./scrapper.js')

class Cinema {
  constructor (days, url) {
    this.daysTo = days
    this.urlCinema = url
  }

  getCinemaTimes (url) {
    return scrapper.getHtml(url).then(function (html) {
      const resp = cheerio('select[name="movie"] > option', html)

      const movies = []
      for (let i = 1; i < resp.length; i++) {
        movies.push({
          value: resp[i].attribs.value,
          name: resp[i].children[0].data
        })
      }
      return movies
    }).then(moviesOption => {
      const daysAvailable = this.daysTo
      const result = []
      return Promise.all(
        daysAvailable.map(async function (day) {
          for (let i = 0; i < moviesOption.length; i++) {
            const res = await scrapper.getCinemaTimes(url + `/check?day=${day}&movie=${moviesOption[i].value}`)

            for (let j = 0; j < res.length; j++) {
              if (res[j].status === 1) {
                const picked = moviesOption.find(o => o.value === res[j].movie)
                const obj = { day: res[j].day, time: res[j].time, name: picked.name }
                result.push(obj)
              }
            }
          }
          return result
        })
      )
    })
  }
}
module.exports = {
  Cinema
}
