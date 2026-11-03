# ⚡ TaskFlow — Multi-Tier Collaborative Task Management Platform

[![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-6DB33F?logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Frontend](https://img.shields.io/badge/Frontend-Tailwind%20%2B%20HTML5-6366F1?logo=tailwindcss&logoColor=white)](https://mihir-1304.github.io/TaskFlow/)
[![MongoDB](https://img.shields.io/badge/MongoDB-6.0-47A248?logo=mongodb&logoColor=white)](https://www.mongodb.com/)
[![Docker](https://img.shields.io/badge/Docker-Containerized-2496ED?logo=docker&logoColor=white)](https://www.docker.com/)
[![License: MIT](https://img.shields.io/badge/License-MIT-purple.svg)](LICENSE)

> 🚀 **Live Interactive Demo**: [https://mihir-1304.github.io/TaskFlow/](https://mihir-1304.github.io/TaskFlow/)

**TaskFlow** is an enterprise-grade, multi-tier collaborative task management system engineered for high-velocity software engineering teams. Built with a decoupled **Spring Boot 3.2 REST API** backend, a high-density **Linear/Jira-inspired frontend** with zero AI-slop aesthetic, and a **MongoDB** document data store, TaskFlow provides smooth drag-and-drop state transitions, multi-view boards, subtask checklists, sprint velocity metrics, and one-command **Docker Compose** containerization.

---

## 🌟 Key Features & Engineering Highlights

- **Multi-View Agile Workspace**:
  - **Kanban Board**: Real HTML5 drag-and-drop with optimistic UI updates, visual drop indicators, column item counts, and story point summations.
  - **Sortable List / Table View**: GitHub Projects-style tabular view with multi-column sorting (Key, Title, Status, Priority, Estimate, Assignee).
  - **Sprint Velocity & Capacity Analytics**: Real-time burndown progress, sprint completion percentage, bottleneck detection, and individual engineer workload distribution.
- **Deep Issue Slide-Over Drawer**:
  - In-place title & markdown description editing.
  - Interactive subtask checklists with auto-calculated progress bars.
  - Story point estimation selector (`1`, `2`, `3`, `5`, `8`, `13`).
  - Priority matrix (`P0 - Urgent`, `P1 - High`, `P2 - Medium`, `P3 - Low`).
  - Immutable activity audit trail & developer comment log.
- **Enterprise Design & Polish**:
  - Linear/Raycast-inspired slate dark palette (`#090d16` canvas, crisp `1px` borders, high-contrast typography).
  - Inline SVG icons with zero external CDN dependency or font flicker.
  - Global keyboard shortcuts (`C` for new issue, `/` for search, `B` for Board, `L` for List, `V` for Velocity, `Esc` to close).
- **Reliable Data Persistence & Portability**:
  - Automatic `localStorage` persistence across browser sessions.
  - **JSON Export & Import**: One-click sprint backup downloads and custom sprint plan restoration.
  - "Reset Sample Data" preset with 10 real-world engineering tasks.
- **Spring Boot Microservices Backend**:
  - Role-Based Access Control (RBAC) with JWT Bearer authentication.
  - MongoDB compound indexing on `{ boardId: 1, status: 1, priority: -1 }`.
  - Docker Compose orchestration with health checks.

---

## 🏗️ System Architecture

```
[ Client: Tailwind CSS + Responsive Reactive Engine ]
                    │
                    ▼  (HTTP / JSON + Bearer JWT)
[ Spring Boot API Gateway & Filter Chain ]
        ├── JwtAuthenticationFilter
        ├── SecurityContextHolder (RBAC Guard)
        └── RateLimiterInterceptor (Bucket4j)
                    │
                    ▼
[ REST Controllers & Business Logic Layer ]
        ├── AuthController (Register, Login, Refresh)
        ├── BoardController (CRUD, Team Membership)
        └── TaskController (Transitions, Checklists, Comments, Priorities)
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

# Launch Spring Boot, React Client, MongoDB, and Redis
docker compose up -d --build
```

Access the applications:
- **Live Static Demo**: `https://mihir-1304.github.io/TaskFlow/`
- **Local Web Client**: `http://localhost:3000`
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
| `POST` | `/api/v1/tasks/{id}/subtasks`| Add checklist subtask | Any Member |
| `DELETE` | `/api/v1/tasks/{id}` | Delete task | `MANAGER`, `ADMIN` |

---

## 🛠️ Technology Stack

- **Backend**: Java 17, Spring Boot 3.2, Spring Security 6, Spring Data MongoDB, JJWT, Lombok
- **Frontend**: Modern Tailwind CSS, HTML5 Drag & Drop API, Responsive Reactive Engine
- **Database**: MongoDB 6.0, Redis 7.0
- **DevOps**: Docker, Docker Compose, Nginx, GitHub Actions

---

## 👨‍💻 Author

**Mihir Mahato**  
Software Developer | B.Tech CSE 2026  
GitHub: [@MIHIR-1304](https://github.com/MIHIR-1304)  
LinkedIn: [Mihir Mahato](https://www.linkedin.com/in/mihir-mahato-btech)  
Email: mihirmahato1304@gmail.com
