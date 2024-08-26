document.addEventListener("DOMContentLoaded", () => {
  const initializeFireworkButtons = () => {
    addFireworkEffect(".bigEventBtn", `${rootPath}/board/big-event`);
    addFireworkEffect(".minorEventBtn", `${rootPath}/board/minor-event`);
    addFireworkEffect(".benefitBtn", `${rootPath}/board/benefit`);
    addFireworkEffect(".communityBtn", `${rootPath}/board/community`);
  };

  const addFireworkEffect = (buttonClass, url) => {
    const buttons = document.querySelectorAll(buttonClass);
    buttons.forEach((button) => {
      button.addEventListener("click", (e) => {
        e.preventDefault();
        e.stopPropagation(); // 이벤트 버블링 방지
        button.classList.add("on");

        setTimeout(() => {
          button.classList.remove("on");
          window.location.href = url;
        }, 1000);
      });
    });
  };

  initializeFireworkButtons();
});
