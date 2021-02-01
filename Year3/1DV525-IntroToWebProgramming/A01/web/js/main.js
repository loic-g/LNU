
var wordlist = ["Python","JavaScript","HTML","CSS","C++","TypeScript","Rust","Scheme","Java","Kotlin","C#","Perl","PHP","Scala","Swift","MATLAB","SQL","Golang","Ruby","R Programming Language"];


function randomWord(){
    const randomNumber = Math.floor(Math.random() * wordlist.length);
    
    document.getElementById('randomword').innerHTML = wordlist[randomNumber];
    document.getElementById('lowercase').innerHTML = "Word in lowercase: "+wordlist[randomNumber].toLowerCase();
    document.getElementById('uppercase').innerHTML = "Word in uppercase: "+wordlist[randomNumber].toUpperCase();

    document.getElementById('charactersnb').innerHTML = "There are "+wordlist[randomNumber].length+" characters in this word!";
    var vowelsnb = getVowels(wordlist[randomNumber]);
    document.getElementById('vowelsnb').innerHTML = "There are "+vowelsnb+" vowel(s) in this word!";
    document.getElementById('consonantnb').innerHTML ="There are "+(wordlist[randomNumber].length-vowelsnb)+" consonant(s) in this word!";
    
}

function getVowels(str) {
    var m = str.match(/[aeiouy]/gi);
    return m === null ? 0 : m.length;
  }

function gameRandomWord(str){
    
    const randomNumber = Math.floor(Math.random() * wordlist.length);
    const randomWord = wordlist[randomNumber].toLowerCase();
    
    if(randomWord.indexOf(str)>-1){
        var count =0;
        var positions = [];
        for (i=0;i<randomWord.length;i++){
            if(randomWord.charAt(i)==str){
                positions[count++] = i;
            }
        }
        document.getElementById('word').innerHTML = "Random word: "+randomWord;
        document.getElementById('answer').innerHTML = "YES";
        document.getElementById('count').innerHTML = "There are "+count +" '"+str+"' in the word "+randomWord+"!";
        document.getElementById('position').innerHTML = "Position(s): " + positions.toString();
    }else{
        document.getElementById('word').innerHTML = "Random word: "+randomWord;
        document.getElementById('answer').innerHTML = "NO";
    }
    document.getElementById('letter').value='';
    
}
