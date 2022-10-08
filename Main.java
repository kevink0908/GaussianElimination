// Programmer: Kevin Kim
// Instructor: Professor Raheja
// Class: CS 3010-03
// Date: 10/09/2022
// Project: Gaussian Elimination with Scaled Partial Pivoting

import java.util.Scanner;
import java.lang.Math;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class Main
{
    public static void main(String args[])
    {
        int userInput = 0;
        int count = 0;
        double[] ratioArray = new double[10];
        String fileName;
        Scanner keyboard = new Scanner(System.in);

        // prompt the user to enter the number of linear equations to solve
        // using the Gaussian elimination with Scaled partial Pivoting.
        do {
            System.out.print("Please enter the number of linear equations (n <= 10)");
            System.out.print(" to solve using the Gaussian elimination with");
            System.out.print(" Scaled Partial Pivoting method: ");
            count = keyboard.nextInt();
            // clear the buffer.
            keyboard.nextLine();

            // check to see if the user has inputted a valid integer value.
            if (count % 1 != 0)
            {
                // if not, prompt the user to input a valid integer value.
                System.out.print("\nPlease input a valid integer value (1 - 10)...");
            }
        } while ((count > 0) && (count <= 10));

        Gaussian equations = new Gaussian(count);

        // prompt the user to enter the coefficients from the command line
        // or a file name which has the augmented coefficient matrix.
        do {
            System.out.println("\nPlease enter your choice: ");
            System.out.println("(1) Enter the coefficients from the command line.");
            System.out.println("(2) Enter a file name with augmented coefficient matrix.");
            userInput = keyboard.nextInt();
            // clear the buffer.
            keyboard.nextLine();

            // check to see if the user has inputted a valid integer value.
            if (count % 1 != 0)
            {
                System.out.println("\nPlease choose from the following options...");
            }
        } while ((userInput == 1) || (userInput == 2));

        // check to see if the user wishes to enter the coefficients from the command line.
        if (1 == userInput)
        {
            // if so, call the PerformGaussianElimination for command line inputs.
            equations.PerformGaussianElimination(count);
        }
        // check to see if the user wishes to enter a file name
        // which has the augmented coefficient matrix.
        else if (2 == userInput)
        {
            // if so, prompt the user to enter the file name and then
            // call the PerformGaussianElimination for file reading.
            System.out.print("Please enter the file name: ");
            fileName = keyboard.nextLine();

            // check to see if a file exists.
            File myFile = new File(fileName);
            if (myFile.exists())
            {
                // if so, call the PerformGaussianElimination for file reading.
               equations.PerformGaussianElimination(fileName, count);
            }
            else
            {
                // otherwise, terminate the program because the file does not exist.
                System.out.println("\nThe file does not exist...");
                System.out.println("Terminating the program...");
                System.exit(0);
            }
        }
        // otherwise, terminate the program as there was a corruption in the program.
        else
        {
            System.exit(0);
        }

    } // end of main.

}
