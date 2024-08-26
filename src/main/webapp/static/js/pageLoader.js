document.addEventListener("DOMContentLoaded", () => {
  const body = document.querySelector("body");

  const fadeOutAndLoadPage = (url, method = "GET", data = null) => {
    body.style.transition = "opacity 0.5s";
    body.style.opacity = "0";

    setTimeout(() => {
      loadPage(url, method, data);
    }, 500);
  };

  const loadPage = (url, method = "GET", data = null) => {
    const xhr = new XMLHttpRequest();
    xhr.open(method, url, true);
    xhr.setRequestHeader("X-Requested-With", "XMLHttpRequest");

    if (method === "POST") {
      xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
      xhr.send(encodeFormData(data));
    } else {
      xhr.send();
    }

    xhr.onload = () => {
      if (xhr.status >= 200 && xhr.status < 400) {
        const parser = new DOMParser();
        const doc = parser.parseFromString(xhr.responseText, "text/html");

        const newContent = doc.querySelector("body").innerHTML;
        body.innerHTML = newContent;

        const newBodyClass = doc.querySelector("body").className;
        body.className = newBodyClass;

        history.pushState({}, "", url);

        fadeIn();
        reinitializeScripts();
      }
    };
  };

  const encodeFormData = (data) => {
    if (!data) return "";
    return Object.keys(data)
      .map(
        (key) => encodeURIComponent(key) + "=" + encodeURIComponent(data[key])
      )
      .join("&");
  };

  const fadeIn = () => {
    body.style.transition = "opacity 0.5s";
    body.style.opacity = "1";
  };

  window.fadeOutAndLoadPage = fadeOutAndLoadPage;
});
