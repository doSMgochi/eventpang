document.addEventListener("DOMContentLoaded", function () {
  const items = document.querySelectorAll(".item");

  const observerOptions = {
    threshold: 0.1,
  };

  const observer = new IntersectionObserver((entries, observer) => {
    entries.forEach((entry, index) => {
      if (entry.isIntersecting) {
        setTimeout(() => {
          entry.target.classList.add("show");
        }, index * 200);
        observer.unobserve(entry.target);
      }
    });
  }, observerOptions);

  items.forEach((item) => {
    observer.observe(item);
  });
});
