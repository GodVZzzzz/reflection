package reflection;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author Zhang
 * @date 2018/8/14
 * @Description
 */
public class CopyOfTest {
    public static void main(String[] args) {
        int[] a = {1,2,3};
        a = (int[]) goodCopy(a,10);
        System.out.println(Arrays.toString(a));

        String[] b = {"Tom","Dick","Harry"};
        b = (String[]) goodCopy(b,10);
        System.out.println(Arrays.toString(b));

        System.out.println("Error:");
        b = (String[]) badCopyOf(b,10);
    }

    public static Object[] badCopyOf(Object[] a, int newLength){
        Object[] newArray = new Object[newLength];
        System.arraycopy(a,0,newArray,0,Math.min(a.length,newLength));
        return newArray;
    }

    public static Object goodCopy(Object a, int newLength){
        Class c1 = a.getClass();
        if(! c1.isArray())
            return null;
        Class componentType = c1.getComponentType();
        int length = Array.getLength(a);
        Object newArray = Array.newInstance(componentType,newLength);
        System.arraycopy(a,0,newArray,0,Math.min(length,newLength));
        return newArray;
    }
}
