# Mini Hospital Emergency Management System
# Student Information

**Student Name:** Asra Mohamed  

**Module:** CIT300 - Data Structures and Algorithms  

**Project Title:** Mini Hospital Emergency Management System Using Data Structures  

**Programming Language:** Java  



## Project Description

This is a console-based Java application developed for a CIT300 Data Structures and Algorithms assignment. The system manages hospital emergency records while demonstrating four fundamental data structures:

- Binary Search Tree for patient records
- Queue for emergency patient waiting order
- Stack for completed treatment history
- Singly Linked List for each patient's visit history

The application uses object-oriented programming, separate classes, input validation, exception handling, and a menu-driven interface.

## Features

- Register a new patient
- Search a patient by Patient ID
- Delete a patient by Patient ID
- Display all patients in ascending Patient ID order
- Add patients to the emergency queue
- Treat the next emergency patient using FIFO order
- Add and remove treatment history records using LIFO order
- Add, search, remove, and display patient visit records
- Load sample data automatically when the program starts

## Project Structure

```text
MiniHospitalEmergencySystem/
|-- README.md
`-- src/
    |-- Main.java
    |-- models/
    |   |-- Patient.java
    |   |-- Treatment.java
    |   `-- Visit.java
    |-- bst/
    |   |-- PatientBST.java
    |   `-- PatientNode.java
    |-- queue/
    |   |-- EmergencyQueue.java
    |   `-- QueueNode.java
    |-- stack/
    |   |-- TreatmentStack.java
    |   `-- StackNode.java
    `-- linkedlist/
        |-- VisitLinkedList.java
        `-- VisitNode.java
