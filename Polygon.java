public class Polygon {
    protected int[] angles;
    protected int numOfSides;
    public  Polygon(){
        this.numOfSides=0;
        angles=null;
    }

    public Polygon(int numOfSides,int[] angles){
        this.numOfSides=numOfSides;
        this.angles=angles;
    }

    public Integer getNumOfSides(){
        return numOfSides;
    }
}
