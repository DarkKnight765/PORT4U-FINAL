# 🌐 Port4U – Portfolio Management Web Application

**Port4U** is a secure and role-based web applicationdeveloped using  the Spring Boot framework where we can choose a template, enter our details and publish the portfolio very easily in minimal steps...


---

## 🚀 Features

- 🔐 Email & password-based authentication
- ✉️ OTP verification during registration
- 🧑‍💻 Role-based dashboards for clients and admins
- 📄  Dynamic portfolio rendering: User-specific portfolio data is injected into HTML templates
- 🗃️ Local database integration via Spring Data JPA
- 🌐 REST APIs for client-server interaction
- 🎨 Simple frontend using HTML, CSS, and JS

---

## 🛠️ Tech Stack

| Layer     | Technology             |
|-----------|------------------------|
| Backend   | Spring Boot, Spring Security |
| Frontend  | HTML, CSS, JavaScript |
| Database  | (local)          |
| ORM       | Spring Data JPA        |

---

## 🧩 Role-Based Access

- 👤 **Client**:
  - Register/Login
  - Build and update their portfolio
  - View a customized dashboard
- 👨‍💼 **Admin**:
  - Access admin dashboard
  - View/manage users and templates
  - Perform admin-specific tasks

---

## Prerequisites for running our web application

- Maven should be installed to declare Project dependencies & build plugins.
-Any java SDK, available freely in the market

## How to run the project


1. Clone the repository.
2. In terminal switch to backend folder ie cd /.backend where pom.xml exists.
3. run mvn spring-boot:run command that will start the springboot backend server on port 8081.
4. route to http://localhost:8081/signup.html
