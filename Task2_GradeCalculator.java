import java.util.Scanner;

public class Task2_GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();
        
        if (numSubjects <= 0) {
            System.out.println("Please enter a valid number of subjects.");
            return;
        }
        
        int totalMarks = 0;
        
        for (int i = 1; i <= numSubjects; i++) {
            System.out.print("Enter marks obtained in subject " + i + " (out of 100): ");
            int marks = scanner.nextInt();
            
            if (marks < 0 || marks > 100) {
                System.out.println("Marks should be in the range of 0 to 100.");
                return;
            }
            
            totalMarks += marks;
        }
        
        double averagePercentage = (double) totalMarks / (numSubjects * 100) * 100;
        
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage + "%");
        
        String grade = calculateGrade(averagePercentage);
        System.out.println("Grade: " + grade);
    }
    
    public static String calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return "A+";
        } else if (averagePercentage >= 80) {
            return "A";
        } else if (averagePercentage >= 70) {
            return "B";
        } else if (averagePercentage >= 60) {
            return "C";
        } else if (averagePercentage >= 50) {
            return "D";
        } else {
            return "F";
        }
    }
}

