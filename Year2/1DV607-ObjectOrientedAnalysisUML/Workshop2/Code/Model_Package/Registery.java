package Model_Package;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Registery {
    private ArrayList<Member> Members;
    private int userIDs;

    public Registery(){
        if(fetchFromDatabase()==null){
            Members = new ArrayList<>();
            userIDs = 1;
        }else {
            Members = fetchFromDatabase();
            userIDs = Members.get(Members.size()-1).getMemberID()+1;
        }
    }

    public void addMember(Member member){
        if(!isMemberInDatabaseAlready(member.getPersonal_Number())){
            member.setID(userIDs);
            this.Members.add(member);
            userIDs++;
            loadToDatabase();
        }
    }
    public void updateMember(Member member) {
        if (Members.contains(member)) {
            int index = Members.indexOf(member);
            Members.set(index,member);
        }
        loadToDatabase();
    }
    public void deleteMember(Member toDelete){
        Members.remove(toDelete);
        loadToDatabase();
    }
    public void addBoat(Member member, Boat boat){

        member.addBoat(boat);
        loadToDatabase();
        //updateMember(member);
    }

    public void updateBoatData(Member member,Boat boat, int index){
        if (index>=0 && index<member.getBoats().size()){
            member.updateBoat(boat,index);
            loadToDatabase();
        }
    }
    public void deleteBoat(Member member,int index){
        if (index>=0 && index<member.getBoats().size()){
            member.removeBoat(member.getBoats().get(index));
            loadToDatabase();
        }
    }

    public Member searchData(String personalNB){
        for(Member member:Members){
            if (member.getPersonal_Number().equals(personalNB)){
                return member;
            }
        }
        return null;
    }

    public void loadToDatabase(){

        //String text = fetchFromDatabase(); //Call of the method "fetchFromDatabase" and return a string of an JSON Array
            /*
            If the String "text" is empty then creates a new JSON Array and add the JSON Object(Member).
            */

        JSONArray memberList = new JSONArray();
            memberList.put(Members);

        try {
            //Creates a FileWriter to be able to write on a file.
            FileWriter fileWriter = new FileWriter("../DataBase.json");//Select the name of the file and the location
            fileWriter.write(memberList.toString()); //Writes the file with the database
            fileWriter.close();//Close the FileWriter
        }catch (IOException e){
            e.printStackTrace();
        }
            //writeToDatabase(memberList); //Method to write the JSON Array to a JSON file for the database.
    }

    public boolean isMemberInDatabaseAlready(String personalNumber){
        ArrayList<Member> DB = fetchFromDatabase();
        if(DB.size()==0){
            return false;
        }
        for(Member member:DB){
            if(member.getPersonal_Number().equals(personalNumber)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Member> fetchFromDatabase() {

        try {
            //Get the file of the Database
            File file = new File("../DataBase.json");
            //Scanner to be able to read the file
            Scanner sT = new Scanner(file);

            String text = "";
            //Will loop until the scanner has no next line to read.
            while (sT.hasNext()) {
                text += sT.nextLine();
            }
            if(!text.equals("[]")){

                JSONArray database = new JSONArray(text);
                ArrayList<Member> temp = new ArrayList<>();


                JSONArray members = database.getJSONArray(0);
                for (int i=0;i<members.length();i++){
                    JSONObject tempObjectMember = members.getJSONObject(i);
                    Member tempMember = new Member(tempObjectMember.getString("memberName"),tempObjectMember.getString("personal_Number"));
                    //tempMember.setID(tempObject.getInt("memberID"));
                    JSONArray boats = tempObjectMember.getJSONArray("boats");
                    for(int k=0;k<boats.length();k++){
                        JSONObject boat = boats.getJSONObject(k);
                        tempMember.addBoat(new Boat(boat.getString("boatType"),boat.getDouble("boatWidth"),boat.getDouble("boatLength")));
                    }

                    temp.add(new Member(tempMember,tempObjectMember.getInt("memberID")));
                }


                return temp;
            }



        } catch (IOException e) {

        }
        return null;
    }

}
