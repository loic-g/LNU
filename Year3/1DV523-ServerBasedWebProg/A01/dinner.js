const scrapper = require('./scrapper')
const cheerio = require('cheerio')

class Dinner {
  async loginRestaurant (url) {
    const dinnerHTML = await scrapper.getDinnerTimes(url)
    const freeTables = cheerio('p > input', dinnerHTML)

    const availableTable = []
    for (let i = 0; i < freeTables.length; i++) {
      const value = freeTables[i].attribs.value
      const table = {
        day: value.substring(0, 3),
        startTime: value.substring(3, 5),
        endTime: value.substring(5, 7)
      }
      availableTable.push(table)
    }
    return availableTable
  }
}

module.exports = {
  Dinner
}
