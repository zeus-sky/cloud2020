/**
 * @author Meng Yang
 * @create 2021-04-25 15:40
 */
public class T {

    public static void main(String[] args) {
        String[] a=null;
        a = b();
        System.out.println(a.length);
    }

    public static String[] b(){
        return new String[2];
    }
}
