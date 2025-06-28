document.addEventListener("DOMContentLoaded", function () {
  const stored = localStorage.getItem("portfolioDataMinimal");
  if (!stored) return;

  const data = JSON.parse(stored);

  // Update Title and Subtitle
  if (data.title) document.getElementById("portfolioTitle").textContent = data.title;
  if (data.subtitle) document.getElementById("portfolioSubtitle").textContent = data.subtitle;

  // Experience
  if (data.exp1Title) document.getElementById("exp1Title").textContent = data.exp1Title;
  if (data.exp1Desc) document.getElementById("exp1Desc").textContent = data.exp1Desc;
  if (data.exp2Title) document.getElementById("exp2Title").textContent = data.exp2Title;
  if (data.exp2Desc) document.getElementById("exp2Desc").textContent = data.exp2Desc;
  if (data.exp3Title) document.getElementById("exp3Title").textContent = data.exp3Title;
  if (data.exp3Desc) document.getElementById("exp3Desc").textContent = data.exp3Desc;

  // Education
  if (Array.isArray(data.education)) {
    const eduList = document.getElementById("educationList");
    eduList.innerHTML = ""; // Clear default
    data.education.forEach(item => {
      const li = document.createElement("li");
      li.innerHTML = item;
      eduList.appendChild(li);
    });
  }

  // Skills
  if (Array.isArray(data.skills)) {
    const skillList = document.getElementById("skillList");
    skillList.innerHTML = ""; // Clear default
    data.skills.forEach(skill => {
      const li = document.createElement("li");
      li.textContent = skill;
      skillList.appendChild(li);
    });
  }

  // Projects
  if (Array.isArray(data.projects)) {
    const projectCards = document.getElementById("projectCards");
    projectCards.innerHTML = ""; // Clear existing
    data.projects.forEach(project => {
      if (project.title || project.desc) {
        const div = document.createElement("div");
        div.innerHTML = `<h5>${project.title || ""}</h5><p>${project.desc || ""}</p>`;
        projectCards.appendChild(div);
      }
    });
  }
});

// Save function (manual save)
function saveTemplate() {
  const data = localStorage.getItem("portfolioDataMinimal");
  if (!data) {
    alert("No data to save! Customize first.");
    return;
  }
  localStorage.setItem("savedMinimalTemplate", data);
  alert("Template saved successfully!");
}
