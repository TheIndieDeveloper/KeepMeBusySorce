package com.main.kmb.gamestates;

import java.awt.Point;
import java.util.ArrayList;

public class Collision {

	public static boolean PlayerBlock(Point pt1, Point pt2) {
		for(Block b : PlayingState.blocks){
			if(b.isSolid()){
				if(b.contains(pt1) || b.contains(pt2)){
					return true;
				}
			}
		}
		return false;
	}

	public static boolean PlayerBlock(Point point) {
		for(Block b : PlayingState.blocks){
			if(b.isSolid()){
				if(b.contains(point)){
					return true;
				}
			}
		}
		return false;
	}
}
