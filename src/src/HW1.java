package src;
import java.util.*;
import java.util.regex.Pattern;
public class HW1 {
	public static int q;	
	//take out the last number
	public static int TakeOutLast(String s, Integer lastLen){
		while(lastLen>0 && Character.isDigit(s.charAt(lastLen))){
			lastLen--;
		}
		return lastLen;	
	}
	
	
	
	public Stack<String> Expression(String s){
		int i;
		String[] inOrder1 = Save.StoreInList(s);
			//store numbers into stack1 and operators into stack2, and clear stack2 afterwards
		Stack<String> stack1 = new Stack<String>();
		Stack<String> stack2 = new Stack<String>();
		for (i = 0; i < inOrder1.length; i++){
			//System.out.println(inOrder1[i]);
			if(inOrder1[i].charAt(0) >= '0' && inOrder1[i].charAt(0) <= '9'){
				stack1.push(inOrder1[i]);
			}
			else if(inOrder1[i].charAt(0) == '*'){
				stack2.push(inOrder1[i]);
			}
			else{
				while(!stack2.isEmpty() && stack2.lastElement().charAt(0) == '*'){
						stack1.push(stack2.pop());
				}
				stack2.push(inOrder1[i]);
			}	
			
		}
		while(!stack2.isEmpty()){		//push all the rest operators into stack1
			stack1.push(stack2.pop());
		}
		//for test
		System.out.println(stack1);
		return stack1;
}

	
	public static void main(String[] args){
		boolean flag = false;
		String s = Input.IO();
			//check if the formula is in the correct form
		do{//the loop of input
			
			flag = Check.Check1(s);	
		}while(!flag);

		//calculate the formula
		//check out if there's charater
		boolean flag1 = true;		//flag1=true if there's only digit in s
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) >= 'A' && s.charAt(i) <='z')
				flag1 = false;
		}
		System.out.println(s);
		if(flag1 == true){
			HW1 hw1 = new HW1();
			Stack postOrder = hw1.Expression(s);
			Integer result = Simplify.Simplify1(postOrder);
			System.out.println(result);
		}
		else{
			String s1 =" ";
			String[] a = null;
			boolean flag2 = false;//judge of simplify 
			boolean flag3 = true;//judge of assignment
			do
			{
				s1 = Input.IO();
				a = s1.split(" ");
				if(a[0].equals("!simplify"))
				{
					String temp = " ";//assignment string
					String value = " ";
					for(int i = 1;i <a.length;i++)
					{
						temp = a[i];
						if(temp.length() < 2)
						{
							flag3 = false;
							break;
						}
						value = temp.substring(2);
						//letter=digit && if has var which  does not exit
						if(!value.matches("[0-9]{1,}") || temp.charAt(1) != '=' || !Character.isLetter(temp.charAt(0)) || !s.contains(temp.substring(0,1))) flag3 = false;
					}
					if(flag3) flag2 = true;
					if(!flag2) System.out.println("wrong format!");
					flag3 = true;
				}
				else  System.out.println("wrong format!");
			}while(!flag2);
			//judge of assignment finish
			
			String temp = " ";
			String value = " ";
			for(int i = 1;i <a.length;i++)
			{
					temp = a[i];
					value = temp.substring(2);
					s = s.replaceAll(temp.substring(0,1),value);//replace var 
					System.out.println(s);
			}
			
			//assignment finish	
			if(Check.Check1(s) == false && flag2){
				HW1 hw1 = new HW1();
				Stack postOrder = hw1.Expression(s);
				Integer result = Simplify.Simplify1(postOrder);
				System.out.println(result);
				//System.exit(-1);
			}
			else if(flag2){
				String all1 = "";
				String all2 = "";
				all1 = Simplify.Sm(s);
				all2 = Simplify.simplify2(all1);
				System.out.println(all2);
			}
		}
		
	}	
}


