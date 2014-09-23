package com.GameName.Cube.Cubes;

import com.GameName.Cube.Cube;

public class AirCube extends Cube {

	public AirCube() {
		super("Air");
		
		setSolid(false);
		setVisable(false);
		setOpacity(0f);
	}
	
}
