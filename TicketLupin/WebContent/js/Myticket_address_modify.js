

/*변수 선언*/



var userName = document.querySelector('#name');

var mobile = document.querySelector('#mobile');

var adress = document.querySelector('#address');

var error = document.querySelectorAll('.error_next_box');



/*이벤트 핸들러 연결*/



userName.addEventListener("focusout", checkName);

mobile.addEventListener("focusout", checkPhoneNum);

address.addEventListener("focusout", checkAddress);




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



function checkPhoneNum() {
    var isPhoneNum = /([01]{2})([01679]{1})([0-9]{3,4})([0-9]{4})/;

    if(mobile.value === "") {
        error[1].innerHTML = "필수 정보입니다.";
        error[1].style.display = "block";
    } else if(!isPhoneNum.test(mobile.value)) {
        error[1].innerHTML = "형식에 맞지 않는 번호입니다.";
        error[1].style.display = "block";
    } else {
        error[1].style.display = "none";
    }

    
}

function checkAdress(){
	var addressPattern = /[a-zA-Z가-힣0-9]/;

    if(adress.value === "") {
        error[2].innerHTML = "필수 정보입니다.";
        error[2].style.display = "block";
    } else if(!adressPattern.test(address.value)) {
        error[2].innerHTML = "형식에 맞지 않는 번호입니다.";
        error[2].style.display = "block";
    } else {
        error[2].style.display = "none";
    }

}