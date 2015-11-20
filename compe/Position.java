public class Position{
  private int i,j;
  public Position(int i, int j){
    this.i = i;
    this.j = j;
  }
  public int getI(){ return this.i; }
  public int getJ(){ return this.j; }

  public Position diff(int di, int dj){
    return new Position(this.getI() + di, this.getJ() + dj);
  }

  @Override
  public String toString(){
    return String.format("%s %s", this.i, this.j);
  }

  @Override
  public boolean equals(Object o){
    if(o instanceof Position){
      Position p = (Position)o;
      return (p.getI() == this.i && p.getJ() == this.j);
    }
    return false;
  }
  @Override
  public int hashCode(){
    return this.i * 8 + this.j;
  }
}


