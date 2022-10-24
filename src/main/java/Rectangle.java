/**
 * @version 1.0
 * @Name ZTY
 * @Date 2022-10-21 13:40
 * @注释
 */
public class Rectangle {
    public double height;
    public double width;

    public void Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }

    public void getPerimeter(double height, double width){
        System.out.println("周长为：" + (height + width) * 2);
    }
    public void getArea(double height, double width){
        System.out.println("面积为：" + height * width);
    }

    public static void main(String[] args) {
        Rectangle r = new Rectangle();
        r.Rectangle(1, 2);
        r.getPerimeter(1, 2);
        r.getArea(1, 2);
    }
}
