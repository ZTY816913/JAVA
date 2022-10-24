import java.util.Scanner;

/**
 * @version 1.0
 * @Name ZTY
 * @Date 2022-10-21 12:09
 * @注释
 */
public class Person {
    public String name;
    public  int age;

    public Person(){

    }
    public  Person(String name,int age){
        this.name=name;
        this.age=age;
    }
    public  void  show(){
        System.out.println("姓名："+name+"年龄："+age);
    }

    public static void main(String[] args) {
        Person q=new Person();
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入名字，年龄");
        q.name=sc.next();
        q.age=sc.nextInt();
        q.show();
    }

}
