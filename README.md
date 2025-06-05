# EduScore: Academic Performance Tracker

A comprehensive JavaFX-based school management system designed to streamline student and teacher administration with an intuitive desktop interface.

## 🎯 Overview

EduScore is a desktop application built with JavaFX that provides educational institutions with essential tools for managing students and teachers. The application features a modern, transparent UI design with drag-and-drop window functionality and robust database integration.

## ✨ Features

### 🔐 Authentication System
- Secure login interface with username/password validation
- Database-driven user authentication
- Error handling with user-friendly alerts

### 👨‍🎓 Student Management
- **Student Registration**: Add new students with comprehensive details
- **Student Editing**: Search, view, and update student information
- **Data Fields**: ID, name, class, grade, birthday, address
- **Search Functionality**: Real-time search capabilities

### 👩‍🏫 Teacher Management
- **Teacher Registration**: Add new teaching staff
- **Teacher Editing**: Search, view, and update teacher profiles
- **Subject Assignment**: Assign subjects (Mathematics, Science, English, History, Religion, Sinhala, etc.)
- **Credential Management**: Username and password handling

### 📊 Data Management
- **MySQL Integration**: Robust database connectivity
- **CRUD Operations**: Complete Create, Read, Update, Delete functionality
- **Data Validation**: Input validation and error handling
- **Search & Filter**: Advanced search capabilities across all modules

## 🛠️ Technical Stack

- **Framework**: JavaFX
- **Language**: Java
- **Database**: MySQL
- **Architecture**: MVC (Model-View-Controller)
- **UI**: FXML with transparent styling

## 📁 Project Structure

```
src/main/java/chaula/eduscore/
├── Main.java                           # Application entry point
├── MainController.java                 # Login controller
├── MainModel.java                      # Authentication model
└── non_Academic/
    ├── Student.java                    # Student entity
    ├── non_AcademicMain.java          # Main dashboard
    ├── non_AcademicModel.java         # Data access layer
    ├── register_Student/
    │   └── register_StudentModel.java  # Student registration logic
    ├── edit_Student/
    │   └── edit_StudentController.java  # Student editing interface
    └── edit_Teacher/
        ├── edit_TeacherController.java  # Teacher editing interface
        └── edit_TeacherMain.java       # Teacher management window
```

## 🚀 Getting Started

### Prerequisites
- Java 11 or higher
- JavaFX SDK
- MySQL Server
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### Database Setup

#### Option 1: Quick Setup with SQL Dump (Recommended)
1. Start your MySQL server
2. Create a new database:
   ```sql
   CREATE DATABASE EduScore;
   ```
3. Import the provided SQL dump file from the repository root:
   ```bash
   mysql -u root -p EduScore < database_dump.sql
   ```
   Or using MySQL Workbench:
    - Open MySQL Workbench
    - Connect to your MySQL server
    - Go to Server → Data Import
    - Select "Import from Self-Contained File"
    - Choose the `database_dump.sql` file from the repository root
    - Select "EduScore" as the target schema
    - Click "Start Import"

#### Option 2: Manual Setup
If you prefer to set up the database manually, create the following tables:

```sql
-- Create database
CREATE DATABASE EduScore;
USE EduScore;

-- User table for authentication
CREATE TABLE userTbl (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Student table
CREATE TABLE studenttbl (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    student_first_name VARCHAR(50) NOT NULL,
    student_last_name VARCHAR(50) NOT NULL,
    student_class VARCHAR(20),
    student_grade INT,
    student_birthday DATE,
    student_address TEXT
);

-- Teacher table
CREATE TABLE teachertbl (
    teacher_id INT AUTO_INCREMENT PRIMARY KEY,
    teacher_first_name VARCHAR(50) NOT NULL,
    teacher_last_name VARCHAR(50) NOT NULL,
    teacher_subject VARCHAR(50),
    teacher_birthday DATE,
    teacher_address TEXT,
    teacher_username VARCHAR(50) UNIQUE,
    teacher_password VARCHAR(255)
);
```

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/RealChAuLa/EduScore.git
   cd EduScore
   ```

2. Set up the database using the SQL dump (see Database Setup above)

3. Configure database connection in model files if needed:
   ```java
   private static final String JDBC_URL = "jdbc:mysql://localhost:3306/eduscore";
   private static final String USERNAME = "root";
   private static final String PASSWORD = "your_mysql_password";
   ```

4. Build and run the application

### Default Login Credentials
After importing the SQL dump, you can use the following default credentials:
- **Username**: `chalana`
- **Password**: `123`


## 🎨 User Interface Features

- **Transparent Windows**: Modern glassmorphism design
- **Drag & Drop**: Moveable windows without title bars
- **Responsive Design**: Centered window positioning
- **Real-time Search**: Instant filtering of records
- **Form Validation**: Comprehensive input validation
- **Confirmation Dialogs**: Safe data modification with user confirmation

## 📈 Key Functionalities

### Student Operations
- Add new students with all personal details
- Search students by name or ID
- Update student information including class assignments
- View complete student profiles in table format

### Teacher Operations
- Register new teachers with subject specializations
- Manage teacher credentials and personal information
- Search and filter teacher records
- Update teacher profiles and subject assignments

### System Features
- Secure login system
- Database connection management
- Error handling and user feedback
- Window management and UI navigation

## 🔧 Configuration

The application uses MySQL for data persistence. Update the database configuration in:
- `MainModel.java`
- `non_AcademicModel.java`
- `register_StudentModel.java`

Default database settings:
- **Host**: localhost
- **Port**: 3306
- **Database**: EduScore
- **Username**: root
- **Password**: (empty by default)

## 🗃️ Database Schema

The application uses three main tables:
- **userTbl**: Stores login credentials for system access
- **studenttbl**: Contains all student information and academic details
- **teachertbl**: Manages teacher profiles, subjects, and credentials

For detailed schema information, refer to the `database_dump.sql` file in the repository root.