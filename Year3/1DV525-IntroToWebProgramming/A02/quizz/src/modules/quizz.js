/**
 * Sample module to use for Quizz using async await
 */

 
async function getFirstQuestion() {
    const url = "http://courselab.lnu.se/question/1"
    const response = await fetch(url)
    let data;

    if (!response.ok) {
        let data = await response.json()

        console.log(response)
        console.log(JSON.stringify(data, null, 4))

        throw new Error(`HTTP error! status: ${response.status}`);
    }

    console.log(response)
    // response.json().then((data) => {
    //     console.log(JSON.stringify(data, null, 4))
    // })
    // data = await response.json()
    // console.log(JSON.stringify(data, null, 4))

    return response
};



async function sendQuestionResponsePost(url, body) {
    const data = {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(body)
    }

    const response = await fetch(url, data)

    if (!response.ok) {
        let data = await response.json()

        console.log(response)
        console.log(JSON.stringify(data, null, 4))

        throw new Error(data.message);
    }

    console.log(response)
    // response.json().then((data) => {
    //     console.log(JSON.stringify(data, null, 4))
    // })
    // data = await response.json()
    // console.log(JSON.stringify(data, null, 4))

    return response
};



async function getQuestion(url) {
    const data = {
        method: "GET",
    }

    console.log(url)
    console.log(data)
    if (url === "http://courselab.lnu.se/question/326") {
        url = "http://mikaelroos.se:3001/question/326";
    }
    const response = await fetch(url, data)
    console.log(response)

    if (!response.ok) {
        let data = await response.json()

        console.log(response)
        console.log(JSON.stringify(data, null, 4))

        throw new Error(`HTTP error! status: ${response.status}`);
    }

    console.log(response)
    // response.json().then((data) => {
    //     console.log(JSON.stringify(data, null, 4))
    // })
    // data = await response.json()
    // console.log(JSON.stringify(data, null, 4))

    return response
};

var timeLeft; //function to count time 
var time; //variable that holds time 
let finalTime = 0; //variable that holds how much time the player took 
let amountOfQAnswered = 0;


async function countdown(duration, reset, messageToDisplay, nameId) {
    amountOfQAnswered++;
    if (reset == true) {
        clearInterval(timeLeft);
        finalTime += (duration - time);
        console.log(finalTime);
        //setScoreTime(finalTime);
    }
    time = duration
    timeLeft = setInterval(function () {

        messageToDisplay.textContent = --time;
        if (time <= 0) {
            clearInterval(timeLeft);
            //console.log()

            messageToDisplay.textContent = time;

            document.getElementById("nextquestion").hidden = true;
            document.getElementById("restart").hidden = false;
            document.getElementById("sendButton").hidden = true;

            document.getElementById("question").innerHTML = "You have lost!";
            //To check if the user did a higher score and update localStorage in fucntion of that!  
            let time = getScoreTime()
            var existing = localStorage.getItem(nameId);
            let user = JSON.parse(existing)
            if (time < user.time && user.score == 6) {
                user.time = time;
                window.localStorage.setItem(nameId, JSON.stringify(user));
            } else if (amountOfQAnswered > user.score) {
                user.score = amountOfQAnswered;
                ser.time = time;
                window.localStorage.setItem(nameId, JSON.stringify(user));
            }




            console.log(player)
            console.log(finalTime)
            //window.localStorage.setItem(nameId, JSON.stringify(player));


        }

    }, 1000);


}

function stopCountdown() {
    clearInterval(timeLeft);
}

function getScoreTime() {
    return finalTime;
}



export default {
    getFirstQuestion,
    sendQuestionResponsePost,
    getQuestion,
    stopCountdown,
    countdown,
    getScoreTime,
    finalTime
};
