import java.util.Scanner;
import java.util.LinkedList;

public class Board 
    implements Cloneable{

  private final int LENGTH = 8;
  private Cell[][] xs;
  
  public Board(){
    this.xs = new Cell[LENGTH][];
    for(int i=0; i<xs.length; i++){
      this.xs[i] = new Cell[LENGTH];
      for(int j=0; j<this.xs[i].length; j++){
        this.xs[i][j] = Cell.Empty;
      }
    }
    this.xs[3][3] = Cell.White;
    this.xs[3][4] = Cell.Black;
    this.xs[4][3] = Cell.Black;
    this.xs[4][4] = Cell.White;
  }
  public Board(String bstr){
    this.xs = new Cell[LENGTH][];
    Scanner sc = new Scanner(bstr);
    for(int i=0;i<LENGTH; i++){
      this.xs[i] = new Cell[LENGTH];
      for(int j=0; j<LENGTH; j++){
        Cell c;
        switch(sc.next()){
        case "o": c = Cell.White; break; 
        case "x": c = Cell.Black; break; 
        default: c = Cell.Empty; break; 
        }
        this.xs[i][j] = c;
      }
    }
  }
  private Board(Cell[][] xs){
    this.xs = new Cell[LENGTH][];
    for(int i=0; i<this.xs.length; i++){
      this.xs[i] = new Cell[LENGTH];
      for(int j=0; j<this.xs[i].length; j++){
        this.xs[i][j] = xs[i][j];
      }
    }
  }

  public Cell get(Position p){
    if(p.getI() < 0 || p.getI() >= LENGTH) return Cell.Empty;
    if(p.getJ() < 0 || p.getJ() >= LENGTH) return Cell.Empty;
    return this.xs[p.getI()][p.getJ()];
  }

  public Position[] gains(Position p, Cell c){
    if(this.get(p) != Cell.Empty) return new Position[]{};
    LinkedList<Position> targets = new LinkedList<Position>();
    int[] ds = new int[]{-1, 0, 1};
    for(int x=0; x<ds.length; x++){
      for(int y=0; y<ds.length; y++){
        if(x != 0 || y != 0){
          check(p.diff(ds[y],ds[x]), c, ds[y], ds[x], 0, targets);    
        }
      }
    }
    if(targets.size() > 0){
      targets.add(p);
    }
    return targets.toArray(new Position[targets.size()]);
  }
  private int check(Position p, Cell c, int di, int dj, int count,
      LinkedList<Position> targets){
    if(count > 0 && this.get(p) == c){
      for(int d=0; d<count; d++){
        targets.add(new Position(p.getI()-di*(d+1), p.getJ()-dj*(d+1)));
      }
      return count;
    }
    if(c.isEnemy(this.get(p))){
      return check(p.diff(di,dj), c, di, dj, count+1, targets);
    }
    return 0;
  }

  public void replace(Position p, Cell c){
    for(Position target : this.gains(p,c)){
      this.xs[target.getI()][target.getJ()] = c;
    }
  }

  public NumStatus count(){
    int wc = 0, bc = 0, ec = 0;
    for(int i=0; i<this.xs.length; i++){
      for(int j=0; j<this.xs[i].length; j++){
        switch(this.xs[i][j]){
        case White: wc++; break;
        case Black: bc++; break;
        case Empty: ec++; break;
        }
      }
    }
    return new NumStatus(wc,bc,ec);
  }

  public Candidate[] candidates(Cell c){
    LinkedList<Candidate> cs = new LinkedList<Candidate>();
    for(int i=0; i<this.xs.length; i++){
      for(int j=0; j<this.xs[i].length; j++){
        Position p = new Position(i,j);
        Position[] targets = gains(p,c);
        if(targets.length > 0){
          Board b = this.clone();
          b.replace(p,c);
          cs.add(new Candidate(p, b, targets.length));
        }
      }
    }
    return cs.toArray(new Candidate[cs.size()]);
  }

  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder();
    for(int i=0; i<this.xs.length; i++){
      for(int j=0; j<this.xs[i].length; j++){
        Cell c = this.xs[i][j];
        sb.append(String.format("%s ", c.toString()));
      }
      if(i<this.xs.length-1) sb.append("\n");
    }
    return sb.toString();
  }

  @Override
  public Board clone(){
    return new Board(this.xs);    
  }
}
