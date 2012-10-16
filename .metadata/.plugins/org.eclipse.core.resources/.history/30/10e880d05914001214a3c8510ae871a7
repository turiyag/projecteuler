package extras;

/**
 * Mitchell Ludwig #10015370
 */

public class Sorter {
	
	// Constructor
	public Sorter() {
	}
	
	/**
	 * Bubblesort: Sorts an integer array
	 * 
	 * @param iaToSort
	 *            The array to sort
	 * @return BblSort({1,6,3,8}) returns {1,3,6,8}
	 */
	static public int[] BblSort(final int[] iaToSort) {
		final int[] iaNewToSort = iaToSort.clone();
		Boolean bSwapped; // Allows for early exit
		int i = 0;
		int iSwap; // For swapping
		int iEnd; // For efficiency
		
		iEnd = iaNewToSort.length; // Initialize iEnd so that the loop runs
									// through the entire array
		// The loop doesn't need to run through the entire array every time
		// because the last element will run to the end of the array every time
		// and therefore it is sorted. This changed the runtime from:
		// T=n^2
		// into
		// T=(n^2-n)/2
		do {
			bSwapped = false; // For early exit
			iEnd--;
			// Loop through the unsorted portion of the list
			for (i = 0; i < iEnd; i++) {
				// If they need to be swapped
				if (iaNewToSort[i] > iaNewToSort[i + 1]) {
					// Swap them
					iSwap = iaNewToSort[i];
					iaNewToSort[i] = iaNewToSort[i + 1];
					iaNewToSort[i + 1] = iSwap;
					// And mark that the array is not sorted yet
					bSwapped = true;
				}
			}
			
		} while (bSwapped); // Early exit if we didn't swap anything on a run
							// through
		// Return the sorted string
		return iaNewToSort;
	}
	
	// Bubblesort
	// Sorts a string into anagram form
	// BblSort("fdcbea") returns "abcdef"
	static public String BblSort(final String sToSort) {
		Boolean bSwapped; // Allows for early exit
		int i = 0;
		final char caWord[] = sToSort.toCharArray(); // Character arrays are
														// easier to work with
		char cSwap; // For swapping
		int iEnd; // For efficiency
		
		String sRet; // Return value
		
		iEnd = sToSort.length(); // Initialize iEnd so that the loop runs
									// through the entire array
		// The loop doesn't need to run through the entire array every time
		// because the last element will run to the end of the array every time
		// and therefore it is sorted. This changed the runtime from:
		// T=n^2
		// into
		// T=(n^2-n)/2
		do {
			bSwapped = false; // For early exit
			iEnd--;
			// Loop through the unsorted portion of the list
			for (i = 0; i < iEnd; i++) {
				// If they need to be swapped
				if (caWord[i] > caWord[i + 1]) {
					// Swap them
					cSwap = caWord[i];
					caWord[i] = caWord[i + 1];
					caWord[i + 1] = cSwap;
					// And mark that the array is not sorted yet
					bSwapped = true;
				}
			}
			
		} while (bSwapped); // Early exit if we didn't swap anything on a run
							// through
		// Return the sorted string
		sRet = new String(caWord);
		return sRet;
	}
	
	// BubbleSort
	// Sorts an array of words
	// BblSort{"abc","abb","zzz","abd"} returns {"abb","abc","abd","zzz"}
	static public String[] BblSort(final String saToSort[]) {
		
		Boolean bSwapped;
		int i;
		final String[] saWords = saToSort.clone();
		String sSwap;
		int iEnd;
		
		iEnd = saWords.length; // Initialize iEnd so that the loop runs through
								// the entire array
		// The loop doesn't need to run through the entire array every time
		// because the last element will run to the end of the array every time
		// and therefore it is sorted. This changed the runtime from:
		// T=n^2
		// into
		// T=(n^2-n)/2
		do {
			bSwapped = false; // For early exit
			iEnd--;
			// Loop through the unsorted portion of the list
			for (i = 0; i < iEnd; i++) {
				// If they need to be swapped
				if (saWords[i].compareTo(saWords[i + 1]) > 0) {
					// Swap them
					sSwap = saWords[i];
					saWords[i] = saWords[i + 1];
					saWords[i + 1] = sSwap;
					// And mark that the array is not sorted yet
					bSwapped = true;
				}
			}
		} while (bSwapped);// Early exit if we didn't swap anything on a run
							// through
		// Return the sorted string
		return saWords;
	}
	
	// Bubblesort
	// Sorts string pairs by their first value
	// BblSort({"aaa","zing"},{"d","blah"},{"b","pow"}) returns
	// {"aaa","zing"},{"b","pow"},{"d","blah"}
	static public StrPair[] BblSort(final StrPair spaToSort[]) {
		Boolean bSwapped;
		int i;
		final StrPair[] spaWords = spaToSort.clone();
		StrPair spSwap;
		int iEnd;
		
		iEnd = spaWords.length;
		do {
			bSwapped = false;
			iEnd--;
			for (i = 0; i < iEnd; i++) {
				if (spaWords[i].compare(spaWords[i + 1]) < 0) {
					spSwap = spaWords[i];
					spaWords[i] = spaWords[i + 1];
					spaWords[i + 1] = spSwap;
					bSwapped = true;
				}
			}
		} while (bSwapped);
		return spaWords;
	}
	
