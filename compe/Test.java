import java.io.*;
import java.nio.file.*;

public class Test {
  public static void main(String[] args)
    throws IOException{

    String str = new String(Files.readAllBytes(Paths.get("test.txt")));
    Board board = new Board(str);
    System.out.println( board.toString() );
  
    Position pos = new Position(3,5);
    for(Candidate c : board.candidates(Cell.White)){
      System.out.println(c.getPosition());
      System.out.println(c.getGain());
      System.out.println(c.getBoard().toString()); 
      NumStatus ns = c.getBoard().count();
      System.out.println(
          String.format("o:%d, x:%d", 
            ns.getWhiteCount(),
            ns.getBlackCount()));
    }
  }

}
