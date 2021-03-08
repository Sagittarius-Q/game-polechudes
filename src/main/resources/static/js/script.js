let attempt = 0;

function getQuestionPlace() {
    return document.getElementById('question');
}

function getAnswerPlace() {
    return document.getElementById('answer');
}

function getKeyboardLettersPlace(){
    return document.getElementById('keyboard');
}

function getRefreshBtn(){
    return document.getElementById('refresh');
}
function getHomeBtn(){
    return document.getElementById('home');
}

function getAttemptPlace(){
    return document.getElementsByClassName('attempt_text')[0];
}

function setQuestion(question) {
    let questionElementFromDOM = getQuestionPlace();
    questionElementFromDOM.innerText = question.hint;
    setAnswer(question.answer);
}


function setAnswer(answer) {
    let answerElementFromDOM = getAnswerPlace();
    createLetterBox(answerElementFromDOM, answer)
}

function start(question){
    let startBox = document.createElement('div');
    let startBtn = document.createElement('span');
    startBox.setAttribute('id','startBox');
    startBtn.setAttribute('id','startBtn');
    startBtn.setAttribute('data-hardness', 'easyQuestions');
    startBtn.innerText = 'Start Game'
    document.body.appendChild(startBox);
    startBox.appendChild(startBtn);
    startBtn.addEventListener('click', startGame);
    startGame();
}

function startGame(e){
    let startBox = document.getElementById('startBox');
    startBox.remove()
    refreshBtn()
    setKeyboard()
    setQuestion(question)
    homeBtn();
}
function setKeyboard() {
    let keyboardElementFromDOM = getKeyboardLettersPlace()
    let keyboardLetterBox;
    let letters = ['q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm'];
    for (let i = 0; i < letters.length; i++) {
        keyboardLetterBox = document.createElement('span');
        keyboardLetterBox.className = 'keyboardBox';
        keyboardLetterBox.innerText = letters[i];
        keyboardLetterBox.addEventListener('click', onKeyClick)
        keyboardElementFromDOM.appendChild(keyboardLetterBox);
    }
}

function createLetterBox(answerElementFromDOM, randomAnswer) {
    let letterBox;
    let letterLength = randomAnswer.length;
    for (var i = 0; i < letterLength; i++) {
        letterBox = document.createElement('span');
        letterBox.className = `answer_letter answer_letter_${randomAnswer[i].toLowerCase()}`;
        letterBox.innerText = '-';
        answerElementFromDOM.appendChild(letterBox);
    }
}

function onKeyClick(e){
    let letter = e.target
    isAnswerLetterRight(letter);
    e.target.removeEventListener('click', onKeyClick);
}

function isAnswerLetterRight(letter){

    let list = document.getElementsByClassName(`answer_letter_${letter.innerText}`)
    let allBlock = document.getElementsByClassName(`answer_letter`);
    let allOpened = true;

    if(list.length){

        for (let i = 0; i < list.length; i++) {
            list[i].innerText = letter.innerText;
            console.log(list.length)

        }
        for (let i = 0; i < allBlock.length; i++) {
            if (allBlock[i].innerText == "-") {
                allOpened = false;
            }
        }
        letter.style.backgroundColor = '#00ff00'

        if(allOpened){
            youWin()
        }
    }
    else{
        attempt++
        falseAnswerAttempt()
        letter.style.backgroundColor = '#ff0000'
    }
}

function youWin(){

    let modal = document.createElement('div');
    let modalText = document.createElement('span');
    let youWin = document.createElement('span');
    document.body.appendChild(modal);
    modal.appendChild(modalText);
    modal.appendChild(youWin);
    modal.setAttribute('id','modal');
    youWin.className = 'modalTextYouWin'
    youWin.innerText = "You Win"
    modalText.className = 'modalText';
    modalText.innerText = 'New Game';
    modalText.addEventListener('click', setRefresh)
}

function falseAnswerAttempt(){
    let attemptElementFromDOM = getAttemptPlace();
    if (attempt === 5){
        gameOver()
    }else{
        attemptElementFromDOM.innerText = `you have ${ 5 - attempt} attempts`;
    }
}

function refreshBtn(){
    let refreshBtn = getRefreshBtn();
    refreshBtn.addEventListener('click', setRefresh)
}
function homeBtn(){
    let homeBtn = getHomeBtn();
    homeBtn.addEventListener('click' , setHome)
}
function setHome(){
    window.location.pathname= '/home'
}


function setRefresh(){
    window.location.pathname= '/game';
}

function gameOver(){


    let container= document.createElement('div');
    let text = document.createElement('span');
    let newGameBtn = document.createElement('span');
    container.setAttribute('id','container');
    text.setAttribute('id','text');
    newGameBtn.setAttribute('id','newGameBtn');
    text.innerText = 'GAME OVER';
    newGameBtn.innerText = 'New Game';
    document.body.appendChild(container);
    container.appendChild(text);
    container.appendChild(newGameBtn);
    newGameBtn.addEventListener('click',setRefresh);
}