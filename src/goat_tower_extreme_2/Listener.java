package goat_tower_extreme_2;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Listener extends JPanel implements KeyListener {
    private char c = 'e';
    
    //store the whole list
    //really we just need goat right now tho
    public EntityList list;
    public Goat goat;
    public static boolean reset_flag=false;
    
    public Listener(EntityList in_list) {
        addKeyListener(this);
        list=in_list;
        goat=in_list.goat;
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    public void keyPressed(KeyEvent k) {
        int keyCode = k.getKeyCode();
        switch (keyCode) {
        case KeyEvent.VK_LEFT:
            goat.accel(0,-1);
        case KeyEvent.VK_A:
            goat.accel(0,-1);
            break;
        case KeyEvent.VK_RIGHT:
            goat.accel(0,1);
        case KeyEvent.VK_D:
            goat.accel(0,1);
            break;
    	case KeyEvent.VK_UP:
    		goat.jump();
    	case KeyEvent.VK_W:
    		goat.jump();
    		//goat.accel(1,-1);
    		break;
    	case KeyEvent.VK_SPACE:
    		goat.stop();
    		break;
    	case KeyEvent.VK_R:
    		reset_flag=true;
    		break;
    	case KeyEvent.VK_DOWN:
    		goat.vel[1]=-10;
    		break;
    }

    }
    public void keyReleased(KeyEvent k) { 

        int keyCode = k.getKeyCode();
        switch (keyCode) {
        case KeyEvent.VK_LEFT:
            goat.accel(0,0);
            break;
        case KeyEvent.VK_A:
            goat.accel(0,0);
            break;
        case KeyEvent.VK_RIGHT:
            goat.accel(0,0);
            break;
        case KeyEvent.VK_D:
            goat.accel(0,0);
            break;
    	case KeyEvent.VK_UP:
    		//goat.jump();
    		//goat.accel(1,0);
    		break;
    }
    }
    public void keyTyped(KeyEvent k) {
        int keyCode = k.getKeyCode();
      /*  switch (keyCode) {
        case KeyEvent.VK_LEFT:
            goat.accel(0,-1);
            break;
        case KeyEvent.VK_RIGHT:
            goat.accel(0,1);
            break;
    	case KeyEvent.VK_UP:
    		goat.accel(1,-1);
    		break;
    	case KeyEvent.VK_DOWN:
    		goat.accel(1,1);
    		break;
    }
    		*/
    }

}