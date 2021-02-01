package Model_Package;

import java.util.ArrayList;

public class Member {
    //********************************************//
    //********THIS CLASS IS FOR THE MEMBER********//
    //********************************************//
    private String name;
    private String personalNumber;
    private int ID;
    private ArrayList<Boat> Boats;

    //Constructor for the member
    public Member(String name, String personalNumber) {

        this.name = name;
        this.personalNumber = personalNumber;
        this.Boats = new ArrayList<>();
        this.ID = -1;
    }

    public Member(Member copy, int ID) {

        this.name = copy.getMemberName();
        this.personalNumber = copy.getPersonal_Number();
        this.Boats = copy.getBoats();
        this.ID = ID;
    }


    public String getMemberName(){
        return name;
    }

    public String getPersonal_Number(){
        return personalNumber;
    }

    public int getMemberID(){
        return ID;
    }

    public void setID(int iD){
        this.ID = iD;
    }

    public void setMemberName(String name){
        this.name = name;
    }

    public void setPersonal_Number(String personalNumber){
        this.personalNumber = personalNumber;
    }

    public ArrayList<Boat> getBoats(){
        return Boats;
    }

    public void addBoat(Boat boat){
        this.Boats.add(boat);
    }

    public void updateBoat(Boat boat,int index){
        Boats.set(index,boat);
    }

    public void removeBoat(Boat boat){
        this.Boats.remove(boat);
    }
}
