# Address Book Challenge

## 📌 Overview

This project is a solution for the *Address Book* technical challenge.

The application reads a text file with people's information and shows:

- How many men are in the address book.
- The name of the oldest person.
- The age difference (in days) between Bill McKnight and Paul Robinson.

The main focus was to keep the code clear, well-organized, and easy to test.

---

## ✅ Requirements

To run this project, you need:

- **Java 17** installed on your machine.
- `JAVA_HOME` environment variable configured.

### ❗ Maven is not required

This project uses **Maven Wrapper**.

This means:

- You don't need to install Maven on your computer.
- The project automatically downloads the correct Maven version the first time you run it.
- It ensures everyone uses the same version.

---

## 📂 Project Structure

src  
├── main  
│   ├── java  
│   │   └── com.challenge  
│   │       ├── application (App entry point)
│   │       ├── domain      (Data models)
│   │       ├── parser      (File reading logic)
│   │       └── service     (Business rules)
│   └── resources  
│       └── AddressBook.txt  
└── test  
└── java

---

## 🏗 Design Decisions

### Focus on the data model
The `Person` entity was created as a Java `record`. This keeps the data immutable (cannot be changed after creation) and the code very simple.

### Separation of work
The project is organized into clear layers:

- `application` → Controls the flow and starts the app.
- `domain` → The data models.
- `parser` → Responsible for reading and understanding the input file.
- `service` → Where the main logic (math and rules) happens.

This keeps everything decoupled, making it easier to maintain.

### Input handling
The app can be run in two ways:

1. By giving a file path as an argument.
2. By automatically using the `AddressBook.txt` file inside `src/main/resources`.

---

## ▶ How to Run

> ⚠️ **Important (Windows / PowerShell)** > In PowerShell, you must use `.\` before `mvnw.cmd` because the terminal does not run commands from the current folder by default.

### 1️⃣ Build the project

Linux/macOS:
```bash
./mvnw clean package
```

Windows (PowerShell):
```powershell
.\mvnw.cmd clean package
```

### 2️⃣ Run using Maven Wrapper

Linux/macOS:
```bash
./mvnw exec:java -Dexec.mainClass="com.challenge.Main"
```

Windows (PowerShell):
```powershell
.\mvnw.cmd exec:java "-Dexec.mainClass=com.challenge.Main"
```

### 3️⃣ Run the generated JAR

After building:
```bash
java -jar target/address-book-challenge-1.0.0.jar
```

Or with a custom file:
```bash
java -jar target/address-book-challenge-1.0.0.jar path/to/file.txt
```

---

## 🧪 Running Tests

Tests were created using JUnit 5.

Linux/macOS:
```bash
./mvnw test
```

Windows (PowerShell):
```powershell
.\mvnw.cmd test
```

---

## ⚙ Technologies Used

- Java 17
- Maven (via Maven Wrapper)
- JUnit 5
- Use of `Optional` to handle empty results safely.

---

## 📌 Future Improvements

If the project grows, we could add:

- Support for CSV or JSON files.
- Better error handling for messy files.
- Faster processing for very large files.
- Integration tests.

---

## 🎯 Final Thoughts

The goal was to provide a simple, readable, and well-structured solution without making it more complex than necessary.