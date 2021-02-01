
export default class memoryGame {
  /**
   * Constructor for the MemoryGame
   *
   * @class
   * @param {number} id - Id of the window
   */
  constructor (id) {
    this.windowId = id
    this.clickedPic = null
    this.counter = 0
    this.amountOfTries = 0
  }

  /**
   * Method to set how the window will look like and to decide what
   * kind of game the user wants. Choice between 2x2, 2x4 and 4x4.
   *
   * @param {HTMLElement} memoryDiv - Div holding the whole window
   * @param {HTMLElement} memoryHeader - Div holding just the header
   */
  run (memoryDiv, memoryHeader) {
    const memoryContent = memoryDiv.querySelector('#memoryContent')
    memoryContent.id = 'memory' + this.windowId + 'content'
    const titleOfWindow = document.createTextNode('Memory ' + this.windowId)
    const closeButton = memoryHeader.querySelector('#closeButtonM')
    closeButton.addEventListener('click', () => {
      memoryDiv.remove()
    })
    memoryHeader.insertBefore(titleOfWindow, closeButton)

    // To get the buttons to know the size of the game
    const fourXfour = memoryDiv.querySelector('#fourbyfour')
    const twoXtwo = memoryDiv.querySelector('#twobytwo')
    const twoXfour = memoryDiv.querySelector('#twobyfour')

    fourXfour.addEventListener('click', () => {
      this.fourByFour(memoryDiv)
    })

    twoXtwo.addEventListener('click', () => {
      this.twoByTwo(memoryDiv)
    })

    twoXfour.addEventListener('click', () => {
      this.twoByFour(memoryDiv)
    })
  }

