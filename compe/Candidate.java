
public class Candidate{
  private Position position;
  private Board board;
  private int gain;

  public Candidate(Position position, Board board, int gain){
    this.position = position;
    this.board = board;
    this.gain = gain;
  }

  public Position getPosition(){ return this.position; }
  public Board getBoard(){ return this.board; }
  public int getGain(){ return this.gain; }
}
