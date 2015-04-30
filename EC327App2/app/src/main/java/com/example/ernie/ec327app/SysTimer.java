package com.example.ernie.ec327app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

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
