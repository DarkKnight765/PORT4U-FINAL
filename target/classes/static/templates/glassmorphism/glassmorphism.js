// Load data from localStorage and update the page
document.addEventListener("DOMContentLoaded", function () {

    // Retrieve data from localStorage
    const storedData = localStorage.getItem("portfolioData");

    if (storedData) {
        // Parse the JSON string into an object
        const portfolioData = JSON.parse(storedData);

        // Access static fields
        console.log("Name:", portfolioData.name);
        console.log("Email:", portfolioData.email);
        console.log("GitHub:", portfolioData.github);
        console.log("LinkedIn:", portfolioData.linkedin);

        // Access dynamic blocks
        console.log("Experience:");
        portfolioData.experience.forEach((exp, index) => {
            console.log(`  Block ${index + 1}:`, exp);
        });

        console.log("Education:");
        portfolioData.education.forEach((edu, index) => {
            console.log(`  Block ${index + 1}:`, edu);
        });

        console.log("Achievements:");
        portfolioData.achievements.forEach((ach, index) => {
            console.log(`  Block ${index + 1}:`, ach);
        });

        console.log("Skills:");
        portfolioData.skills.forEach((skill, index) => {
            console.log(`  Block ${index + 1}:`, skill);
        });

        console.log("Projects:");
        portfolioData.projects.forEach((proj, index) => {
            console.log(`  Block ${index + 1}:`, proj);
        });

        function getBlock(sectionName, blockIndex) {
            const section = portfolioData[sectionName];
            if (section && section[blockIndex]) {
                return section[blockIndex]; // Return the requested block
            } else {
                console.log(`No data found for ${sectionName} block ${blockIndex + 1}`);
                return null;
            }
        }

    } else {
        console.log("No portfolio data found in localStorage.");
    }

    

});


