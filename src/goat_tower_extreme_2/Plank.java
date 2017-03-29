package goat_tower_extreme_2;

import java.awt.image.BufferedImage;

public class Plank extends Entity {

	public Plank(BufferedImage inputimg, int x_i, int y_i, int x_vi, int y_vi, int x_max, int y_max) {
		super(inputimg, x_i, y_i, x_vi, y_vi, x_max, y_max);
		// TODO Auto-generated constructor stub

		collision_flag=2;
	    
	}

    public void check_collision(Entity ent)
    {
    	//if the flag is 1 we stop the goat if it's colliding
    	//if the flag is 0 we just want to check if we're standing
    	
        for(int i=0; i<2; i++){
        	if(pos[i]>max[i])
        	{
        		pos[i]=max[i];
        		vel[i]=-vel[i];
        	}
        	if(pos[i]<min[i])
        	{
        		pos[i]=min[i];
        		vel[i]=-vel[i];
        	}
    	}
		}
    

}
