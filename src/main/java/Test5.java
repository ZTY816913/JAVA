import java.util.Scanner;

public class Test5 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
            System.out.println("请输入a,b,c的值：");
            System.out.print("a:");
            int a= sc.nextInt();
            System.out.print("b:");
            int b= sc.nextInt();
            System.out.print("c:");
            int c= sc.nextInt();

        double root = (-b + Math.sqrt(b*b-4*a*c))/(2*a);

        System.out.printf("方程的一个根是：%.2f",root);
    }

}
