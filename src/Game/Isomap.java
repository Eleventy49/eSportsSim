package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Vector;

import javax.imageio.ImageIO;

public class Isomap {

	static int numTilesX = 5;
	static int numTilesY = 5;
	static BufferedImageLoader loader = new BufferedImageLoader();
	static BufferedImage grass = new BufferedImage(200,100, BufferedImage.TYPE_INT_RGB);
	static BufferedImage dirt = new BufferedImage(200,100, BufferedImage.TYPE_INT_RGB);
	static BufferedImage dirtFillRight = new BufferedImage(100,200, BufferedImage.TYPE_INT_RGB);
	static BufferedImage dirtFillLeft = new BufferedImage(100,200, BufferedImage.TYPE_INT_RGB);
	
	static int[][] heightMap = {{10,0,-10,0,10},
								{0,0,-5,0,0},
								{-10,0,0,0,-10},
								{0,0,-5,0,0},
								{10,0,-10,0,10}};
	static int time = 0;
	static int tile_width = 100;
	public static void init() {
		try {
			grass = loader.loadImage("/grass.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			dirt = loader.loadImage("/dirt.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			dirtFillLeft = loader.loadImage("/dirtFillerLeft.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			dirtFillRight = loader.loadImage("/dirtFillerRight.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void drawLineTheta(int x, int y, int distance, double theta, Graphics g) {
		double angle = theta * Math.PI / 180;
		int startX = x;
		int startY = y;
		double endX   = x + (distance * Math.sin(angle));
		double endY   = y + (distance * Math.cos(angle));
		g.drawLine(startX, startY, (int)endX, (int)endY);
	}
	public static void drawLineTheta(Point x, int distance, double theta, Graphics g) {
		drawLineTheta(x.x,x.y,distance,theta,g);
	}
	//116.5, 63.5, -116.5, -63.5
	
	public static int isometricOffsetX(int j, int offset) {
		return -(100*j) + offset;
	}
	public static int isometricOffsetY(int i, int j, int offset) {
		return (50*i) - (50*j);
	}
	public static Point calcVertex(int t, int i, int j, int height, int offset) {
			switch(t) {
			case 0: //vTop
				return new Point(i * tile_width + isometricOffsetX(j,offset) , 
						j * tile_width - height + isometricOffsetY(i,j,offset));
			case 1: //vRight
				return new Point(i * tile_width + tile_width + isometricOffsetX(j,offset) , 
						j * tile_width + (tile_width/2) - height + isometricOffsetY(i,j,offset));
			case 2: //vBot
				return new Point(i * tile_width + isometricOffsetX(j,offset) ,
						j * tile_width + tile_width- height + isometricOffsetY(i,j,offset));
			case 3: //vLeft
				return new Point(i * tile_width - tile_width + isometricOffsetX(j,offset) , 
						j * tile_width + (tile_width/2)- height + isometricOffsetY(i,j,offset));
			default:
				return null;
			}
	}
	public static void render(Graphics g){
		time += 1;
		heightMap[4][4] = 15;
		heightMap[3][2] = 15;
		heightMap[1][1] = -20;
		String[][] map = new String[10][10];
		for(int i=0; i<10;i++) {
			for(int j=0; j<10;j++)
				map[i][j]="g";
		}
		
		boolean[][][] replaceTable = new boolean[numTilesX][numTilesY][4];
		for(int i=0; i<numTilesX;i++) {
			for(int j=0; j<numTilesY;j++) {
				for(int n=0; n<4; n++)
				replaceTable[i][j][n] = false;
			}
		}
		Point vTop = null;
		Point vRight= null;
		Point vLeft = null;
		Point vBot =null;
		int offset = 600;
		for (int i = numTilesX -1; i >=0; i--)
			for(int j = numTilesY -1; j>=0;j--)
			{
				int isometricOffsetX = -(100*j) + offset;
		    	int isometricOffsetY = (50*i) - (50*j);
		    	vTop = calcVertex(0,i,j, heightMap[i][j],offset);
		    	vRight = calcVertex(1,i,j, heightMap[i][j],offset);
		    	vBot = calcVertex(2,i,j, heightMap[i][j],offset);
		    	vLeft = calcVertex(3,i,j, heightMap[i][j],offset);
		    	try {
					if(heightMap[i][j] < heightMap[i][j-1])
						g.drawImage(dirtFillLeft,calcVertex(0,i,j-1,heightMap[i][j-1],offset).x - 100,
								calcVertex(0,i,j-1,heightMap[i][j-1],offset).y, null);
			    	}
		    	
		    	catch(IndexOutOfBoundsException e)
		    	{
		    		
		    	}
		    	try {
					if(heightMap[i][j] < heightMap[i-1][j] || i == numTilesX)
						g.drawImage(dirtFillRight,calcVertex(0,i-1,j,heightMap[i-1][j],offset).x,
								calcVertex(0,i-1,j,heightMap[i-1][j],offset).y, null);
			    	}
			    	catch(IndexOutOfBoundsException e)
			    	{
			    		
			    	}
		    	try {
					if(j == numTilesX-1)
						g.drawImage(dirtFillLeft,calcVertex(3,i,j,heightMap[i][j],offset).x,
								calcVertex(0,i,j,heightMap[i][j],offset).y, null);
			    	}
			    	catch(IndexOutOfBoundsException e)
			    	{
			    		
			    	}
		    	try {
					if(i == numTilesY-1)
						g.drawImage(dirtFillRight,calcVertex(0,i,j,heightMap[i][j],offset).x,
								calcVertex(0,i,j,heightMap[i][j],offset).y, null);
						System.out.println();
			    	}
			    	catch(IndexOutOfBoundsException e)
			    	{
			    		
			    	}
			}
		for (int i = numTilesX -1; i >= 0; i--)
		    for (int j = numTilesY - 1; j >= 0; j--) {
		    	/*
		    	time++;
		    	if(time%100 == 0) {
		    		for(int x=0;x<heightMap.length;x++)
		    			for(int y=0;y<heightMap.length;y++) {
		    				heightMap[x][y] = (int) (heightMap[x][y]*1.1);
		    			}
		    				
		    	}
		    	*/
		    	int isometricOffsetX = -(100*j) + offset;
		    	int isometricOffsetY = (50*i) - (50*j);
		    	vTop = calcVertex(0,i,j, heightMap[i][j],offset);
		    	vRight = calcVertex(1,i,j, heightMap[i][j],offset);
		    	vBot = calcVertex(2,i,j, heightMap[i][j],offset);
		    	vLeft = calcVertex(3,i,j, heightMap[i][j],offset);
		    	
		    	if(map[i][j] != null && map[i][j].equals("g")) { 
					g.drawImage(grass, vTop.x-100, vTop.y, null);
					System.out.println("Print?");
		      }
		    	g.setColor(Color.black);
		    	
		    	//vertical lines
		    	
		    	
		    	
		    	if(i==0) {
		    		if((j-1 >= 0) && Math.min(heightMap[i][j-1], heightMap[i][j]) == heightMap[i][j])
		    			drawLineTheta(vTop, heightMap[i][j] - heightMap[i][j-1],0,g);
		    	}
		    	
		    	if(j==0) {
		    		if((i-1 >= 0) && Math.min(heightMap[i-1][j], heightMap[i][j]) == heightMap[i][j])
		    			drawLineTheta(vTop, heightMap[i][j] - heightMap[i-1][j],0,g);
		    	}
		    	
		    	
		    	
		    	
		    	if(!replaceTable[i][j][0]) {    //v1
		    		if(!(heightMap[i][j] < 0))
		      g.drawLine(vTop.x, vTop.y, vRight.x, vRight.y);
		    		else {
		    			drawLineTheta(vTop.x,vTop.y,Math.round(111.8f + (heightMap[i][j])),63.5,g); //v1
		    			try {
		    			drawLineTheta(vTop.x,vTop.y,heightMap[i][j] - heightMap[i-1][j-1],0,g); //v4
		    			}
		    			catch(IndexOutOfBoundsException e)
		    			{}
		    		}
		    	}
		    	
		    	if(!replaceTable[i][j][1]  && !(heightMap[i][j] < 0)) //v2
		      g.drawLine(vRight.x, vRight.y, vBot.x, vBot.y);
		    	
		    	if(!replaceTable[i][j][2] && !(heightMap[i][j] < 0)) //v3
		      g.drawLine(vBot.x, vBot.y, vLeft.x, vLeft.y);
		    	
		    	if(!replaceTable[i][j][3]) { //v4
		    		if(!(heightMap[i][j] < 0))
		      g.drawLine(vLeft.x, vLeft.y, vTop.x, vTop.y);
		    		else
		    		drawLineTheta(vTop.x,vTop.y,Math.round(111.8f + (heightMap[i][j])),-63.5,g);
		    	}
		     
		      
		      if(heightMap[i][j] > 0) {
		    	  try {
		    		  replaceTable[i-1][j-1][1] = true; // direct up v3
			    	  replaceTable[i-1][j-1][2] = true; // direct up v2
		    	  }
		    	  catch(IndexOutOfBoundsException e) {
		    		  
		    	  }
		    	  try {
		    	  replaceTable[i][j-1][2] = true;   // upLeft v2
		    	  replaceTable[i][j-1][3]=true;     // upLeft v3
		    	  Point temp = calcVertex(2,i,j-1,heightMap[i][j-1],offset);
		    	  g.drawLine(vRight.x, vRight.y, temp.x, temp.y);
		    	  drawLineTheta(calcVertex(0,i,j-1,heightMap[i][j-1],offset),
		    			  Math.round(111.8f - (heightMap[i][j])),
		    			  -63.5 ,g );
		    	  }
		    	  catch(IndexOutOfBoundsException e)
		    	  {}
		    	  
		    	  try {
		    		  replaceTable[i-1][j][0] = true;   // upRight v1
			    	  replaceTable[i-1][j][1] = true;   // upRight v3
			    	  Point temp = calcVertex(2,i-1,j,heightMap[i-1][j],offset);
			    	  g.drawLine(vLeft.x, vLeft.y, temp.x, temp.y);
			    	  drawLineTheta(calcVertex(0,i-1,j,heightMap[i-1][j],offset),
			    			  Math.round(111.8f - (heightMap[i][j])),
			    			  63.5 ,g );
		    	  }
		    	  catch(IndexOutOfBoundsException e)
		    	  {}
		    	  try {
			    	  Point temp = calcVertex(0,i+1,j+1,heightMap[i+1][j+1],offset);
			    	  g.drawLine(vBot.x, vBot.y, temp.x, temp.y);
			    	  }
			    	  catch(IndexOutOfBoundsException e) {
			    		  drawLineTheta(vBot,heightMap[i][j],0,g);
			    	  }
		    	 
		      }
		      
		    }
	}
}
