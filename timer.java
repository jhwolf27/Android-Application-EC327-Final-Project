/**
 * Created by Christopher on 4/14/15.
 */

import java.util.Calendar;

public class timer {
    //initialize fields for timer class
    private boolean resetted;
    private boolean running;
    private Calendar beg;
    private Calendar end;


    public timer() {
        resetted = true;
        running = false;
        beg = 0;
        end = 0;
    }

     public void start() {
        if (!running) {
            if (resetted)
                beg = java.util.Calendar.getInstance();
            else
                beg -= end - java.util.Calendar.getInstance();
            running = true;
            resetted = false;
        }
    }

    public void stop() {
        if (running) {
            end = java.util.Calendar.getInstance();
            running = false;
        }
    }

    public void reset() {
        if (running)
            stop();
        resetted = true;
        beg = 0;
        end = 0;
    }

    public boolean isRunning() {
        return running;
    }

    public double getScore() {
        if (running)
            return (java.util.Calendar.getInstance() - beg)*2.5; //chose random multiplication facotr for score
        else
            return (end - beg)*2.15;
    }

    public void printScore(double score) {
        //not sure how to print to gui
    }
}
