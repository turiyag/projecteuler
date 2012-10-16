package extras;

/*
 * Mitchell Ludwig
 * #10015370
 * Binary tree class
 */
public class AVLTree {
	private WordPosPair	_wpMostCommon;	// Tracks the most often occurring word
	private AVLNode		_nRoot;		// Tracks the root node
										
	// Constructors
	/**
	 * Empty tree
	 */
	public AVLTree() {
		// Make the head and tail point to nothing and make the length 0
		_nRoot = null;
	}
	
	/**
	 * Tree with one element
	 * 
	 * @param oElem
	 *            The element to put into the root node
	 */
	public AVLTree(final Object oElem) {
		_nRoot = new AVLNode(oElem);
	}
	
	/**
	 * Tree with root, the root can be the root of a full tree This constructor
	 * will calculate the height and node count automatically
	 * 
	 * @param nRoot
	 *            The root node
	 */
	public AVLTree(final AVLNode nRoot) {
		// Make the head and tail point to nothing and make the length 0
		_nRoot = nRoot;
		CalcNodeCount();
	}
	
	// Getters
	/**
	 * Returns the root node
	 */
	public AVLNode GetRoot() {
		return _nRoot;
	}
	
	/**
	 * Returns the height of the tree (the height of the root node)
	 * 
	 * @return The tree height
	 */
	public int GetHeight() {
		if (_nRoot != null) {
			return _nRoot.GetHeight();
		} else {
			return 0;
		}
	}
	
	/**
	 * Returns the most often used word
	 * 
	 * @return The most common word in the tree
	 */
	public WordPosPair GetMostCommon() {
		return _wpMostCommon;
	}
	
	// Setter
	/**
	 * Sets the root node
	 * 
	 * @param nRoot
	 *            The new root node
	 */
	public void SetRoot(final AVLNode nRoot) {
		// Set the root node, and calculate the new height and node count
		_nRoot = nRoot;
		CalcNodeCount();
	}
	
