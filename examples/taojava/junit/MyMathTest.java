package taojava.examples.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests of the MyMath class.
 */
public class MyMathTest 
{
  @Test
  public void testAverage() 
  {
    assertEquals("zero", 0, MyMath.average(0, 0));
    for (int i = 0; i &lt; 100; i++) 
      {
        assertEquals(i + " vs " + -i, 0, MyMath.average(i, -i));
      } // for
  } // testAverage

  @Test
  public void testIsEven() 
  {
    for (int i = -100; i &lt; 100; i += 2) 
      {
        assertEquals(i + " is even", true, MyMath.isEven(i));
        assertEquals((i + 1) + " is odd", false, MyMath.isEven(i + 1));
      } // for
  } // testIsEven
} // MyMathTest
