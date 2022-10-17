package sample1;

import java.util.Scanner;
import java.util.Stack;

public class postFixEval {
	
	static boolean isOperator(String result) {
		if(result.equals("+") || result.equals("-") || result.equals("*") || result.equals("/") || result.equals("%")) {
			return true;
		}
		else {
			return false;
		}	
	}
	
	public static void evalExp(String exp[],int len) {
		Stack<Integer> stk = new Stack<>();
		String result;
		for(int i=0;i<len;i++) {
			result = exp[i];			
			if(isOperator(result) == true) {
				int op1 = stk.pop();
				int op2 = stk.pop();
				switch(result) {
				case "+":
					int add=op2+op1;
					stk.push(add);
					break;
					
				case "-":
					int sub= op2-op1;
					stk.push(sub);
					break;
					
				case "*":
					int mult= op2*op1;
					stk.push(mult);
					break;
					
				case "/":
					int div= op2/op1;
					stk.push(div);
					break;
					
				case "%":
					int rem= op2 %op1;
					stk.push(rem);
					break;
				}
				
			}
			else {
				String operand = exp[i];
				int var = Integer.parseInt(operand);
				stk.push(var);
			}
		}
		System.out.print("Final Answer is"+stk.pop());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter length of Infix exprsn");
		int len = sc.nextInt();
		String arr[] = new String[10];
		for(int i=0;i<len;i++) {
			arr[i] = sc.next();
		}

		evalExp(arr,len);
		
	}

}
