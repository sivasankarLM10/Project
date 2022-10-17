package sample;

import java.util.Scanner;
import java.util.Stack;

public class balParanthesis {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the string: ");
		String str = sc.nextLine();
		Stack stk = new Stack<>();
		char element;
		int flag=0;
		
		for(int i=0;i<str.length();i++) {
			char result = str.charAt(i);
			if(result== '{' || result == '(' || result == '[') {
				stk.push(result);
			}
			
			else if(result == '}' || result ==')' || result == ']'){
					if(stk.isEmpty()==true) {
						flag = -1;
						break;
					}
					element = (char) stk.peek();
					if(element=='{' && result == '}' || 
					   element=='[' && result == ']' ||
					   element=='(' && result == ')' ) {
						stk.pop();
					}
					else {
						flag = -1;
						break;
					}
				}
		}
		
		if (flag == -1 || stk.isEmpty()!=true) {
			System.out.println("Not balanced paranthesis");
		}
		else if(stk.isEmpty()==true) {
			System.out.println("Balanced Paranthesis");
		}
		
		
	}

}
