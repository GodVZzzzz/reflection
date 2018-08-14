package reflection;

import java.util.ArrayList;

/**
 * @author Zhang
 * @date 2018/8/14
 * @Description
 */
public class ObjectAnalyzerTest {
    public static void main(String[] args) {
        ArrayList<Integer> squares = new ArrayList<>();
        for(int i = 1; i <= 5; i++){
            squares.add(i*i);
            System.out.println(new ObjectAnalyzer().toString(squares));
        }
    }
}
