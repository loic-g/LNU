/*
 * File:	Philosopher.java
 * Course: 	Operating Systems
 * Code: 	1DV512
 * Author: 	Suejb Memeti (modified by Kostiantyn Kucher)
 * Date: 	November 2019
 */

import javax.swing.*;
import java.util.Random;

public class Philosopher implements Runnable {

	/*
	 * Controls whether logs should be shown on the console or not.
	 * Logs should print events such as: state of the philosopher, and state of the chopstick
	 * 		for example: philosopher # is eating;
	 * 		philosopher # picked up the left chopstick (chopstick #)
	 */
	public boolean DEBUG = false;

	private int id;

	private final Chopstick leftChopstick;
	private final Chopstick rightChopstick;

	private Random randomGenerator = new Random();
	protected boolean stopPhilosopherThread = false; //Boolean to keep track of when the Philosopher need to be shutdown.

	private int numberOfEatingTurns = 0;
	private int numberOfThinkingTurns = 0;
	private int numberOfHungryTurns = 0;

	private double thinkingTime = 0;
	private double eatingTime = 0;
	private double hungryTime = 0;

	private boolean isPhilosopherHungry; //Boolean that keep track if the philosopher is hungry or not.

	public Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick, int seed, boolean debug) {
		this.id = id;
		this.leftChopstick = leftChopstick;
		this.rightChopstick = rightChopstick;

		this.DEBUG = debug;


		/*
		 * set the seed for this philosopher. To differentiate the seed from the other philosophers, we add the philosopher id to the seed.
		 * the seed makes sure that the random numbers are the same every time the application is executed
		 * the random number is not the same between multiple calls within the same program execution

		 * NOTE
		 * In order to get the same average values use the seed 100, and set the id of the philosopher starting from 0 to 4 (0,1,2,3,4).
		 * Each philosopher sets the seed to the random number generator as seed+id.
		 * The seed for each philosopher is as follows:
		 * 	 	P0.seed = 100 + P0.id = 100 + 0 = 100
		 * 		P1.seed = 100 + P1.id = 100 + 1 = 101
		 * 		P2.seed = 100 + P2.id = 100 + 2 = 102
		 * 		P3.seed = 100 + P3.id = 100 + 3 = 103
		 * 		P4.seed = 100 + P4.id = 100 + 4 = 104
		 * Therefore, if the ids of the philosophers are not 0,1,2,3,4 then different random numbers will be generated.
		 */

		randomGenerator.setSeed(id+seed);
	}
	public int getId() {
		return id;
	}

	public double getAverageThinkingTime() {
		/* TODO
		 * Return the average thinking time
		 * Add comprehensive comments to explain your implementation
		 */
		return thinkingTime/numberOfThinkingTurns;
		//The average of the thinking time is always the amount of time that the
		//philosopher was thinking divided by the amount of time
	}

	public double getAverageEatingTime() {
		/* TODO
		 * Return the average eating time
		 * Add comprehensive comments to explain your implementation
		 */
		/**/
		return (eatingTime/numberOfEatingTurns);
	}

	public double getAverageHungryTime() {
		/* TODO
		 * Return the average hungry time
		 * Add comprehensive comments to explain your implementation
		 */
		return (hungryTime/numberOfHungryTurns);
	}

	public int getNumberOfThinkingTurns() {
		return numberOfThinkingTurns;
	}

	public int getNumberOfEatingTurns() {
		return numberOfEatingTurns;
	}

	public int getNumberOfHungryTurns() {
		return numberOfHungryTurns;
	}

	public double getTotalThinkingTime() {
		return thinkingTime;
	}

	public double getTotalEatingTime() {
		return eatingTime;
	}

	public double getTotalHungryTime() {
		return hungryTime;
	}

	public Chopstick getLeftChopstick(){return leftChopstick;}

	public Chopstick getRightChopstick(){return rightChopstick;}

	@Override
	public void run() {
		/* TODO
		 * (Initialize some additional variables, if necessary)
		 *
		 * Think,
		 * Get hungry,
		 * Pick up the left and then the right chopstick,
		 * Eat,
		 * Release the chopsticks.
		 * ^^^ Repeat until the thread is interrupted
		 *
		 * Increment the thinking/hungry/eating turns counter *when each turn starts*.
		 *
		 * Update the duration of each turn *after the turn is completely finished*.
		 * Keep track of total hungry turn durations by getting the current timestamp with System.currentTimeMillis()
		 * when the turn starts, then another System.currentTimeMillis() after the turn has finished, and subtracting these.
		 * For thinking and eating turns, use the duration generated with randomGenerator.nextInt(1000).
		 *
		 * If DEBUG is true, print the log messages for each event.
		 * Additionally, you might want to print a message such as "philosopher X has finished" when the thread terminates
		 * (for debugging purposes).
		 *
		 *
		 * Add comprehensive comments to explain your implementation, including deadlock prevention/detection.
		 * You should start with a straightforward implementation, but you will eventually have to make it more sophisticated
		 * w.r.t the order (and conditions) of the actions and the threads synchronization in order to pass the tests with the expected results!
		 */

		while(stopPhilosopherThread == false){
			/* To start the philosopher into thinking mode*/
			if (DEBUG){
				System.out.println("Philisopher n*"+getId()+" is currently thinking.");
			}
			//To increase the number of thinking time
			numberOfThinkingTurns++;
			int random = randomGenerator.nextInt(1000);//Random number that will determine how long the Philosopher is thinking.

			//Time the philosopher will be thinking = random
			try{
				Thread.sleep(random);
				thinkingTime+=random;//Adding the amount of time the philosopher was thinking to the variable keeping track of the thinking time.
			}catch(InterruptedException e){
				System.out.println(""+e);
			}

			/*Now the philosopher is hungry */
			if(DEBUG){
				System.out.println("Philosopher n*"+getId()+" is currently hungry.");
			}
			numberOfHungryTurns++;
			isPhilosopherHungry = true; //Set this variable so that the it is stuck in the while loop below until the Philosopher is not hungry anymore.
			double hungryTimeStart = System.currentTimeMillis(); // Start the hungry time
			while(isPhilosopherHungry) {
				if(stopPhilosopherThread){ //if the threads need to be stop then save the info of the hungry time and break the loop.
					double hungryTimeEnd = System.currentTimeMillis();
					this.hungryTime += hungryTimeEnd - hungryTimeStart;
					break;
				}else if (canPickLeftandRight()) {//if the left and right chopsticks can be picked then save the hungry time.
					double hungryTimeEnd = System.currentTimeMillis();
					this.hungryTime += hungryTimeEnd - hungryTimeStart;
					isPhilosopherHungry = false; //set it to false because the philosopher is no longer hungry.
					if (DEBUG) {
						System.out.println("Philosopher n*" + getId() + " just picked the right and left chopstick.");
					}
				}

			}


			if(stopPhilosopherThread){//to make sure if it need to be stopped without continuing all the processes
				break;
			}

			/*Now the Philosopher is eating*/
			if(DEBUG){
				System.out.println("Philosopher n*"+getId()+ " is currently eating");
			}
			numberOfEatingTurns++;
			int randomEatingTime = randomGenerator.nextInt(1000); //Random number that will be the time that the philosopher is eating
			try{
				Thread.sleep(randomEatingTime);
				eatingTime+=randomEatingTime;
			}catch (InterruptedException e){
				System.out.println("" + e);
			}

			/*Release Chopsticks*/
			leftChopstick.getLock().unlock();
			rightChopstick.getLock().unlock();
			if (DEBUG){
				System.out.println("Philosopher n*"+getId() + " puts down the right and left chopstick.");
			}
		}
	}
	/*Method to pick up the left and right chopsticks and return true if success, otherwise return false */
	public boolean canPickLeftandRight(){
		while(isPhilosopherHungry){
			if(stopPhilosopherThread){//to make sure the system did not ask to stop all Philosophers
				return false;
			}else {
				synchronized (this) {//to make sure that this philosopher is not going to be used by other threads.
					if (leftChopstick.getLock().tryLock()) {
						if (rightChopstick.getLock().tryLock()) {// if the left and right chopsticks can be picked up, then we lock them both
							return true;
						} else { // if the right chopstick cannot be picked up then we need to release the left chopsticks too.
							leftChopstick.getLock().unlock();

						}
					}
				}
			}

		}

		return false;
	}


}
