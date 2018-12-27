import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

class LRU
{ 
	// Method to find page faults using indexes 
	static int pageFaults(int pages[], int capacity) 
	{
		/*
		 * Put your code in here!
		 */

		int result = 0;

		Set<Integer> uniqNums = new TreeSet<>();

		for (int p : pages) {
			uniqNums.add(p);
		}

		int[] memoryState = new int[capacity];
		// Fill all the array positions with -1,
		// marking all the memory positions as empty.
		Arrays.fill(memoryState, -1);

		int[] useHistory = new int[uniqNums.size()];


		int counter = 1;
		
		for (int i = memoryState.length; i < pages.length; i++) {





		}






	} 
	
	// Driver Method to test your algorithm with a simple example
	public static void main(String args[]) 
	{
		/*
		 * This is an array that holds the reference string for all
		 * page requests.
		 */
		int pages[] = {5, 1, 0, 3, 2, 3, 0, 4, 2, 3, 0, 3, 5, 2}; 
		
		// This is the number of available page frames
		int memoryCapacity = 3; 
		
		int faults = pageFaults(pages, memoryCapacity);
		System.out.println(faults);
	} 
} 
