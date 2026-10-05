import java.util.ArrayList;
import java.util.Scanner;

/** A simple console application for managing a to-do list. */
public class ToDoList {
    private final ArrayList<Task> tasks = new ArrayList<>();
    private final Scanner scanner;

    public ToDoList(Scanner scanner) {
        this.scanner = scanner;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            new ToDoList(scanner).run();
        }
    }

    public void run() {
        System.out.println("Welcome to your To-Do List!");

        while (true) {
            printMenu();
            String input = readLine("Choose an option: ");

            // End gracefully if console input is closed.
            if (input == null) {
                System.out.println("\nGoodbye!");
                return;
            }

            switch (input.trim()) {
                case "1":
                    addTask();
                    break;
                case "2":
                    viewTasks();
                    break;
                case "3":
                    completeTask();
                    break;
                case "4":
                    removeTask();
                    break;
                case "5":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please enter 1 through 5.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n--- TO-DO LIST MENU ---");
        System.out.println("1. Add a task");
        System.out.println("2. View all tasks");
        System.out.println("3. Mark a task as complete");
        System.out.println("4. Remove a task");
        System.out.println("5. Exit");
    }

    private void addTask() {
        String description = readLine("Enter the task description: ");
        if (description == null) {
            return;
        }

        description = description.trim();
        if (description.isEmpty()) {
            System.out.println("Task description cannot be empty.");
            return;
        }

        tasks.add(new Task(description));
        System.out.println("Task added: " + description);
    }

    private void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Your to-do list is empty.");
            return;
        }

        System.out.println("\n--- YOUR TASKS ---");
        int completedCount = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String status = task.isCompleted() ? "[X]" : "[ ]";
            System.out.println((i + 1) + ". " + status + " " + task.getDescription());
            if (task.isCompleted()) {
                completedCount++;
            }
        }
        System.out.println("Completed: " + completedCount + " / " + tasks.size());
    }

    private void completeTask() {
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks to complete.");
            return;
        }

        viewTasks();
        int index = readTaskIndex("Enter the task number to complete: ");
        if (index == -1) {
            return;
        }

        Task task = tasks.get(index);
        if (task.isCompleted()) {
            System.out.println("That task is already complete.");
        } else {
            task.markCompleted();
            System.out.println("Task completed: " + task.getDescription());
        }
    }

    private void removeTask() {
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks to remove.");
            return;
        }

        viewTasks();
        int index = readTaskIndex("Enter the task number to remove: ");
        if (index == -1) {
            return;
        }

        Task removedTask = tasks.remove(index);
        System.out.println("Task removed: " + removedTask.getDescription());
    }

    /** Converts a displayed task number to an ArrayList index. */
    private int readTaskIndex(String prompt) {
        String input = readLine(prompt);
        if (input == null) {
            return -1;
        }

        try {
            int number = Integer.parseInt(input.trim());
            if (number < 1 || number > tasks.size()) {
                System.out.println("Invalid task number. Enter 1 through " + tasks.size() + ".");
                return -1;
            }
            return number - 1;
        } catch (NumberFormatException e) {
            System.out.println("Please enter a whole-number task number.");
            return -1;
        }
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        if (!scanner.hasNextLine()) {
            return null;
        }
        return scanner.nextLine();
    }

    /** Each task keeps its description and completion status together. */
    private static class Task {
        private final String description;
        private boolean completed;

        public Task(String description) {
            this.description = description;
            this.completed = false;
        }

        public String getDescription() {
            return description;
        }

        public boolean isCompleted() {
            return completed;
        }

        public void markCompleted() {
            completed = true;
        }
    }
}
