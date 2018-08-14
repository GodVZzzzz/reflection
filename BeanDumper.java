package reflection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.*;
import java.lang.reflect.Method;

/**
 * @author Zhang
 * @date 2018/8/14
 * @Description
 */
public class BeanDumper extends JFrame {

    private JTextField query = new JTextField(20);
    private JTextArea results = new JTextArea();

    public BeanDumper(){
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(new JLabel("Qualified bean name:"));
        p.add(query);
        add(BorderLayout.NORTH,p);
        add(new JScrollPane(results));
        Dumper dumper = new Dumper();
        query.addActionListener(dumper);
        query.setText("reflection.Frog");
        dumper.actionPerformed(new ActionEvent(dumper,0,""));
    }

    public void dump(Class<?> bean){
        results.setText("");
        BeanInfo bi = null;
        try{
            bi = Introspector.getBeanInfo(bean,Object.class);
        }catch (IntrospectionException e){
            print("Couldn't introspect "+bean.getName());
            return;
        }

        for(PropertyDescriptor d: bi.getPropertyDescriptors()){      //获取属性数组
            Class<?> p = d.getPropertyType();           //通过属性方法设置和返回的对象的类型
            if(p == null)
                continue;
            print("Property type:\n "+p.getName()+"Property name:\n "+d.getName());
            Method readMethod = d.getReadMethod();         //获取读方法
            if(readMethod != null){
                print("read method:\n "+readMethod);
            }
            Method writeMethod = d.getWriteMethod();          //获取写方法
            if(writeMethod != null){
                print("Write method:\n "+writeMethod);
            }
            print("========================");
        }

        print("Public methods:");
        for(MethodDescriptor m : bi.getMethodDescriptors()){      //获取公共方法数组
            print(m.getMethod().toString());
        }
        print("========================");

        print("Event support:");
        for(EventSetDescriptor e : bi.getEventSetDescriptors()){     //获取事件方法数组
            print("Listener type:\n "+e.getListenerType().getName());

            for(Method lm : e.getListenerMethods()){
                print("Listener method:\n "+lm.getName());
            }

            for(MethodDescriptor lmd : e.getListenerMethodDescriptors()){
                print("Method descriptor:\n "+lmd.getMethod());
            }
            Method addListener = e.getAddListenerMethod();
            print("Add Listener Method:\n "+addListener);
            Method removeListener = e.getRemoveListenerMethod();
            print("Remove Listener Method:\n "+removeListener);
            print("======================");
        }

    }

    class Dumper implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = query.getText();
            Class<?> c = null;
            try{
                c = Class.forName(name);
            }catch (ClassNotFoundException ex){
                results.setText("Couldn't find"+name);
                return;
            }
            dump(c);
        }
    }

    public void print(String s){
        results.append(s+"\n");
    }

    public static void main(String[] args) {
        JFrame frame = new BeanDumper();
        frame.setSize(600,500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
