import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;


public class SysTimer extends JFrame
{
	private Timer timer;
	public int count =0;
	
	public SysTimer(){
		timer = new Timer(1000, new TimerListener());
		timer.start();
	}
	private class TimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			count++;
			System.out.println(count + "seconds elapsed");
		}
	}
	public static void main(String[] args){
		new SysTimer();
	}
}