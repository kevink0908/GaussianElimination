import java.util.Scanner;
import java.lang.Math;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class Gaussian()
{
    private int numCoefficients;
    private double[][] cArray;
    private double[] bArray;

    // this is the default constructor.
    public Gaussian(int n)
    {
        numCoefficients = n;
        cArray = new double[n][n];
        bArray = new double[n];
    } // end of default CTOR.

    // this function will prompt the user to enter the coefficients
    // and the b value at the command line for each row in order to perform
    // the Gaussian elimination with Scaled Partial Pivoting method.
    public void PerformGaussianElimination(int numCoefficients)
    {
        int firstIndex = 0;
        int maxVal = 0;
        double userInt;
        double multiplier = 0.0;
        double[][] cArray = new double[numCoefficients][numCoefficients];
        double[] bArray = new double[numCoefficients];
        double[] resultArray = new double[numCoefficients];
        Scanner keyboard = new Scanner(System.in);

        for (int i = 1; i <= numCoefficients; i++)
        {
            System.out.println("\nInputting the coefficients for Row #" + i + "...");

            for (int j = 1; j <= numCoefficients; j++)
            {
                // prompt the user to enter the coefficients.
                do {
                    System.out.print("\nPlease enter the coefficient for x" + i + ": ");

                    if (keyboard.hasNextDouble())
                    {
                        userInt = keyboard.nextDouble();
                        this.setCoefficients((i - 1), (j - 1), userInt);
                    }
                    else
                    {
                        System.out.println("\nPlease enter a valid value...");
                    }
                } while (keyboard.hasNextDouble());

            }
        }

        // prompt the user to enter the coefficients for each row.
        for (int i = 1; i <= numCoefficients; i++)
        {
            System.out.println("\nInputting the coefficients for Row #" + i + "...");

            System.out.println("\nPlease enter the coefficient for x: ");
            xVal = keyboard.nextInt();
            // clear the buffer.
            keyboard.nextLine();
            maxVal = Math.abs(xVal);
            firstIndex = xVal;

            System.out.println("\nPlease enter the coefficient for y: ");
            yVal = keyboard.nextInt();
            // clear the buffer.
            keyboard.nextLine();

            // check to see if the y-value is the largest.
            if (Math.abs(yVal) > maxVal)
            {
                // if so, update the max value.
                maxVal = Math.abs(yVal);
            }
            // also, check to see if the y-value is the first index.
            if (xVal == 0)
            {
                // if so, then y-value will be the first index.
                firstIndex = yVal;
            }

            System.out.println("\nPlease enter the coefficient for z: ");
            zVal = keyboard.nextInt();
            // clear the buffer.
            keyboard.nextLine();

            // check to see if the z-value is the largest.
            if (Math.abs(zVal) > maxVal)
            {
                // if so, update the max value.
                maxVal = Math.abs(zVal);
            }
            // also, check to see if the z-value is the first index.
            if ((xVal == 0) && (yVal == 0))
            {
                // if so, then z-value will be the first index.
                firstIndex = zVal;
            }

            System.out.println("\nPlease enter the coefficient for b: ");
            bVal = keyboard.nextInt();
            // clear the buffer.
            keyboard.nextLine();

            // output the scaled ratios at each step.
            System.out.print("\nThe scaled ratio for this row is: ");
            System.out.println(GetRatio(firstIndex, maxVal));

            // determine the multiplier for the current step.
            if (GetRatio(firstIndex, maxVal) >= 0.0)
            {
                multiplier = GetRatio(firstIndex, maxVal;
            }

            // mention the pivot row selected based on the scaled ratio.
            for (int i = 0; i < ; i++)
            {
                // show the intermediate matrix at each step
                // of the Gaussian Elimination process.
            }

            // display the final output of the program.


        } // end of the for loop.

    } // end of "PerformGaussianElimination"

    // this function will read the augmented coefficient matrix (including
    // the b values) in a simple text file format in order to perform the
    // Gaussian elimination with Scaled Partial Pivoting method.
    public void PerformGaussianElimination(String fileName, int numCoefficients)
    {

    } // end of "PerformGaussianElimination2"

    public double GetRatio(int firstIndex, int largestNum)
    {
        return firstIndex / largestNum;
    } // end of GetRatio.

    public int getNumCoefficients()
    {
        return numCoefficients;
    }

    public void setCoefficients(int row, int col, double val)
    {
        cArray[row][col] = val;
    }

    public double getCoefficients(int row, int col)
    {
        return cArray[row][col];
    }

    public void setBValues(int row, double val)
    {
        bArray[row] = val;
    }

    public double getBValues(int row)
    {
        return bArray[row];
    }
}
