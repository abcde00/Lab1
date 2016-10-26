import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * .
 * Input a polynomial, assign it with some values and then it simplifies the polynomial
 */
public class HW1 {
    /**
     * .
     * q对应的注释（Javadoc缺少）
     */
    private static int q;
    //check if the formula is in the correct form,
    // return true if there is unknown number(e.g. x,y), else false

    /**
     * .
     * magic number
     */
    private static int arrayLength = 26;

    /**
     * 加号
     */
    private static char add = '+';
    /**
     * 乘号
     */
    private static char mul = '*';

    /**
     * .
     * check if the polynomial is in correct form
     *
     * @param s 要检查的字符串
     * @return 检查是否通过
     */
    public static boolean check(final String s) {
        boolean flag = true;
        final int len = s.length();
        //System.out.println(len)
        char sign;
        //take out the last number and check if it is correct
        int lastNum = len - 1;
        if (Character.isDigit(s.charAt(lastNum))) {
            while (Character.isDigit(s.charAt(lastNum))) {
                lastNum--;
            }
        } else if (s.charAt(lastNum) >= 'A' && s.charAt(lastNum) <= 'z') {
            lastNum--;
        } else {
            System.out.println("Wrong Format!");
            flag = false;
        }
        //check out the rest formula
        for (int i = 0; i < lastNum + 1; i++) {
            if (Character.isDigit(s.charAt(i))) {
                while (Character.isDigit(s.charAt(i))) {
                    i++;
                }
            } else if (s.charAt(i) >= 'A' && s.charAt(i) <= 'z') {
                i++;
            } else {
                System.out.println("Wrong Format!");
                flag = false;
            }
            sign = s.charAt(i);
            if (!(sign == add || sign == mul)) {
                System.out.println("Wrong Format!");
                flag = false;
            }
        }
        return flag;
    }


    /**
     * .
     * caculate the polynomial is it does not contain any characters
     *
     * @param s 对应的注释（Javadoc缺少）
     * @return 对应的注释（Javadoc缺少）
     */
    public final Stack<String> expression(final String s) {
        //take out all the parts and put them separtely into the list
        int i;
        ArrayList inOrder = new ArrayList<String>();
        String num = "";
        int lastLen = s.length() - 1;
        //take out the last num
        if (Character.isDigit(s.charAt(lastLen))) {
            while (Character.isDigit(s.charAt(lastLen))) {
                lastLen--;
            }
        }
        //store the rest(without the last) into the list
        StringBuffer numBuf = new StringBuffer(num);
        for (i = 0; i < lastLen + 1; i++) {
            if (Character.isDigit(s.charAt(i))) {
                while (Character.isDigit(s.charAt(i))) {
                    numBuf.append(s.charAt(i));
                    i++;
                }
                i--;
                inOrder.add(numBuf.toString());
                numBuf = new StringBuffer("");
            } else {
                inOrder.add(s.charAt(i));
            }
        }
        num = numBuf.toString();
        //add the last num into the list
        for (i = lastLen + 1; i < s.length(); i++) {
            num = num + s.charAt(i);
        }
        inOrder.add(num);
        //change that list into a string form
        String[] inOrder1 = new String[inOrder.size()];
        for (i = 0; i < inOrder.size(); i++) {
            inOrder1[i] = inOrder.get(i).toString();
        }
        //for test
        System.out.println(inOrder);

        //store numbers into stack1 and operators
        // into stack2, and clear stack2 afterwards
        Stack<String> stack1 = new Stack<String>();
        Stack<String> stack2 = new Stack<String>();
        for (i = 0; i < inOrder.size(); i++) {
            //System.out.println(inOrder1[i]);
            if (inOrder1[i].charAt(0) >= '0' && inOrder1[i].charAt(0) <= '9') {
                stack1.push(inOrder1[i]);
            } else if (inOrder1[i].charAt(0) == mul) {
                stack2.push(inOrder1[i]);
            } else if (inOrder1[i].charAt(0) == add) {
                while (!stack2.isEmpty()
                        && stack2.lastElement().charAt(0) == mul) {
                    stack1.push(stack2.pop());
                }
                stack2.push(inOrder1[i]);
            }

        }
        //push all the rest operators into stack1
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        //for test
        System.out.println(stack1);
        return stack1;
    }

