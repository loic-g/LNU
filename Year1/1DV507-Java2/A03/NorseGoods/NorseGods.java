package NorseGoods;

public class NorseGods {
    private String name;
    private String race;
    private String desc;

    public NorseGods(){
        this.name ="Random Name";
        this.race="Random Race";
        this.desc="This god does not exist, please change the name, race and description";
    }

    public NorseGods(String na, String ra, String de){
        this.name = na;
        this.race=ra;
        this.desc=de;
    }

    public String getName(){return name;}
    public String getRace(){return race;}
    public String getDesc(){return desc;}

    public void setName(String NAME){
        this.name=NAME;
    }

    public void setRace(String RACE){
        this.race=RACE;

    }

    public void setDesc(String DESC){
        this.desc=DESC;
    }
}
