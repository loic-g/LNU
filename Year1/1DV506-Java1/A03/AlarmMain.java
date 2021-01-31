package lg222sv_assign3;

public class AlarmMain {

	public static void main(String[] args) {
		
		AlarmClock alarm = new AlarmClock(23,48);
		alarm.displayTime();
		alarm.setAlarm(6, 15);
		
		for (int i=0;i<500;i++) {
			alarm.timeTick();
		}
		
		alarm.displayTime();

	}

}
