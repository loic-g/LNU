
import Chat from './chat.js'
import MemoryGame from './memoryGame.js'
import WeatherApp from './weatherApp.js'

let chatId = 1
let memoryId = 1
let weatherId = 1
let zIndex = 0

window.addEventListener('load', main)
/** Brain of the website which decides where to send the user depending on the button pressed */
function main () {
  document.querySelector('nav').addEventListener('click', event => {
    switch (event.target.id) {
      case 'chatB': {
        const chat = new Chat(chatId++)
        createWindowChat(chat)
        break
      }
      case 'memory': {
        const memoryGame = new MemoryGame(memoryId++)
        createWindowMemory(memoryGame)
        break
      }
      case 'weather': {
        const weather = new WeatherApp(weatherId++)
        createWindowWeather(weather)
        break
      }
    }
  })
}
/**
 * Creates the basic Chat Window and appends it to the page and starts
 * the chat application!
 *
 * @param {number} chat - Object of type Chat
 */
function createWindowChat (chat) {
  const windowPage = document.getElementById('window')
  const templateChat = document.getElementsByTagName('template')[0]
  const item = templateChat.content.querySelector('div')

  const chatDiv = document.importNode(item, true)
  chatDiv.id = 'chat' + chatId

  const chatHeader = chatDiv.querySelector('#chatHeader')
  chatHeader.id = 'chat' + chatId + ' header'

  windowPage.appendChild(chatDiv)
  chatDiv.addEventListener('click', () => {
    chatDiv.style.zIndex = zIndex++
  })
  dragElement(chatDiv, chatHeader)
  chat.run(chatDiv, chatHeader)
}

/**
 * Creates the basic Memory Window and appends it to the page and starts
 * the chat application!
 *
 * @param {number} memory - Object of type MemoryGame
 */
function createWindowMemory (memory) {
  const windowPage = document.getElementById('window')
  const templateMemory = document.getElementsByTagName('template')[1]
  const item = templateMemory.content.querySelector('div')

  const memoryDiv = document.importNode(item, true)
  memoryDiv.id = 'memory' + memoryId

  const memoryHeader = memoryDiv.querySelector('#memoryHeader')
  memoryHeader.id = 'memory' + memoryId + ' header'

  windowPage.appendChild(memoryDiv)

  memoryDiv.addEventListener('click', () => {
    memoryDiv.style.zIndex = zIndex++
  })
  dragElement(memoryDiv, memoryHeader)

  memory.run(memoryDiv, memoryHeader)
}

/**
 * Creates the basic Weather Window and appends it to the page and starts
 * the chat application!
 *
 * @param {number} weather - Object of type WeatherApp
 */
function createWindowWeather (weather) {
  const windowPage = document.getElementById('window')
  const templateWeather = document.getElementsByTagName('template')[2]
  const item = templateWeather.content.querySelector('div')

  const weatherDiv = document.importNode(item, true)
  weatherDiv.id = 'weather' + weatherId

  const weatherHeader = weatherDiv.querySelector('#weatherHeader')
  weatherHeader.id = 'weather' + weatherId + ' header'

  windowPage.appendChild(weatherDiv)

  weatherDiv.addEventListener('click', () => {
    weatherDiv.style.zIndex = zIndex++
  })
  dragElement(weatherDiv, weatherHeader)
  weather.run(weatherDiv, weatherHeader)
}

/**
 * Method to allow the elements to drag accross the window
 *
 * @param {HTMLElement} div - Takes the the window Div to be able to get the whole window
 * @param {HTMLElement} header - Takes just the header inside the windowDiv
 */
function dragElement (div, header) {
  let pos1 = 0; let pos2 = 0; let pos3 = 0; let pos4 = 0
  if (header) {
    header.onmousedown = dragMouse
  } else {
    div.onmousedown = dragMouse
  }

  /**
   * EventListener that activate when the mouse
   * button is pressed on the header.
   *
   * @param {Event} event - EventListener
   */
  function dragMouse (event) {
    event = event || window.event
    event.preventDefault()
    // Get mouse position when starting
    pos3 = event.clientX
    pos4 = event.clientY

    document.onmouseup = closeElement
    document.onmousemove = elementDrag
  }

  /**
   * Method to stop the eventListener on that element.
   */
  function closeElement () {
    document.onmousemove = null
    document.onmouseup = null
  }

  /**
   * Method to change the position of the window on the
   * screen depending on where the user drag it.
   *
   * @param {Event} event - eventListener
   */
  function elementDrag (event) {
    event = event || window.event
    event.preventDefault()

    pos1 = pos3 - event.clientX
    pos2 = pos4 - event.clientY
    pos3 = event.clientX
    pos4 = event.clientY

    div.style.top = (div.offsetTop - pos2) + 'px'
    div.style.left = (div.offsetLeft - pos1) + 'px'
  }
}
