document.addEventListener("DOMContentLoaded", function () {
  var body = document.querySelector("body");

  // 초기화 작업 시작
  reinitializeScripts();

  // 페이지 내 링크 클릭 시 페이드 효과와 함께 페이지 로드
  document.body.addEventListener("click", function (e) {
    var target = e.target;

    while (target && target.nodeName !== "A") {
      target = target.parentNode;
    }

    if (target && target.nodeName === "A") {
      var url = target.getAttribute("href");

      if (
        url === `${rootPath}/` ||
        window.location.pathname === `${rootPath}/`
      ) {
        return;
      }

      e.preventDefault();

      // POST 요청이 필요한 URL인지 확인
      var method =
        target.getAttribute("data-method") === "POST" ? "POST" : "GET";

      fadeOutAndLoadPage(
        url,
        method,
        method === "POST" ? { key1: "value1", key2: "value2" } : null
      );
    }
  });

  // 페이드 아웃 후 페이지 로드
  function fadeOutAndLoadPage(url, method = "GET", data = null) {
    body.style.transition = "opacity 0.5s";
    body.style.opacity = "0";

    setTimeout(function () {
      loadPage(url, method, data);
    }, 500);
  }

  // 페이지 로드 함수
  function loadPage(url, method = "GET", data = null) {
    var xhr = new XMLHttpRequest();
    xhr.open(method, url, true);
    xhr.setRequestHeader("X-Requested-With", "XMLHttpRequest");

    if (method === "POST") {
      xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
      xhr.send(encodeFormData(data));
    } else {
      xhr.send();
    }

    xhr.onload = function () {
      if (xhr.status >= 200 && xhr.status < 400) {
        var parser = new DOMParser();
        var doc = parser.parseFromString(xhr.responseText, "text/html");

        var newContent = doc.querySelector("body").innerHTML;
        body.innerHTML = newContent;

        var newBodyClass = doc.querySelector("body").className;
        body.className = newBodyClass;

        // URL 업데이트
        history.pushState({}, "", url);

        fadeIn();
        reinitializeScripts();
      }
    };
  }

  // 폼 데이터를 URL 인코딩 형식으로 변환하는 함수
  function encodeFormData(data) {
    if (!data) return "";
    return Object.keys(data)
      .map(function (key) {
        return encodeURIComponent(key) + "=" + encodeURIComponent(data[key]);
      })
      .join("&");
  }

  // 페이드 인 효과
  function fadeIn() {
    body.style.transition = "opacity 0.5s";
    body.style.opacity = "1";
  }

  // 스크립트 재초기화 함수
  function reinitializeScripts() {
    initializeMenuItems();
    initializeFireworkButtons();

    if (typeof initializeLoginForm === "function") {
      initializeLoginForm();
    }

    if (typeof initializeJoinForm === "function") {
      initializeJoinForm();
    }

    if (typeof initializeModifyForm === "function") {
      initializeModifyForm();
    }
  }

  // 불꽃놀이 버튼 초기화 함수
  function initializeFireworkButtons() {
    addFireworkEffect(".bigEventBtn", `${rootPath}/board/big-event`);
    addFireworkEffect(".minorEventBtn", `${rootPath}/board/minor-event`);
    addFireworkEffect(".benefitBtn", `${rootPath}/board/benefit`);
    addFireworkEffect(".communityBtn", `${rootPath}/board/community`);
  }

  // 불꽃놀이 효과 추가 함수
  function addFireworkEffect(buttonClass, url) {
    const buttons = document.querySelectorAll(buttonClass);
    buttons.forEach((button) => {
      button.addEventListener("click", function (e) {
        e.preventDefault();
        button.classList.add("on");

        setTimeout(() => {
          button.classList.remove("on");
          window.location.href = url;
        }, 1000);
      });
    });
  }

  // 메뉴 항목 초기화 함수
  function initializeMenuItems() {
    var menuItems = document.querySelectorAll("nav ul li");

    menuItems.forEach(function (item) {
      var submenu = item.querySelector("ul");

      if (submenu) {
        item.addEventListener("mouseenter", function () {
          submenu.style.maxHeight = submenu.scrollHeight + "px";
          submenu.style.opacity = "1";
          submenu.style.transition = "max-height 0.5s ease, opacity 0.5s ease";
        });

        item.addEventListener("mouseleave", function () {
          submenu.style.maxHeight = "0";
          submenu.style.opacity = "0";
          submenu.style.transition = "max-height 0.5s ease, opacity 0.5s ease";
        });
      }
    });
  }

  // 로그인 폼 초기화 함수
  function initializeLoginForm() {
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

      let yesValid = true;
      for (let i = 0; i < errorMessages.length; i++) {
        const span = errorMessages[i];
        span.style.display = "none";
        if (!(yesValid = emptyValid(i))) break;
      }

      if (yesValid) {
        const xhr = new XMLHttpRequest();
        xhr.open("POST", form.action, true);
        xhr.setRequestHeader(
          "Content-Type",
          "application/x-www-form-urlencoded"
        );
        xhr.onload = function () {
          if (xhr.status >= 200 && xhr.status < 400) {
            const parser = new DOMParser();
            const doc = parser.parseFromString(xhr.responseText, "text/html");

            const newContent = doc.querySelector("body").innerHTML;
            document.body.innerHTML = newContent;

            const newBodyClass = doc.querySelector("body").className;
            document.body.className = newBodyClass;

            history.pushState({}, "", `${rootPath}/`);

            fadeIn();
            reinitializeScripts();
          } else {
            alert("알 수 없는 오류로 로그인에 실패하였습니다.");
          }
        };
        const formData = new FormData(form);
        xhr.send(new URLSearchParams(formData).toString());
      }
    };

    inputs[INPUT_INDEX.BUTTON].addEventListener("click", onLoginSubmit);
  }

  function initializeJoinForm() {
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

            xhr.onload = function () {
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
  }

  function initializeModifyForm() {
    const form = document.querySelector("form.user.modify-form");
    if (!form) {
      console.error("modify 폼을 찾을 수 없습니다.");
      return;
    }

    const INPUT_INDEX = {
      USER_ID: 0,
      CURRENT_PASSWORD: 1,
      NICK: 3,
      EMAIL: 4,
      BIRTH: 5,
      TEL: 6,
      BUTTON: 11,
    };

    const ERROR_MESSAGE = [
      "* 아이디는 필수 항목입니다",
      "* 현재 비밀번호는 필수 항목입니다",
      "* 닉네임은 필수 항목입니다",
      "* 이메일은 필수 항목입니다",
      "* 생년월일은 필수 항목입니다",
      "* 전화번호는 필수 항목입니다",
      "* 이메일 형식이 올바르지 않습니다",
      "* 전화번호 형식이 올바르지 않습니다",
    ];

    const inputs = form.querySelectorAll("input");
    const errorMessages = form.querySelectorAll("span");

    // 유효성 검사 함수
    const validateInput = (index) => {
      let value = inputs[index].value;
      if (index !== INPUT_INDEX.NEW_PASSWORD && !value) {
        errorMessages[index].textContent = ERROR_MESSAGE[index];
        errorMessages[index].style.display = "inline-block";
        inputs[index].focus();
        return false;
      }
      if (
        index === INPUT_INDEX.EMAIL &&
        value &&
        !/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(value)
      ) {
        errorMessages[index].textContent = ERROR_MESSAGE[7];
        errorMessages[index].style.display = "inline-block";
        inputs[index].focus();
        return false;
      }
      if (
        index === INPUT_INDEX.TEL &&
        value &&
        !/^\d{10,11}$/.test(value.replace(/-/g, ""))
      ) {
        errorMessages[index].textContent = ERROR_MESSAGE[8];
        errorMessages[index].style.display = "inline-block";
        inputs[index].focus();
        return false;
      }
      return true;
    };

    // 폼 제출 처리
    const onSubmit = (event) => {
      event.preventDefault();

      let valid = true;
      errorMessages.forEach((span) => (span.style.display = "none"));

      // 유효성 검사
      for (let i = 0; i < Object.keys(INPUT_INDEX).length - 1; i++) {
        if (!validateInput(i)) {
          valid = false;
          break;
        }
      }
    };

    inputs[INPUT_INDEX.BUTTON].addEventListener("click", onSubmit);

    inputs.forEach((input, index) => {
      if (index !== INPUT_INDEX.BUTTON) {
        input.addEventListener("keypress", (event) => {
          if (event.key === "Enter") {
            event.preventDefault();
          }
        });
      }
    });
  }
});
