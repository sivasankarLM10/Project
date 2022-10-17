package sample;

public class driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack stack = new Stack(5);
		stack.pushElement(10);
		stack.pushElement(20);
		stack.pushElement(30);
		stack.pushElement(40);
		stack.pushElement(50);
		System.out.println("Size of Stack is "+stack.size());
		stack.popElement();
		System.out.println("After poping");
		stack.printStack();
		System.out.println("Size of stack after poping is " + stack.size());

	}

}