    /**
     * .
     * simplify the polynomial
     *
     * @param postOrder   polynomial in post order form
     * @return stack
     */
    public static Integer simplify(final Stack<String> postOrder) {
        Stack stack = new Stack();
        for (int i = 0; i < postOrder.size(); i++) {
            if (postOrder.get(i).charAt(0) >= '0'
                    && postOrder.get(i).charAt(0) <= '9') {
                stack.push(Integer.parseInt(postOrder.get(i)));
            } else {
                Integer a = (Integer) stack.pop();
                Integer b = (Integer) stack.pop();
                Integer result = 0;
                if (postOrder.get(i).charAt(0) == add) {
                    result = a + b;
                } else if (postOrder.get(i).charAt(0) == mul) {
                    result = a * b;
                }
                stack.push(result);
            }
        }
        return (Integer) stack.pop();
    }

    /**
     * .
     * simplify the polynomial
     *
     * @param s  the polynomial
     * @return   the simplified polynoimal in string form
     */
    public static String sm(final String s) {
        boolean flag = false,
                flag1 = false;
        String part = "";
        String all = "";
        for (int i = 0; i < s.length(); i++) {
            int num = 1;
            char[] a = new char[arrayLength];        //to save the characters
            //to save the number of characters
            int[] b = new int[arrayLength];
            int y = 0;        //count the num of different characters
            int temp = 0;
            while (s.charAt(i) != add && i != s.length()) {

                if (Character.isDigit(s.charAt(i)) && i != s.length()) {
                    temp = i;
                    while (Character.isDigit(s.charAt(i))
                            && i < s.length() - 1) {
                        i++;
                    }
                    if (Character.isDigit(s.charAt(i)) && i == s.length() - 1) {
                        i++;
                    }
                    num = num * (Integer.parseInt(s.substring(temp, i)));
                    i--;
                    if (i == s.length() - 1) {
                        break;
                    } else {
                        i++;
                    }
                } else if (Character.isLetter(s.charAt(i)) && i != s.length()) {
                    for (int k = 0; k < y; k++) {
                        if (a[k] == s.charAt(i)) {
                            b[k] = b[k] + 1;
                            flag = true;
                        }
                    }
                    if (!flag && i != s.length()) {
                        a[y] = s.charAt(i);
                        b[y] = 1;
                        y += 1;
                    }
                    flag = false;
                    i++;
                } else {
                    i++; //*
                }
                q = y;
                if (i == s.length()) {
                    break;
                }
            }
            if (num == 1) {
                part = "";
                flag1 = true;
            } else {
                part = Integer.toString(num);
            }
            for (int k = 0; k < q; k++) {
                for (int x = 0; x < b[k]; x++) {
                    if (x != 0 || !flag1) {
                        part = "" + part + "*";
                    }
                    part = "" + part + a[k];
                }
            }
            if (!all.equals("")) {
                all = "" + all + add + part;
            } else {
                all = "" + all + part;
            }
        }
        return all;
    }

