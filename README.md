# InternX — Internship Portal (Full Stack Web Application)

InternX is a full-stack internship and job portal where companies can post opportunities and students can apply, track applications, and manage profiles using a secure role-based system.

---

## Features

- Student, Company, and Admin roles
- Internship and job posting system
- Online application and tracking
- Profile management
- Secure authentication (JWT planned)
- Clean monorepo structure

---

## Tech Stack

Backend:
- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security

Frontend:
- Vite
- React
- TypeScript
- Node.js 22

Database:
- MySQL
- MySQL Workbench
- EER Diagram
- Forward Engineering

---

## Project Structure

internship-portal/
backend/
internship-portal-api/
frontend/
internship-portal-ui/
docs/
erd/
eer-diagram.png

---

## Database Design

The database schema is designed using MySQL Workbench with an EER diagram and created using Forward Engineering.

Diagram location:
docs/erd/eer-diagram.png

Main Tables:
- users (STUDENT / COMPANY / ADMIN)
- student_profiles
- company_profiles
- jobs
- applications

---

## Setup

### Database

1. Open MySQL Workbench
2. Create a new model
3. Design EER diagram
4. Use Forward Engineer
5. Create database: internship_portal

---

### Backend

Open:
backend/internship-portal-api

Configure:
src/main/resources/application.properties

Run:

./mvnw spring-boot:run

Backend URL:
http://localhost:8080

---

### Frontend

Open:
frontend/internship-portal-ui

Install:

npm install

Run:

npm run dev

----