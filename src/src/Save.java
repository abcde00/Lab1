package src;
import java.util.ArrayList;
import java.util.Stack;

public class Save {
	//take out all the parts and put them separtely into the list and transfer them into string	
		public static String[] StoreInList(String s){	
			int i;
			ArrayList inOrder = new ArrayList<String>();
			String num = "";		
			int last = s.length() - 1;
			
			int lastLen = last;
			if(Character.isDigit(s.charAt(last))){		//take out the last num
				lastLen = HW1.TakeOutLast(s, last);
			}
			for(i = 0; i < lastLen + 1; i++){	//store the rest(without the last) into the list
				if(Character.isDigit(s.charAt(i))){
					while(i<lastLen+1 && Character.isDigit(s.charAt(i))){
						num = num + s.charAt(i);
						i++;
					}
					i--;
					inOrder.add(num);
					num = "";
				}
				else{
					inOrder.add(s.charAt(i));
				}
			}
			if(lastLen+1!=s.length()){	
				for(i = lastLen + 1; i < s.length(); i++){		//add the last num into the list
					num = num + s.charAt(i);
				}
				inOrder.add(num);
			}	
				//change that list into a string form
			String[] inOrder1 = new String[inOrder.size()];
			for(i = 0; i < inOrder.size(); i++){
				inOrder1[i] = inOrder.get(i).toString();
			}
			System.out.println(inOrder);
			return inOrder1;
		}
		
}
