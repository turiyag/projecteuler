package datastructures;

/**
 * Mitchell Ludwig #10015370
 */

// Generic doubly linked list node
public class ListNode {
	private Object		_oData;	// Contains the data
	private ListNode	_lnNext;	// Pointer to the next node
	private ListNode	_lnPrev;	// Pointer to the previous node
									
	// Constructor
	// Make a lonely new node with no friends, with the specified data
	public ListNode(final Object oData) {
		_lnPrev = null;
		_oData = oData;
		_lnNext = null;
	}
	
	// Constructor
	// Make a new node with one friend, with the specified data
	public ListNode(final Object oData, final ListNode lnNext) {
		_lnPrev = null;
		_oData = oData;
		_lnNext = lnNext;
	}
	
	// Constructor
	// Make a popular new node with two friends, with the specified data
	public ListNode(final ListNode lnPrev, final Object oData, final ListNode lnNext) {
		_lnPrev = lnPrev;
		_oData = oData;
		_lnNext = lnNext;
	}
	
	// Getters
	public Object GetElem() {
		return _oData;
	}
	
	public ListNode GetNext() {
		return _lnNext;
	}
	
	public ListNode GetPrev() {
		return _lnPrev;
	}
	
	// Setters
	public void SetElem(final Object oData) {
		_oData = oData;
	}
	
	public void setNext(final ListNode lnNext) {
		_lnNext = lnNext;
	}
	
	public void setPrev(final ListNode lnPrev) {
		_lnPrev = lnPrev;
	}
	
}
