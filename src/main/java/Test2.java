import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
            System.out.println("请输入一个三位整数：");

            int a= sc.nextInt();

            int b=a/100;
                a%=100;

            int c=a/10;
                a%=10;

        System.out.println("各位数字之和为："+(b+c+a));
    }
}
