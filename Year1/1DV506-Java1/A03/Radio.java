package lg222sv_assign3;

public class Radio {
	private int Volume;
	private int Channel;
	private boolean Working;
	
	
	public Radio() {
		Volume = 1;
		Channel =1;
		Working =false;
	}
	public void turnOn() {
		Volume = 1;
		Channel =1;
		Working =true;
	}
	public void turnOff() {
		Volume = 1;
		Channel =1;
		Working = false;
	}

	public void setVolume(int vol) {
		if (Working=true) {
			if ((vol<=5) && (vol>=0)) {
				Volume = vol;
			}
			else
				System.out.println("New volume not within range!");
		}
		else
			System.out.println("Radio off ==> No adjustment possible");
	}
	
	public void volumeUp() {
		if (Working==true) {
			if ((Volume<5) && (Volume>=0)) {
				Volume +=1;
			}
			else
				System.out.println("New volume not within range!");
		}
		else
			System.out.println("Radio off ==> No adjustment possible");
	}
	
	public void volumeDown() {
		if (Working==true) {
			if ((Volume<=5) && (Volume>0)) {
				Volume -=1;
			}
			else
				System.out.println("New volume not within range!");
		}
		else
			System.out.println("Radio off ==> No adjustment possible");
	}
	
	public void setChannel(int channel) {
		if (Working==true) {
			if ((channel<=10) && (channel>=1)) {
				Channel = channel;
			}
			else
				System.out.println("New channel not within range!");
		}
		else
			System.out.println("Radio off ==> No adjustment possible");
	}
	public void channelDown() {
		if (Working==true) {
			if ((Channel<=10) && (Channel>1)) {
				Channel -= 1;
			}
			else
				System.out.println("New channel not within range!");
		}
		else
			System.out.println("Radio off ==> No adjustment possible");
	}
	public void channelUp() {
		if (Working==true) {
			if ((Channel<10) && (Channel>=1)) {
				Channel += 1;
			}
			else
				System.out.println("New channel not within range!");
		}
		else
			System.out.println("Radio off ==> No adjustment possible");
	}
	
	public String getSettings() {
		String Settings = "Settings: On "+Working+", Channel "+Channel+", Volume "+Volume; 
		
		return Settings;
	}
}
