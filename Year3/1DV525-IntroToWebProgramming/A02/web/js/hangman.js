/**
 * Showing off how to display/hide parts of a SVG-image.
 */
window.Hangman = (function () {
    "use strict";
    var wordToFind;
    let indexDraw = 3;
    var words = ["blue", "green", "yellow", "orange", "red", "black", "purple", "magenta", "white", "grey", "pink"];
    var letterUsed = [];
    var letterFound = [];

    var hangman = {

        // Get all elements as their id
        "partAsElement": {
            "hill": document.getElementById('hang_hill'),
            "gallow": document.getElementById('hang_construction'),
            "body": document.getElementById('hang_body'),
            "rightarm": document.getElementById('hang_rightarm'),
            "leftarm": document.getElementById('hang_leftarm'),
            "rightleg": document.getElementById('hang_rightleg'),
            "leftleg": document.getElementById('hang_leftleg'),
            "rope": document.getElementById('hang_rope'),
            "head": document.getElementById('hang_head')
        },

        // Create an array with all valid parts
        "validParts": [
            "hill",
            "gallow",
            "rope",
            "head",
            "body",
            "rightarm",
            "leftarm",
            "rightleg",
            "leftleg"

        ],


        /**
         * Initiate the game, select a random word from the word Array. 
         * 
         */


        "init": function () {
            var x = Math.floor((Math.random() * words.length));

            wordToFind = words[x];
            console.log(wordToFind);

            for (let i = 3; i < this.validParts.length; i++) {
                this.hide(this.validParts[i]);
            }

            for (let i = 0; i < wordToFind.length; i++) {
                letterFound.push("_");
            }

            document.getElementById('word').innerHTML = this.toString(letterFound);
            document.getElementById('livesLeft').innerHTML = 9 - indexDraw;

        },
        "toString": function (array) {
            var word = "";
            if (array.length > 0) {
                for (let i = 0; i < array.length; i++) {
                    word += array[i] + " ";
                }
            }

            return word;
        },

        "game": function (str) {
            if (this.checkIfWon()) {

            } else {
                if (wordToFind.includes(str)) { //Check if the letter is inside the word. 
                    for (let i = 0; i < wordToFind.length; i++) {
                        if (wordToFind.charAt(i) === str) {
                            letterFound[i] = str;
                        }
                    }

                    document.getElementById('word').innerHTML = this.toString(letterFound);
                    if (this.checkIfWon()) {
                        document.getElementById('result').innerHTML = "YOU WON, CONGRATS";
                        document.getElementById('letter').disabled = true; 
                    }
                } else { //Letter is not in the word
                    if (letterUsed.includes(str)) {
                        console.log("Letter is already included " + str)
                    } else {
                        letterUsed.push(str);
                    }
                    this.show(this.validParts[indexDraw++]);
                }

                console.log(letterUsed);
                document.getElementById('letter').value = '';
                document.getElementById('letterUsedAlready').innerHTML = this.toString(letterUsed);
                document.getElementById('livesLeft').innerHTML = 9 - indexDraw;
                if ((9 - indexDraw) == 0) {
                    console.log("End of Game")
                    document.getElementById('result').innerHTML = "YOU LOST";

                }
            }


        },

        "checkIfWon": function () {
            if (letterFound.includes("_")) {
                return false;
            } else {
                return true;
            }
        },

        /**
         * Check if part a valid part, writes error message to console if the part is invalid.
         *
         * @param string part Name of the part to check.
         *
         * @returns boolean true if valid part, else false.
         */
        "isValid": function (part) {
            if (this.validParts.indexOf(part) === -1) {
                window.console.log("The part is not valid: " + part);
                return false;
            }
            window.console.log("The part is valid: " + part);
            return true;
        },


        /**
         * Hide a part.
         *
         * @param string part Name of the part to hide.
         *
         * @returns void.
         */
        "hide": function (part) {
            if (this.isValid(part)) {
                window.console.log("Hiding part: " + part);
                this.partAsElement[part].style.display = "none";
            }
        },


        /**
         * Show a part.
         *
         * @param string part Name of the part to show.
         *
         * @returns void.
         */
        "show": function (part) {
            if (this.isValid(part)) {
                window.console.log("Showing part: " + part);
                this.partAsElement[part].style.display = "inline";
            }
        }


    };

    window.console.log("You can now use the hangman object as a part of the window-object." +
        "Try\n\nwindow.Hangman.hide('gallow')\nwindow.Hangman.show('gallow')" +
        "\n\nHere are all the parts you can work on.");
    window.console.log(hangman.validParts);

    // Return the object to make it visible.
    return hangman;
})();


window.addEventListener('load', function () {
    'use strict';

    Hangman.init();

})