package trafficsim;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class TrafficLight implements ActionListener{
	
	private Image layoutImg;	//the layout image of the traffic light which would have transparent hole for light
	private Timer tm;
	private Vector2 position;
	private AffineTransform trans;
	private ImageObserver imObs;
	private int id, timer =0;
	private Color[] currentLightColor;
	//Individual light positions
	private Vector2 left_light_pos;
	private Vector2 right_light_pos;
	private Vector2 forward_pos;
	private Random random = new Random();
	private int trafficTime = 5;
	private int orientation; // 0 -Horizontal, 1-Vertical


	public Vector2 getLeft_light_pos() {
		return left_light_pos;
	}

	public void setLeft_light_pos(Vector2 left_light_pos) {
		this.left_light_pos = left_light_pos;
	}


	public Vector2 getRight_light_pos() {
		return right_light_pos;
	}


	public void setRight_light_pos(Vector2 right_light_pos) {
		this.right_light_pos = right_light_pos;
	}


	public Vector2 getForward_pos() {
		return forward_pos;
	}


	public void setForward_pos(Vector2 forward_pos) {
		this.forward_pos = forward_pos;
	}

	public boolean leftGo, forwardGo, rightGo;
	
	public TrafficLight(File imgSrc, int x, int y, int angle, int orient, int id,ImageObserver iObs){
		imObs = iObs;
		trans = new AffineTransform();
		orientation = orient;
		leftGo = false;
		forwardGo = false;
		rightGo = false;
		tm = new Timer(1, this);
		this.id = id;
		currentLightColor = new Color[3]; //index 0-Left, 1-forward, 2-right
		currentLightColor[0] = Color.red; currentLightColor[1] = Color.red; currentLightColor[2] = Color.red;
		
		try {
			layoutImg = ImageIO.read(imgSrc);
			tm = new Timer(1, this);
			position = new Vector2(x,y);
			trans.setToTranslation(position.x, position.y);
			trans.rotate(Math.toRadians(angle), layoutImg.getWidth(imObs)/2, layoutImg.getHeight(imObs)/2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tm.start();
	}

	
	public int getOrientation() {
		return orientation;
	}


	public AffineTransform getTrans() {
		return trans;
	}




	
	public Image getLayoutImg() {
		return layoutImg;
	}


	public Color[] getCurrentLightColor() {
		return currentLightColor;
	}

	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		timer++;
		if(id == 1){
			if(timer >55 && timer < (trafficTime*100)-70){
				leftGo = true;
				forwardGo = true;
				rightGo=false;
			}
			if(timer < trafficTime*100 && timer >(trafficTime*100)-49){
				leftGo = false;
				forwardGo = false;
				currentLightColor[0] = Color.yellow;
				currentLightColor[1] = Color.yellow;
			}
			if(timer > trafficTime*100){
				currentLightColor[0] = Color.red;
				currentLightColor[1] = Color.red;
			}
			if(timer > (trafficTime+4)*100){
				timer = 0;
			}
		}
		
		if(id == 2){
			if(timer > trafficTime*100 && timer < ((trafficTime+4)*100)-50){
				forwardGo = true;
				rightGo = true;
				leftGo = false;
			}
			
			if(timer < (trafficTime+4)*100 && timer > ((trafficTime+4)*100)-49){
				forwardGo = false;
				rightGo = false;
				
				currentLightColor[2] = Color.yellow;
				currentLightColor[1] = Color.yellow;
			}
			
			if(timer > (trafficTime+4)*100){
				currentLightColor[2] = Color.red;
				currentLightColor[1] = Color.red;
			}
			if(timer > (trafficTime+4)*100){
				timer = 0;
			}
		}
		
		if(id == 3){

			 if(timer > 55 && timer < (trafficTime*100)-70){
				leftGo = false;
				forwardGo = true;
				rightGo=true;
			}
			if(timer < trafficTime*100 && timer >(trafficTime*100)-49){
				rightGo = false;
				forwardGo = false;
				currentLightColor[2] = Color.yellow;
				currentLightColor[1] = Color.yellow;
			}
			if(timer > trafficTime*100){
				rightGo = false;
				forwardGo = false;
				currentLightColor[2] = Color.red;
				currentLightColor[1] = Color.red;
			}
			if(timer > (trafficTime+4)*100){
				timer = 0;
			}
		}
		
		if(id == 4){
						
			if(timer > trafficTime*100 && timer < ((trafficTime+4)*100)-50){
				forwardGo = true;
				rightGo = false;
				leftGo = true;
			}
			
			if(timer < (trafficTime+4)*100 && timer > ((trafficTime+4)*100)-49){
				forwardGo = false;
				leftGo = false;
				
				currentLightColor[0] = Color.yellow;
				currentLightColor[1] = Color.yellow;
			}
			
			if(timer > (trafficTime+4)*100){
				currentLightColor[0] = Color.red;
				currentLightColor[1] = Color.red;
				timer =0;
			}
		}
		//color index, 0-left, 1-forward, 2-right
		if(leftGo){
			currentLightColor[0] = Color.green;
		}
		
		if(forwardGo){
			currentLightColor[1] = Color.green;
		}
		
		if(rightGo){
			currentLightColor[2] = Color.green;
		}
	}
}
