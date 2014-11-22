package com.main.kmb.main;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.main.kmb.Assets.Mouse_KeyManager;
import com.main.kmb.Entity.Entity;
import com.main.kmb.Entity.Player;
import com.main.kmb.engine.GameLoop;

public class frame extends JFrame {

	public static int WIDTH = 1280;
	public static int HEIGHT = 720;
	
	public frame(String name) {
		setTitle(name);
		setResizable(false);
		addKeyListener(new Entity());
		
		//TEST
		addMouseMotionListener(new Mouse_KeyManager());
		addMouseListener(new Mouse_KeyManager());
		addKeyListener(new Mouse_KeyManager());
		setContentPane(new GameLoop());
		pack();
		setSize(WIDTH, HEIGHT);
		setCursor(this, 0, 0, "mouse.png");
		setIconImage(new ImageIcon(getClass().getResource("icon.png")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void setCursor(JFrame jf, int mouseX, int mouseY, String url){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage(url);
		Cursor c = toolkit.createCustomCursor(image , new Point(mouseX, mouseY), "png");
		jf.setCursor(c);
	}
	
}
