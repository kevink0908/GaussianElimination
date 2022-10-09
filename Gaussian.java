import java.lang.Math;

public class Gaussian
{
    private int numEquations;
    private double[][] cArray;
    private double[] bArray;
    private double[] scaleFactorVectorArray;
    private double[] ratioArray;
    private double[] resultArray;

    // this is the type constructor.
    public Gaussian(int n)
    {
        numEquations = n;
        cArray = new double[n][n];
        bArray = new double[n];
        scaleFactorVectorArray = new double[n];
        ratioArray = new double[n];
        resultArray = new double[n];

    } // end of default CTOR.

    // this method performs the Gaussian elimination with
    // Scaled Partial Pivoting method.
    public void PerformGaussianElimination(double[][] cArray, double[] bArray)
    {
        int count = 0;
        int pivotRow = 0;
        double maxVal = 0.0;
        double firstNum = 0.0;
        double maxRatio = 0.0;
        double ratio = 0.0;
        double multiplier = 0.0;

        // perform the Gaussian elimination with Scaled Partial Pivoting method.
        System.out.println("\nPerforming Gaussian Elimination with Scaled Partial Pivoting...");

        while (count < getNumEquations())
        {
            // obtain the scale factor vector.
            for (int i = count; i < getNumEquations(); i++)
            {
                for (int j = count; j < getNumEquations(); j++)
                {
                    // check to see if the coefficient is the first value.
                    if (j == count)
                    {
                        firstNum = getCoefficients(i, j);
                        maxVal = Math.abs(getCoefficients(i, j));
                    }
                    // check to see if we need to update the maxVal.
                    else if (maxVal < getCoefficients(i, j))
                    {
                        // if so, update the max value.
                        maxVal = Math.abs(getCoefficients(i, j));
                    }
                    scaleFactorVectorArray[i] = maxVal;
                    ratio = Math.abs(firstNum) / maxVal;
                }

                // save the scaled ratio.
                ratioArray[i] = ratio;

                // check to see if the current row is the first row
                // or if the max ratio is less than the current ratio.
                if (count == 0 || maxRatio < ratio)
                {
                    // if so, update the max ratio.
                    maxRatio = ratio;
                    // also update the pivot row because the row
                    // with the largest ratio will be the pivot row.
                    pivotRow = i;
                }
            }

            // output the scaled ratios at each step.
            System.out.print("\nThe scaled ratio for step #" + (count + 1) + " is: " );
            System.out.print("[ ");
            for (int i = 0; i < ratioArray.length; i++)
            {
                System.out.print(ratioArray[i]);
                if (i == (ratioArray.length - 1))
                {
                    System.out.print(" ]");
                }
                else
                {
                    System.out.print(", ");
                }
            }

            // mention the pivot row selected based on the scaled ratio.
            System.out.print("\nRow #" + (pivotRow + 1) + " was selected as the pivot");
            System.out.println(" row because this row had the largest ratio.\n");

            // swap the rows with coefficients so that the pivot row
            // will be in an appropriate location.
            double[] tempArray = new double[getNumEquations() - count];
            tempArray = cArray[count];
            cArray[count] = cArray[pivotRow];
            cArray[pivotRow] = tempArray;

            // also swap the b values for the same two rows.
            double temp = bArray[count];
            bArray[count] = bArray[pivotRow];
            bArray[pivotRow] = temp;

            // apply the multiplier to the pivot row and then subtract
            // the pivot row from the row that was swapped with.
            // NOTE: we are incrementing 1 to the count because the pivot
            // row has been moved to the appropriate location.
            for (int i = count + 1; i < getNumEquations(); i++)
            {
                multiplier = getCoefficients(i, count) / getCoefficients(count, count);

                // update the coefficients.
                for (int j = count; j < getNumEquations(); j++)
                {
                    GetCArray()[i][j] -= (multiplier * GetCArray()[count][j]);
                }
                // then update the b value.
                GetBArray()[i] -= (multiplier * getBValues(count));

            }

            // show the intermediate matrix at each step
            // of the Gaussian Elimination process.
            PrintMatrix();

            count++;
        }

        // lastly, perform back substitution in order to solve for each variable.
        SetResultArray(PerformBackSubstitution());

    } // end of "PerformGaussianElimination"

    // this function performs the back substitution.
    protected double[] PerformBackSubstitution()
    {
        // perform back substitution.
        double[] array = new double[getNumEquations()];

        for (int i = getNumEquations() - 1; i >= 0; i--)
        {
            double sum = 0.0;

            for (int j = i + 1; j < getNumEquations(); j++)
            {
                sum += getCoefficients(i, j) * array[j];
            }
            array[i] = (getBValues(i) - sum) / getCoefficients(i, i);
        }

        // return the array that contains the final values for each variable.
        return array;
    }

    public void PrintScaledRatios()
    {

    }

    // this function displays the final solution.
    public void PrintOutput(double[] array)
    {
        System.out.println("\nDisplaying the final output:");

        // display the final output of the program.
        for (int i = 1; i <= array.length; i++)
        {
            System.out.println("x" + i + " = " + array[i - 1]);
        }
    }

    // this function is used to display the intermediate matrix
    // at each step of the Gaussian Elimination process.
    public void PrintMatrix()
    {
        System.out.println("Displaying the intermediate matrix for the current step...");

        for (int i = 0; i < getNumEquations(); i++)
        {
            // display the coefficients.
            System.out.print("[ ");
            for (int j = 0; j < getNumEquations(); j++)
            {
                System.out.print(getCoefficients(i, j));

                if (j == (getNumEquations() - 1))
                {
                    System.out.print(" ]");
                }
                else
                {
                    System.out.print(", ");
                }
            }

            // display the variables.
            System.out.print(" [ x" + (i + 1) + " ] ");
            // display the b values.
            System.out.println("= [ " + getBValues(i) + " ]");

        }

    } // end of "PrintMatrix"

    public double[][] GetCArray()
    {
        return this.cArray;
    }

    public double[] GetBArray()
    {
        return this.bArray;
    }

    public void SetResultArray(double[] array)
    {
        resultArray = array;
    }

    // this function returns the base address of the result array
    // that contains the final values for each variable.
    public double[] GetResultArray()
    {
        return this.resultArray;
    }

    public int getNumEquations()
    {
        return this.numEquations;
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
