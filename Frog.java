package reflection;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Zhang
 * @date 2018/8/14
 * @Description
 */
public class Frog {
    private int jumps;
    private Color color;
    private Spots spots;
    private boolean jmpr;
    private ArrayList<ActionListener> actionListeners = new ArrayList<>();

    public int getJumps() {
        return jumps;
    }

    public void setJumps(int jumps) {
        this.jumps = jumps;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Spots getSpots() {
        return spots;
    }

    public void setSpots(Spots spots) {
        this.spots = spots;
    }

    public boolean isJmpr() {
        return jmpr;
    }

    public void setJmpr(boolean jmpr) {
        this.jmpr = jmpr;
    }

    public void addActionListener(ActionListener l){
        actionListeners.add(l);
    }

    public void removeActionListner(ActionListener l){
        actionListeners.remove(l);
    }

    public void croak(){
        System.out.println("Ribbet!");
    }
}

class Spots{}
