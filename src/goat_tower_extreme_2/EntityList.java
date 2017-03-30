package goat_tower_extreme_2;

import java.awt.image.BufferedImage;

public class EntityList {
	
	FileUtil fu=new FileUtil();
    public BufferedImage goatimg;
    public BufferedImage saltimg;
    public BufferedImage grassimg;
    public BufferedImage plankimg;
    
	static int num_planks=18;
	static int num_grass=20;
	static int num_entities=2+num_grass+num_planks;
	
	Frame frame;
	static Goat goat;
	static Salt salt;
	static Grass[] grass=new Grass[num_grass];
	static Plank[] planks=new Plank[num_planks];
	
	int x_max;
	int y_max;

    public EntityList(DrawFrame dframe) {  
		x_max=(int) dframe.getPreferredSize().getWidth();
		y_max=(int) dframe.getPreferredSize().getHeight();
		dframe.master_list=this;
		frame=dframe.frame;
    
    	init_goat();
    	init_salt();
    	init_grass();
    	init_planks();
    }

    private void init_goat()
    {
    	goatimg=fu.LoadImg("goat.png");      
    	goat= new Goat(goatimg,frame,700,500,0,0,x_max,y_max);
    }
    
    private void init_salt()
    {
    	saltimg=fu.LoadImg("salt.png");      
    	salt= new Salt(saltimg,frame,1100,-2700,0,0,x_max,y_max);
    }
    
    private void init_grass()
    {
    	grassimg=fu.LoadImg("grass.png");
    	for (int i=0;i<num_grass;i++)
		{
			grass[i]= new Grass(grassimg,frame,-1,-1,0,0,x_max,y_max);
		}
    }

    private void init_planks()
    {
    	plankimg=fu.LoadImg("plank.jpg");
    	for (int i=0;i<num_planks;i++)
		{
    		if(i==0)
    			planks[i]=new Plank(plankimg,frame,1000,1200,0,0,x_max,y_max);
    		else if(i==1)
    			planks[i]=new Plank(plankimg,frame,1500,1000,0,0,x_max,y_max);
    		else
    			planks[i]= new Plank(plankimg,frame,2000,1200-200*i,-i,0,x_max,y_max);
		}
    }
    
    public void update_entities(int time)
    {
    	
    	frame.reconfigure(time);
		goat.reconfigure(time);
		if(goat.get_pos(0, 1)-100<frame.get_pos(0,1))
			frame.pos[1]=(double) goat.get_pos(0, 1)-100;
		
		//check_collision will set can_jump_flag to 1 if we can jump
		goat.can_jump_flag=0;
		goat.check_collision(null);
		
		goat.check_collision(salt);
		
		for(int i=0; i<num_grass;i++)
		{
			grass[i].reconfigure(time);
			goat.check_collision(grass[i]);
		}
		
		for(int i=0; i<num_planks;i++)
		{
			planks[i].reconfigure(time);
			planks[i].check_collision(null);
			goat.check_collision(planks[i]);
		}
		
    }
    
    public Entity[] get_list()
    {
    	int i=0;
    	Entity[] list=new Entity[num_entities];
    	list[i]=(Entity) goat;
    	i++;
    	
    	list[i]=(Entity) salt;
    	i++;
    	
    	for(int j=0;j<planks.length;j++,i++)
        {
        	list[i]=(Entity) planks[j];
        }
    	
    	for(int j=0;j<grass.length;j++,i++)
        {
        	list[i]=(Entity) grass[j];
        }


    	return list;
    	
    }
}
