package datastructures;

/*
 * Mitchell Ludwig
 * #10015370
 * Word and Position Array class
 */

public class WordPosPair {
	private String	_sWord; // The word
	private int[]	_iaPos; // The positions where the word appears in the text
	
	// _iaPos format: {n (# of occurrences),occurrence #1,...,occurrence #n}
	
	// Constructors
	// Initializes the word to be empty, and the pos list to be empty
	public WordPosPair() {
		_sWord = "";
		_iaPos = null;
	}
	
	// Initializes word to a string, and _iaPos to a position
	public WordPosPair(final String sWord, final int lPos) {
		_sWord = sWord;
		_iaPos = new int[2];
		_iaPos[0] = 1;
		_iaPos[1] = lPos;
	}
	
	// Getters
	public String GetWord() {
		return _sWord;
	}
	
	public int[] GetPos() {
		return _iaPos;
	}
	
	public int GetPosCount() {
		return _iaPos[0];
	}
	
	// Setters
	public void SetWord(final String sWord) {
		_sWord = sWord;
	}
	
	public void SetPos(final int[] llPos) {
		_iaPos = llPos;
	}
	
	// Adds a new position to the position list
	public void AddPos(final int lPos) {
		// Make a new array of size+1
		final int[] iaNew = new int[_iaPos.length + 1];
		// Copy the old array into the new array
		for (int i = 0; i < _iaPos.length; i++) {
			iaNew[i] = _iaPos[i];
		}
		// Bump up the pos count
		iaNew[0] = iaNew[0] + 1;
		// Insert the new pos
		iaNew[_iaPos.length] = lPos;
		// And save it as the new position array
		_iaPos = iaNew;
	}
}
