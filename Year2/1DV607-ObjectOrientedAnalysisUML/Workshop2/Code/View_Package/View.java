package View_Package;

import Controller_Package.Controller;
import Model_Package.Member;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class View{




        public void menu(Controller br){


            Scanner sc = new Scanner(System.in);
            System.out.println("\n--------------------------------------------------");
            System.out.println("-----------------------MENU-------------------------");
            System.out.println("----------------------------------------------------");

            System.out.println("Choose from these choices");
            System.out.println("-------------------------");
            System.out.println("1 - Add a Member");
            System.out.println("2 - Delete a Member");
            System.out.println("3 - Update a Member");
            System.out.println("-------------------------");
            System.out.println("4 - Member info");
            System.out.println("-------------------------");
            System.out.println("5 - Add a Boat");
            System.out.println("6 - Delete a Boat");
            System.out.println("7 - Update a Boat");
            System.out.println("-------------------------\n");
            System.out.println("Press \"C\" for Compact List. Or press \"V\" for Verbose List");
            System.out.println("--------------------------------\n");
            System.out.println("8 - Quit\n");

            System.out.println("--------------------------------\n");

            System.out.print("Your choice :\t");



            String chooseNum = sc.nextLine();


            //ADD a MEMBER
            if(chooseNum.equals("1")){
                System.out.println("--- Add a Member ---");
                System.out.print("Full name : ");
                String name = sc.nextLine();
                System.out.print("Personal number : ");
                String personalNumber = sc.nextLine();
                if(br.searchData(personalNumber)==null){
                    br.addMember(name,personalNumber);
                }else{
                    System.out.println("\n*****************************************");
                    System.out.println("This member already exist in the database");
                    System.out.println("*****************************************");
                }

            } else if (chooseNum.equals("2")){

                System.out.println("--- Delete a Member ---");
                System.out.println("Personal Number :");
                String personalNumber = sc.nextLine();
                if(br.searchData(personalNumber)!=null){
                    br.deleteMember(personalNumber);
                }else{
                    System.out.println("\n******************************************");
                    System.out.println("This member does not exist in the database");
                    System.out.println("******************************************");
                }


            } else if(chooseNum.equals("3")){

                System.out.println("--- Update a Member ---");

                System.out.println("Personal Number :");
                String personalNumber = sc.nextLine();
                if(br.searchData(personalNumber)!=null){
                    System.out.print("Name to Update : ");
                    String name = sc.nextLine();
                    br.updateMemberData(name,personalNumber);
                }else{
                    System.out.println("\n******************************************");
                    System.out.println("This member does not exist in the database");
                    System.out.println("******************************************");
                }



            } else if(chooseNum.equals("4")){
                System.out.println("--- Member info ---");
                System.out.println("Personal Number :");
                String personalNumber = sc.nextLine();
                if(br.searchData(personalNumber)!=null){
                    Member requiredMember = br.searchData(personalNumber);

                    JSONObject specificMember = new JSONObject(requiredMember);
                    System.out.println(specificMember.toString(4));
                }else{
                    System.out.println("\n******************************************");
                    System.out.println("This member does not exist in the database");
                    System.out.println("******************************************");
                }


            } else if(chooseNum.equals("5")){

                System.out.println("--- Add a Boat ---");

                System.out.println("Personal Number :");
                String personalNumber = sc.nextLine();

                if(br.searchData(personalNumber)!=null){
                    System.out.println("Boat Type : ");
                    String boatType = sc.nextLine();

                    System.out.println("Boat Length : ");
                    double boatLength = sc.nextDouble();

                    System.out.println("Boat Width :");
                    double boatWidth = sc.nextDouble();


                    br.addBoat(boatType, boatLength, boatWidth, personalNumber);
                }else{
                    System.out.println("\n******************************************");
                    System.out.println("This member does not exist in the database");
                    System.out.println("******************************************");
                }


            } else if(chooseNum.equals("6")){

                System.out.println("--- Delete a Boat ---");

                System.out.println("Personal Number :");
                String personalNumber = sc.nextLine();

                if(br.searchData(personalNumber)!=null){
                    Member member =  br.searchData(personalNumber);
                    JSONArray boat = new JSONArray(member.getBoats());
                    // JSONArray arr = member.getJSONArray("Boat(s)");

                    if(member.getBoats().size() !=0){

                        System.out.println("Here are the available boats : " + boat.toString(4));

                        System.out.println("Choose a boat, starting from 0 to " + (member.getBoats().size()-1)+":");
                        int index = sc.nextInt();

                        br.deleteBoat(index, personalNumber);
                    }else{
                        System.out.println("There is no boat associated to this member");
                    }
                }else{
                    System.out.println("\n******************************************");
                    System.out.println("This member does not exist in the database");
                    System.out.println("******************************************");
                }







            } else if(chooseNum.equals("7")){

                System.out.println("--- Update a Boat ---");

                System.out.println("Personal Number :");
                String personalNumber = sc.nextLine();

                if(br.searchData(personalNumber)!=null){
                    Member specificMember =  br.searchData(personalNumber);
                    JSONArray boats = new JSONArray(specificMember.getBoats());
                    if(specificMember.getBoats().size() !=0){
                        System.out.println("Here are the available boats : " + boats.toString(4));

                        System.out.println("Choose a boat, starting from 0 to " + (specificMember.getBoats().size()-1));
                        int index = sc.nextInt();

                        System.out.println("Boat Type: ");
                        sc.nextLine();
                        String boatType = sc.nextLine();

                        System.out.println("Boat Length: ");
                        double boatLength = sc.nextDouble();

                        System.out.println("Boat Width: ");
                        double boatWidth = sc.nextDouble();


                        br.updateBoatData(index ,boatType, boatLength, boatWidth, personalNumber);
                    }else{
                        System.out.println("There is no boat associated to this member");
                    }
                }else{
                    System.out.println("\n******************************************");
                    System.out.println("This member does not exist in the database");
                    System.out.println("******************************************");
                }

//


            } else if(chooseNum.equals("8")){
                System.exit(0);
            }



            //COMPACT LIST
            else if (chooseNum.equals("C") || chooseNum.equals("c")){
                ArrayList<Member> database = br.fetchFromDatabase();
                StringBuilder sb = new StringBuilder();
                if(database !=null){
                    for(int i=0;i<database.size();i++){
                        sb.append("***************************\n");
                        sb.append("Name: "+database.get(i).getMemberName()+"\n");
                        sb.append("Member ID: "+database.get(i).getMemberID()+"\n");
                        sb.append("Number of Boats: "+database.get(i).getBoats().size()+"\n");
                        sb.append("***************************\n");

                    }
                    System.out.println(sb.toString());
                }else{
                    System.out.println("**********************************");
                    System.out.println("There is no member in the database");
                    System.out.println("**********************************");
                }

            }

            //VERBOSE LIST
            else if (chooseNum.equals("V")|| chooseNum.equals("v")) {
                ArrayList<Member> database = br.fetchFromDatabase();
                if(database !=null) {
                    JSONArray printout = new JSONArray(database);
                    System.out.println(printout.toString(4));
                }else{
                    System.out.println("**********************************");
                    System.out.println("There is no member in the database");
                    System.out.println("**********************************");
                }



            }
        }

}

