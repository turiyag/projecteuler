package extras;

/*
 * Mitchell Ludwig
 * #10015370
 * Binary tree class
 */
public class BinaryTree {
	protected BTNode		_nRoot;		// Tracks the root node
	protected int			_lHeight;		// Tracks the tree height
	protected int			_lNodeCount;	// Tracks the number of nodes in the
											// tree, also tracks the number of
											// unique words
	protected int			_lWordCount;	// Tracks the number of words in the
											// file
	protected WordPosPair	_wpMostCommon;	// Tracks the most often occurring
											// word
											
	// Constructors
	
	// Empty tree
	public BinaryTree() {
		// Make the head and tail point to nothing and make the length 0
		_nRoot = null;
		_lHeight = 0;
		_lNodeCount = 0;
	}
	
	// Tree with one element
	public BinaryTree(final Object oElem) {
		_nRoot = new BTNode(oElem);
		_lHeight = 1;
		_lNodeCount = 1;
	}
	
	// Tree with root
	public BinaryTree(final BTNode nRoot) {
		// Make the head and tail point to nothing and make the length 0
		_nRoot = nRoot;
		_lHeight = CalcHeight();
		_lNodeCount = CalcNodeCount();
	}
	
	// Getters
	public BTNode GetRoot() {
		return _nRoot;
	}
	
	public int GetHeight() {
		return _lHeight;
	}
	
	public int GetNodeCount() {
		return _lNodeCount;
	}
	
	public int GetWordCount() {
		return _lWordCount;
	}
	
	public WordPosPair GetMostCommonWord() {
		return _wpMostCommon;
	}
	
	// Setter
	public void SetRoot(final BTNode nRoot) {
		// Set the root node, and calculate the new height and node count
		_nRoot = nRoot;
		_lHeight = CalcHeight();
		_lNodeCount = CalcNodeCount();
	}
	
	// Adds a string to a string type tree
	// Returns true if string already exists in tree
	// NOTE: This method is not used, see AddWordPos below
	public boolean AddString(final String sData) {
		// Start at the root
		BTNode nParent = GetRoot();
		BTNode nNew;
		String sCurr = (String) nParent.GetElem();
		int iDepth = 1; // Used to ensure that the _lHeight is kept up to date
		
		if (_nRoot == null) {
			// If the tree is empty, make it a 1 node tree
			nNew = new BTNode(sData);
			SetRoot(nNew);
			// The string was not already in the tree, return false
			return false;
		}
		
		// Don't stop
		while (true) {
			// Keep track of the current depth
			iDepth += 1;
			if (sCurr.compareTo(sData) < 0) {
				// If sCurr<sData then go right
				if (nParent.GetRight() != null) {
					// If the right node exists already, enter it and loop
					nParent = nParent.GetRight();
					sCurr = (String) nParent.GetElem();
				} else {
					// If it does not exist already
					// Make a new node
					nNew = new BTNode(sData, nParent);
					// Add the new node to the tree
					nParent.SetRight(nNew);
					// Up the node count
					_lNodeCount += 1;
					// If we have traversed deep enough, we could have just
					// increased the height, in which case, _lHeight must be
					// updated
					if (iDepth > _lHeight) {
						_lHeight = iDepth;
					}
					// The string was not already in the tree, return false
					return false;
				}
			} else if (sCurr.compareTo(sData) > 0) {
				// If sCurr>sData then go left
				if (nParent.GetLeft() != null) {
					// If the left node exists already, enter it and loop
					nParent = nParent.GetLeft();
					sCurr = (String) nParent.GetElem();
				} else {
					// If it does not exist already
					// Make a new node
					nNew = new BTNode(sData, nParent);
					// Add the new node to the tree
					nParent.SetLeft(nNew);
					// Up the node count
					_lNodeCount += 1;
					// If we have traversed deep enough, we could have just
					// increased the height, in which case, _lHeight must be
					// updated
					if (iDepth > _lHeight) {
						_lHeight = iDepth;
					}
					// The string was not already in the tree, return false
					return false;
				}
			} else {
				// The string was already in the tree, return true
				return true;
			}
		}
	}
	
