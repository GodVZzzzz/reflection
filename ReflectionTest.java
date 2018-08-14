package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * @author Zhang
 * @date 2018/8/14
 * @Description
 */
public class ReflectionTest {
    public static void main(String[] args) {
        String name;
        if(args.length > 0){
            name = args[0];
        }

        else {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter class name(e.g. java.util.Date): ");
            name = in.next();
        }

        try {
            Class c1 = Class.forName(name);
            Class superC1 = c1.getSuperclass();
            String modifiers = Modifier.toString(c1.getModifiers());
            if(modifiers.length() > 0)
                System.out.println(modifiers+" ");
            System.out.print("class "+name);
            if(superC1 != null && superC1 != Object.class)
                System.out.println(" extends "+superC1.getName());

            System.out.print("\n{\n");
            printConstructors(c1);
            System.out.println();
            printMethods(c1);
            System.out.println();
            printFileds(c1);
            System.out.println("}");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void printConstructors(Class c1) {
        Constructor[] constructors = c1.getDeclaredConstructors();

        for(Constructor c: constructors){
            String name = c.getName();
            System.out.print("    ");
            String modifiers = Modifier.toString(c.getModifiers());
            if(modifiers.length() > 0)
                System.out.print(modifiers+" ");
            System.out.print(name+"(");

            Class[] paremeters = c.getParameterTypes();
            for(int j = 0; j < paremeters.length; j++){
                if(j > 0)
                    System.out.print(", ");
                System.out.print(paremeters[j].getName());
            }

            System.out.println(");");
        }
    }

    public static void printMethods(Class c1){
        Method[] methods = c1.getDeclaredMethods();

        for(Method m: methods){
            Class retType = m.getReturnType();
            String name = m.getName();

            System.out.print("   ");
            String modifiers = Modifier.toString(m.getModifiers());
            if(modifiers.length() > 0)
                System.out.print(modifiers+" ");
            System.out.print(retType.getName()+" "+name+"(");

            Class[] paramTypes = m.getParameterTypes();
            for(int j = 0; j < paramTypes.length; j++){
                if(j > 0)
                    System.out.print(", ");
                System.out.print(paramTypes[j].getName());
            }

            System.out.println(");");
        }
    }

    public static void printFileds(Class c1){
        Field[] fields = c1.getDeclaredFields();

        for (Field f : fields){
            Class type = f.getType();
            String name = type.getName();
            System.out.print("   ");
            String modifiers = Modifier.toString(f.getModifiers());
            if(modifiers.length() > 0)
                System.out.print(modifiers+" ");
            System.out.println(type.getName()+" "+name+";");
        }
    }
}