  /**
   * Method to shuffle an array randomly using the Fisher-Yates algorithm
   *
   * @param {Array} arr - array to shuffle
   * @returns {Array} - suffled array
   */
  shuffle (arr) {
    for (let i = arr.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * i)
      const temp = arr[i]
      arr[i] = arr[j]
      arr[j] = temp
    }
    return arr
  }

  /**
   * Method to create the 4x4 memory game and the logic behind it.
   *
   * @param {HTMLElement} memoryDiv - Div holding the whole window
   */
  fourByFour (memoryDiv) {
    // create suffle array here
    const imageArr = [0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7]
    const shuffledArr = this.shuffle(imageArr)

    memoryDiv.style.width = '500px'
    memoryDiv.style.height = '600px'
    const memoryHeader = memoryDiv.getElementsByTagName('div')[0]
    const icon = memoryHeader.getElementsByTagName('img')[0]

    icon.style.marginRight = '380px'
    const memoryContent = memoryDiv.getElementsByClassName('memoryContent')[0]
    const memorySelect = memoryContent.querySelector('#memorySelect')
    memorySelect.style.display = 'none'

    for (let j = 0; j < 4; j++) {
      const divRow = document.createElement('div')
      divRow.className = 'row4x4'
      for (let i = 0; i < 4; i++) {
        const divColumn = document.createElement('div')
        divColumn.className = 'gameImage'
        divColumn.innerText = '?'
        divColumn.dataset.value = shuffledArr[j * 4 + i]
        divColumn.id = 'memory' + this.windowId + 'divNB' + ((j * 4) + i)
        divColumn.addEventListener('click', () => {
          this.clickable(divColumn, '4x4')
        })
        divRow.appendChild(divColumn)
      }
      memoryContent.appendChild(divRow)
    }
  }

  /**
   * Method to make the each picture clickable, counts trial amount and check if the user has won.
   *
   * @param {HTMLElement} divColumn - Div containing the picture
   * @param {string} game - String to show the size of the game
   */
  clickable (divColumn, game) {
    const value = divColumn.dataset.value
    if (!this.clickedPic) {
      this.clickedPic = divColumn
      const image = document.createElement('img')
      image.src = '/_dist_/img/' + value + '.jpg'
      image.style.width = '100px'
      divColumn.innerText = ''
      divColumn.appendChild(image)
    } else if (this.clickedPic) {
      if (value === this.clickedPic.dataset.value) {
        const image = document.createElement('img')
        image.src = '/_dist_/img/' + value + '.jpg'
        image.style.width = '100px'
        divColumn.innerText = ''
        divColumn.appendChild(image)
        this.clickedPic = null
        this.counter++
        this.amountOfTries++
      } else {
        this.amountOfTries++
        const image = document.createElement('img')
        image.src = '/_dist_/img/' + value + '.jpg'
        image.style.width = '100px'
        divColumn.innerText = ''
        divColumn.appendChild(image)
        setTimeout(() => {
          this.clickedPic.innerHTML = '?'
          this.clickedPic = null
          divColumn.innerHTML = '?'
        }, 500)
      }
    }
    if (game === '4x4') {
      if (this.counter === 8) {
        const memoryContent = document.querySelector('#memory' + this.windowId + 'content')
        const label = document.createElement('label')
        label.innerHTML = 'Congratulation you have WON!<br>' + 'Tries: ' + this.amountOfTries
        memoryContent.appendChild(label)
      }
    } else if (game === '2x4') {
      if (this.counter === 4) {
        const memoryContent = document.querySelector('#memory' + this.windowId + 'content')
        const label = document.createElement('label')
        label.innerHTML = 'Congratulation you have WON!<br>' + 'Tries: ' + this.amountOfTries
        memoryContent.appendChild(label)
      }
    } else if (game === '2x2') {
      if (this.counter === 2) {
        const memoryContent = document.querySelector('#memory' + this.windowId + 'content')
        const label = document.createElement('label')
        label.innerHTML = 'Congratulation you have WON!<br>' + 'Tries: ' + this.amountOfTries
        memoryContent.appendChild(label)
      }
    }
  }

  /**
   * Method to create the 2x2 memory game and the logic behind it.
   *
   * @param {HTMLElement} memoryDiv - Div holding the whole window
   */
  twoByTwo (memoryDiv) {
    // create suffle array here
    const imageArr = [0, 0, 1, 1]
    const shuffledArr = this.shuffle(imageArr)

    memoryDiv.style.width = '260px'
    memoryDiv.style.height = '350px'
    const memoryHeader = memoryDiv.getElementsByTagName('div')[0]
    const icon = memoryHeader.getElementsByTagName('img')[0]

    icon.style.marginRight = '140px'
    const memoryContent = memoryDiv.getElementsByClassName('memoryContent')[0]
    const memorySelect = memoryContent.querySelector('#memorySelect')
    memorySelect.style.display = 'none'

    for (let j = 0; j < 2; j++) {
      const divRow = document.createElement('div')
      divRow.className = 'row4x4'
      for (let i = 0; i < 2; i++) {
        const divColumn = document.createElement('div')
        divColumn.className = 'gameImage'
        divColumn.innerText = '?'
        divColumn.dataset.value = shuffledArr[j * 2 + i]
        divColumn.addEventListener('click', () => {
          this.clickable(divColumn, '2x2')
        })
        divRow.appendChild(divColumn)
      }
      memoryContent.appendChild(divRow)
    }
  }

  /**
   * Method to create the 2x4 memory game and the logic behind it.
   *
   * @param {HTMLElement} memoryDiv - Div holding the whole window
   */
  twoByFour (memoryDiv) {
    const imageArr = [0, 0, 1, 1, 2, 2, 3, 3]
    const shuffledArr = this.shuffle(imageArr)

    memoryDiv.style.width = '500px'
    memoryDiv.style.height = '350px'
    const memoryHeader = memoryDiv.getElementsByTagName('div')[0]
    const icon = memoryHeader.getElementsByTagName('img')[0]

    icon.style.marginRight = '380px'
    const memoryContent = memoryDiv.getElementsByClassName('memoryContent')[0]
    const memorySelect = memoryContent.querySelector('#memorySelect')
    memorySelect.style.display = 'none'

    for (let j = 0; j < 2; j++) {
      const divRow = document.createElement('div')
      divRow.className = 'row4x4'
      for (let i = 0; i < 4; i++) {
        const divColumn = document.createElement('div')
        divColumn.className = 'gameImage'
        divColumn.innerText = '?'
        divColumn.dataset.value = shuffledArr[j * 4 + i]
        divColumn.addEventListener('click', () => {
          this.clickable(divColumn, '2x4')
        })
        divRow.appendChild(divColumn)
      }
      memoryContent.appendChild(divRow)
    }
  }
}