    /**
     * .
     * simplify the polynomial again
     *
     * @param s  the polynomial
     * @return the simplified polynomial
     */
    private static String simplify2(final String s) {
        ArrayList<String> a = new ArrayList<String>();
        String news = "";
        for (int i = 0; i < s.length(); i++) {
            int temp = i;  // sinvar
            while (s.charAt(i) != add) {
                i++; //
                if (i == s.length()) {
                    break;
                }
            }
            a.add(s.substring(temp, i)); //cutting and storage
        }
        int sum = 0;
        for (int j = 0; j < a.size(); j++) {
            boolean singvar = false;
            //judge of contains digit
            singvar = Pattern.compile("(?i)[a-z]").matcher(a.get(j)).find();
            //false => not include letter
            //String tranlate into int
            if (!singvar) {
                sum += Integer.parseInt(a.get(j));
            } else if (j == a.size() - 1) {
                news = "" + news + a.get(j);
            } else {
                news = "" + news + a.get(j) + add;
            }
        }

        if (sum != 0) {
            news = "" + sum + add + news;
        }
        if (news.charAt(news.length() - 1) == add) {
            //delete the end of add
            news = news.substring(0, news.length() - 1);
        }
        String[] merge = null;
        merge = news.split("\\+");
        String newmerge = "";
        for (int i = 0; i < merge.length; i++) {
            for (int j = i + 1; j < merge.length; j++) {
                if (merge[i] != null && merge[j] != null
                        && merge[i].substring(1).equals(
                        merge[j].substring(1))) {
                    int addsum = Integer.parseInt(merge[i].substring(0, 1))
                            + Integer.parseInt(merge[j].substring(0, 1));
                    merge[i] = String.valueOf(addsum)
                            + merge[i].substring(1);
                    merge[j] = null;
                }
            }
            if (i != 0 && merge[i] != null) {
                newmerge = newmerge + add + merge[i];
            }
            if (i == 0) {
                newmerge = merge[0];
            }
        }
        return newmerge;
    }


    /**
     * .
     * the whole program in steps
     *
     * @param args
     */
    public static void main(final String... args) {
        //scan the formula
        boolean flag = false;
        Scanner in = new Scanner(System.in, "utf8");
        String s = " ";
        //check if the formula is in the correct form
        do { //the loop of input
            s = in.nextLine();
            flag = check(s);
        } while (!flag);
        System.out.println("Polynomial is in correct form!");
        //calculate the formula
        //check out if there's charater
        boolean flag1 = true;        //flag1=true if there's only digit in s
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 'A' && s.charAt(i) <= 'z') {
                flag1 = false;
            }
        }
        System.out.println(s);
        if (flag1) {
            HW1 hw1 = new HW1();
            Stack postOrder = hw1.expression(s);
            Integer result = simplify(postOrder);
            System.out.println(result);
        } else {
            String s1 = " ";
            String[] a = null;
            boolean flag2 = false; //judge of simplify
            boolean flag3 = true; //judge of assignment
            //赋值输错判断循环
            do {
                s1 = in.nextLine();
                a = s1.split(" ");
                if (a[0].equals("!simplify")) {
                    String temp = " "; //assignment string
                    String value = " ";
                    for (int i = 1; i < a.length; i++) {
                        temp = a[i];
                        if (temp.length() < 2) {
                            flag3 = false;
                            break;
                        }
                        value = temp.substring(2);
                        //letter=digit && if has var which  does not exit
                        if (!value.matches("[0-9]{1,}")
                                || temp.charAt(1) != '='
                                || !Character.isLetter(temp.charAt(0))
                                || !s.contains(temp.substring(0, 1))) {
                            flag3 = false;
                        }
                    }
                    if (flag3) {
                        flag2 = true;
                    }
                    if (!flag2) {
                        System.out.println("wrong format!");
                    }
                    flag3 = true;
                } else {
                    System.out.println("wrong format!");
                }
            } while (!flag2);
            //judge of assignment finish

            String temp = " ";
            String value = " ";
            for (int i = 1; i < a.length; i++) {
                temp = a[i];
                value = temp.substring(2);
                s = s.replaceAll(temp.substring(0, 1), value); //replace var
                System.out.println(s);
            }

            //assignment finish
            if (!check(s) && flag2) {
                HW1 hw1 = new HW1();
                Stack postOrder = hw1.expression(s);
                Integer result = simplify(postOrder);
                System.out.println(result);
            } else if (flag2) {
                String all1;
                String all2;
                all1 = sm(s);
                all2 = simplify2(all1);
                System.out.println(all2);
            }
        }
    }

}


