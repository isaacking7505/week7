import java.io.*;
import java.util.*;

public class week_7_assignments {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Problem 1: Basic Try-Catch
        System.out.println("Problem 1: Basic Try-Catch");
        try {
            int a = 10;
            int b = 0;
            int result = a / b;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Division by zero is not allowed.");
        }

        // Problem 2: Multiple Catch Blocks
        System.out.println("\nProblem 2: Multiple Catch Blocks");
        try {
            String str = "abc";
            int number = Integer.parseInt(str);
            System.out.println("Converted number: " + number);
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException caught.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Number format is invalid.");
        }

        // Problem 3: Finally Block
        System.out.println("\nProblem 3: Finally Block");
        BufferedReader reader = null;
        try {
            FileReader file = new FileReader("nonexistent.txt");
            reader = new BufferedReader(file);
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        } catch (IOException e) {
            System.out.println("Error reading file.");
        } finally {
            System.out.println("Program has finished executing.");
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Error closing the file reader.");
                }
            }
        }

        // Problem 4: Custom Exception
        System.out.println("\nProblem 4: Custom Exception");

        // Problem 5: Throwing Exceptions 
        System.out.println("\nProblem 5: Throwing Exceptions - Enter your age:");
        try {
            int age = scanner.nextInt();
            checkAge(age);
        } catch (InvalidAgeException e) {
            System.out.println("Caught InvalidAgeException: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter an integer.");
            scanner.next(); 
        }

        // Problem 6: Exception Propagation
        System.out.println("\nProblem 6: Exception Propagation");
        try {
            methodThatThrows();
        } catch (Exception e) {
            System.out.println("Caught exception from methodThatThrows: " + e.getMessage());
        }

        // Problem 7: User input
        System.out.println("\nProblem 7: Catching Multiple Exceptions - Enter an integer:");
        try {
            int input = scanner.nextInt();
            System.out.println("You entered: " + input);
        } catch (InputMismatchException e) {
            System.out.println("Error: Input is not an integer.");
            scanner.next(); 
        } catch (NoSuchElementException e) {
            System.out.println("Error: No input provided.");
        }

        // Problem 8: Resource Management 
        System.out.println("\nProblem 8: Resource Management with Try-With-Resources");
        try (BufferedReader br = new BufferedReader(new FileReader("nonexistent.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found in try-with-resources.");
        } catch (IOException e) {
            System.out.println("Error reading file in try-with-resources.");
        }

        // Problem 9: Calculating Average
        System.out.println("\nProblem 9: Creating a Robust Method (Calculating Average)");
        int[] nums = {1, 2, 3, 4, 5};
        System.out.println("Average of nums array: " + calculateAverage(nums));
        int[] emptyArray = {};
        System.out.println("Average of empty array: " + calculateAverage(emptyArray));
        int[] nullArray = null;
        System.out.println("Average of null array: " + calculateAverage(nullArray));

        // Problem 10: Logging Exceptions
        System.out.println("\nProblem 10: Logging Exceptions");
        try {
            int[] arr = new int[2];
            System.out.println(arr[5]);
        } catch (Exception e) {
            System.out.println("An exception occurred. Logging to file.");
            try (FileWriter fw = new FileWriter("error.log", true);
                 PrintWriter pw = new PrintWriter(fw)) {
                pw.println("Logged Exception: " + e.toString());
            } catch (IOException ioEx) {
                System.out.println("Error writing to log file.");
            }
        }

        scanner.close();
    }

    // Problem 4: Custom Exception class 
    static class InvalidAgeException extends Exception {
        public InvalidAgeException(String message) {
            super(message);
        }
    }

    static void checkAge(int age) throws InvalidAgeException {
        if (age <= 0) {
            throw new InvalidAgeException("Invalid age: Age must be greater than 0.");
        } else {
            System.out.println("Age is valid: " + age);
        }
    }

    // Problem 6: Method that throws an exception
    static void methodThatThrows() throws Exception {
        throw new Exception("Exception thrown in methodThatThrows.");
    }

    // Problem 9: calculate the average of an array of integers
    public static double calculateAverage(int[] arr) {
        double average = 0;
        try {
            if (arr == null) {
                throw new NullPointerException("Array is null.");
            }
            if (arr.length == 0) {
                throw new ArithmeticException("Array is empty.");
            }
            int sum = 0;
            for (int num : arr) {
                sum += num;
            }
            average = (double) sum / arr.length;
        } catch (NullPointerException | ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return average;
    }
}
