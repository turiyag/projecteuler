/**
 * Mitchell Ludwig
 * #10015370
 */


public class LinkList {
	private ListNode _lnHead; //Tracks the first element in the list
	private ListNode _lnTail; //Tracks the last element in the list
	private int _len; //Tracks the number of items in the list
	
	//Empty list constructor
	public LinkList(){
		//Make the head and tail point to nothing and make the length 0
		_lnHead=null;
		_lnTail=null;
		_len=0;
	}

	//Single element constructor
	public LinkList(Object oElem){
		//Set the head to a new node containing the specified element
		_lnHead=new ListNode(oElem);
		//In a 1 element list, the head is the tail
		_lnTail=_lnHead;
		//Set the length to 1
		_len=1;
	}
	
	//Length getter
	public int length(){
		return _len;
	}

	//Head getter
	public ListNode GetHead(){
		return _lnHead;
	}
	
	//Adds a new node to the end of the list
	public void add(Object oElem){
		if (_len>0){
		//If the list is not empty
			//Then increase the length by 1
			_len=_len+1;
			//Insert a new node after the last node
			_lnTail.setNext(new ListNode(_lnTail,oElem,null));
			//And set the tail to this new node
			_lnTail=_lnTail.GetNext();
		} else {
		//If the list is empty
			//Set the length to 1
			_len=1;
			//Add the first node
			_lnHead=new ListNode(oElem);
			//In a 1 element list, the head is the tail
			_lnTail=_lnHead;
		}
	}

	//Remove the last node from the list
	public void remove(){
		if (_len>0){
		//If the list is not empty
			//Decrement the length
			_len=_len-1;
			//Set the tail to be equal to the 2nd last element
			_lnTail=_lnTail.GetPrev();
			//Delete the last element
			_lnTail.setNext(null);			
		}
	}
	
	//Empty the list of all elements
	public void clear(){
		//Set the length to 0
		_len=0;
		//Clear the head and tail, and let the garbage collector take care of the rest
		_lnHead=null;
		_lnTail=null;
	}
	
	//Output the entire list into an object array
	public Object[] toArray(){
		//Define an array large enough to hold the entire list
		Object[] oaRet=new Object[_len];
		//Set lnNode to the first element
		ListNode lnNode=GetHead();
		//Loop through every node and copy every element to the array
		for(int i=0;i<_len;i++){
			oaRet[i]=lnNode.GetElem();
			lnNode=lnNode.GetNext();
		}
		//Return the array
		return oaRet;
	}
	
	//Output the entire list into a string array
	public String[] toStringArray(){
		//Define an array large enough to hold the entire list
		String[] saRet=new String[_len];
		//Set lnNode to the first element
		ListNode lnNode=GetHead();
		//Loop through every node and copy every element to the array
		for(int i=0;i<_len;i++){
			saRet[i]=(String)lnNode.GetElem();
			lnNode=lnNode.GetNext();
		}
		//Return the array
		return saRet;
	}
}


