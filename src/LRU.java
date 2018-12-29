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

		int pageFaultsCount = 0;

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

		for (int p = 0; p < pages.length; p++) {

			boolean hit = false;

			// Check if a memory frame already contains the requested page
			for (int f = 0; f < memoryState.length; f++) {
				if (memoryState[f] == pages[p]) {
					// Found a frame that contains the requested page
					useHistory[pages[p]] = counter;
					counter += 1;
					hit = true;
					break;
				}
			}

			// If no memory frame contains the requested page
			if (!hit) {
				boolean storedPage = false;
				pageFaultsCount += 1;
				// First check whether there are any empty memory frames left to use
				// in order to store the requested page
				for (int f = 0; f < memoryState.length; f++) {
					if (memoryState[f] == -1) {
						// The frame is empty and can be used to store the requested page
						memoryState[f] = pages[p];
						useHistory[pages[p]] = counter;
						counter += 1;
						storedPage = true;
						break;
					}
				}

				// If no empty frames where found in the memory, the requested page
				// will be saved in the memory frame that contains the LRU page
				if (!storedPage) {
					// Find the LRU page currently stored in the memory

					int minPagePos = 0;
					int minUseValue = useHistory[memoryState[0]];

					for (int m = 0; m < memoryState.length; m++) {
						if (useHistory[memoryState[m]] < minUseValue) {
							minPagePos = m;
							minUseValue = useHistory[memoryState[m]];
						}
					}

					// replace the LRU page in memory with the page requested
					memoryState[minPagePos] = pages[p];

					useHistory[pages[p]] = counter;
					counter += 1;

				}
			}






		}




		return pageFaultsCount;

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
