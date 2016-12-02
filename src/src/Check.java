package src;
public class Check {
	//check if the formula is in the correct form, return true if there is unknown number(e.g. x,y), else false
	public static boolean Check1(String s){
		boolean flag = true;
		int len = s.length();
		char sign;
		//take out the last number and check if it is correct
		int last = len - 1;
		int lastNum = last;
		if(last != 0){	
			if(Character.isDigit(s.charAt(last))){
				lastNum = HW1.TakeOutLast(s, last);
			}
			else if(s.charAt(lastNum) >= 'A' && s.charAt(lastNum) <='z'){
				lastNum--;
			}
			else{
				System.out.println("Wrong Format!");
				flag = false;
			}
		}		
		//check out the rest formula
		for(int i = 0; i < lastNum + 1; i++){
			if(Character.isDigit(s.charAt(i))){
				while(i<(lastNum+1) && Character.isDigit(s.charAt(i))){		
					i++;
				}
			}
			else if(s.charAt(i) >= 'A' && s.charAt(i) <='z'){
				i++;
			}
			else{
				System.out.println("Wrong Format!");
				flag = false;
			}
			if(i<(lastNum+1)){	
				sign = s.charAt(i);
				if(sign == '+' || sign =='*'){
					
				}
				else{
					System.out.println("Wrong Format!");
					flag = false;
				}
			}
		}
		return flag;
	}
	
	
	
	public String Check2(String s1,String s)
  	{
	    String[] a = null;
	    boolean flag2 = false;//judge of simplify 
		boolean flag3 = true;//judge of assignment
  		do
  		{
  	    	
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
  				if(!flag2) return "Wrong format!";
  				flag3 = true;
  			}
  			else  return "Wrong format!";
  		}while(!flag2);
  	    return "okfun";
  	}
  
}
