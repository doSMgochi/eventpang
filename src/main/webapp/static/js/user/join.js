const initializeSignupForm = () => {
  const form = document.querySelector("form.user.join-form");
  if (!form) {
    console.error("Join 폼을 찾을 수 없습니다.");
    return;
  }

  const INPUT_INDEX = {
    ID: 0,
    PASSWORD: 1,
    NICK: 2,
    EMAIL: 3,
    BIRTH: 4,
    TEL: 5,
    GENDER_MALE: 6,
    GENDER_FEMALE: 7,
    ROLE_ENTERPRISE: 8,
    ROLE_PERSONAL: 9,
    BUTTON: 10,
  };

  const ERROR_MESSAGE = [
    "* 아이디는 필수 항목입니다",
    "* 비밀번호는 필수 항목입니다",
    "* 닉네임은 필수 항목입니다",
    "* 이메일은 필수 항목입니다",
    "* 생년월일은 필수 항목입니다",
    "* 전화번호는 필수 항목입니다",
    "* 이메일 형식이 올바르지 않습니다",
    "* 전화번호 형식이 올바르지 않습니다",
    "* 아이디가 이미 사용 중입니다",
  ];

  const inputs = form.querySelectorAll("input");
  const errorMessages = form.querySelectorAll("span");
  const birthInput = inputs[INPUT_INDEX.BIRTH];
  birthInput.type = "text";
  birthInput.placeholder = "생년월일";
  birthInput.addEventListener("focus", () => {
    birthInput.type = "date";
    birthInput.placeholder = "";
  });
  birthInput.addEventListener("blur", () => {
    if (birthInput.value === "") {
      birthInput.type = "text";
      birthInput.placeholder = "생년월일";
    }
  });

  const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  const phoneRegex = /^\d{10,11}$/;

  const checkIdDuplicate = (callback) => {
    const userId = inputs[INPUT_INDEX.ID].value;
    if (userId) {
      fetch(`${rootPath}/user/check_id?user_id=${encodeURIComponent(userId)}`)
        .then((response) => response.text())
        .then((result) => {
          if (result === "EXISTS") {
            errorMessages[INPUT_INDEX.ID].textContent = ERROR_MESSAGE[8];
            errorMessages[INPUT_INDEX.ID].style.display = "inline-block";
            callback(false);
          } else {
            callback(true);
          }
        })
        .catch((error) => {
          console.error("Error:", error);
          callback(false);
        });
    } else {
      callback(true);
    }
  };

  const validateInput = (index) => {
    let value = inputs[index].value;
    if (!value) {
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
    event.stopPropagation(); // 이벤트 버블링 방지

    let valid = true;
    errorMessages.forEach((span) => (span.style.display = "none"));

    checkIdDuplicate((isAvailable) => {
      if (isAvailable) {
        for (let i = 0; i < inputs.length; i++) {
          if (!validateInput(i)) {
            valid = false;
            break;
          }
        }
        if (valid) {
          const xhr = new XMLHttpRequest();
          xhr.open("POST", form.action, true);
          xhr.setRequestHeader(
            "Content-Type",
            "application/x-www-form-urlencoded"
          );

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
      }
    });
  };

  inputs[INPUT_INDEX.BUTTON].addEventListener("click", onSubmit);

  inputs[INPUT_INDEX.ID].addEventListener("blur", () => {
    checkIdDuplicate((isAvailable) => {
      if (!isAvailable) {
        inputs[INPUT_INDEX.ID].focus();
      }
    });
  });
};
