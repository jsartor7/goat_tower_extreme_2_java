package goat_tower_extreme_2;

import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

public class Goat extends Entity{

    int kill_count=0;
    String color_mode="normal";
    int can_jump_flag=0;
    
	public Goat(BufferedImage inputimg,Frame frame, int x_i, int y_i, int x_vi, int y_vi, int x_max, int y_max) {
		super(inputimg,frame, x_i, y_i, x_vi, y_vi, x_max, y_max);
		// TODO Auto-generated constructor stub
		accel[1]=1;
	}

    public void check_collision(Entity ent)
    {
    	
    	int sum=0;
    	double dif[]={0, 0};
    	double sdif[]={0, 0};
		double ddif[]={0,0};
		if(ent!=null)
		{
    	for(int i=0;i<2;i++)
    	{
    		//raw position difference,center to center
    		dif[i]=pos[i]-ent.get_pos(0, i);
    		//sizes added together, not really a diff
    		sdif[i]=size[i]/2+ent.size[i]/2;
    		//how much am i overlapping in this component
    		ddif[i]=sdif[i]-Math.abs(dif[i]);
    		
    			
    		double compare=sdif[i];
    			if(i==1&&ent.collision_flag==2)
    				compare+=2;
    		if(Math.abs(dif[i])<compare)
    			sum+=1;
    	}
    	
    	if(sum==2)
    	{
    		if(ent.collision_flag==1)
    		{
    			ent.respawn();
    			kill_count++;
    			setColorScale();
    		}
    		else if(ent.collision_flag==2)
    		{
    			
    			
    			//basically the direction that you're barely overlapping in
    			//is the direction that you're colliding in
    			//corners are a bit buggy.
    			
    			//ddif is bigger so we're stopping in x direction
    			int stop_dir;
    			int stop_pos;
    			boolean flag=false;
    			//this whole block is retarded
    			if(ddif[1]>ddif[0])
    			{
    				stop_dir=0;
    			//dif is >0 so we're on the right hand side
    				if(dif[0]>0)
    					stop_pos=(int) (ent.get_pos(0, 0)+(size[0]/2+ent.size[0]/2))+1;
    				else
    					stop_pos=(int) (ent.get_pos(0, 0)-(size[0]/2+ent.size[0]/2))-1;
    			}
    			else
    			{
    				stop_dir=1;
    				if(dif[1]>0)
    				{
    					stop_pos=(int) (ent.get_pos(0, 1)+(size[1]/2+ent.size[1]/2))+1;
    					flag=true;
    				}
    				else
    				{
    					stop_pos=(int) (ent.get_pos(0, 1)-(size[1]/2+ent.size[1]/2))-1;
    					if(ddif[0]>5)
    						can_jump_flag=1;
    				}
    			}
    			stop(stop_dir,stop_pos);
    			
    			//bouncing off the tops when you hit your head
    			if(flag==true)
    				vel[1]=1;
    			
    		}
    	}	
		}
		else
		{
			//don't need to be doing this every time...
        for(int i=0; i<2; i++){
        	if(pos[i]>max[i])
        	{
        		pos[i]=max[i];
        		vel[i]=0;
        	}
        	if(i==0&&pos[i]<min[i])
        	{
        		pos[i]=min[i];
        		vel[i]=0;
        	}
    	}
        if(pos[1]>=max[1])
        	can_jump_flag=1;
		}
    }
    
    private void stop(int dir, double stop_pos)
    {
    	vel[dir]=0;
		pos[dir]=stop_pos;
    }
    
    public void jump()
    {
    	if(can_jump_flag==1)
    		vel[1]=-30;	
    }
    
    public void reconfigure(int time)
    {
    	super.reconfigure(time);
    }
    
    public void setColorScale() {
        // scales[0] = 1f;
        // scales[1] = 1f;
        // scales[2] = 1f;
     	offsets[0]=1f;
     	offsets[1]=1f;
     	offsets[2]=1f;
     	String temp_str=color_mode;

     	//this is kind of clunky
     	//also some sound effects here would be nice
     	if(kill_count>10000)
     	{
     		offsets[1]= 0f;
     		offsets[0]= 0f;
     		offsets[2]= 0f;
     	        scales[0] = 0f;
     	        scales[1] = 0f;
     	        scales[2] = 0f;
     		color_mode="black";
     	}
     	else if(kill_count>1000)
     	{
     		offsets[1]= 0f;
     		offsets[0]= 0f;
     		offsets[2]= 0f;
     	        scales[0] = 0.5f;
     	        scales[1] = 0.5f;
     	        scales[2] = 0.5f;
     		color_mode="grey";
     	}
     	else if(kill_count>500)
     	{
     		offsets[0]=130f;
     		color_mode="red";
     	}
     	else if(kill_count>100)
     	{
     		offsets[1]= 130f;
     		color_mode="green";
     	}
         if(temp_str!=color_mode)
         	rop = new RescaleOp(scales, offsets, null);
     }


}
