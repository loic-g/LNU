
/**
 * This file is just a silly example to show everything working in the browser.
 * When you're ready to start on your site, clear the file. Happy hacking!
 **/
import Quizz from './modules/quizz.js'
import HighScoreTable from './modules/highScoreTable.js'

const content = document.getElementById("content");
const answer = document.getElementById("response");
const nextQ = document.getElementById("nextquestion");
const restart = document.getElementById("restart");
const submitButton = document.getElementById("sendButton");
const endMessage = document.getElementById("endMessage");
const alternate = document.getElementById("alternate");
const timerCountdown = document.getElementById("time");
const timerDiv = document.getElementById("timer");
const nameOfPlayer = document.getElementById("nameOfPlayer");
const nameButton = document.getElementById("submitName");

let nameId = "";
let amountOfQAnswered = 0;

let nextUrl = null;
let nbOfAlternatives = 0;


function updateContent(str) {
  content.innerHTML = str
}


nameButton.addEventListener("click", function () { //TO start game after getting name
  nameId = nameOfPlayer.value;

  if (nameId === "") {
    updateContent("You need to choose an username before submitting")
  } else {

    if (window.localStorage.getItem(nameId) == null) { //
      console.log(nameId + "NO HERE YET")
      let player = {
        name: nameId,
        score: 0,
        time: 0,
      }
      window.localStorage.setItem(nameId, JSON.stringify(player));
    }
    console.log("ALREADY THERE")
    Quizz.countdown(10, false, timerCountdown, nameId)

    Quizz.getFirstQuestion()
      .then(response => {
        console.log(response)

        response.json().then((data) => {
          console.log(data)
          updateContent(data.question)


          nextUrl = data.nextURL
        });
      })
      .catch((err) => {
        console.log('Fetch Error :-S', err)
        restart.hidden = false;
      });

    console.log(nameId)
    nameOfPlayer.hidden = true;
    nameButton.hidden = true;


    nextQ.hidden = false;
    answer.hidden = false;
    timerDiv.hidden = false;
    submitButton.hidden = false;
    timerCountdown.hidden = false;



  }

})

window.addEventListener("load", function () {
  restart.hidden = true;
  nextQ.hidden = true;
  answer.hidden = true;
  timerDiv.hidden = true;
  submitButton.hidden = true;
  timerCountdown.hidden = true;
  updateContent("Please enter your Username!")

})

sendButton.addEventListener("click", function () {

  nextQ.disabled = false;
  alternate.hidden = true;
  answer.hidden = false;
  console.log(alternate);
  let id = "";
  if (alternate.firstChild == null) {
    console.log("No Alternatives")
    //console.log(alternate.firstChild); 
    id = answer.value
  } else {
    // TO check the answer from alternative choices 
    var alter = document.getElementsByName('radio');
    for (var i = 0; i < alter.length; i++) {
      if (alter[i].checked) {
        console.log(alter[i].value)
        id = alter[i].value;

      }
    }
  }
  //console.log(id)

  let body = {
    answer: id
  }

  Quizz.stopCountdown();

  Quizz.sendQuestionResponsePost(nextUrl, body)
    .then(response => {
      console.log(response)

      response.json().then((data) => {
        if (data.hasOwnProperty('nextURL')) {
          //Check if it has Next question 

          nextUrl = data.nextURL

        } else {
          //PlAYER HAS WON
          endMessage.innerHTML = "<b>CONGRATUALTION!<b> You have answered all question correctly."
          nextQ.hidden = true;
          restart.hidden = false;

          //To check if the user did a higher score and update localStorage in fucntion of that!  
          let time = Quizz.getScoreTime()
          var existing = localStorage.getItem(nameId);
          let user = JSON.parse(existing)
          if (time < user.time && user.score == 6) {
            user.time = time; 
            window.localStorage.setItem(nameId, JSON.stringify(user));
          } else if (amountOfQAnswered > user.score) {
            user.score = amountOfQAnswered
            user.time = time
            window.localStorage.setItem(nameId, JSON.stringify(user));
          }
          HighScoreTable.drawHighScoreTable()
        }
        //nextquestion.disabled = false; 
        updateContent(data.message)
        submitButton.hidden = true;
        answer.value = "";
        alternate.hidden = true;
        while (alternate.firstChild) {
          alternate.removeChild(alternate.firstChild);
        }
        amountOfQAnswered++;

      });
    })
    .catch((err) => {
      console.log("Error of POST")
      updateContent(err.message);
      restart.hidden = false;
      nextQ.disabled = true;
      sendButton.disabled = true;

      //To check if the user did a higher score and update localStorage in fucntion of that!  
      let time = Quizz.getScoreTime()
      var existing = localStorage.getItem(nameId);
      let user = JSON.parse(existing)
      if (time < user.time && user.score == 6) {
        user.time = time; 
        window.localStorage.setItem(nameId, JSON.stringify(user));
      } else if (amountOfQAnswered > user.score) {
        user.score = amountOfQAnswered
        user.time = time
        window.localStorage.setItem(nameId, JSON.stringify(user));
      }
      HighScoreTable.drawHighScoreTable()
     

      //console.log('Fetch Error :-S', err)
    });
})

nextQ.addEventListener("click", function () {
  let id = answer.value
  submitButton.hidden = false;
  nextQ.disabled = true;


  updateContent(status + id)
  console.log(status + id)

  Quizz.countdown(10, true, timerCountdown, nameId);
  Quizz.getQuestion(nextUrl)
    .then(response => {
      console.log(response)

      response.json().then((data) => {
        //CHECK if the question has Alternatives
        if (data.hasOwnProperty("alternatives")) {
          answer.hidden = true;
          alternate.hidden = false;
          nbOfAlternatives = Object.keys(data.alternatives).length;// to get the amount of alternatives in the Question
          let arr = [];
          for(let i=0;i<nbOfAlternatives;i++){
            arr.push(Object.values(data.alternatives)[i]);
          }

          for (let i = 0; i < nbOfAlternatives; i++) { //Loop through to create each input and Label
            //Input creation
            var input = document.createElement("input");
            input.setAttribute('type', 'radio');
            input.setAttribute('value', "alt" + (i + 1));
            input.setAttribute('name', "radio")
            alternate.append(input)

            //Label creation 
            var label = document.createElement("LABEL");
            label.innerHTML = arr[i] + "<br>";
            alternate.append(label)
          }


        }
        updateContent(data.question)
        nextUrl = data.nextURL
      });
    })
    .catch((err) => {
      restart.hidden = false;
      console.log('Fetch Error :-S', err)
    });

})

restart.addEventListener("click", function () {
  window.location.reload();
})


