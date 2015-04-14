#include "timer.h"
#include <ctime>

timer::timer() {
    resetted = true;
    running = false;
    beg = 0;
    end = 0;
}

void timer::start() {
    if (!running) {
        if (resetted)
            beg = clock();
        else
            beg -= end - clock();
        running = true;
        resetted = false;
    }
}

void timer::stop() {
    if (running) {
        end = clock();
        running = false;
    }
}

void timer::reset() {
    if (running)
        stop();
    resetted = true;
    beg = 0;
    end = 0;
}

bool timer::isRunning() {
    return running;
}

double timer::getScore() {
    if (running)
        return (clock() - beg)*2.5; //chose random multiplication facotr for score
    else
        return (end - beg)*2.15;
}

void printScore(double score) {
    //not sure how to print to gui
}