	// Adds a word to a word type tree
	// Returns true if word already exists in tree
	// NOTE: This function is used in the place of AddString
	public boolean AddWordPos(final String sData, final int lPos) {
		// Start at the root
		BTNode nParent = GetRoot();
		WordPosPair wCurr;
		String sCurr;
		int iDepth = 1; // Used to ensure that the _lHeight is kept up to date
		
		if (nParent == null) {
			// If the tree is empty, make it a 1 node tree
			final WordPosPair wpNew = new WordPosPair(sData, lPos);
			final BTNode nNew = new BTNode(wpNew);
			SetRoot(nNew);
			_wpMostCommon = wpNew;
			_lWordCount = 1;
			// The string was not already in the tree, return false
			return false;
		}
		// Up the word count
		_lWordCount += 1;
		// Don't stop
		while (true) {
			// Keep track of the depth
			iDepth += 1;
			// Load the current word and positions
			wCurr = (WordPosPair) nParent.GetElem();
			sCurr = wCurr.GetWord();
			if (sCurr.compareTo(sData) < 0) {
				// If sCurr<sData then go right
				if (nParent.GetRight() != null) {
					// If it already exists, enter it and loop
					nParent = nParent.GetRight();
				} else {
					// If it does not exist then
					// Make a new node
					final BTNode nNew = new BTNode(new WordPosPair(sData, lPos), nParent);
					// Add the node to the tree
					nParent.SetRight(nNew);
					// Up the node count
					_lNodeCount += 1;
					// If we have traversed deep enough, we could have just
					// increased the height, in which case, _lHeight must be
					// updated
					if (iDepth > _lHeight) {
						_lHeight = iDepth;
					}
					// The string was not already in the tree, return false
					return false;
				}
			} else if (sCurr.compareTo(sData) > 0) {
				// If sCurr>sData then go left
				if (nParent.GetLeft() != null) {
					// If it already exists, enter it and loop
					nParent = nParent.GetLeft();
				} else {
					// If it does not exist then
					// Make a new node
					final BTNode nNew = new BTNode(new WordPosPair(sData, lPos), nParent);
					// Add the node to the tree
					nParent.SetLeft(nNew);
					// Up the node count
					_lNodeCount += 1;
					// If we have traversed deep enough, we could have just
					// increased the height, in which case, _lHeight must be
					// updated
					if (iDepth > _lHeight) {
						_lHeight = iDepth;
					}
					// The string was not already in the tree, return false
					return false;
				}
			} else {
				// If the word already exists in the tree then
				// Add the new position to the position array
				wCurr.AddPos(lPos);
				// See if it is the new most common word
				if (wCurr.GetPosCount() > _wpMostCommon.GetPosCount()) {
					_wpMostCommon = wCurr;
				}
				// The string was already in the tree, return true
				return true;
			}
		}
	}
	
	// Given a word as input, this function finds it in the tree, and returns
	// the WordPosPair associated to that word
	public WordPosPair FindWord(final String sData) {
		// Start at the root
		BTNode nParent = GetRoot();
		WordPosPair wCurr;
		String sCurr;
		
		if (nParent == null) {
			// It's not going to be in the tree if the tree is empty
			return null;
		}
		// Don't stop
		while (true) {
			// Load the word information
			wCurr = (WordPosPair) nParent.GetElem();
			sCurr = wCurr.GetWord();
			if (sCurr.compareTo(sData) < 0) {
				// If sCurr<sData then go right
				if (nParent.GetRight() != null) {
					// If the node exists follow it and loop
					nParent = nParent.GetRight();
				} else {
					// If the word is not in the tree, return null
					return null;
				}
			} else if (sCurr.compareTo(sData) > 0) {
				if (nParent.GetLeft() != null) {
					// If the node exists follow it and loop
					nParent = nParent.GetLeft();
				} else {
					// If the word is not in the tree, return null
					return null;
				}
			} else {
				// If the word is in the tree, return wCurr
				return wCurr;
			}
		}
	}
	
	// Calculate the full height of the tree
	public int CalcHeight() {
		return CalcHeight(_nRoot);
	}
	
	// Calculate the height of the subtree of a certain node
	public int CalcHeight(final BTNode nHead) {
		if (nHead == null) {
			// If nHead does not exist, then there is just as much tree
			// as there is spoon.
			// Reference to The Matrix. Watch it before calling yourself
			// a computer scientist
			return 0;
		}
		if (nHead.GetLeft() == null) {
			if (nHead.GetRight() == null) {
				// If there are no children the height is 1
				return 1;
			} else {
				// If there is a right child, and no left child,
				// then the height is the height of the subtree of the
				// right child + 1
				return CalcHeight(nHead.GetRight()) + 1;
			}
		} else {
			if (nHead.GetRight() == null) {
				// If there is a left child, and no right child,
				// then the height is the height of the subtree of the
				// left child + 1
				return CalcHeight(nHead.GetLeft()) + 1;
			} else {
				// If there are two children, then the height is the height
				// of the largest subtree + 1
				final int iHLeft = CalcHeight(nHead.GetLeft());
				final int iHRight = CalcHeight(nHead.GetRight());
				if (iHLeft >= iHRight) {
					return iHLeft + 1;
				} else {
					return iHRight + 1;
				}
			}
		}
	}
	
	// Calculate the number of nodes in the whole tree
	public int CalcNodeCount() {
		return CalcNodeCount(_nRoot);
	}
	
	// Calculate the number of nodes in the subtree of a certain node, including
	// the subtree root
	public int CalcNodeCount(final BTNode nHead) {
		if (nHead == null) {
			// If nHead does not exist, then there is just as much tree
			// as there is spoon.
			// Reference to The Matrix. Watch it before calling yourself
			// a computer scientist
			return 0;
		}
		if (nHead.GetLeft() == null) {
			if (nHead.GetRight() == null) {
				// If there are no children the count is 1
				return 1;
			} else {
				// If there is a right child, and no left child,
				// then the count is the count of the subtree of the
				// right child + 1
				return CalcNodeCount(nHead.GetRight()) + 1;
			}
		} else {
			if (nHead.GetRight() == null) {
				// If there is a left child, and no right child,
				// then the count is the count of the subtree of the
				// left child + 1
				return CalcNodeCount(nHead.GetLeft()) + 1;
			} else {
				// If there are two children, then the count is the count
				// of both subtrees + 1
				return CalcNodeCount(nHead.GetLeft()) + CalcNodeCount(nHead.GetRight()) + 1;
			}
		}
	}
}
