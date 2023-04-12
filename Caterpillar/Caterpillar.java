package Assignment2;

public class Caterpillar extends MyDoublyLinkedList<Position> {
    public Caterpillar() {
        //super();
        add(new Position(7, 7));
        this.size = 1;
    }

    public Position getHead() {
        int x = this.peekFirst().getX();
        int y = this.peekFirst().getY();
        Position mine = new Position(x, y);
        return mine;
    }

    public void eat(Position p) throws IllegalArgumentException {
        Position currentHead = this.getHead();
        int m = Math.abs(p.getX()-currentHead.getX());
        int n = Math.abs(p.getY()-currentHead.getY());
        if ((m==0 && n==1) || (m==1 && n==0)){
            this.addFirst(p);
        }
        else {
            throw new IllegalArgumentException("the input position is not orthogonally adjacent to the current head position");
        }
    }

    public void move(Position p){
        this.eat(p);
        removeLast();
    }

    public boolean selfCollision(Position p){
        for (Position mine : this){
            if(mine.equals(p)){
                return true;
            }
        }
        return false;
    }
}