	// QuickSort public function
	// Sorts pairs of strings by their first value
	// QwkSort({"aaa","zing"},{"d","blah"},{"b","pow"}) returns
	// {"aaa","zing"},{"b","pow"},{"d","blah"}
	static public StrPair[] QwkSort(final StrPair spaToSort[]) {
		// Copy to a new array, don't mess with the original
		final StrPair spaCopy[] = spaToSort.clone();
		// Invoke the true QuickSort to sort the copy
		QSControl(spaCopy, 0, spaCopy.length - 1);
		// Return the copy
		return spaCopy;
	}
	
	// QuickSort
	// Sorts an array of string pairs by the first element
	// QwkSort({"aaa","zing"},{"d","blah"},{"b","pow"}) returns nothing, but
	// directly sorts the input array into
	// {"aaa","zing"},{"b","pow"},{"d","blah"}
	// NOTE: This function is private to the class, and is indirectly invoked by
	// the public function QwkSort
	static private void QSControl(final StrPair spaToSort[], final int iLeft, final int iRight) {
		int iIndex;
		// If it makes sense to keep sorting
		if (iLeft < iRight) {
			// Split a section of the input array into two sides
			iIndex = QSSplit(spaToSort, iLeft, iLeft, iRight);
			// Sort the side that contains elements less than the pivot
			QSControl(spaToSort, iLeft, iIndex - 1);
			// Sort the side that contains elements greater than the pivot
			QSControl(spaToSort, iIndex, iRight);
		}
	}
	
	// QuickSort pivot split operation
	// Sorts a section the input array into the form (less than
	// pivot)+pivot+(more than pivot)
	// and returns the pivot index
	// NOTE: This function is private to the class, and is indirectly invoked by
	// the public function QSControl
	static private int QSSplit(final StrPair spaToSort[], final int iIndex, final int iLeft, final int iRight) {
		// Set the pivot value
		final StrPair spPivot = spaToSort[iIndex];
		StrPair spSwap; // For swapping
		int i; // Tracks the left side of the operation
		int j; // Tracks the right side of the operation
		
		i = iLeft; // Start at the left side, to move right
		j = iRight; // and the right side, to move left
		
		while (i <= j) {
			while (spaToSort[i].compare(spPivot) > 0) {
				// Set i such that spaToSort[i] is > spPivot
				i++;
			}
			while (spaToSort[j].compare(spPivot) < 0) {
				// Set i such that spaToSort[i] is < spPivot
				j--;
			}
			if (i <= j) {
				// Once two values to swap are found, swap them
				spSwap = spaToSort[i];
				spaToSort[i] = spaToSort[j];
				spaToSort[j] = spSwap;
				// And move the indices inward
				i++;
				j--;
			}
		}
		// Return the position
		return i;
	}
	
	// This ends the section of my code which was built for the project
	// In the following section, I will implement MergeSort
	// so that I better understand how it works
	
	// ...
	
	// Which isn't to say that I wouldn't like bonus marks :D
	
	// MergeSort public function
	// Accessible publicly, this returns the sorted version of the input string,
	// without directly editing the input
	// ex. MergeSort("mergesort") returns "eegmorrst"
	static public String MergeSort(final String sToSort) {
		final char[] caCopy = sToSort.toCharArray(); // Copy the string into a
														// char[]
		MergeSort(caCopy, 0, sToSort.length() - 1); // Sort the char[]
		// Return the resulting string
		final String sRet = new String(caCopy);
		return sRet;
	}
	
	// MergeSort controller
	// Not accessible publicly, this performs a recursive MergeSort on the input
	// char[]
	static private void MergeSort(final char[] caToSort, final int iLeft, final int iRight) {
		final int iLen = iRight - iLeft + 1; // Calculate length
		if (iLen == 1) {
			return; // If the length is 1, it must be sorted
		} else {
			// Otherwise, make sure both halves are sorted
			MergeSort(caToSort, iLeft, iLeft + iLen / 2 - 1);
			MergeSort(caToSort, iLeft + iLen / 2, iRight);
			// Then combine the halves
			MSMergeSorted(caToSort, iLeft, iRight);
		}
	}
	
	// MergeSort
	// Not publicly accessible, this function takes two sorted input arrays, and
	// turns them into one big sorted one
	// Used in MergeSort controller
	// ex. MSMergeSorter("acegbdfh",0,7) changes it to "abcdefgh"
	static private void MSMergeSorted(final char[] caLetters, final int iLeft, final int iRight) {
		final int iLen = iRight - iLeft + 1; // Length of the entire section to
												// be merged
		int i = iLeft; // Beginning of the left section
		int j = iLen / 2 + iLeft; // Beginning of the right section
		int k = 0; // Index of a character in caTemp
		
		// Start a new array, to hold the result temporarily
		final char[] caTemp = new char[iLen];
		
		// Fill the temporary array with the sorted elements
		
		// Loop through every character
		for (k = 0; k < iLen; k++) {
			// If i has moved past the end of the left section
			if (i >= iLeft + iLen / 2) {
				// Use j
				j++;
				caTemp[k] = caLetters[j - 1];
			}
			// If j has moved past the end of the right section
			else if (j > iRight) {
				// Use i
				i++;
				caTemp[k] = caLetters[i - 1];
			}
			// If it hasn't moved past the end of either section
			else {
				// If the letter at i is earlier in the alphabet than the letter
				// at j
				if (caLetters[i] < caLetters[j]) {
					// Use i
					i++;
					caTemp[k] = caLetters[i - 1];
				}
				// If the letter at i is later in the alphabet than the letter
				// at j
				else {
					// Use j
					j++;
					caTemp[k] = caLetters[j - 1];
				}
			}
		}
		
		// Copy the contents of the temporary array into the input array
		for (k = 0; k < iLen; k++) {
			caLetters[k + iLeft] = caTemp[k];
		}
		
	}
	
}
