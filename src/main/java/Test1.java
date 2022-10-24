import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入一个两位数：");

        int a= sc.nextInt();
        System.out.print("该数的逆序数是：");
        while (a!=0){
                int b=a%10;
                    a/=10;
            System.out.print(b);
        }

    }
}
