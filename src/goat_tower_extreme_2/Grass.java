package goat_tower_extreme_2;

import java.awt.image.BufferedImage;

public class Grass extends Entity{

	public Grass(BufferedImage inputimg, Frame frame,int x_i, int y_i, int x_vi, int y_vi, int x_max, int y_max) {
		super(inputimg,frame, x_i, y_i, x_vi, y_vi, x_max, y_max);
		// TODO Auto-generated constructor stub
		collision_flag=1;
		random_pos(-1);
	}

}
