package objects;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Dice {
	
	Dice(int width, int heigth)
	{
		this.initDice(width, heigth);
	}
		
	private void initDice(int width, int heigth)
	{
		this.roll = true;
		this.width = width;
		this.heigth = heigth;
		this.dice = new JFrame("Dice");
		this.dice.setSize(this.width, this.heigth);
		//setting the layout to gridbagconstraints
		this.dice.setLayout(new GridBagLayout());
		//make windows close when clicking on 'x' in top right corner
		this.dice.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.bcalc = new JButton("Dice");
		this.dice.add(this.bcalc);
		
		//making it visible
		this.dice.setVisible(true);
		this.bcalc.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(!getDisabled())
				{
					if(getRoll())
					{
						setVal();
						setRoll();
					}
				}
			}
		});
	}
	
	public int getVal()
	{
		this.setRoll();
		return this.val;
	}
	
	private boolean getDisabled()
	{
		return this.disable;
	}
	
	public boolean getRoll()
	{
		return this.roll;
	}
	
	private void setVal()
	{
		this.val = new Random().nextInt(6) + 1;
	}
	
	public void setRoll()
	{
		this.roll = !this.roll;
	}
	
	public void setDisabled(boolean val)
	{
		this.disable = val;
	}
	
	JButton bcalc;
	JFrame dice;
	int width;
	int heigth;
	boolean roll;
	boolean disable;
	int val;
	
	/*Random wuerfel = new Random();
	int augenZahl;
	
	augenZahl = 1 + wuerfel.nextInt(6);
	System.out.println(augenZahl);*/
			
}