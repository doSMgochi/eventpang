const initializeModifyForm = () => {
  const form = document.querySelector("form.user.modify-form");
  if (!form) {
    console.error("Modify 폼을 찾을 수 없습니다.");
    return;
  }

  const INPUT_INDEX = {
    USER_ID: 0,
    CURRENT_PASSWORD: 1,
    NEW_PASSWORD: 2,
    NICK: 3,
    EMAIL: 4,
    BIRTH: 5,
    TEL: 6,
    BUTTON: 11,
  };

  const ERROR_MESSAGE = [
    "* 아이디는 필수 항목입니다",
    "* 현재 비밀번호는 필수 항목입니다",
    "",
    "* 닉네임은 필수 항목입니다",
    "* 이메일은 필수 항목입니다",
    "* 생년월일은 필수 항목입니다",
    "* 전화번호는 필수 항목입니다",
    "* 이메일 형식이 올바르지 않습니다",
    "* 전화번호 형식이 올바르지 않습니다",
  ];

  const inputs = form.querySelectorAll("input");
  const errorMessages = form.querySelectorAll("span");

  const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  const phoneRegex = /^\d{10,11}$/;

  const validateInput = (index) => {
    let value = inputs[index].value;
    if (!value && index !== INPUT_INDEX.NEW_PASSWORD) {
      errorMessages[index].textContent = ERROR_MESSAGE[index];
      errorMessages[index].style.display = "inline-block";
      inputs[index].focus();
      return false;
    }
    if (index === INPUT_INDEX.EMAIL && !emailRegex.test(value)) {
      errorMessages[index].textContent = ERROR_MESSAGE[6];
      errorMessages[index].style.display = "inline-block";
      inputs[index].focus();
      return false;
    }
    if (index === INPUT_INDEX.TEL) {
      value = value.replace(/-/g, "");
      if (!phoneRegex.test(value)) {
        errorMessages[index].textContent = ERROR_MESSAGE[7];
        errorMessages[index].style.display = "inline-block";
        inputs[index].focus();
        return false;
      }
    }
    return true;
  };

  const onSubmit = (event) => {
    event.preventDefault();
    event.stopPropagation();

    let valid = true;
    errorMessages.forEach((span) => (span.style.display = "none"));

    for (let i = 0; i < inputs.length; i++) {
      if (!validateInput(i)) {
        valid = false;
        break;
      }
    }

    if (valid) {
      const xhr = new XMLHttpRequest();
      xhr.open("POST", form.action, true);
      xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

      xhr.onload = () => {
        if (xhr.status >= 200 && xhr.status < 400) {
          window.location.href = `${rootPath}/`;
        } else {
          console.error(
            "폼 요청 실패",
            xhr.status,
            xhr.statusText,
            xhr.responseText
          );
          alert("전송에 실패했습니다. 다시 시도해주세요.");
        }
      };

      const formData = new FormData(form);
      xhr.send(new URLSearchParams(formData).toString());
    }
  };

  inputs[INPUT_INDEX.BUTTON].addEventListener("click", onSubmit);
};
