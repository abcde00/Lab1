package junit_test;
import src.Check;
import src.HW1;
import src.Simplify;

import java.util.Stack;
import static org.junit.Assert.*;
import java.util.Stack;
import org.junit.Test;

public class simplifytest{
  @Test
  public void testsm(){
    HW1 lab1_sim=new HW1();
    Stack postOrder = lab1_sim.Expression("3+5*6*7+9");
    Simplify sm = new Simplify();
	Integer result =sm.Simplify1(postOrder);
    assertEquals(222,result,0);
    
    String s2 = sm.Sm("3*x*y+7+8+x*7*y");
    String result2 = sm.simplify2(s2);
    assertEquals("15+10*x*y",result2);

   }
  @Test
  public void testjudge(){
	    Check judge = new Check();
	    String result = judge.Check2("!simplify x=2 y=3","3+5*x");
	    assertEquals("Wrong format!",result);
  }
}
