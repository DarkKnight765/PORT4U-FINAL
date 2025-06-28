// When the form is submitted, save all data
document
    .getElementById("portfolio-form")
    .addEventListener("submit", function (event) {
        event.preventDefault(); // Prevent the form from refreshing the page

        // Collect data from the form
        const formData = {
            name: document.getElementById("name").value, // Name input
            email: document.getElementById("email").value, // Email input
            github: document.getElementById("github").value, // GitHub username
            linkedin: document.getElementById("linkedin").value, // LinkedIn username
            experience: getBlocksData("experience-blocks"), // Data from experience blocks
            education: getBlocksData("education-blocks"), // Data from education blocks
            achievements: getBlocksData("achievement-blocks"), // Data from achievement blocks
            skills: getBlocksData("skill-blocks"), // Data from skills blocks
            projects: getBlocksData("project-blocks"), // Data from project blocks
        };

        // Save the form data to localStorage
        localStorage.setItem("portfolioData", JSON.stringify(formData));

        // Let the user know the form was submitted
        alert("Form submitted successfully!");

        // Show the update button
        document
            .getElementById("update-btn-container")
            .classList.remove("d-none");
    });

// Function to get data from dynamically added blocks
function getBlocksData(sectionId) {
    const blocks = document
        .getElementById(sectionId)
        .querySelectorAll(".block"); // Find all blocks in the section
    const data = [];

    blocks.forEach(function (block) {
        const inputs = block.querySelectorAll("input"); // Get all input fields in a block
        const blockData = {};

        // Collect values from each input
        inputs.forEach(function (input, index) {
            blockData[field${index + 1}] = input.value; // Store input values as "field1", "field2", etc.
        });

        data.push(blockData); // Add the block data to the array
    });

    return data; // Return all the blocks' data
}

// Function to add a new block dynamically
function addBlock(sectionId, blockType) {
    const section = document.getElementById(sectionId); // Find the section where the block will be added
    const blockCount = section.children.length + 1; // Count existing blocks to give the new block a number

    // Create a new block
    const block = document.createElement("div");
    block.className = "block mb-3"; // Add styling classes to the block

    // Add content to the block, including a delete button
    block.innerHTML = `
        ${section.firstElementChild.innerHTML} 
        <button type="button" class="btn btn-danger mt-2" onclick="removeBlock(this)">Delete</button>
    `;

    section.appendChild(block); // Add the block to the section
}

// Function to remove a block dynamically
function removeBlock(button) {
    const block = button.parentElement; // Find the block that contains the button
    block.remove(); // Remove the block from the section
}

// Redirect to a new page when the "Update Portfolio" button is clicked
document
    .getElementById("update-portfolio")
    .addEventListener("click", function () {
        window.location.href = "glassmorphism.html"; // Redirect to the new page
    });