package src;
import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Pattern;

public class Simplify {
	public static int q;
	public static Integer Simplify1(Stack<String> postOrder){
		Stack stack = new Stack();
		for(int i = 0; i < postOrder.size(); i++){
			if(postOrder.get(i).charAt(0) >= '0' && postOrder.get(i).charAt(0) <= '9'){
				stack.push(Integer.parseInt(postOrder.get(i)));
			}
			else{
				Integer a = (Integer)stack.pop();
				Integer b = (Integer)stack.pop();
				Integer result = 0;
				if(postOrder.get(i).charAt(0) == '+')
					result = a + b;
				else if(postOrder.get(i).charAt(0) == '*')
					result = a * b;
				stack.push(result);
			}
		}
		return (Integer)stack.pop();
	}
	
	public static String Sm(String s){
		boolean flag = false,flag1 = false;
		String part = "";
		String all = "";
		for(int i = 0; i < s.length(); i++){
			int num = 1;
			char[] a = new char[26];		//to save the characters
			int[] b = new int[26];		//to save the number of characters
			int y = 0;		//count the num of different characters
			int temp = 0;
			while(s.charAt(i) != '+' && i != s.length()){
				
				if(Character.isDigit(s.charAt(i))&& i != s.length())
				{
					temp=i;
					while(Character.isDigit(s.charAt(i))&& i < s.length() - 1){
						i++;
					}
					if(Character.isDigit(s.charAt(i))&& i == s.length() - 1)
						i++;
					String t=s.substring(temp, i);
					num = num * (Integer.parseInt(s.substring(temp, i)));
					i--;
					if(i != s.length()-1) i++;
					else{
						
						break;
					}
				}
				else if(Character.isLetter(s.charAt(i))&& i != s.length())
				{
					
					for(int k = 0;k < y;k++)
					{
						if(a[k] == s.charAt(i))
						{
							b[k]=b[k]+1;
							flag = true;
						}
					}
					if(!flag&& i != s.length())
					{
						a[y] = s.charAt(i);
						b[y] = 1;
						y +=1;
					}
					flag = false;
					i++;
				}
				else i++; //*
				q=y;
				if(i == s.length()) break;
			}
			if(num == 1)
				{
				part = "";
				flag1=true;
				}
			else part = ""+num;
			for(int k = 0;k <q;k++)
			{
				for(int x =0;x<b[k];x++)
				{
					if(x!=0 || !flag1) part = ""+part+"*";
					part = ""+part  + a[k];
				}

			}
			if(all != "") all = "" + all +'+'+ part;
			else all = "" + all + part;
			a = null;
			b = null;
			
		}
		return all;
	}
	
	public static String simplify2(String s)//merge similar iterms
    {
    	ArrayList<String> a = new ArrayList<String>();
    	String news = "";
    	int letter1 = 0;
    	int letter2 = 0;
    	for(int i = 0;i < s.length();i++)
    	{
    		int temp = i;  // sinvar
    		while(s.charAt(i) != '+') 
    		{
    			i++; //
    			if(i == s.length()) break;
    		}
    		a.add(s.substring(temp, i));//cutting and storage
    	}
    	int sum = 0;
		for(int j = 0;j < a.size();j++)
		{
			boolean singvar = false;
			singvar = Pattern.compile("(?i)[a-z]").matcher(a.get(j)).find();//judge of contains digit
			//false => not include letter
			if(!singvar) sum +=  Integer.parseInt(a.get(j));//String tranlate into int
			else if(j == a.size()-1) news = "" + news + a.get(j);
			else news = "" + news + a.get(j) + '+';
		}
		
		if(sum != 0) news = "" + sum + '+' + news;
    	if(news.charAt(news.length()-1) == '+') news = news.substring(0, news.length()-1);//delete the end of '+'
    	String[] merge = null;
		merge = news.split("\\+");
		String newmerge = "";
		for(int i = 0;i < merge.length;i++)
		{
			for(int j = i+1;j<merge.length;j++)
			{
				if(merge[i] != null && merge[j] != null)
				{
					for(int k = 0;k < merge[i].length();k++)
					{
						if(merge[i].charAt(k) =='*')
						{
							letter1 = k;
							break;
						}
					}
					for(int k = 0;k < merge[j].length();k++)
					{
						if(merge[j].charAt(k) =='*')
						{
							letter2 = k;
							break;
						}
					}
					System.out.println(i+merge[i].substring(letter1));
					System.out.println(j+merge[j].substring(letter2));
					if((merge[i].substring(letter1)).equals(merge[j].substring(letter2)))//match the suffix
					{
					
						int addsum = Integer.parseInt(merge[i].substring(0, letter1))+Integer.parseInt(merge[j].substring(0, letter2));
						merge[i] =  String.valueOf(addsum)+merge[i].substring(letter1);
						merge[j] = null;
					}
					
				}
			}
			if(i!= 0 && merge[i] != null) newmerge = newmerge+'+'+merge[i];
			if(i == 0) newmerge = merge[0];
		}
		return newmerge;
    }


}