	/**
	 * Adds a word to a word type tree NOTE: This function is used in the place
	 * of AddString
	 * 
	 * @param sData
	 *            The string to add to the tree
	 * @return The node containing the newly added word
	 */
	public AVLNode AddWordPos(final String sData, final int lPos) {
		// Start at the root
		AVLNode nParent = GetRoot();
		WordPosPair wCurr;
		String sCurr;
		if (nParent == null) {
			// If the tree is empty, make it a 1 node tree
			final WordPosPair wpNew = new WordPosPair(sData, lPos);
			final AVLNode nNew = new AVLNode(wpNew);
			// Set the root, (also sets Height and Node Count)
			SetRoot(nNew);
			_wpMostCommon = wpNew;
			// The string was not already in the tree, return false
			return nNew;
		}
		// Don't stop
		while (true) {
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
					final AVLNode nNew = new AVLNode(new WordPosPair(sData, lPos), nParent);
					// Add the node to the tree
					nParent.SetRight(nNew);
					nParent.SetHeight();
					// Ensure an AVL Tree
					CheckAVL(nParent.GetParent());
					// The string was not already in the tree, return false
					return nNew;
				}
			} else if (sCurr.compareTo(sData) > 0) {
				// If sCurr>sData then go left
				if (nParent.GetLeft() != null) {
					// If it already exists, enter it and loop
					nParent = nParent.GetLeft();
				} else {
					// If it does not exist then
					// Make a new node
					final AVLNode nNew = new AVLNode(new WordPosPair(sData, lPos), nParent);
					// Add the node to the tree
					nParent.SetLeft(nNew);
					nParent.SetHeight();
					// Ensure an AVL Tree
					CheckAVL(nParent.GetParent());
					// The string was not already in the tree, return false
					return nNew;
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
				return nParent;
			}
		}
	}
	
	/**
	 * Given a word as input, this function finds it in the tree
	 * 
	 * @param sData
	 *            The word to search for in the tree
	 * @return The WordPosPair associated to that word, or null if not found
	 */
	public WordPosPair FindWord(final String sData) {
		// Start at the root
		AVLNode nParent = GetRoot();
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
	
	/**
	 * Calculate the full height of the tree
	 * 
	 * @return The height of the tree
	 */
	public int CalcHeight() {
		return CalcHeight(_nRoot);
	}
	
	/**
	 * Calculate the height of the subtree of a certain node
	 * 
	 * @param nHead
	 *            The top of the subtree
	 * @return The height of the subtree, including nHead
	 */
	public int CalcHeight(final AVLNode nHead) {
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
	
	/**
	 * Calculate the number of nodes in the whole tree
	 */
	public int CalcNodeCount() {
		return CalcNodeCount(_nRoot);
	}
	
	/**
	 * Calculate the number of nodes in the subtree of a certain node, including
	 * the subtree root
	 * 
	 * @param nHead
	 *            The node to count all subnodes of
	 * @return Returns the number of nodes in the subtree, including nHead
	 */
	public int CalcNodeCount(final AVLNode nHead) {
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
	
	/**
	 * Checks a node (before the node has the correct height) to see if any AVL
	 * rotations are needed to balance the tree If any rotation is needed, then
	 * CheckAVL will call LeftRot and/or RightRot to rebalance the tree NOTE:
	 * nGrand's height must not be set yet, CheckAVL will correct the height
	 * 
	 * @param nGrand
	 *            The node to test balance and set the height of
	 */
	public void CheckAVL(final AVLNode nGrand) {
		
		// An empty node can't have subtrees, or unbalanced subtrees, exit
		if (nGrand == null) {
			return;
		}
		
		final int iOldHeight = nGrand.GetHeight(); // Track the old height of
													// nGrand
		nGrand.SetHeight(); // Correct the height of nGrand
		
		AVLNode nParent;
		AVLNode nChild;
		if (nGrand.CalcBalFactor() <= -2) {
			// Right-heavy tree
			nParent = nGrand.GetRight();
			if (nParent.CalcBalFactor() == -1) {
				LeftRot(nGrand, nParent);
				if (iOldHeight != nParent.GetHeight()) {
					if (nParent.GetParent() != null) {
						CheckAVL(nParent.GetParent());
					}
				}
			} else {
				nChild = nParent.GetLeft();
				RightRot(nParent, nChild);
				LeftRot(nGrand, nChild);
				if (iOldHeight != nChild.GetHeight()) {
					if (nChild.GetParent() != null) {
						CheckAVL(nChild.GetParent());
					}
				}
			}
		} else if (nGrand.CalcBalFactor() >= 2) {
			nParent = nGrand.GetLeft();
			if (nParent.CalcBalFactor() == -1) {
				nChild = nParent.GetRight();
				LeftRot(nParent, nChild);
				RightRot(nGrand, nChild);
				if (iOldHeight != nChild.GetHeight()) {
					if (nChild.GetParent() != null) {
						CheckAVL(nChild.GetParent());
					}
				}
			} else {
				RightRot(nGrand, nParent);
				if (iOldHeight != nParent.GetHeight()) {
					if (nParent.GetParent() != null) {
						CheckAVL(nParent.GetParent());
					}
				}
			}
		} else {
			if (iOldHeight != nGrand.GetHeight()) {
				if (nGrand.GetParent() != null) {
					CheckAVL(nGrand.GetParent());
				}
			}
		}
	}
	
	/**
	 * AVL left rotation
	 * 
	 * @param nRoot
	 *            The current highest node
	 * @param nPivot
	 *            The node that replaces nRoot as the highest node
	 */
	private void LeftRot(final AVLNode nRoot, final AVLNode nPivot) {
		if (nRoot == _nRoot) {
			_nRoot = nPivot;
		}
		nRoot.SetRight(nPivot.GetLeft());
		if (nRoot.GetRight() != null) {
			nRoot.GetRight().SetParent(nRoot);
		}
		
		nPivot.SetLeft(nRoot);
		
		if (nRoot.GetParent() != null) {
			if (nRoot.GetParent().GetLeft() == nRoot) {
				nRoot.GetParent().SetLeft(nPivot);
			} else {
				nRoot.GetParent().SetRight(nPivot);
			}
		}
		nPivot.SetParent(nRoot.GetParent());
		nRoot.SetParent(nPivot);
	}
	
	/**
	 * AVL right rotation
	 * 
	 * @param nRoot
	 *            The current highest node
	 * @param nPivot
	 *            The node that replaces nRoot as the highest node
	 */
	private void RightRot(final AVLNode nRoot, final AVLNode nPivot) {
		if (nRoot == _nRoot) {
			_nRoot = nPivot;
		}
		nRoot.SetLeft(nPivot.GetRight());
		if (nRoot.GetLeft() != null) {
			nRoot.GetLeft().SetParent(nRoot);
		}
		nPivot.SetRight(nRoot);
		
		if (nRoot.GetParent() != null) {
			if (nRoot.GetParent().GetLeft() == nRoot) {
				nRoot.GetParent().SetLeft(nPivot);
			} else {
				nRoot.GetParent().SetRight(nPivot);
			}
		}
		nPivot.SetParent(nRoot.GetParent());
		
		nRoot.SetParent(nPivot);
		
	}
	
}
