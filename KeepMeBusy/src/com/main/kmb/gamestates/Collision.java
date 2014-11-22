package com.main.kmb.gamestates;

import java.awt.Point;
import java.util.ArrayList;

public class Collision {

	public static boolean PlayerBlock(Point p, Block b) {
		return b.contains(p);
	}
}
