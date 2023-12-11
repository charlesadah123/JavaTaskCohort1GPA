package gpacalculator;

import java.util.Scanner;

public class GPACalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

    try{
        System.out.println("Welcome to GPA Calculator\n");

        int totalQualityPoints = 0;
        int totalGradeUnits = 0;

        
        System.out.print("Enter the number courses yo want to calculate for: ");
        int counter = Integer.parseInt(scanner.nextLine());
        // Create an array to store course details
        Course[] courses = new Course[counter];

        System.out.println("|----------------------------|-----------------------|------------|---------------------|");
        System.out.println("| COURSE & CODE              | COURSE UNIT           | GRADE      | GRADE-UNIT          |");
        System.out.println("|----------------------------|-----------------------|------------|---------------------|");

        for (int i = 0; i < courses.length; i++) {
            // Input course details
            System.out.print("Enter Course Name and Code: ");
            String courseNameAndCode = scanner.nextLine();

            System.out.print("Enter Course Unit: ");
            int courseUnit = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter Course Score: ");
            int courseScore = Integer.parseInt(scanner.nextLine());

            // Calculate quality points
            int gradeUnit = calculateQualityPoint(courseScore);

            // Calculate grade unit (limited to the range 1 to 5)
            int qualityPoint = calculateGradeUnit(courseUnit, gradeUnit);

            // Store course details in the array
            courses[i] = new Course(courseNameAndCode, courseUnit, getGrade(courseScore), gradeUnit);

            // Update total quality points and grade units
            totalQualityPoints += qualityPoint;
            totalGradeUnits += gradeUnit;
        }

        // Display the final output in tabular form
        System.out.println("|----------------------------|-----------------------|------------|---------------------|");
        System.out.println("| COURSE & CODE              | COURSE UNIT           | GRADE      | GRADE-UNIT          |");
        System.out.println("|----------------------------|-----------------------|------------|---------------------|");
        for (Course course : courses) {
            System.out.printf("| %-26s | %-21d | %-10s | %-19d |\n", course.getCourseNameAndCode(), course.getCourseUnit(),
                    course.getGrade(), course.getGradeUnit());
        }

        System.out.println("|---------------------------------------------------------------------------------------|");
        System.out.printf("\nYour GPA is = %.2f to 2 decimal places.\n", calculateGPA(totalQualityPoints, totalGradeUnits));

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {   
           scanner.close();
        }
    }

    private static int calculateQualityPoint(int score) {
        if (score >= 70) {
            return 5;
        } else if (score >= 60) {
            return 4;
        } else if (score >= 50) {
            return 3;
        } else if (score >= 45) {
            return 2;
        } else if (score >= 40) {
            return 1;
        } else {
            return 0;
        }
    }

    private static int calculateGradeUnit(int courseUnit, int qualityPoint) {
        return courseUnit * qualityPoint;
    }

    private static String getGrade(int score) {
        if (score >= 70) {
            return "A";
        } else if (score >= 60) {
            return "B";
        } else if (score >= 50) {
            return "C";
        } else if (score >= 45) {
            return "D";
        } else if (score >= 40) {
            return "E";
        } else {
            return "F";
        }
    }

    private static double calculateGPA(int totalQualityPoints, int totalGradeUnits) {
        if (totalGradeUnits == 0) {
            return 0.0;
        }
        double rawGPA = (double) totalQualityPoints / totalGradeUnits;
       // Round the GPA to two decimal places
        return Math.round(rawGPA * 100.00) / 100.00;
    }
}

class Course {
    private String courseNameAndCode;
    private int courseUnit;
    private String grade;
    private int gradeUnit;

    public Course(String courseNameAndCode, int courseUnit, String grade, int gradeUnit) {
        this.courseNameAndCode = courseNameAndCode;
        this.courseUnit = courseUnit;
        this.grade = grade;
        this.gradeUnit = gradeUnit;
    }

    public String getCourseNameAndCode() {
        return courseNameAndCode;
    }

    public int getCourseUnit() {
        return courseUnit;
    }

    public String getGrade() {
        return grade;
    }

    public int getGradeUnit() {
        return gradeUnit;
    }
}
