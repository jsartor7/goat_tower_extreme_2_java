package goat_tower_extreme_2;

import java.awt.image.BufferedImage;

public class Salt extends Entity{

	public Salt(BufferedImage inputimg, Frame inputframe, int x_i, int y_i, double x_vi, double y_vi, int x_max,
			int y_max) {
		super(inputimg, inputframe, x_i, y_i, x_vi, y_vi, x_max, y_max);
		// TODO Auto-generated constructor stub
		collision_flag=1;
		//should make it so when you get the salt something happens

	}

}
