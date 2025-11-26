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

### 📁 Files Included
- UML diagram  
- Command interface + concrete commands  
- EditorMemento & Caretaker  
- `TextEditorApp.java`

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

### 📁 Files Included
- UML diagram  
- Subsystem classes (Projector, Lights, DVDPlayer, etc.)  
- Command classes  
- `HomeTheaterFacade.java`  
- Demo runner

---

## 📌 Task 3 – Meme Creation

A fun meme created based on university life and design pattern stress.  
The meme image is included in the repository.

---


