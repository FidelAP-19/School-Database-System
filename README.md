# School Database Management System

A comprehensive Java-based database management system for educational institutions, demonstrating object-oriented programming principles through multi-level inheritance, file I/O operations, and a full-featured menu-driven interface.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![OOP](https://img.shields.io/badge/OOP-Inheritance-blue?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)

## 📋 Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Class Structure](#class-structure)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Technical Highlights](#technical-highlights)
- [What I Learned](#what-i-learned)
- [Future Enhancements](#future-enhancements)
- [Author](#author)

## 🎯 Overview

This project implements a school database management system that manages courses, faculty, students, and general staff through a hierarchical class structure. Built as part of CMP 158 at Lehman College, it demonstrates core computer science concepts including inheritance, polymorphism, file I/O, and exception handling.

### Key Statistics
- **7 Classes** with inheritance relationships
- **3-Level Inheritance Hierarchy**
- **15 CRUD Operations** via menu interface
- **~1,600 Lines of Code**
- **100% Functional** with comprehensive error handling

## ✨ Features

### Core Functionality
- ✅ Create and manage courses, faculty, students, and general staff
- ✅ Add courses to faculty teaching schedules
- ✅ Enroll students in courses and track credits
- ✅ Query system for specific faculty, students, or courses
- ✅ Find min/max values (courses, credits, teaching loads)
- ✅ Persistent data storage via file I/O
- ✅ Menu-driven console interface with 15 operations

### Advanced Features
- ✅ Custom sorting via `Comparable` interface implementation
- ✅ Object equality checking with overridden `equals()` methods
- ✅ Formatted output via `toString()` overrides
- ✅ Exception handling for file operations
- ✅ Defensive programming with boundary checks
- ✅ Static variable tracking for auto-generated IDs

## 🏗️ Class Structure

### Inheritance Hierarchy

```
Person (Base Class)
├── Employee
│   ├── Faculty
│   └── GeneralStaff
└── Student

Course (Standalone - implements Comparable<Course>)
```

### Class Responsibilities

#### **Person** (Base Class)
- Stores: name, birthYear
- Implements: `Comparable<Person>`
- Compares by: birthYear

#### **Employee** (extends Person)
- Stores: deptName, employeeID (auto-generated)
- Implements: inherited `Comparable`
- Compares by: employeeID
- Tracks: total number of employees (static)

#### **Student** (extends Person)
- Stores: major, isGraduate, studentID (auto-generated), coursesTaken (ArrayList)
- Implements: inherited `Comparable`
- Compares by: total credits earned
- Tracks: total number of students (static)

#### **Faculty** (extends Employee)
- Stores: isTenured, coursesTaught (ArrayList)
- Implements: inherited `Comparable`
- Compares by: number of courses taught
- Max capacity: 100 courses

#### **GeneralStaff** (extends Employee)
- Stores: duty (job responsibility)
- Implements: inherited `Comparable`
- Inherits comparison from Employee

#### **Course** (Standalone)
- Stores: isGraduateCourse, courseDept, courseNum, numCredits
- Implements: `Comparable<Course>`
- Compares by: courseNum

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code) or command line

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/FidelAP-19/School-Database-System.git
cd School-Database-System
```

2. **Compile the project**
```bash
javac *.java
```

3. **Run the application**
```bash
java Main
```

### Input File Format

Create a file named `SchoolDB_Initial.txt` with the following format:

```
Course: true,777,CMP,4
Faculty: John Smith,1980,Computer Science,true
Student: Jane Doe,2000,Computer Science,false
GeneralStaff: Bob Johnson,1975,Facilities,Maintenance
```

**Format Rules:**
- Each line starts with object type: `Course:`, `Faculty:`, `Student:`, or `GeneralStaff:`
- Fields are comma-separated
- Boolean values: `true` or `false`

## 💻 Usage

### Menu Options

When you run the program, you'll see a menu with 15 operations:

```
=== MENU ===
1.  Create 3 New Courses
2.  Create 3 New Faculty
3.  Create 3 New General Staff
4.  Create 3 New Students
5.  Add 2 Courses to Faculty
6.  Add 2 Courses to Student
7.  Add Array of 2 Courses to Faculty
8.  Add Array of 2 Courses to Student
9.  Get Course from Faculty by Index
10. Get Course from Student by Index
11. Query Faculty for Course
12. Faculty with Most/Least Courses
13. Find Min/Max Course
14. Student with Most/Least Credits
15. Display All Data
0.  Exit and Save
```

### Example Usage

**Creating a New Course:**
```
Enter choice: 1

--- Enter 3 New Courses ---
Course 1:
Are you a Graduate course? (true/false): false
Enter Course Number: 168
Enter Course Department: CMP
Enter Number of Credits: 4
```

**Adding Courses to a Student:**
```
Enter choice: 6

Which student? (Enter index): 0
Which course? (Enter index): 0
Course added successfully!
```

## 🔧 Technical Highlights

### Object-Oriented Design

#### **Inheritance Hierarchy**
- **3-level deep** inheritance demonstrating class relationships
- Proper use of `super()` for parent class initialization
- Method overriding for specialized behavior

#### **Polymorphism**
```java
// Collections can hold parent type with child objects
ArrayList<Person> people = new ArrayList<>();
people.add(new Student("Alice", 2000, "CS", false));
people.add(new Employee("Bob", 1985, "IT"));
```

#### **Encapsulation**
- All fields are `private`
- Public getters/setters for controlled access
- Defensive copying where appropriate

### Data Structures

#### **ArrayList Usage**
```java
private ArrayList<Course> coursesTaught;  // Faculty's courses
private ArrayList<Course> coursesTaken;   // Student's courses
```

**Benefits:**
- Dynamic sizing (no fixed array limits)
- Built-in methods (`add`, `get`, `size`)
- Type safety with generics

### File I/O & Exception Handling

```java
try {
    FileInputStream fbs = new FileInputStream("SchoolDB_Initial.txt");
    Scanner inFS = new Scanner(fbs);
    // Process file...
    inFS.close();
} catch (FileNotFoundException e) {
    System.out.println("Cannot find file");
} catch (IOException e) {
    System.out.println("Error reading file");
}
```

### Custom Sorting with Comparable

```java
@Override
public int compareTo(Student other) {
    int thisCreditTotal = calculateTotalCredits();
    int otherCreditTotal = other.calculateTotalCredits();
    
    if (thisCreditTotal > otherCreditTotal) return 1;
    if (thisCreditTotal < otherCreditTotal) return -1;
    return 0;
}
```

### Defensive Programming

**Boundary Checking:**
```java
public Course getCourseTaken(int index) {
    if (index < 0 || index >= coursesTaken.size()) {
        return null;  // Safe return instead of crash
    }
    return coursesTaken.get(index);
}
```

**Capacity Limits:**
```java
public void addCourseTaught(Course course) {
    if (coursesTaught.size() < 100) {  // Prevent overflow
        coursesTaught.add(course);
    }
}
```

### Static Variables for ID Generation

```java
public class Student extends Person {
    static private int numStudents = 0;  // Shared across all instances
    private int studentID;
    
    public Student() {
        numStudents++;                    // Increment counter
        studentID = numStudents;          // Auto-generate unique ID
    }
}
```

## 📚 What I Learned

### Core Concepts
- ✅ **Multi-level Inheritance** - Building complex class hierarchies
- ✅ **Polymorphism** - Using parent types to reference child objects
- ✅ **Encapsulation** - Protecting data with private fields and public methods
- ✅ **Abstraction** - Separating interface from implementation

### Java-Specific Skills
- ✅ **Comparable Interface** - Custom sorting logic
- ✅ **Method Overriding** - `equals()`, `toString()`, `compareTo()`
- ✅ **ArrayList Collections** - Dynamic data structures
- ✅ **File I/O** - Reading/writing persistent data
- ✅ **Exception Handling** - Graceful error recovery
- ✅ **Static vs Instance** - Understanding class-level vs object-level data

### Software Engineering Practices
- ✅ **Code Organization** - Logical class structure
- ✅ **Defensive Programming** - Null checks, boundary validation
- ✅ **User Input Validation** - Preventing invalid data
- ✅ **Documentation** - Clear comments and method signatures

### Problem-Solving
- ✅ **String Parsing** - Converting file data to objects
- ✅ **Data Validation** - Ensuring data integrity
- ✅ **Menu-Driven Architecture** - Building interactive applications
- ✅ **Algorithm Design** - Finding min/max, calculating totals

## 🎓 Academic Context

**Course:** CMP 158 - Programming Methods II  
**Institution:** Lehman College, City University of New York (CUNY)  
**Semester:** Fall 2024  
**Grade:** A (4.0 GPA)

This project served as the capstone assignment, demonstrating mastery of:
- Object-oriented programming principles
- Java collections framework
- File I/O and exception handling
- Software design and architecture

## 🔮 Future Enhancements

### Planned Features
- [ ] **Database Integration** - Migrate from file storage to SQL database
- [ ] **GUI Interface** - Build JavaFX or Swing graphical interface
- [ ] **Search Functionality** - Add search by name, department, course number
- [ ] **Reporting System** - Generate reports (enrollment, teaching load, etc.)
- [ ] **Export to CSV** - Allow data export in various formats
- [ ] **Undo/Redo** - Command pattern for operation history
- [ ] **Data Validation** - More robust input validation
- [ ] **Unit Tests** - JUnit test coverage for all classes

### Technical Improvements
- [ ] **Builder Pattern** - Simplify object creation with many parameters
- [ ] **Factory Pattern** - Centralize object creation logic
- [ ] **Repository Pattern** - Abstract data access layer
- [ ] **Logging** - Add proper logging instead of print statements
- [ ] **Configuration File** - External config for settings (file paths, limits)

## 📁 Project Structure

```
School-Database-System/
├── Person.java              # Base class for all people
├── Employee.java            # Base class for employees
├── Student.java             # Student class with course tracking
├── Faculty.java             # Faculty with teaching assignments
├── GeneralStaff.java        # General staff with duties
├── Course.java              # Course information
├── Main.java                # Entry point (basic version)
├── Driver_SchoolDB.java     # Full menu-driven application
├── SchoolDB_Initial.txt     # Sample input data
└── README.md                # This file
```

## 🤝 Contributing

This is an educational project, but feedback and suggestions are welcome!

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/improvement`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/improvement`)
5. Open a Pull Request

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

## 👤 Author

**Fidel Perez**
- GitHub: [@FidelAP-19](https://github.com/FidelAP-19)
- LinkedIn: [Fidel Perez](https://www.linkedin.com/in/fidel-perez-51288929b/)
- Portfolio: [fidelperez.dev](https://your-portfolio-site.com) *(coming soon)*

## 🙏 Acknowledgments

- **Professor:** CMP 158 instructor at Lehman College
- **Institution:** Lehman College, City University of New York (CUNY)
- **Course:** Programming Methods II

---

**⭐ If you found this project helpful or interesting, please consider giving it a star!**

---

*Built with ☕ and dedication to learning software engineering*
