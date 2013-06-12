package username.layout;

/**
 * A text block surrounded by a box.
 *
 * @author Samuel A. Rebelsky
 * @version 1.0 of September 2005
 */
public class BoxedBlock
  implements TextBlock
{
  // +--------------+------------------------------------------------------
  // | Class Fields |
  // +--------------+

  /**
   * A really big sequence of dashes.  This sequence may grow as
   * the program operates.
   */
  String lotsOfDashes = "--";


  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The stuff in the box
   */
  TextBlock contents;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new line with contents _contents.
   */
  public BoxedBlock(TextBlock _contents)
  {
    this.contents = _contents;
  } // BoxedBlock(String)

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block.
   *
   * @pre
   *   0 <= i < this.height()
   * @exception Exception
   *   if the precondition is not met
   */
  public String row(int i)
    throws Exception
  {
    int h = this.contents.height();
    // The top and bottom of the box
    if ((i == 0) || (i == h+1) ) {
      return "+" + dashes(this.contents.width()) + "+";
    }
    // Stuff within the box
    else if ((i > 0) && (i <= h)) {
      return "|" + this.contents.row(i-1) + "|";
    }
    // Everything else
    else {
      throw new Exception("Invalid row " + i);
    }
  } // row(int)

  /**
   * Determine how many rows are in the block.
   */
  public int height()
  {
    return 2 + this.contents.height();
  } // height()

  /**
   * Determine how many columns are in the block.
   */
  public int width()
  {
    return 2 + this.contents.width();
  } // width()
 
  // +---------------+-----------------------------------------------------
  // | Class Methods |
  // +---------------+

  /**
   * Build a sequence of dashes of a specified length.
   */
  String dashes(int len)
  {
    // Make sure the collection of dashes is big enough
    while (lotsOfDashes.length() < len) {
      lotsOfDashes = lotsOfDashes.concat(lotsOfDashes);
    }
    // Extract an appropriate length substring
    return lotsOfDashes.substring(0,len);
  } // dashes(int)

} // class BoxedBlock
