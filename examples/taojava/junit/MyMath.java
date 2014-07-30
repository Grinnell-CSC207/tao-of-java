package taojava.examples.junit;

/**
 * A simple set of mathematical methods for testing with JUnit.
 */
public class MyMath 
{
  /**
   * Compute the average of two integers, rounded down.
   *
   * @param left
   *   One of the two integers
   * @param right
   *   The other integer
   */
  public static int average(int left, int right) {
    return (left + right) / 2;
  } // average(int,int)

  /**
   * Determine if a number, n, is even.
   */
  public static boolean isEven(int n) {
    return ((n % 2) == 0);
  } // isEven
} // class MyMath
