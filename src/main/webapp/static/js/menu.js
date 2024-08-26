document.addEventListener("DOMContentLoaded", () => {
  const initializeMenuItems = () => {
    const menuItems = document.querySelectorAll("nav ul li");

    menuItems.forEach((item) => {
      const submenu = item.querySelector("ul");

      if (submenu) {
        item.addEventListener("mouseenter", () => {
          submenu.style.maxHeight = submenu.scrollHeight + "px";
          submenu.style.opacity = "1";
          submenu.style.transition = "max-height 0.5s ease, opacity 0.5s ease";
        });

        item.addEventListener("mouseleave", () => {
          submenu.style.maxHeight = "0";
          submenu.style.opacity = "0";
          submenu.style.transition = "max-height 0.5s ease, opacity 0.5s ease";
        });
      }
    });
  };

  initializeMenuItems();
});
