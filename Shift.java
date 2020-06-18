import java.util.Scanner;

public class Shift {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the text: ");
        String text1=scanner.nextLine();
        System.out.println("Enter the pattern: ");
        String pattern1=scanner.nextLine();
        int textLength=text1.length();
        int patternLength=pattern1.length();
        char[] text=new char[textLength];
        char[] pattern=new char[patternLength];
        char[] temp=new char[patternLength];
        text=text1.toCharArray();
        pattern=pattern1.toCharArray();
        for (int i = 0; i < patternLength; i++)
            temp[patternLength - i - 1] = pattern1.charAt(i);
        String str= String.copyValueOf(temp);
        System.out.println(str);
        System.out.println(String.valueOf(text));
        System.out.println(String.valueOf(pattern));
        int[] b=preprocessing(temp,str);
        int[] m = new int[patternLength];
        //for(int i=0;i<patternLength;i++)
            //System.out.println("Value of "+i+" is "+Integer.toBinaryString(b[i]));
        for(int i=0;i<patternLength;i++) {
            m[i]=~b[i];
            //System.out.println(Integer.toBinaryString(m[i]));
        }
      searching(text,patternLength,textLength,pattern,m);
    }
    private static int[] preprocessing(char[] pattern,String str)
    {
        int end = Math.min(pattern.length, 32);
        int[] b = new int[pattern.length];
        int j = 1;
        for (int i = 0; i <end; ++i, j <<= 1) {
            b[new String(pattern).indexOf(pattern[i])] |= j;

        }
        return b;
    }
    //public static int neg(int m)
    //{
      //  int n=-1;
        //return  n-m;
    //}

    private static void searching(char[] text, int m, int n,
                                 char[] pattern, int[] b)
    {
        int hit=(int)Math.pow(2,m-1);
        System.out.println(Integer.toBinaryString(hit));
        int a=-1;

        for (int i=0;i<n;i++)
        {
            System.out.println("Value of b in position "+i+" is " +Integer.toBinaryString(b[i]));
            if (i<=m) {
                a = ((a << 1) | b[i]);
            }
            else {
                int f = -1;
                a=((a << 1)) | f;
            }
            System.out.println("Value of a is " +Integer.toBinaryString(a));
            int and=a&hit;
            if(and==0) {
                System.out.println("Found at position " + (i - m + 1));
                break;
            }
        }

    }
}