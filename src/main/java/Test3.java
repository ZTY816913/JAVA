import java.util.Scanner;

public class Test3 {
    public static void main(String[] args) {
            Scanner sc=new Scanner(System.in);
            double celsius = sc.nextDouble(); ;
            double fahrenheit = (9.0/5) *  celsius +32;

            System.out.println("摄氏度:" + celsius);
            System.out.println("华氏度:" + fahrenheit);

    }
}