```

## Data Structures Used

### 1. Binary Search Tree - Patient Records

Classes:

- `Patient.java`
- `PatientNode.java`
- `PatientBST.java`

The Patient BST stores patient records using Patient ID as the key. A smaller Patient ID is stored in the left subtree, while a larger Patient ID is stored in the right subtree.

Operations:

- Insert new patient
- Search patient by ID
- Delete patient by ID
- Display patients using in-order traversal

Presentation explanation:

A Binary Search Tree is useful when records need to be searched, inserted, and displayed in sorted order. In this system, Patient ID is the key. In-order traversal visits the left subtree, current node, and right subtree, so patients are displayed in ascending ID order. Average-case search, insertion, and deletion are O(log n), but the worst case is O(n) if the tree becomes unbalanced.

### 2. Queue - Emergency Patient Queue

Classes:

- `QueueNode.java`
- `EmergencyQueue.java`

The emergency queue follows the FIFO principle: First In, First Out. The first patient added to the queue is the first patient treated.

Operations:

- Enqueue patient
- Dequeue next patient
- Display waiting patients
- Handle empty queue

Presentation explanation:

A queue is appropriate for emergency waiting management when patients are treated in arrival order. This implementation uses linked nodes with `front` and `rear` references. Enqueue happens at the rear, and dequeue happens at the front. Both operations are O(1).

### 3. Stack - Treatment History

Classes:

- `Treatment.java`
- `StackNode.java`
- `TreatmentStack.java`

The treatment history stack follows the LIFO principle: Last In, First Out. The most recently completed treatment is shown or removed first.

Operations:

- Push completed treatment record
- Pop most recent treatment record
- Display treatment history
- Handle empty stack

Presentation explanation:

A stack is suitable for tracking the most recent completed treatment because the latest record is usually the first one reviewed. This implementation uses linked nodes with a `top` reference. Push and pop happen at the top of the stack, so both are O(1).

### 4. Singly Linked List - Patient Visit History

Classes:

- `Visit.java`
- `VisitNode.java`
- `VisitLinkedList.java`

Each `Patient` object contains its own `VisitLinkedList`, so visit records are maintained separately for each patient.

Operations:

- Add visit
- Remove visit
- Search visit
- Display visit history

Presentation explanation:

A singly linked list is useful for maintaining a sequence of visit records where the number of visits can grow dynamically. Each node stores a visit and a reference to the next node. Adding at the end preserves insertion order. Searching and deletion require traversal, so they are O(n).

## Sample Test Data

The program loads the following sample data automatically.

### Patients

| Patient ID | Name | Age | Contact | Condition |
|---|---|---:|---|---|
| 1001 | Kamal Silva | 45 | 0714567890 | Chest pain |
| 1002 | Sarah Jayasuriya | 52 | 0789876543 | High fever |
| 1003 | Amina Perera | 34 | 0771234567 | Asthma attack |
| 1004 | Ravi Kumar | 61 | 0765558899 | Diabetes emergency |
| 1005 | Nimal Fernando | 28 | 0752223334 | Fractured arm |

### Emergency Queue Examples

- 1001 - Kamal Silva
- 1002 - Sarah Jayasuriya
- 1003 - Amina Perera

### Treatment History Examples

- 3001 - Nimal Fernando - Applied cast for fractured arm
- 3002 - Ravi Kumar - Stabilized blood sugar level
- 3003 - Amina Perera - Provided emergency nebulizer treatment

### Visit History Examples

- 2001 - Amina Perera - Wheezing - Nebulizer therapy
- 2002 - Amina Perera - Breathing difficulty - Inhaler adjustment
- 2003 - Kamal Silva - Chest discomfort - ECG and observation
- 2004 - Nimal Fernando - Arm injury - X-ray and splint
- 2005 - Ravi Kumar - Low blood sugar - Glucose support

## How to Run

Open a terminal in the project folder:

```bash
cd MiniHospitalEmergencySystem
```

Compile the project:

```bash
javac -d out src/Main.java src/models/*.java src/bst/*.java src/queue/*.java src/stack/*.java src/linkedlist/*.java
```

Run the application:

```bash
java -cp out Main
```

On Windows PowerShell, the same commands can be used from inside `MiniHospitalEmergencySystem`.

## Sample Output

```text
========== Hospital Emergency Management System ==========
1. Register New Patient
2. Search Patient
3. Delete Patient
4. Display All Patients
5. Add Emergency Patient
6. Treat Next Emergency Patient
7. Display Emergency Queue
8. Add Treatment History
9. Remove Most Recent Treatment
10. View Treatment History
11. Add Patient Visit
12. Search Patient Visit
13. Remove Patient Visit
14. Display Patient Visit History
15. Exit
==========================================================
Enter your choice: 4

--- All Patients Sorted by Patient ID ---
Patient ID: 1001 | Name: Kamal Silva | Age: 45 | Contact: 0714567890 | Condition: Chest pain
Patient ID: 1002 | Name: Sarah Jayasuriya | Age: 52 | Contact: 0789876543 | Condition: High fever
Patient ID: 1003 | Name: Amina Perera | Age: 34 | Contact: 0771234567 | Condition: Asthma attack
Patient ID: 1004 | Name: Ravi Kumar | Age: 61 | Contact: 0765558899 | Condition: Diabetes emergency
Patient ID: 1005 | Name: Nimal Fernando | Age: 28 | Contact: 0752223334 | Condition: Fractured arm
```

## GitHub Commit Plan

1. Created Java project structure
2. Implemented Patient, Treatment, and Visit models
3. Implemented Patient BST node and insertion
4. Added BST searching, deletion, and in-order traversal
5. Implemented emergency queue with enqueue and dequeue
6. Implemented treatment history stack with push and pop
7. Implemented patient visit linked list operations
8. Added menu-driven console interface and input validation
9. Added sample hospital test data
10. Updated README with setup instructions and DSA explanation

## Suggested Presentation Points

- Explain why Patient ID is a suitable BST key.
- Demonstrate in-order traversal by displaying patients sorted by ID.
- Show FIFO behavior by adding patients to the emergency queue and treating the next patient.
- Show LIFO behavior by adding treatments and removing the most recent record.
- Explain how each patient owns a separate linked list for visit history.
- Discuss time complexity for each operation.
