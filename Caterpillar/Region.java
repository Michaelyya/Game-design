package Assignment2;

public class Region {
    private int xMin;
    private int yMin;
    private int xMax;
    private int yMax;

    public Region(int x1, int y1, int x2, int y2){
        this.xMin = x1;
        this.yMin = y1;
        this.xMax = x2;
        this.yMax = y2;
    }

    public boolean contains(Position p){
        int x = p.getX();
        int y = p.getY();
        if (x<xMin || x>xMax){
            return false;
        }
        else if (y<yMin || y>yMax){
            return false;
        }
        return true;
    }
}
