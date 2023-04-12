package Assignment2;

public class Position {
    private int xIntercept;
    private int yIntercept;

    public Position(int xCoordinate, int yCoordinate){
        this.xIntercept = xCoordinate;
        this.yIntercept = yCoordinate;
    }

    public Position(Position duplicate){
        xIntercept = duplicate.xIntercept;
        yIntercept = duplicate.yIntercept;
    }

    public void reset(int newXCoordinate, int newYCoordinate){
        this.xIntercept = newXCoordinate;
        this.yIntercept = newYCoordinate;
    }

    public void reset(Position newDuplicate){
        this.xIntercept = newDuplicate.xIntercept;
        this.yIntercept = newDuplicate.yIntercept;
    }

    public static int getDistance(Position p1, Position p2){
        return Math.abs((p1.xIntercept-p2.xIntercept)) + Math.abs((p1.yIntercept)-p2.yIntercept);
    }

    public int getX(){
        return this.xIntercept;
    }

    public int getY(){
        return this.yIntercept;
    }

    public void moveWest(){
        this.xIntercept -= 1;
    }
    public void moveEast(){
        this.xIntercept += 1;
    }
    public void moveNorth(){
        this.yIntercept -= 1;
    }
    public void moveSouth(){
        this.yIntercept += 1;
    }
    public boolean equals(Object obj){
        if(!(obj instanceof Position)){
            return false;
        }
        if(!(((Position)obj).xIntercept == this.xIntercept)){
            return false;
        }
        if(!(((Position)obj).yIntercept == this.yIntercept)){
            return false;
        }
        return true;
    }
    public String toString(){
        return(Integer.toString(this.getX())+Integer.toString(this.getY()));
    }


}
