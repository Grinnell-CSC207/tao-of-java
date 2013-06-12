package username.layout;

/**
 * One line of text.
 *
 * @author Samuel A. Rebelsky
 * @version 1.0 of September 2005
 */
public class TextLine
  implements TextBlock
{
  // +--------------+------------------------------------------------------
  // | Class Fields |
  // +--------------+

  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The contents of the line.
   */
  String line;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new line with contents _line.
   */
  public TextLine(String _line)
  {
    this.line = _line;
  } // TextLine(String)

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block.
   *
   * @pre
   *   i == 0
   * @exception Exception
   *   if i != 0
   */
  public String row(int i)
    throws Exception
  {
    if (i != 0) {
      throw new Exception("Invalid row " + i);
    }
    return this.line;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   */
  public int height()
  {
    return 1;
  } // height()

  /**
   * Determine how many columns are in the block.
   */
  public int width()
  {
    return this.line.length();
  } // width()
 
  // +---------------+-----------------------------------------------------
  // | Class Methods |
  // +---------------+

} // class TextLine
