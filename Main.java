// Programmer: Kevin Kim
// Instructor: Professor Raheja
// Class: CS 3010-03
// Date: 10/09/2022
// Project: Gaussian Elimination with Scaled Partial Pivoting

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class Main
{
    public static void main(String args[]) throws IOException, InputMismatchException
    {
        int count = 0;
        int userInput = 0;
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
        } while (!(count > 0) && (count <= 10));

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
            if ((userInput != 1) && (userInput != 2))
            {
                System.out.println("\nPlease choose from the following options...");
            }
        } while ((userInput != 1) && (userInput != 2));

        // check to see if the user wishes to enter the coefficients from the command line.
        if (1 == userInput)
        {
            // if so, call the SetValuesFromCommandLine method for command line inputs.
            SetValuesFromCommandLine(equations);
        }
        // check to see if the user wishes to enter a file name
        // which has the augmented coefficient matrix.
        else if (2 == userInput)
        {
            // if so, prompt the user to enter the file name.
            System.out.print("\nPlease enter the file name: ");
            fileName = keyboard.nextLine();

            // check to see if a file exists.
            File myFile = new File(fileName);
            if (myFile.exists())
            {
                // if so, call the SetValuesFromFile for file reading.
               SetValuesFromFile(equations, fileName);
            }
            else
            {
                // otherwise, terminate the program because the file does not exist.
                System.out.println("\nThe file does not exist...");
                System.out.println("Terminating the program...");
                System.exit(0);
            }
        }
        // otherwise, terminate the program as the user input was neither 1 or 2
        // and there was a corruption in the program.
        else
        {
            System.exit(0);
        }

        // perform Gaussian Elimination with Scaled Partial Pivoting method.
        equations.PerformGaussianElimination(equations.GetCArray(), equations.GetBArray());

        // display the final output and terminate the program.
        equations.PrintOutput(equations.GetResultArray());
        System.out.println("\nTerminating the program... Good bye!");

    } // end of main.

    // this function will prompt the user to enter the coefficients
    // and the b value at the command line for each row.
    public static void SetValuesFromCommandLine(Gaussian equations)
    {
        int count = equations.getNumEquations();
        Scanner keyboard = new Scanner(System.in);

        for (int i = 1; i <= count; i++)
        {
            System.out.print("\nInputting the coefficients and the b value");
            System.out.println(" for Row #" + i + "...");

            // set the coefficients.
            for (int j = 1; j <= count; j++)
            {
                // prompt the user to enter the coefficients.
                System.out.print("\nPlease enter the coefficient for x" + j + ": ");

                if (keyboard.hasNextDouble())
                {
                    equations.setCoefficients((i - 1), (j - 1), keyboard.nextDouble());
                }
                else
                {
                    do {
                        System.out.println("\nPlease enter a valid value...");
                    } while (!(keyboard.hasNextDouble()));
                }

            } // end of inner for.

            // set the b values.
            System.out.print("\nPlease enter the b value for Row #" + i + ": ");
            if (keyboard.hasNextDouble())
            {
                equations.setBValues((i - 1), keyboard.nextDouble());
            }
            else
            {
                do {
                    System.out.println("\nPlease enter a valid value...");
                } while (!(keyboard.hasNextDouble()));
            }

        } // end of outer for.

    } // end of "SetValuesFromCommandLine"

    // this function will read the augmented coefficient matrix (including
    // the b values) in a simple text file format.
    public static void SetValuesFromFile(Gaussian equations, String fileName)
            throws FileNotFoundException
    {
        int count = equations.getNumEquations();
        File userFile = new File(fileName);
        Scanner scanner = new Scanner(userFile);

        System.out.print("\nReading the coefficients and the b value");
        System.out.println(" from " + fileName + "...");

        for (int i = 0; i < count; i++)
        {
            // set the coefficients.
            for (int j = 0; j < count; j++)
            {
                // check to see if the next value is a double.
                if (scanner.hasNextDouble())
                {
                    // if so, set it as a coefficient.
                    equations.setCoefficients(i, j, scanner.nextDouble());
                }
                else
                {
                    // otherwise, terminate the program because it is corrupted.
                    System.out.print("\nERROR: program encountered invalid values...");
                    System.out.println("Terminating the program...");
                    System.exit(0);
                }

            } // end of inner for.

            // check to see if the next value is a double.
            if (scanner.hasNextDouble())
            {
                // if so, set it as a b value.
                equations.setBValues(i, scanner.nextDouble());
            }
            else
            {
                // otherwise, terminate the program because it is corrupted.
                System.out.print("\nERROR: program encountered invalid values...");
                System.out.println("Terminating the program...");
                scanner.close();
                System.exit(0);
            }

        } // end of outer for.

        // close the file.
        scanner.close();

    } // end of "SetValuesFromFile"

} // end of "Main" class.
