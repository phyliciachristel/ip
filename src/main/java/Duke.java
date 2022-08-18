import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void markAsUndone() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            return "[" + getStatusIcon() + "] " + this.description;
        }
    }
    public static class Deadline extends Task {

        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }
    public static class Todo extends Task {

        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static class Event extends Task {
        protected String at;

        public Event(String description, String at) {
            super(description);
            this.at = at;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + at + ")";
        }
    }

    public static class DukeExceptions extends Exception {
        public DukeExceptions(String message) {
            super("oh nooo the " + message + "cannot be empty!");
        }
    }
    public static void main(String[] args) {
        String reply = "";
        String exit = "bye";
        List<Task> todoList = new ArrayList<>();
        System.out.println("hi im chompers what do u need!!!\n");

        while(true) {
            Scanner scanIn = new Scanner(System.in);
            reply = scanIn.nextLine();

            if(reply.equals(exit)) {
                System.out.println("bye see u");
                scanIn.close();
                break;
            } else if (reply.equals("list")) {
                System.out.println("here! ur tasks:");
                printList(todoList);
            } else {
                String[] substr = reply.split(" ", 2);
                Integer index;
                Task temp;
                switch (substr[0]) {
                    case "mark":
                        if(substr.length == 1) {
                            System.out.println("enter an index!");
                            break;
                        }
                        try {
                            index = Integer.parseInt(substr[1]) - 1;
                            if(index < 0 || index >= todoList.size()) {
                                System.out.println("thrs nth there :<");
                                continue;
                            }
                            temp = todoList.get(index);
                            temp.markAsDone();
                            System.out.println("oke this is done now:\n" + temp);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input");
                        }
                        break;
                    case "unmark":
                        if(substr.length == 1) {
                            System.out.println("enter an index!");
                            break;
                        }
                        try {
                            index = Integer.parseInt(substr[1]) - 1;
                            if(index < 0 || index >= todoList.size()) {
                                System.out.println("thrs nth there :<");
                                continue;
                            }
                            temp = todoList.get(index);
                            temp.markAsUndone();
                            System.out.println("oke this is undone now:\n" + temp);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input");
                        }

                        break;
                    case "delete":
                        if(substr.length == 1) {
                            System.out.println("enter an index!");
                            break;
                        }
                        try {
                            index = Integer.parseInt(substr[1]) - 1;
                            if(index < 0 || index >= todoList.size()) {
                                System.out.println("thrs nth there :<");
                                continue;
                            }
                            temp = todoList.get(index);
                            todoList.remove(temp);
                            System.out.println("oke this is deleted now:\n" + temp);
                            System.out.println("now u have " + todoList.size() + " task(s)!");
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input");
                        }
                        break;
                    case "todo":
                        if(substr.length == 1) {
                            System.out.println("The description cannot be empty!");
                            break;
                        }
                        temp = new Todo(substr[1]);
                        todoList.add(temp);
                        System.out.println("oke i added:\n" + temp.toString());
                        System.out.println("now u have " + todoList.size() + " task(s)!");
                        break;
                    case "deadline":
                        if(substr.length == 1) {
                            System.out.println("The description cannot be empty!");
                            break;
                        }
                        String[] dlDesc = substr[1].split(" /by ", 2);
                        if(dlDesc.length < 2) {
                            System.out.println("The deadline cannot be empty");
                            break;
                        }
                        temp = new Deadline(dlDesc[0], dlDesc[1]);
                        todoList.add(temp);
                        System.out.println("oke i added:\n" + temp.toString());
                        System.out.println("now u have " + todoList.size() + " task(s)!");
                        break;
                    case "event":
                        if(substr.length == 1) {
                            System.out.println("The description cannot be empty!");
                            break;
                        }
                        String[] eventDesc = substr[1].split(" /at ", 2);
                        if(eventDesc.length < 2) {
                            System.out.println("The date cannot be empty");
                            break;
                        }
                        temp = new Event(eventDesc[0], eventDesc[1]);
                        todoList.add(temp);
                        System.out.println("oke i added:\n" + temp.toString());
                        System.out.println("now u have " + todoList.size() + " task(s)!");
                        break;
                    default:
                        System.out.println("idk what that means :(");
                        break;
                }

            }

        }
    }
    public static void printList(List<Task> list) {
        for(int i = 1; i <= list.size(); i++) {
            Task task = list.get(i-1);
            System.out.println(i + "." + task.toString());
        }
    }
}
