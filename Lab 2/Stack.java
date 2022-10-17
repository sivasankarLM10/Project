package sample;

public class Stack {
	int arr[];
	int size;
	int top;
	Stack(int capacity){
		size = capacity;
		arr = new int[size];
		top = -1;
	}
	//Check if the Stack is full 
	public boolean isFull() {
		return(size-1==top);
	}
	
	//Push an element into Stack
	public void pushElement(int element) {
		if(isFull()) {
			System.out.println("Stack is full");
			System.exit(1);
		}
		top++;
		arr[top] = element;
		System.out.println("Element "+element+" is pushed");
	}
	
	//Check if Stack is empty
	public boolean isEmpty() {
		return(top==-1);
	}
	
	//Pop an element from stack
	public void popElement() {
		int delElement;
		if(isEmpty()) {
			System.out.println("Stack is Empty");
			System.exit(1);
		}
		delElement = arr[top] ;
		System.out.println("Element "+delElement+" is popped");
		top--;
	}
	
	//Print the size of Stack
	public int size() {
		return top+1;
	}
	
	//Print all the elements in the stack
	public void printStack() {
		System.out.println("Stack elements are:");
		for(int i=0;i<=top;i++) {
			System.out.print(arr[i]+" ");
			
		}
		System.out.println();
	}
}
