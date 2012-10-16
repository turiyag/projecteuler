package extras;

/*
 * Mitchell Ludwig
 * #10015370
 * Binary tree node class
 */

public class BTNode {
	protected Object	_oData;	// Contains the data
	protected BTNode	_nLeft;	// Pointer to the next node
	protected BTNode	_nRight;	// Pointer to the previous node
	protected BTNode	_nParent;	// Pointer to the parent node
									
	// Constructor
	// Make a lonely new node with no friends, with no data
	public BTNode() {
		_nLeft = null;
		_oData = null;
		_nRight = null;
		_nParent = null;
	}
	
	// Make a lonely new node with no friends, with the specified data
	public BTNode(final Object oData) {
		_nLeft = null;
		_oData = oData;
		_nRight = null;
		_nParent = null;
	}
	
	// Constructor
	// Make a lonely new node with no friends, with the specified data
	public BTNode(final Object oData, final BTNode nParent) {
		_nLeft = null;
		_oData = oData;
		_nRight = null;
		_nParent = nParent;
	}
	
	// Constructor
	// Make a lonely new node with no friends, with the specified data
	public BTNode(final Object oData, final BTNode nParent, final BTNode nLeft, final BTNode nRight) {
		_nLeft = nLeft;
		_oData = oData;
		_nRight = nRight;
		_nParent = nParent;
	}
	
	// Getters
	public Object GetElem() {
		return _oData;
	}
	
	public BTNode GetLeft() {
		return _nLeft;
	}
	
	public BTNode GetRight() {
		return _nRight;
	}
	
	public BTNode GetParent() {
		return _nParent;
	}
	
	// Setters
	public void SetElem(final Object oData) {
		_oData = oData;
	}
	
	public void SetLeft(final BTNode nLeft) {
		_nLeft = nLeft;
	}
	
	public void SetRight(final BTNode nRight) {
		_nRight = nRight;
	}
	
	public void SetParent(final BTNode nParent) {
		_nParent = nParent;
	}
}
