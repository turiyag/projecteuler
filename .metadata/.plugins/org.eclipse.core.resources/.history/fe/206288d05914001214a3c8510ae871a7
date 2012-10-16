package extras;

/*
 * Mitchell Ludwig
 * #10015370
 * Binary tree node class
 */

public class AVLNode {
	private final Object	_oData;	// Stored object data
	private AVLNode			_nLeft;	// Pointer to the next node
	private AVLNode			_nRight;	// Pointer to the previous node
	private AVLNode			_nParent;	// Pointer to the parent node
	private int				_iHeight;	// Height of the subtree starting at
										// this node
										
	// Constructors
	/**
	 * Make a lonely new node with no friends, with the specified data
	 */
	public AVLNode(final Object oData) {
		_nLeft = null;
		_oData = oData;
		_nRight = null;
		_nParent = null;
		_iHeight = 1;
	}
	
	/**
	 * Make a lonely new node with no friends, with a parent, with the specified
	 * data
	 */
	public AVLNode(final Object oData, final AVLNode nParent) {
		_nLeft = null;
		_oData = oData;
		_nRight = null;
		_nParent = nParent;
		_iHeight = 1;
	}
	
	// Getters
	/**
	 * Gets the left child
	 */
	public AVLNode GetLeft() {
		return _nLeft;
	}
	
	/**
	 * Gets the right child
	 */
	public AVLNode GetRight() {
		return _nRight;
	}
	
	/**
	 * Gets the parent of the node
	 */
	public AVLNode GetParent() {
		return _nParent;
	}
	
	/**
	 * Gets the object data
	 */
	public Object GetElem() {
		return _oData;
	}
	
	/**
	 * Gets the height of the node
	 */
	public int GetHeight() {
		return _iHeight;
	}
	
	// Setters
	/**
	 * Sets the Left child
	 * 
	 * @param nLeft
	 *            The new left child
	 */
	public void SetLeft(final AVLNode nLeft) {
		_nLeft = nLeft;
		SetHeight();
	}
	
	/**
	 * Sets the Right child
	 * 
	 * @param nRight
	 *            The new right child
	 */
	public void SetRight(final AVLNode nRight) {
		_nRight = nRight;
		SetHeight();
	}
	
	/**
	 * Sets the Parent
	 * 
	 * @param nParent
	 *            The new Parent
	 */
	public void SetParent(final AVLNode nParent) {
		_nParent = nParent;
	}
	
	/**
	 * Sets the height
	 * 
	 * @param iHeight
	 *            The new height
	 */
	public void SetHeight(final int iHeight) {
		_iHeight = iHeight;
	}
	
	/**
	 * Sets the height to max(leftheight,rightheight)+1
	 */
	public void SetHeight() {
		int iLeft;
		int iRight;
		if (_nLeft == null) {
			iLeft = 0;
		} else {
			iLeft = _nLeft.GetHeight();
		}
		if (_nRight == null) {
			iRight = 0;
		} else {
			iRight = _nRight.GetHeight();
		}
		_iHeight = Math.max(iLeft, iRight) + 1;
	}
	
	// Methods
	/**
	 * Calculates the balance factor based on the heights of the two child
	 * subtrees
	 */
	public int CalcBalFactor() {
		int iLeft;
		int iRight;
		if (_nLeft == null) {
			iLeft = 0;
		} else {
			iLeft = _nLeft.GetHeight();
		}
		if (_nRight == null) {
			iRight = 0;
		} else {
			iRight = _nRight.GetHeight();
		}
		return iLeft - iRight;
	}
}
