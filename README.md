Here's a self-running **`README.md`** for your **Count Words Spring Boot project** that includes:

* ✅ How to clone from GitHub
* 🐳 Docker build and run steps
* 📫 How to test using Postman with a sample collection link and Swagger API document

---


# 📝 Count Words Service

A Spring Boot microservice that processes a list of strings to:
- Count words that start with 'M' or 'm'
- Return words longer than 5 characters

This service is secured with JWT and documented using Swagger.

---

## 🚀 How to Run the Project

### ✅ 1. Clone the Repository


git clone https://github.com/shivpal-chouhan/count-words-service.git
cd count-words-service


---

### ⚙️ 2. Run Locally via Maven

Make sure you have JDK 17+ and Maven 3.6+ installed.


mvn clean install
mvn spring-boot:run


App runs at:
📍 http://localhost:8080
Swagger UI: http://localhost:8080/swagger-ui.html

---

### 🐳 3. Run Using Docker

#### 🛠️ Build the Docker Image


docker build -t count-words-service .


#### ▶️ Run the Docker Container


docker run -p 8080:8080 count-words-service


---

### 🔐 4. JWT Authentication

Use the `/auth/login` endpoint to authenticate:


POST http://localhost:8080/auth/login
Content-Type: application/json

{
  "username": "user",
  "password": "password"
}


You will get a JWT token in response. Use it in the `Authorization` header as:


Authorization: Bearer <your-jwt-token>


---

### 🔍 5. API Endpoint


POST /api/words/process


#### Request Body (JSON)


{
  "words": ["Monday", "Tuesday", "march", "apple", "Machine", "banana"]
}


#### Response:


{
  "countStartsWithM": 3,
  "wordsLongerThanFive": ["Monday", "Tuesday", "march", "Machine", "banana"]
}

Please refer swagger document for API contract Swagger UI: http://localhost:8080/swagger-ui.html

---

## 📫 Postman Collection

Use the following link to import the pre-configured Postman collection:
🔗 [Postman Collection](https://lunar-zodiac-822690.postman.co/workspace/My-Workspace~b392624a-95e9-4f98-8fc6-b52bf0de9d70/collection/4823605-df4d2ba5-2e77-42e8-8628-85ca79d5890b?action=share&creator=4823605)

> Make sure to replace the JWT token in the environment after login.

---

## 🔧 Tech Stack

* Java 17
* Spring Boot 3.x
* Security (JWT-based)
* Maven
* Docker
* Swagger (OpenAPI 3)
* JUnit 5

---

## 👨‍💻 Author

**Shivpal Chouhan**
📧 [LinkedIn](https://www.linkedin.com/in/shivpal-chouhan-aa256318/)
🛠 GitHub: [shivpal-chouhan](https://github.com/shivpal-chouhan)

---