export default class Chat {
  /**
   * Sets the windowID,the username and create variables for the messages, socket.
   *
   * @class
   * @param {number} id - id of the window
   */
  constructor (id) {
    this.windowId = id
    this.msg = null
    this.webSocket = null
    this.username = window.localStorage.getItem('username') === null ? '' : window.localStorage.getItem('username')
  }

  /**
   * Main method that put the basic information on the screen and
   * decides which methods to call depending on user choice.
   *
   * @param {HTMLElement} chatDiv - HTMLElement of the window Div of the chat
   * @param {HTMLElement} chatHeader - HTMLElement of the window header of the chat
   */
  run (chatDiv, chatHeader) {
    chatDiv.addEventListener('click', () => {
      chatDiv.style.zIndex++
    })

    const chatSetting = chatDiv.querySelector('#CSetting')
    chatSetting.id = 'CSetting' + this.windowId
    const addNewUser = chatDiv.querySelector('#addNewUser')
    addNewUser.id = 'addNewUser' + this.windowId
    const sendMsgDiv = chatDiv.querySelector('#chatSendMessage')
    sendMsgDiv.id = 'chatSendMessage' + this.windowId
    const chatContent = chatDiv.querySelector('#chatContent')
    chatContent.id = 'chatContent' + this.windowId
    const sendMSG = chatDiv.querySelector('#sendMSG')
    sendMSG.id = 'sendMSG' + this.windowId

    const titleOfWindow = document.createTextNode('Chat ' + this.windowId)
    const closeButton = chatHeader.querySelector('#closeButton')
    chatHeader.insertBefore(titleOfWindow, closeButton)

    if (this.username === '') {
      /* Creating Username */
      sendMsgDiv.style.display = 'none'
      chatSetting.style.display = 'block'

      addNewUser.addEventListener('keypress', (event) => {
        if (event.keyCode === 13) {
          event.preventDefault()
          this.addUser(this)
          titleOfWindow.nodeValue = 'Chat ' + this.windowId + ' - ' + this.username
          chatSetting.style.display = 'none'
          sendMsgDiv.style.display = 'block'
        }
      })
    } else {
      /* Username Found */
      titleOfWindow.nodeValue = 'Chat ' + this.windowId + ' - ' + this.username
      chatSetting.style.display = 'none'
      sendMsgDiv.style.display = 'block'

      this.connectToSocket() // Connects to the server

      sendMsgDiv.addEventListener('keypress', (event) => {
        if (event.keyCode === 13) {
          this.sendMsg(event.target.value)
          event.target.value = ''
          event.preventDefault()
        }
      })
    }

    closeButton.addEventListener('click', () => {
      this.connectToSocket().then(socket => {
        chatDiv.remove()
        socket.send('Client closing connection by intention.')
        socket.close()
      })
    })
  }

  /**
   * Method used to add the user to the localStorage
   */
  addUser () {
    this.username = document.querySelector('#addNewUser' + this.windowId).value
    if (this.username) {
      window.localStorage.setItem('username', this.username)
      document.querySelector('#sendMSG' + this.windowId).addEventListener('keypress', (key) => this.keyPressedIsEnter(key))
    }
  }

  /**
   * Method to check if the key pressed is the Enter key.
   *
   * @param {Event} key - Key
   */
  keyPressedIsEnter (key) {
    if (key.keyCode === 13) {
      key.preventDefault()
      this.sendMsg(key.target.value)
    }
  }

  /**
   * Method to connect to the webserver.
   * It will send the opening message when opening.
   * Will show the message when it receives it
   *
   * @returns {Promise} - Returns the socket
   */
  connectToSocket () {
    return new Promise((resolve, reject) => {
      if (this.chatSocket && this.chatSocket.readyState === 1) {
        resolve(this.chatSocket)
      } else {
        this.chatSocket = new window.WebSocket('ws://vhost3.lnu.se:20080/socket/')
        this.chatSocket.onerror = () => { //
          reject(this.chatSocket)
        }
        this.chatSocket.onopen = () => {
          this.printOpeningMSG('You are connected!')
          resolve(this.chatSocket)
        }
        this.chatSocket.onmessage = (messageEvent) => {
          this.printMessageToUser(JSON.parse(messageEvent.data))
        }
      }
    })
  }

  /**
   * Method to send a message to the server using the JSON format.
   *
   * @param {string} text - text to send
   */
  sendMsg (text) {
    const dataToSend = {
      type: 'message',
      data: text,
      username: this.username,
      channel: 'Secret Channel',
      key: 'eDBE76deU7L0H9mEBgxUKVR0VCnq0XBd'
    }
    this.connectToSocket().then(function (webSocket) {
      webSocket.send(JSON.stringify(dataToSend))
    }).catch(error => {
      console.log('Something went wrong!!', error)
    })
  }

  /**
   * Method to show that the user connected to the server!
   *
   * @param {string} msg - text to show
   */
  printOpeningMSG (msg) {
    if (msg) {
      const chatContent = document.querySelector('#chatContent' + this.windowId)
      const label = document.createElement('label')
      label.innerText = 'You are connected!'
      chatContent.appendChild(label)
    }
  }

  /**
   * Method to print the message on the window, everytime it receive a message from the server.
   *
   * @param {*} msg - Message received
   */
  printMessageToUser (msg) {
    if (msg.type === 'message') {
      const templateMSG = document.importNode(document.querySelector('#msgTemplate'), true)
      const msgDiv = templateMSG.content.cloneNode(true).querySelector('#messageDiv')
      msgDiv.querySelector('#username').innerHTML = '<b>' + msg.username + '<b> : '
      msgDiv.querySelector('#msg').innerHTML = msg.data
      document.querySelector('#chatContent' + this.windowId).appendChild(msgDiv)
    }
  }
}
