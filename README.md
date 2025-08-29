# 📚 Library Management System

A **Library Management System** built using **Java Spring Boot**, with secure REST APIs and database integration.  
This project demonstrates layered architecture (Controller → Service → Repository) and role-based operations in a library environment.

---

## 🛠 Tech Stack
- **Java 17+**
- **Spring Boot**
- **Spring Security**
- **Spring Data JPA (Hibernate)**
- **MySQL Database**
- **Maven** (Build Tool)

---

## 🚀 Features
- User authentication & role-based authorization  
- Manage Books (add, update, delete, search by title)  
- Manage Members (register, update, remove, search by ID or membership date)  
- Borrow & Return records with issue/return tracking  
- RESTful API design following layered architecture  

---

## 📂 Project Structure
src/
┣ main/
┃ ┣ java/
┃ ┃ ┣ com.example.librarymanagementsystem/
┃ ┃ ┃ ┣ controllers/ # REST Controllers
┃ ┃ ┃ ┣ service/ # Business Logic Layer
┃ ┃ ┃ ┣ repository/ # Database Layer
┃ ┃ ┃ ┣ model/ # Entities
┃ ┃ ┃ ┗ LibraryManagementSystemApplication.java
┃ ┗ resources/
┃ ┣ application.properties
┃ ┗ static / templates
┣ test/ # Unit Tests (if any)
┗ pom.xml # Dependencies & build config
---

## 📑 API Endpoints  

### **BookController**
- `/book` → GET all books  
- `/book/title/{title}` → GET book by title  
- `/book/add` → POST add new book  
- `/book/update/title/{title}` → PUT update book by title  
- `/book/delete/title/{title}` → DELETE book by title  

### **MemberController**
- `/member` → GET all members  
- `/member/get/id/{id}` → GET member by id  
- `/member/membership_date?date=yyyy-mm-dd` → GET members by membership date  
- `/member/signup` → POST register new member  
- `/member/update/id/{id}` → PUT update member by id  
- `/member/delete/id/{id}` → DELETE member by id  

### **BorrowRecordController**
- `/borrow_record/issue_book` → POST issue a book  
- `/borrow_record/return_book` → POST return a book  
- `/borrow_record/issue_date/{date}` → GET borrow records by issue date  
- `/borrow_record` → GET all borrow records  

---

## ⚙️ How to Run Locally
1. Clone the repository:
   ```bash
   git clone https://github.com/Ahmed619-git/library-management-system.git
   cd library-management-system

2. Configure application.properties with your MySQL DB credentials:
  spring.datasource.url=jdbc:mysql://localhost:3306/library_db
  spring.datasource.username=your_username
  spring.datasource.password=your_password
  spring.jpa.hibernate.ddl-auto=update

3. Build and run the project:
   mvn spring-boot:run

4. Test APIs using Postman or any REST client.

📌 Repository
https://github.com/Ahmed619-git/library-management-system
