export default class WeatherApp {
  /**
   * Constructor to set the windowID
   *
   * @class
   * @param {number} id - id of the window
   */
  constructor (id) {
    this.windowId = id
  }

  /**
   * Main method that put the basic information on the screen and
   * decides which methods to call depending on user choice.
   * The method will fetch data from OpenWeatherMap API depending on the user input.
   *
   * @param {HTMLElement} weatherDiv - HTMLElement of the window Div of the chat
   * @param {HTMLElement} weatherHeader - HTMLElement of the window header of the chat
   */
  run (weatherDiv, weatherHeader) {
    const weatherContent = weatherDiv.querySelector('#weatherContent')
    weatherContent.id = 'weather' + this.windowId + 'content'

    const titleOfWindow = document.createTextNode('Weather ' + this.windowId)
    const closeButton = weatherHeader.querySelector('#closeButtonW')
    closeButton.addEventListener('click', () => {
      weatherDiv.remove()
    })
    weatherHeader.insertBefore(titleOfWindow, closeButton)

    const weatherSetting = weatherContent.querySelector('#weatherSelect')
    const weatherButton = weatherSetting.querySelector('#weatherBut')
    const weatherCity = weatherSetting.querySelector('#weatherCity')

    weatherButton.addEventListener('click', () => {
      const city = weatherCity.value
      if (city) {
        fetch('http://api.openweathermap.org/data/2.5/weather?q=' + city + '&units=metric&appid=3eb68686c010511244c1f09797509ffe').then(
          response => {
            return response.json()
          }).then(weather => {
          if (weather.cod === 200) {
            const templateWeatherResp = document.getElementsByTagName('template')[4]
            const weatherResp = templateWeatherResp.content.querySelector('div')
            const weatherRespDiv = document.importNode(weatherResp, true)
            const weatherTemp = weatherRespDiv.querySelector('#weatherTemp')
            const weatherDesc = weatherRespDiv.querySelector('#weatherDesc')
            const weatherMin = weatherRespDiv.querySelector('#weatherMin')
            const weatherMax = weatherRespDiv.querySelector('#weatherMax')
            const weatherHumidity = weatherRespDiv.querySelector('#weatherHumidity')
            const weatherWind = weatherRespDiv.querySelector('#weatherWind')

            weatherCity.value = ''
            weatherSetting.remove()

            const weatherTempL = document.createElement('label')
            weatherTempL.innerHTML = '<b>Current: </b><br>' + weather.main.temp + '°C'
            weatherTempL.style.fontSize = 'xx-large'
            weatherTemp.appendChild(weatherTempL)

            const weatherDescL = document.createElement('label')
            weatherDescL.innerHTML = '<b>Weather: </b><br>' + weather.weather[0].main
            weatherDescL.style.fontSize = 'xx-large'
            weatherDesc.appendChild(weatherDescL)

            const weatherMinL = document.createElement('label')
            weatherMinL.innerHTML = '<b>Min: </b><br>' + weather.main.temp_min + '°C'
            weatherMin.appendChild(weatherMinL)

            const weatherMaxL = document.createElement('label')
            weatherMaxL.innerHTML = '<b>Max: </b><br>' + weather.main.temp_max + '°C'
            weatherMax.appendChild(weatherMaxL)

            const weatherHumidityL = document.createElement('label')
            weatherHumidityL.innerHTML = '<b>Humidity: </b><br>' + weather.main.humidity + '%'
            weatherHumidity.appendChild(weatherHumidityL)

            const weatherWindL = document.createElement('label')
            weatherWindL.innerHTML = '<b>Wind: </b><br>' + weather.wind.speed + 'm/s'
            weatherWind.appendChild(weatherWindL)

            const weatherCityL = document.createElement('label')
            weatherCityL.innerHTML = '<b>' + weather.name + '</b><br>'
            weatherCityL.style.fontSize = 'xx-large'
            weatherContent.appendChild(weatherCityL)

            weatherContent.appendChild(weatherRespDiv)
          } else {
            const label = document.createElement('label')
            label.innerHTML = '<br> City not Found, please try again'
            weatherSetting.appendChild(label)
            weatherCity.value = ''
          }
        })
      }
    })
  }
}
