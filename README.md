🏥 Intelligent Hospital Queue Management System
-> A DSA-driven Spring Boot backend project that intelligently manages hospital patient triage using Priority Queue, aging (fairness) algorithms, and real-time queue monitoring, with MySQL persistence.

📌 Problem Statement :
-> Traditional hospital queues follow FIFO (First-In-First-Out), which often fails to:
- Prioritize critical patients
- Prevent long-waiting patients from starvation
- Provide transparent decision logic
- Recover queue state after system restarts

✅ Solution Overview :
> This project implements an intelligent hospital queue management backend that:
- Prioritizes patients based on emergency level
- Ensures fairness using an aging (waiting-time) algorithm
- Uses PriorityQueue (Heap) for optimal scheduling
- Persists patient data in MySQL
- Recovers queue state on application restart
- Provides real-time queue visibility APIs

🧠 Core Concepts Used (DSA Focus) : 
- Priority Queue (Heap)
- Custom Comparator
- Greedy Scheduling
- Aging Algorithm (Starvation Prevention)
- Queue Cloning (Safe Preview)
- Time-based priority computation

🛠️ Tech Stack
🔹 Backend
    Java 17
    Spring Boot
    Spring Web
    Spring Data JPA
🔹 Database
    MySQL
    Hibernate ORM
🔹 Tools
    Maven
    Postman

# IntelliJ IDEA / Eclipse
📂 Project Structure
com.hospital.management
 ├── controller        # REST APIs
 ├── service           # Business + DSA logic
 ├── repository        # JPA repositories
 ├── entity            # Database entities
 ├── model             # DTOs & domain models
 ├── config            # Security & configuration
 └── HospitalQueueSystemApplication.java

🧩 Modules Description
🧑‍⚕️ Patient Registration Module :
- Registers patients via REST API
- Stores patient data in MySQL
- Pushes patient into priority queue

🚑 Intelligent Triage Module :
- Uses PriorityQueue
- Priority formula:
- priority = (emergencyLevel × 10) + waitingTimeInMinutes
- Higher priority patients served first
- Arrival time used as tie-breaker

⚖️ Fairness (Aging) Module :
- Increases priority as waiting time grows
- Prevents starvation of low-urgency patients

📊 Queue Monitoring Module : 
- Shows total patients in queue
- Provides top-N patient preview without disturbing the queue

🔁 Restart Recovery Module : 
- Reloads patients from database on application startup
- Ensures no data loss after restart

🔗 REST API Endpoints :
# Method	Endpoint	Description :
POST	/patients/register	- Register a new patient
GET	/patients/next	- View next patient
POST	/patients/serve	- Serve next patient
GET	/patients/queue/status	- Queue size & top-N preview
GET	/health	- Health check

⚙️ Installation & Setup Guide
🔹 Step 1: Clone Repository
git clone https://github.com/your-username/hospital-queue-system.git
cd hospital-queue-system

🔹 Step 2: Configure Database
Create a MySQL database:
CREATE DATABASE patient_management_db;

# Update application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/patient_management_db
spring.datasource.username=root
spring.datasource.password=*******
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

🔹 Step 3: Build & Run Application
mvn clean install
mvn spring-boot:run

🔹 Step 4: Test APIs

# Use Postman or browser:
http://localhost:8080/health

🧪 Example Request (Patient Registration)
POST /patients/register
{
  "name": "Ramu",
  "age": 22,
  "symptoms": "Fever",
  "emergencyLevel": 2
}

🧠 Decision Explainability
Each patient selection can be explained:
“Selected due to emergency level X and waiting time Y minutes”
This improves transparency and trust, critical in healthcare systems.

🚀 Key Highlights :
📌 Real-world hospital use case
🧠 Strong DSA implementation
⚖️ Fair scheduling logic
🔁 Crash-safe queue recovery
🏗️ Clean layered architecture
🎯 Interview-ready project

📄 Resume-Ready Description :
# Built a hospital queue management backend using PriorityQueue and aging-based scheduling to ensure fair and efficient patient triage, with MySQL persistence and real-time queue monitoring APIs.

📌 Future Enhancements :
- Multi-doctor serving
- Patient status tracking (WAITING / SERVED)
- Authentication & roles
- Frontend dashboard
- Unit testing with JUnit

👨‍💻 Author :
# Pabitra Khatri
# Backend Developer | Java | DSA | Spring Boot

Domain Link : https://pabitrakc1.github.io/Hospital-Queue-Management-System/
