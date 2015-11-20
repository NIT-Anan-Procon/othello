

public class NumStatus{
  private int white, black, empty;

  public NumStatus(int wc, int bc, int ec){
    this.white = wc;
    this.black = bc;
    this.empty = ec;
  }

  public int getWhiteCount(){ return this.white; }
  public int getBlackCount(){ return this.black; }
  public int getEmptyCount(){ return this.empty; }
}
