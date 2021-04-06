

/*변수 선언*/


var id = document.querySelector('#id');

var pw1 = document.querySelector('#pswd1');

var error = document.querySelectorAll('.error_next_box');



/*이벤트 핸들러 연결*/


id.addEventListener("focusout", checkId);
pw1.addEventListener("focusout", checkPw);





/*콜백 함수*/


function checkId() {
    var idPattern = /[a-zA-Z0-9_-]{5,20}/;
    if(id.value === "") {
        error[0].innerHTML = "아이디를 입력하세요.";
        error[0].style.display = "block";
    } else {
     error[3].style.display = "none";
    }
}

function checkPw() {
    var pwPattern = /[a-zA-Z0-9~!@#$%^&*()_+|<>?:{}]{8,16}/;
    if(pw1.value === "") {
        error[1].innerHTML = "비밀번호를 입력하세요.";
        error[1].style.display = "block";
    } else {
     error[3].style.display = "none";
    }
}

