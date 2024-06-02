
# Employee Management System

## Table of Contents

1. [Project Overview](#project-overview)
2. [Features](#features)
3. [Technologies Used](#technologies-used)
4. [Project Structure](#project-structure)
5. [Installation](#installation)
6. [Usage](#usage)

## Project Overview

The Employee Management System is a Java-based application designed to manage employee data and their activities efficiently. This system provides functionalities to add, update, delete, and view employee details, manage attendance, leave requests, and other related information.

## Features

- Employee CRUD operations
- Attendance management
- Leave request handling
- Salary history
- Project details
- User authentication (Admin and Employee)
- Graphical User Interface (GUI)

## Technologies Used

- Java SE 8
- Java Swing (for GUI)
- JDBC (for database connectivity)
- Eclipse IDE

## Project Structure

```
Employee_Management_System/
│
├── src/
│   ├── Controller/
│   │   ├── Database.java
│   │   ├── DayFilter.java
│   │   ├── MouseEvent_crudBtn.java
│   │   ├── MouseListener.java
│   │   └── time.java
│   ├── Model/
│   │   ├── crud_inputs_1.java
│   │   ├── crud_inputs_pt.java
│   │   ├── crud_inputs_sh.java
│   │   ├── crud_inputs.java
│   │   ├── DatePicker.java
│   │   ├── DropBox.java
│   │   ├── Employee_Details.java
│   │   ├── home_bg.java
│   │   ├── jPanelGradient.java
│   │   ├── Leave_request_1.java
│   │   ├── Panel_frame.java
│   │   ├── Project_Table.java
│   │   ├── Salary_History.java
│   │   ├── select_bar.java
│   │   ├── text_field_add.java
│   │   └── TopBar.java
│   ├── View/
│   │   ├── Attendance.java
│   │   ├── CheckIn.java
│   │   ├── CheckOut.java
│   │   ├── Home_pg.java
│   │   ├── Login_s.java
│   │   ├── Login.java
│   │   └── SignUpAdmin.java
│   └── res/
│
├── README.md
└── .gitignore
```

## Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/Employee_Management_System.git
    ```
2. Open the project in your favorite Java IDE (e.g., IntelliJ IDEA, Eclipse).
3. Set up your database and configure the database connection settings in `Database.java`.
4. Build and run the project.

## Usage

- Launch the application.
- Use the login screen to log in as an admin or employee.
- Navigate through the application to manage employee details, attendance, leave requests, and other functionalities.


