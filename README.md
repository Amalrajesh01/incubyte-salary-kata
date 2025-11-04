# 💼 Incubyte Salary Management Kata

This project implements a **production-grade REST API** for managing employees and their salaries, following **Test-Driven Development (TDD)** principles.

>  Built with Java 21 + Spring Boot 3.x + SQLite + JUnit 5 + Mockito.

---

## Overview

This application provides:
1. **Employee CRUD API**
2. **Salary Calculation API** (with TDS deductions)
3. **Salary Metrics API** (min, max, average per country/job)

All data is persisted in a **SQLite** database (`salary_management.db`).

---

## 🧩 Features

### 1️⃣ Employee CRUD
| Method | Endpoint | Description |
|---------|-----------|-------------|
| `POST` | `/employees` | Create a new employee |
| `GET` | `/employees` | Get all employees |
| `GET` | `/employees/{id}` | Fetch employee by ID |
| `PUT` | `/employees/{id}` | Update employee details |
| `DELETE` | `/employees/{id}` | Delete employee (implemented without test by design) |

**Employee Model**
```json
{
  "fullName": "Amal Rajesh",
  "jobTitle": "Engineer",
  "country": "India",
  "salary": 90000.0
}
```

---

### 2️⃣ Salary Calculation

**Endpoint:**
```
GET /salary/calculate?country=India&gross=100000
```

**Logic:**
- 🇮🇳 India → TDS = 10% of gross
- 🇺🇸 United States → TDS = 12%
- Other countries → No deduction

**Response:**
```json
{
  "country": "India",
  "gross": 100000.0,
  "tds": 10000.0,
  "net": 90000.0
}
```

---

### 3️⃣ Salary Metrics

**Endpoints:**

| Method | Endpoint | Description |
|---------|-----------|-------------|
| `GET` | `/metrics/country/{country}` | Returns min, max, avg salary for a country |
| `GET` | `/metrics/job/{title}` | Returns average salary for a given job title |

**Sample:**
```
GET /metrics/country/India
```
**Response:**
```json
{
  "min": 50000.0,
  "max": 150000.0,
  "avg": 100000.0
}
```

---

## ⚙️ Tech Stack

- **Backend:** Spring Boot 3.x (REST + JPA)
- **Database:** SQLite (lightweight persistence)
- **Testing:** JUnit 5, Mockito, MockMvc
- **Build Tool:** Maven
- **Language:** Java 21

---

## 🧪 TDD Process Followed

| Step | Commit Message | Description |
|------|----------------|-------------|
| 1️⃣ | `chore(init): project setup` | Initial Spring Boot project |
| 2️⃣ | `test(EmployeeService): red phase createEmployee()` | Wrote failing test |
| 3️⃣ | `feat(EmployeeService): green phase passed createEmployee()` | Implemented minimal logic |
| 4️⃣ | `refactor(EmployeeService): cleaned up validation` | Applied refactoring |
| 5️⃣ | `test(EmployeeController): red phase POST /employees` | Wrote failing test for controller |
| 6️⃣ | `feat(EmployeeController): green phase` | Controller implemented |
| 7️⃣ | `refactor(EmployeeController): improved response handling` | Code cleanup |
| 8️⃣ | `test(SalaryController): red phase` | Salary calculation test written |
| 9️⃣ | `feat(SalaryController): green phase` | Salary API implemented |
| 🔟 | `refactor(SalaryController): simplified endpoint` | Final cleanup |

> Every module strictly followed the **Red → Green → Refactor** loop.

---

## 🧠 AI & Tools Used

| Tool | Usage |
|------|-------|
| **ChatGPT (GPT-5)** | Assisted in TDD scaffolding, prompt engineering, and refactoring suggestions |
| **Postman** | Manual API validation |
| **Spring Tool Suite (STS)** | Development IDE |
| **SQLite Browser** | DB inspection |

> All AI-assisted steps were reviewed, validated, and tested manually before commits.

---

## 🧰 Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/amalrajesh/incubyte-salary-kata.git
cd incubyte-salary-kata
```

### 2. Build & Run
If you’re using Spring Tool Suite or IntelliJ, just run `IncubyteSalaryKataApplication.java`  
Or via terminal:
```bash
mvn clean install
mvn spring-boot:run
```

### 3. Test
Run all tests:
```bash
mvn test
```

---

## 📦 Database Details

- SQLite file: `salary_management.db`
- Auto-created in the project root.
- Table: `employee`  
  Fields: `id, full_name, job_title, country, salary`

---

## 🧱 Folder Structure
```
src/
 ├── main/java/com/incubyte
 │   ├── controller/
 │   ├── model/
 │   ├── repository/
 │   ├── service/
 │   └── exception/
 └── test/java/com/incubyte
     ├── controller/
     └── service/
```

---

## ✅ Final Notes

- All endpoints tested successfully via **Postman** and **JUnit (MockMvc)**.  
- Code follows **clean architecture, validations, and TDD flow**.  
- SQLite used for simplicity; can be switched to MySQL/Postgres easily.  
- Ready for production deployment.

---

🧑‍💻 **Author:** Amal Rajesh  
📧 amalrajesh01@gmail.com 
🏷️ Java Full-Stack Developer | Spring Boot | TDD | AI-Driven Engineering
