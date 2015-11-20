


public enum Cell {
  White,
  Black,
  Empty;

  public boolean isEnemy(Cell c){
    if(this == White && c == Black) return true;
    if(this == Black && c == White) return true;
    return false;
  }

  @Override
  public String toString(){
    switch(this){
    case White: return "o";
    case Black: return "x";
    default: return "_";
    }
  }
}
