#include <ctime>

class timer {
private:
    bool resetted;
    bool running;
    clock_t beg;
    clock_t end;
public:
    timer();
    void start();
    void stop();
    void reset();
    bool isRunning();
    double getScore();
    void printScore(double);
};
