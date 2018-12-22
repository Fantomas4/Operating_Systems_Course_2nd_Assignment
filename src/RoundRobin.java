import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class RoundRobin
{ 
	// Method that calculates the waiting time for all processes 
	static int[] calcWaitingTime(int burstTime[], int quantum) {
		/*
		 * Put your code here!
		 */

		Queue<Integer> processQueue = new LinkedList<>();
		Queue<Object> burstTimeQueue = new LinkedList<>(Arrays.asList(burstTime));
		int[] waitingTimeArray = new int[burstTime.length];
		int time = 0;

		// Create the processes' numerical ids and put them inside the processQueue queue
		for (int i = 0; i < burstTime.length; i++) {
			processQueue.add(i);
		}

		while (processQueue.peek() != null) {

			int currentProcessId = processQueue.element();
			int timeSpent = 0;

			if ((int) burstTimeQueue.element() > quantum) {
				int newBurstTime = (int) burstTimeQueue.element() - quantum;
				burstTimeQueue.remove();
				burstTimeQueue.add(newBurstTime);
				processQueue.add(processQueue.remove());

				timeSpent += quantum;

			} else if ((int) burstTimeQueue.element() <= quantum) {
				timeSpent += (int)burstTimeQueue.remove();
				processQueue.remove();
			}

			time += timeSpent;

			// update the waiting time counters inside the waitingTimeArray
			for (int w = 0; w<waitingTimeArray.length; w++) {
				if (w != currentProcessId) {
					waitingTimeArray[w] +=timeSpent;
				}
			}

		}

		return  waitingTimeArray;

	}
	
	// Method that calculates turn around time for all processes
	static int[] calcTurnAroundTime(int burstTime[], int waitingTime[])
	{
		/*
		 * Put your code here!
		 */

	}
	
	// Method that prints the results and calculates the average waiting and
	// turnaround times
	static void printAvgTimes(int burstTime[], int quantum) 
	{
		int n = burstTime.length;
		int totalWaitingTime = 0;
		int totalTurnAroundTime = 0; 
	
		// Find waiting time of all processes 
		int[] waitingTime = calcWaitingTime(burstTime, quantum); 
	
		// Find turn around time for all processes 
		int[] turnAroundTime = calcTurnAroundTime(burstTime, waitingTime); 
	
		// Display processes along with all details 
		System.out.println("Process " + " Burst Time " + 
					" Waiting Time " + " Turnaround Time"); 
		System.out.println("=======  ==========  ============  ===============");
		// Calculate total waiting time and total turn 
		// around time 
		for (int i = 0; i < n; i++) { 
			totalWaitingTime += waitingTime[i]; 
			totalTurnAroundTime += turnAroundTime[i]; 
			System.out.println(i + "\t\t" + burstTime[i] +"\t " + 
							waitingTime[i] +"\t\t " + turnAroundTime[i]); 
		} 
	
		System.out.println("\nAverage waiting time = " + 
						(float)totalWaitingTime / (float)n); 
		System.out.println("Average turnaround time = " + 
						(float)totalTurnAroundTime / (float)n); 
	} 
	
	// Driver Method to test your algorithm with a simple example
	public static void main(String[] args) 
	{
		// Burst time of processes. The array index is the process ID.
		int burstTime[] = {5, 15, 4, 3}; 

		// Time quantum 
		int quantum = 3;
		
		printAvgTimes(burstTime, quantum); 
	} 
} 
