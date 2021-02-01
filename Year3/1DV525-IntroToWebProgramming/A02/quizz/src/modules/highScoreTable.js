function drawHighScoreTable() {
    //MAKE TABLE FOR HIGH SCORE 

    /*    <table style="width:100%">
           <tr>
               <th>Firstname</th>
               <th>Lastname</th>
               <th>Age</th>
           </tr>
           <tr>
               <td>Jill</td>
               <td>Smith</td>
               <td>50</td>
           </tr>
           <tr>
               <td>Eve</td>
               <td>Jackson</td>
               <td>94</td>
           </tr>
       </table> */

    let lengthOfStorage = window.localStorage.length


    //document.getElementById("titleHigh").hidden =false;

    var divTable = document.getElementById("highScoreTable");

    var table = document.createElement("table");
    table.style.border = "1px solid black";

    var tr = document.createElement("tr")
    tr.style.border = "1px solid black";

    var thN = document.createElement("th")
    thN.innerHTML = "Name";
    thN.style.border = "1px solid black";

    var thQ = document.createElement("th")
    thQ.innerHTML = "Highest Score"
    thQ.style.border = "1px solid black";

    var thT = document.createElement("th")
    thT.innerHTML = "Time"
    thT.style.border = "1px solid black";

    tr.append(thN)
    tr.append(thQ)
    tr.append(thT)

    table.appendChild(tr);


    var arrOfUser = [];
    //To get all players from LocalStorage
    for (let i = 0; i < lengthOfStorage; i++) {
        let parsedUser = JSON.parse(localStorage.getItem(window.localStorage.key(i)));
        console.log(parsedUser)
        arrOfUser.push(parsedUser);
    }

    //SORTING ALGORITHM
    arrOfUser.sort(function (a, b) {
        if (a.score > b.score) {
            return -1;
        } else if (a.score < b.score) {
            return 1;
        }
        return 0;
    });



    /* <tr>
            <td>Jill</td>
            <td>Smith</td>
            <td>50</td>
       </tr> */
    //Add each value to Table 
    for (let i = 0; i < arrOfUser.length; i++) {

        let tr = document.createElement("tr"); 
        tr.style.border = "1px solid black";

        var tdN = document.createElement("td"); //<td>
        tdN.innerHTML = arrOfUser[i].name
        tdN.style.border = "1px solid black";

        var tdS = document.createElement("td"); //<td>
        tdS.innerHTML = arrOfUser[i].score
        tdS.style.border = "1px solid black";

        var tdT = document.createElement("td"); //<td>
        tdT.innerHTML = arrOfUser[i].time
        tdT.style.border = "1px solid black";
    
       /*  let tdS = document.createElement("td");
        tdScore.appendChild(document.createTextNode(finalScore[i]))


        let tdT = document.createElement("td");
        tdTime.appendChild(document.createTextNode(finalTime[i]))
 */
        tr.append(tdN);
        tr.append(tdS);
        tr.append(tdT);

        table.append(tr);
    }

    // console.log(parsedArr.map(a=> a.score)[0]);


    divTable.append(table);




}





export default {
    drawHighScoreTable
}