package accumulate.generics.比较;

/**
 * @Author: yaodao
 * @Date: 2019/4/3 19:43
 */
public class Box2<T> {
    private T object;

    public void set(T object) {
        this.object = object;
    }

    public T get() {
        return object;
    }


    public static void main(String[] args) {
        Box2 box2 = new Box2();
        box2.set(1);

        String b = (String) box2.get(); // java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String

        System.out.println(b);
    }
}