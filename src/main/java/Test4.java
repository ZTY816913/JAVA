import java.util.Scanner;

public class Test4 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入圆柱底面半径：");
            int a= sc.nextInt();
        System.out.println("请输入圆柱高：");
            double b= sc.nextDouble();

            double V=Math.PI*a*a*b;
        System.out.printf("圆柱的体积:%.2f",V);

    }
}
