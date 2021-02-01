package Model_Package;

public class Boat {
    //********************************************//
    //*********THIS CLASS IS FOR THE BOAT*********//
    //********************************************//
    private String boatType;
    private double boatLength;
    private double boatWidth;

    //Boat's Constructor
    public Boat(String boatType, double boatWidth, double boatLength ) {

        this.boatType = boatType;
        this.boatLength = boatLength;
        this.boatWidth = boatWidth;
    }

    public String getBoatType(){
        return boatType;
    }

    public double getBoatWidth() {
        return boatWidth;
    }

    public double getBoatLength(){
        return boatLength;
    }


    public void setBoatType(String boatType) {
        this.boatType = boatType;
    }

    public void setBoatLength(double boatLength) {
        this.boatLength = boatLength;
    }

    public void setBoatWidth(double boatWidth) {
        this.boatWidth = boatWidth;
    }


}
