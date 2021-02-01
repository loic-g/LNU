package Controller_Package;
import Model_Package.Boat;
import Model_Package.Member;
import Model_Package.Registery;

import java.util.ArrayList;
public class Controller {

        public Registery registery;

        public Controller(){
            registery = new Registery();
            registery.loadToDatabase();
        }

        /*
            This Class will be used as the controller. All the methods will be concentrated into this class.

         */
        //Method to add a member by taking their name and personal number.
        public void addMember(String name, String personalNumber){
            registery.addMember(new Member(name,personalNumber));

        }
        //This method will be used to add a new boat
        public void addBoat(String boatType, double boatLength, double boatWidth, String personalNumber){
            if(registery.searchData(personalNumber)!=null){
                //System.out.println("Found it");
                registery.addBoat(registery.searchData(personalNumber),new Boat(boatType, boatWidth, boatLength));// Creates an object of the class Boat
            }else{
                //System.out.println("Didn't find");
            }

        }

         //This method will be used for deleting member.
        public void deleteMember(String personalnb){
            if(registery.searchData(personalnb)!=null){
                registery.deleteMember(registery.searchData(personalnb));
            }


        }

        //This method will be used for deleting Boats from a member
        public void deleteBoat(int index, String personalNB){
            if(registery.searchData(personalNB)!=null){
                registery.deleteBoat(registery.searchData(personalNB),index);
            }

        }
        //Method to update the member information
        public void updateMemberData(String name, String personalNumber){
            if(registery.searchData(personalNumber)!=null){
                registery.searchData(personalNumber).setMemberName(name);
                registery.updateMember(registery.searchData(personalNumber));
            }
        }
        //Method to update the information of a boat.
        public void updateBoatData(int index, String boatType, double boatLength, double boatWidth, String personalNB){
            if(registery.searchData(personalNB)!=null){
                registery.updateBoatData(registery.searchData(personalNB),new Boat(boatType,boatWidth,boatLength),index);
            }

        }

        public Member searchData(String personalNB){
            return registery.searchData(personalNB);
         }

        public ArrayList<Member> fetchFromDatabase() {
            return registery.fetchFromDatabase();
        }







    }


