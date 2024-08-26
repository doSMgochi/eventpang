const initializeLoginForm = () => {
  const form = document.querySelector("form.user.login-form");
  if (!form) {
    console.error("Login 폼을 찾을 수 없습니다.");
    return;
  }

  const INPUT_INDEX = {
    ID: 0,
    PASSWORD: 1,
    BUTTON: 2,
  };

  const ERROR_MESSAGE = [
    "* 아이디는 필수 항목입니다",
    "* 비밀번호는 필수 항목입니다",
  ];

  const inputs = form.querySelectorAll("input");
  const errorMessages = form.querySelectorAll("span");

  const emptyValid = (index) => {
    const text = inputs[index].value;
    if (!text) {
      errorMessages[index].textContent = ERROR_MESSAGE[index];
      errorMessages[index].style.display = "inline-block";
      inputs[index].select();
      return false;
    }
    return true;
  };

  const onLoginSubmit = (event) => {
    event.preventDefault();
    event.stopPropagation(); // 이벤트 버블링 방지

    let yesValid = true;
    for (let i = 0; i < errorMessages.length; i++) {
      const span = errorMessages[i];
      span.style.display = "none";
      if (!(yesValid = emptyValid(i))) break;
    }

    if (yesValid) {
      const xhr = new XMLHttpRequest();
      xhr.open("POST", form.action, true);
      xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
      xhr.onload = () => {
        if (xhr.status >= 200 && xhr.status < 400) {
          const parser = new DOMParser();
          const doc = parser.parseFromString(xhr.responseText, "text/html");

          const newContent = doc.querySelector("body").innerHTML;
          document.body.innerHTML = newContent;

          const newBodyClass = doc.querySelector("body").className;
          document.body.className = newBodyClass;

          history.pushState({}, "", `${rootPath}/`);

          fadeIn();
          reinitializeScripts(); // 페이지 로드 후 스크립트 재초기화
        } else {
          alert("알 수 없는 오류로 로그인에 실패하였습니다.");
        }
      };
      const formData = new FormData(form);
      xhr.send(new URLSearchParams(formData).toString());
    }
  };

  inputs[INPUT_INDEX.BUTTON].addEventListener("click", onLoginSubmit);
};
