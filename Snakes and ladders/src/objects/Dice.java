package objects;

import java.util.Random;

public class Dice {

	public static void main(String[] args){
		
		Random wuerfel = new Random();
				int augenZahl;
		
			augenZahl = 1 + wuerfel.nextInt(6);
			System.out.println(augenZahl);
			
// +1 weil Z�hlen bei 0 beginnt
		}
	}
