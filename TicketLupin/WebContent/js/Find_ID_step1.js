

/*변수 선언*/




var userName = document.querySelector('#name');

var email = document.querySelector('#email');

var verify = document.querySelector('#verify');

var error = document.querySelectorAll('.error_next_box');



/*이벤트 핸들러 연결*/

userName.addEventListener("focusout", checkName);
email.addEventListener("focusout", isEmailCorrect);
verify.addEventListener("focusout", verifyNum);






/*콜백 함수*/

function checkName() {
    var namePattern = /[a-zA-Z가-힣]/;
    if(userName.value === "") {
        error[0].innerHTML = "필수 정보입니다.";
        error[0].style.display = "block";
    } else if(!namePattern.test(userName.value) || userName.value.indexOf(" ") > -1) {
        error[0].innerHTML = "한글과 영문 대 소문자를 사용하세요. (특수기호, 공백 사용 불가)";
        error[0].style.display = "block";
    } else {
        error[0].style.display = "none";
    }
}




function isEmailCorrect() {
    var emailPattern = /[a-z0-9]{2,}@[a-z0-9-]{2,}\.[a-z0-9]{2,}/;

    if(email.value === ""){ 
		error[1].innerHTML = "필수 정보입니다.";
        error[1].style.display = "block"; 
    } else if(!emailPattern.test(email.value)) {
        error[1].style.display = "block";
    } else {
       error[1].innerHTML = "우측 '인증'버튼을 클릭 후 수신된 인증번호를 확인하세요.";
       error[1].style.color = "#08A600";
       error[1].style.display = "block"; 
    }

}



function  verifyNum() {
    var isMailcheck = /([0-9]{6})/;

    if(verify.value === "") {
        error[3].innerHTML = "인증번호를 입력해주세요.";
        error[3].style.display = "block";
    } else if(!isMailcheck.test(verify.value)) {
        error[3].innerHTML = "형식에 맞지 않는 인증번호입니다.";
        error[3].style.display = "block";
    } else {
        error[3].style.display = "none";
    }

    
}