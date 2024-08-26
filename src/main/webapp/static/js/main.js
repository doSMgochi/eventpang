document.addEventListener("DOMContentLoaded", () => {
  reinitializeScripts();

  document.body.addEventListener("click", (e) => {
    let target = e.target;

    while (target && target.nodeName !== "A") {
      target = target.parentNode;
    }

    if (target && target.nodeName === "A") {
      e.stopPropagation(); // 이벤트 버블링 방지
      const url = target.getAttribute("href");

      if (
        url === `${rootPath}/` ||
        window.location.pathname === `${rootPath}/`
      ) {
        return;
      }

      e.preventDefault();

      const method =
        target.getAttribute("data-method") === "POST" ? "POST" : "GET";

      fadeOutAndLoadPage(
        url,
        method,
        method === "POST" ? { key1: "value1", key2: "value2" } : null
      );
    }
  });
});

const reinitializeScripts = () => {
  if (typeof initializeMenuItems === "function") {
    initializeMenuItems();
  }

  if (typeof initializeFireworkButtons === "function") {
    initializeFireworkButtons();
  }

  if (typeof initializeLoginForm === "function") {
    initializeLoginForm();
  }

  if (typeof initializeSignupForm === "function") {
    initializeSignupForm();
  }

  if (typeof initializeModifyForm === "function") {
    initializeModifyForm();
  }

  if (typeof initializeTagToggle === "function") {
    initializeTagToggle();
  }
};

const initializeTagToggle = () => {
  const toggleButton = document.getElementById("toggleButton");
  const extraTags = document.getElementById("extraTags");

  if (!toggleButton || !extraTags) return;

  const tagButtons = extraTags.querySelectorAll(".btn.tag");

  tagButtons.forEach((button) => {
    button.style.margin = "5px";
  });

  toggleButton.addEventListener("click", () => {
    if (extraTags.style.display === "none") {
      extraTags.style.display = "block";
      toggleButton.textContent = "태그 닫기";
    } else {
      extraTags.style.display = "none";
      toggleButton.textContent = "태그 열기";
    }
  });
};
