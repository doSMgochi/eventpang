document.addEventListener("DOMContentLoaded", () => {
  const fileInput = document.getElementById("imageFile");

  document.getElementById("uploadImage").addEventListener("click", () => {
    fileInput.click();
  });

  fileInput.addEventListener("change", () => {
    const file = fileInput.files[0];

    if (!file) {
      alert("업로드할 파일을 선택하세요.");
      return;
    }

    const formData = new FormData();
    formData.append("imageFile", file);

    fetch(`${rootPath}/upload-image`, {
      method: "POST",
      body: formData,
    })
      .then((response) => response.text())
      .then((text) => {
        console.log("Server Response: ", text);
        try {
          return JSON.parse(text);
        } catch (error) {
          throw new Error("Invalid JSON: " + error.message);
        }
      })
      .then((data) => {
        if (data.success) {
          const imageUrl = data.imageUrl;
          const evtBody = document.querySelector("textarea[name='evt_body']");
          evtBody.value += `\n<img src="${imageUrl}" alt="Uploaded Image">\n`;
        } else {
          alert("이미지 업로드 실패: " + data.message);
        }
      })
      .catch((error) => {
        console.error("Error:", error);
        alert("이미지 업로드 중 오류가 발생했습니다.");
      });
  });

  const pageTypeElement = document.getElementById("page-type");
  const pageType = pageTypeElement
    ? pageTypeElement.getAttribute("data-page-type")
    : null;

  if (pageType === "modify") {
    const dateFields = [
      "evt_writed_time",
      "evt_start_time",
      "evt_end_time",
      "evt_winning_time",
    ];

    dateFields.forEach((fieldName) => {
      const dateInput = document.querySelector(`input[name='${fieldName}']`);
      if (dateInput && dateInput.value) {
        const dateParts = dateInput.value.split("-");
        dateInput.value = `${dateParts[0].substring(2)}/${dateParts[1]}/${
          dateParts[2]
        }`;
      }
    });
  }

  document.getElementById("event-write").addEventListener("submit", (event) => {
    event.preventDefault();

    const category = document.getElementById("firstSelect").value;
    const detailCategory = document.getElementById("secondSelect").value;

    const evtCategory = category + "," + detailCategory;
    document.getElementById("evt_category").value = evtCategory;

    event.target.submit();
  });
});
