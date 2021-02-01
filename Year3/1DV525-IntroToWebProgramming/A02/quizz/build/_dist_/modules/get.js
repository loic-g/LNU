
//ES Module 

function GET(url) {
    //this.url = url; 
    fetch(url)
        .then((response) => {
            if (response.status !== 200) {
                console.log("There is a problem. " + response.status)
                return
            }
            response.json()
                .then(function (data) {
                    console.log(data);
                    document.getElementById("question").innerHTML = data.question;
                })
        }).catch((err) => {
            console.log('fetch Error', err);
        })
}

function POST(url){

}

export default {
    GET
}