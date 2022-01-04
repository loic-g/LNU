// App.js
const Cal = require('./calendar.js')
const Calendar = Cal.Calendar
const Cin = require('./cinema.js')
const Cinema = Cin.Cinema
const Din = require('./dinner')
const Dinner = Din.Dinner

const scrapper = require('./scrapper.js')
const cheerio = require('cheerio')

const restaurantDAYS = new Map()
restaurantDAYS.set('fri', '05')
restaurantDAYS.set('sat', '06')
restaurantDAYS.set('sun', '07')

const recommendationDays = new Map()
recommendationDays.set('05', 'Friday')
recommendationDays.set('06', 'Saturday')
recommendationDays.set('07', 'Sunday')

async function startAnalysing () {
  if (process.argv.length < 3) {
    console.log('Invalid arguments given')
    process.exit()
  } else if (process.argv.length > 3) {
    console.log('There is too many argument given')
    process.exit()
  }
  // Get the first Page
  const firstLink = process.argv[2]
  const firstPageHTML = await scrapper.getHtml(firstLink)
  const resp = cheerio('li > a', firstPageHTML)
  const urls = []
  for (let i = 0; i < resp.length; i++) {
    urls[i] = resp[i].attribs.href
  }
  console.log('Scraping links...OK')
  // Calendar Possible Dates
  const calendar = new Calendar()
  const possibleDates = await calendar.start(urls[0])

  if (possibleDates.length === 0) {
    console.log('No available dates')
    process.exit()
  }
  console.log('Scraping available days...OK')
  // Cinema Possible Dates
  const cinema = new Cinema(possibleDates, urls[1])
  const availableCinemaDates = await cinema.getCinemaTimes(urls[1])
  console.log('Scraping showtime...OK')

  // Dinner Possible Dates
  const dinner = new Dinner()
  const availableDinnerTime = await dinner.loginRestaurant(urls[2])
  console.log('Scraping possible reservation...OK')

  const possibleDinnerTime = []
  for (let i = 0; i < availableDinnerTime.length; i++) {
    for (let j = 0; j < possibleDates.length; j++) {
      if (restaurantDAYS.get(availableDinnerTime[i].day) === possibleDates[j]) {
        possibleDinnerTime.push(availableDinnerTime[i])
      }
    }
  }

  const recommendation = getRecommendation(availableCinemaDates, possibleDinnerTime)
  printRecommendation(recommendation)
}

function getRecommendation (showTimes, dinnerTimes) {
  const recommendations = []
  for (let i = 0; i < showTimes[0].length; i++) {
    for (let j = 0; j < dinnerTimes.length; j++) {
      const cinemaTime = parseInt(showTimes[0][i].time.substring(0, 2))
      const restaurantTime = parseInt(dinnerTimes[j].startTime)

      if (showTimes[0][i].day === restaurantDAYS.get(dinnerTimes[j].day)) {
        if (restaurantTime - cinemaTime >= 2) {
          const reco = {
            day: recommendationDays.get(showTimes[0][i].day),
            nameMovie: showTimes[0][i].name,
            startTimeMovie: showTimes[0][i].time,
            tableTimeS: dinnerTimes[j].startTime,
            tableTimeE: dinnerTimes[j].endTime
          }
          recommendations.push(reco)
        }
      }
    }
  }
  return recommendations
}

function printRecommendation (recommendation) {
  console.log('\nRecommendations')
  console.log('===============')
  for (let i = 0; i < recommendation.length; i++) {
    console.log('* On ' + recommendation[i].day + ' the movie "' + recommendation[i].nameMovie + '" starts at ' + recommendation[i].startTimeMovie +
    ' and there is a free table between ' + recommendation[i].tableTimeS + ':00-' + recommendation[i].tableTimeE + ':00.')
  }
}

startAnalysing()
// console.log(calendarDates,"Try")
// console.log(scrapper.getText(args))
