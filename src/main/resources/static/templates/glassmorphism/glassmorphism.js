// Load data from localStorage and update the page
document.addEventListener("DOMContentLoaded", function () {
    const storedData = localStorage.getItem("portfolioData");

    if (storedData) {
        const portfolioData = JSON.parse(storedData);

        // Update static fields
        document.querySelector(".navbar-brand").textContent = portfolioData.name || "Portfolio";
        document.querySelector("#home h1").innerHTML = Hello! I'm ${portfolioData.name || "a Developer"}, a <span class="text custom-text">Developer</span> based in your city.;
        document.querySelector("#home p").textContent = "I love building tools that are user-friendly, simple, and delightful.";

        // Update contact links
        document.querySelector('#contact a[href*="linkedin"]').href = https://linkedin.com/in/${portfolioData.linkedin || ""};
        document.querySelector('#contact a[href*="github"]').href = https://github.com/${portfolioData.github || ""};
        document.querySelector('#contact a[href^="mailto:"]').href = mailto:${portfolioData.email || ""};

        // Update Experience
        const expCol = document.querySelector("#experience .col-md-6:nth-child(1)");
        expCol.innerHTML = "<h4>Experience</h4>";
        portfolioData.experience.forEach(exp => {
            expCol.innerHTML += <p><strong>${exp.field1 || ""}</strong> at ${exp.field2 || ""} (${exp.field3 || ""})</p>;
        });

        // Update Education
        const eduCol = document.querySelector("#experience .col-md-6:nth-child(2)");
        eduCol.innerHTML = "<h4>Education</h4>";
        portfolioData.education.forEach(edu => {
            eduCol.innerHTML += <p><strong>${edu.field1 || ""}</strong> - ${edu.field2 || ""} (${edu.field3 || ""})</p>;
        });

        // Update Achievements
        const achList = document.querySelector("#achievements ul");
        achList.innerHTML = "";
        portfolioData.achievements.forEach(ach => {
            achList.innerHTML += <li>${ach.field1 || ""}</li>;
        });

        // Update Skills
        const skillsRow = document.querySelector("#skills .row");
        skillsRow.innerHTML = "";
        portfolioData.skills.forEach(skill => {
            skillsRow.innerHTML += `
                <div class="col-md-3">
                    <div class="glass-box">
                        <h5>${skill.field1 || ""}</h5>
                        <p>${skill.field2 || ""}</p>
                    </div>
                </div>`;
        });

        // Update Projects
        const projectsRow = document.querySelector("#projects .row");
        projectsRow.innerHTML = "";
        portfolioData.projects.forEach(proj => {
            projectsRow.innerHTML += `
                <div class="col-md-4">
                    <div class="glass-box">
                        <h5>${proj.field1 || ""}</h5>
                        <p>${proj.field2 || ""}</p>
                        <a href="${proj.field3 || "#"}" class="btn custom-btn" target="_blank">View Project</a>
                    </div>
                </div>`;
        });

    } else {
        console.log("No portfolio data found in localStorage.");
    }
});