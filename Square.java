public class Square extends Polygon{
    private static int squares=0;
    private final int sideLength;
    private int squareId;
    protected int setSquareId(Integer x){
        squareId=x;
        return squareId;
    }

    public Square(){
        new Polygon(4,new int[] {90,90,90,90});
        this.sideLength=0;
        setSquareId(squares++);
    }

    public Square(Integer sideLength){
        new Polygon(4,new int[] {90,90,90,90});
        this.sideLength=sideLength;
        setSquareId(squares++);
    }

    public int getSquareId(){
        return squareId;
    }

    public int getSideLen(){
        return sideLength;
    }

    public int getArea(){
        return sideLength*sideLength;
    }
    public float getAreaFloat(){
        return ((float)sideLength)*((float)sideLength);
    }

    public int getPerimeter(){
        return sideLength*4;
    }

    public int multipleSideAndReturnPerimeter(Integer x){
        return getPerimeter()*x;
    }

    public static int getNumOfSquares(){
        return squares;
    }

}
