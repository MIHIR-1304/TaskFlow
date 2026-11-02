# ⚡ TaskFlow — Multi-Tier Collaborative Task Management Platform

[![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-6DB33F?logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![React](https://img.shields.io/badge/React-18-61DAFB?logo=react&logoColor=black)](https://react.dev/)
[![MongoDB](https://img.shields.io/badge/MongoDB-6.0-47A248?logo=mongodb&logoColor=white)](https://www.mongodb.com/)
[![Docker](https://img.shields.io/badge/Docker-Containerized-2496ED?logo=docker&logoColor=white)](https://www.docker.com/)
[![License: MIT](https://img.shields.io/badge/License-MIT-purple.svg)](LICENSE)

**TaskFlow** is an enterprise-grade, multi-tier collaborative task management system engineered for developer teams and engineering managers. Built with a decoupled **Spring Boot REST API** backend, a dynamic **React** frontend, and a **MongoDB** document data store, TaskFlow provides high-throughput board updates, role-based access control (RBAC), and one-command **Docker Compose** containerization.

---

## 🌟 Key Architecture & Highlights

- **Multi-Tier Decoupled Architecture**: Independent Spring Boot microservices backend communicating via RESTful JSON contracts with a React client.
- **Role-Based Access Control (RBAC)**: Secure token-based JWT authentication with granular permissions for `ADMIN`, `MANAGER`, and `MEMBER` roles.
- **Dynamic MongoDB Schemas**: Flexible document modeling for nested boards, customizable workflow stages, task cards, checklists, and audit history.
- **Docker & Docker Compose**: Full-stack containerization of the Spring Boot service, React web client, MongoDB replica set, and Redis cache.
- **Interactive Kanban Engine**: Real-time card transitions across customizable columns (`Backlog`, `In Progress`, `Code Review`, `Completed`).

---

## 🏗️ System Architecture

```
[ Client: React + Tailwind CSS ]
               │
               ▼  (HTTP / JSON + Bearer JWT)
[ Spring Boot API Gateway & Filter Chain ]
       ├── JwtAuthenticationFilter
       ├── SecurityContextHolder (RBAC Guard)
       └── RateLimiterInterceptor
               │
               ▼
[ REST Controllers & Business Logic Layer ]
       ├── AuthController (Register, Login, Refresh)
       ├── BoardController (CRUD, Team Membership)
       └── TaskController (Transitions, Comments, Priorities)
               │
       ┌───────┴────────┐
       ▼                ▼
[ Redis Cache ]  [ MongoDB Cluster ]
  (Sessions)       (Users, Boards, Tasks, AuditLogs)
```

---

## 🚀 Quickstart with Docker Compose

Run the entire multi-tier stack with a single command:

```bash
# Clone the repository
git clone https://github.com/MIHIR-1304/TaskFlow.git
cd TaskFlow

# Launch Spring Boot, React, MongoDB, and Redis
docker compose up -d --build
```

Access the applications:
- **Web Client**: `http://localhost:3000`
- **Spring Boot REST API**: `http://localhost:8080/api/v1`
- **Swagger / OpenAPI Documentation**: `http://localhost:8080/swagger-ui.html`
- **MongoDB Database**: `mongodb://localhost:27017/taskflow_db`

---

## 📡 RESTful API Endpoints

### 🔐 Authentication (`/api/v1/auth`)
| Method | Endpoint | Description | Role Required |
|---|---|---|---|
| `POST` | `/api/v1/auth/register` | Register new user account | Public |
| `POST` | `/api/v1/auth/login` | Authenticate and issue JWT | Public |
| `GET` | `/api/v1/auth/me` | Fetch active user profile | Any authenticated |

### 📋 Boards (`/api/v1/boards`)
| Method | Endpoint | Description | Role Required |
|---|---|---|---|
| `GET` | `/api/v1/boards` | List user's active boards | Any |
| `POST` | `/api/v1/boards` | Create a new project board | `MANAGER`, `ADMIN` |
| `GET` | `/api/v1/boards/{id}` | Get board detail with cards | Board Member |
| `DELETE` | `/api/v1/boards/{id}` | Delete board | `ADMIN` |

### ⚡ Tasks (`/api/v1/tasks`)
| Method | Endpoint | Description | Role Required |
|---|---|---|---|
| `GET` | `/api/v1/tasks?boardId={id}` | List tasks for a board | Board Member |
| `POST` | `/api/v1/tasks` | Create task card | Board Member |
| `PUT` | `/api/v1/tasks/{id}` | Update task details | Assignee / Manager |
| `PATCH` | `/api/v1/tasks/{id}/status` | Move card status (Kanban drop) | Any Member |
| `DELETE` | `/api/v1/tasks/{id}` | Delete task | `MANAGER`, `ADMIN` |

---

## 🛠️ Technology Stack

- **Backend**: Java 17, Spring Boot 3.2, Spring Security, Spring Data MongoDB, JJWT, Lombok
- **Frontend**: React 18, Tailwind CSS, Lucide Icons, HTML5 Drag & Drop API
- **Database**: MongoDB 6.0, Redis 7.0
- **DevOps**: Docker, Docker Compose, Nginx, GitHub Actions

---

## 👨‍💻 Author

**Mihir Mahato**  
Software Developer | B.Tech CSE 2026  
GitHub: [@MIHIR-1304](https://github.com/MIHIR-1304)  
LinkedIn: [Mihir Mahato](https://www.linkedin.com/in/mihir-mahato-btech)  
Email: mihirmahato1304@gmail.com
