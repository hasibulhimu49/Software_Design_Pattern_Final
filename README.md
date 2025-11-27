# 📘 Design Pattern Final Assignment

This repository contains solutions for two tasks based on Software Design Patterns.  
Each task includes Java code, UML diagrams, and explanations of the patterns used.

---

## 📌 Task 1 – Text Editor

### ✔ Design Patterns Used
- **Command Pattern**
- **Memento Pattern**

### 🔍 Why These Patterns?
- **Command Pattern**  
  Each editor action (insert, delete, update) is treated as a command object.  
  This makes undo/redo operations modular and decoupled from UI logic.

- **Memento Pattern**  
  Stores snapshots of the editor state to restore previous versions during undo/redo operations.

### UML diagram  
<img src="https://github.com/hasibulhimu49/Software_Design_Pattern_Final/blob/main/SoftwareDesignPattern_TextEditor/TextEditor.jpg" alt="DFD" width="600" />

---

## 📌 Task 2 – Home Theater Automation System

### ✔ Design Patterns Used
- **Command Pattern**
- **Facade Pattern**

### 🔍 Why These Patterns?
- **Command Pattern**  
  Converts actions like turning on lights, lowering screens, or starting DVD player into command objects.  
  Supports automation and macro commands.

- **Facade Pattern**  
  Simplifies interaction by providing a single interface (`HomeTheaterFacade`) to control multiple subsystems  
  such as Projector, Lights, DVD Player, Screen, etc.

### UML diagram 
<img src="https://github.com/hasibulhimu49/Software_Design_Pattern_Final/blob/main/SoftwareDesignPattern_HomeTheater2/HomeTheater.jpg" alt="DFD" width="600" />

---

## 📌 Task 3 – Meme Creation

A fun meme created based on university life and design pattern stress.  

<img src="https://github.com/hasibulhimu49/Software_Design_Pattern_Final/blob/main/Me%20in%20first%26last%20semester.jpg" alt="DFD" width="600" />

---


