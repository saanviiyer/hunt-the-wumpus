public class Room {
  static int cid = 0;
  Room[] adj = new Room[3];
  int id;
  public Room(){
    this.id = cid++;
  }
  public Room(int i){
    this.id = i;
  }
  public Room(int i, Room[] a){
    this.adj = a;
    this.id = i;
  }
  public Room(int i, Room a, Room b, Room c){
    this.adj[0] = a;
    this.adj[1] = b;
    this.adj[2] = c;
    this.id = i;
  }

  public void setAdj(Room[] a){
    this.adj = a;
  }
  public void setAdj(Room a, Room b, Room c){
    this.adj[0] = a;
    this.adj[1] = b;
    this.adj[2] = c;
  }

  @Override
  public String toString(){
    return ""+id;
  }

  public String paths(){
    return this.adj[0]+", "+this.adj[1]+", "+this.adj[2];
  }

  public Room[] adj(){
    return this.adj;
  }
}