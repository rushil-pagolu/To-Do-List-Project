HOW TO RUN
1. Open a terminal in the folder containing ToDoList.java.
2. Compile:
   javac ToDoList.java
3. Run:
   java ToDoList

Alternatively, with JDK 11 or newer:
   java ToDoList.java

In an IDE such as IntelliJ or Eclipse, create a Java project, add
ToDoList.java to the source folder, and run its main method.
No package declaration or external dependencies are needed.

FEATURES
- Add a task with a nonempty description.
- View numbered tasks and the number completed.
- Mark a task as complete. Completed tasks display [X].
- Remove a task by its displayed number.
- Exit through menu option 5.
- Handle invalid menu choices, blank descriptions, nonnumeric task
  numbers, out-of-range task numbers, and closed input gracefully.

Tasks are held in memory and are cleared when the program exits.
Task numbers reflect current list positions and change after removal.

QUICK DEMO
1. Choose 1 and enter: Finish homework
2. Choose 1 and enter: Study for quiz
3. Choose 2 to view both tasks.
4. Choose 3, then 1 to complete Finish homework.
5. Choose 4, then 2 to remove Study for quiz.
6. Choose 2 to see the remaining completed task.
7. Choose 5 to exit.