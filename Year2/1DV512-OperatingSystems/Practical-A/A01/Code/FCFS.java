/*
 * File:	Process.java
 * Course: 	Operating Systems
 * Code: 	1DV512
 * Author: 	Suejb Memeti
 * Date: 	November, 2018
 */

import java.util.ArrayList;
import java.util.Collections;

public class FCFS{

	// The list of processes to be scheduled
	public ArrayList<Process> processes;
	public ArrayList<Process> schedulingProcesses;
	private int completionTimeCounter;

	// Class constructor
	public FCFS(ArrayList<Process> processes) {
		this.processes = processes;
		this.schedulingProcesses = new ArrayList<>();
		this.completionTimeCounter =0;
	}

	public void run() {
		// TODO Implement the FCFS algorithm here

		/* The first loop is going to loop through all the processes of the "processes" ArrayList.
		*  The second loop is going to loop through all the processes of the "schedulingProcesses" ArrayList
		*  So for each process of "processes", I am comparing with all the processes of "schedulingProcesses"
		*  and sorting them in ascending order according to the arrival time.									*/
		for(int i=0;i<processes.size();i++){
			//If "schedulingProcesses" is empty then add the process at the first place
			if (schedulingProcesses.size()==0){
				schedulingProcesses.add(processes.get(i));
			}else {
				//To Loop through "schedulingProcesses"
				for (int j = 0; j < schedulingProcesses.size(); j++) {
					//Check if the Process arrival time in "processes" is less than the arrival time of "schedulingProcesses".
					//If it is, therefore the Process will be added at index j and all the processes after that index will be moved to the right.
					if (processes.get(i).arrivalTime<schedulingProcesses.get(j).arrivalTime){
						schedulingProcesses.add(j, processes.get(i));
						break;
					}else{
						//Condition of when this is the last Process of "schedulingProcesses" and therefore we just need to add it to the end.
						//And break the loop.
						if(j==schedulingProcesses.size()-1){
							schedulingProcesses.add(processes.get(i));
							break;
						}
					}
				}
			}
		}
		/* At this point the "schedulingProcesses" ArrayList is now correctly sorted by the arrival time
		*  Now below, it is looping through all the processes of "schedulingProcesses" to be able to calculate
		*  their completed time, turnaround time and waiting time*/

		for(Process process: schedulingProcesses){
			//Condition to make sure that the case when a Process completed time is less than the next process arrival time.
			//Therefore, to calculate the completed time we need to add the arrival time + the burst time.
			//Otherwise, the burst time is the added to "completionTimeCounter" which calculates the completed time of the different processes.
			if(completionTimeCounter<process.arrivalTime){
				completionTimeCounter = process.arrivalTime+process.burstTime;
			}else {
				completionTimeCounter += process.burstTime;
			}

			process.setCompletedTime(completionTimeCounter);

			//Turn Around Time = Completed time of previous process - Arrival time of current process
			int turnAroundTime=completionTimeCounter-(process.getArrivalTime());
			process.setTurnaroundTime(turnAroundTime);

			//Waiting Time = Turn Around time - Burst time of current process.
			int waitingTime = turnAroundTime - process.getBurstTime();
			process.setWaitingTime(waitingTime);
		}
		//updating "processes" with the sorted version
		Collections.copy(this.processes,schedulingProcesses);
	}

	public void printTable() {
		// TODO Print the list of processes in form of a table here
		System.out.println("%%%%%%%%%%%%%%%%% TABLE %%%%%%%%%%%%%%%%%%");
		System.out.println("------------------------------------------");
		System.out.println("PID		AT		BT		CT		TAT		WT");
		for(Process process: schedulingProcesses){
			System.out.println(process.processId+"\t\t"+process.arrivalTime+"\t\t"+process.burstTime+"\t\t"+process.completedTime+"\t\t"+process.turnaroundTime+"\t\t"+process.waitingTime);
		}
		System.out.println("------------------------------------------");
	}

	public void printGanttChart() {
		// TODO Print the demonstration of the scheduling algorithm using Gantt Chart
		String line = "";
		String pIDLine = "";
		String pCompletedTimeLine = "";
		System.out.println("\n%%%%%%%%%%%%%% GANT CHART %%%%%%%%%%%%%%");

		/*The first loop is to loop through each Process of "schedulingProcesses".
		* The second loop is to create the correct amount of space depending of the burst time of the process*/
		for(int i=0;i<schedulingProcesses.size();i++){
			//To write down the first process
			if(i==0){
				pIDLine+="|";
				pCompletedTimeLine+="0";
			}else {
				//Condition to make sure check if there is a waiting time between 2 processes. If there is then
				//a certain amount of '*' will be added depending on how long is the waiting time.
				if(schedulingProcesses.get(i-1).completedTime<schedulingProcesses. get(i).arrivalTime){
					int difference = schedulingProcesses.get(i).getArrivalTime()-schedulingProcesses.get(i-1).completedTime;

					for(int k=0;k<difference;k++){
						pIDLine+="*";
						pCompletedTimeLine+=" ";
					}
					pCompletedTimeLine+=schedulingProcesses.get(i).arrivalTime;
					pIDLine+="||";


				}
			}
			//This loop is used to create the string for each process. Each process will be represented with 1 space * Burst time,
			// plus include the 'P' and 'id' of the process
			//The line with the completed time will be created at the same time and therefore will be dependent of the "pIDLine" string.
			for(int k=0;k<schedulingProcesses.get(i).burstTime+2;k++){
				if(k==schedulingProcesses.get(i).burstTime/2){
					pIDLine+="P"+schedulingProcesses.get(i).processId;
					pCompletedTimeLine+="  ";
					k++;
				}
				pIDLine+=" ";
				pCompletedTimeLine+=" ";
			}
			if(schedulingProcesses.get(i).completedTime<10){
				pCompletedTimeLine+=schedulingProcesses.get(i).completedTime + " ";
			}else{
				pCompletedTimeLine+=schedulingProcesses.get(i).completedTime;
			}

			pIDLine+="||";
		}
		//to create a string as big as the "pIDLine" with equal signs
		for(int i=0;i<pIDLine.length();i++){
			line+="=";
		}


		System.out.println(line);
		System.out.println(pIDLine);
		System.out.println(line);
		System.out.println(pCompletedTimeLine);

		System.out.println("\nThe '*' represents the idle time of the system");
	}

}
