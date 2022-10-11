#include <iostream>
using namespace std;

class Time
{
private:
    int hours;
    int minutes;
    int seconds;

public:
    Time()
    {
        this->hours = 0;
        this->minutes = 0;
        this->seconds = 0;
    };
    Time(int hours, int minutes, int seconds)
    {
        this->hours = hours;
        this->minutes = minutes;
        this->seconds = seconds;
    };
    int getHours()
    {
        return this->hours;
    };
    int getMinutes()
    {
        return this->minutes;
    };
    int getSeconds()
    {
        return this->seconds;
    };
    void display()
    {
        cout << hours << ":" << minutes << ":" << seconds << endl;
    };
    Time add(Time time1, Time time2)
    {
        int hoursAdd = time1.getHours() + time2.getHours();
        if (hoursAdd > 23)
        {
            hoursAdd -= 24;
        }
        int minutesAdd = time1.getMinutes() + time2.getMinutes();
        if (minutesAdd > 59)
        {
            minutesAdd -= 60;
            hoursAdd += 1;
        }
        int secondsAdd = time1.getSeconds() + time2.getSeconds();
        if (secondsAdd > 59)
        {
            secondsAdd -= 60;
            minutesAdd += 1;
        }

        Time time3(hoursAdd, minutesAdd, secondsAdd);
        return time3;
    };
};

int main()
{
    Time time1(12, 40, 30);
    Time time2(21, 23, 43);
    Time time3;
    time3 = time3.add(time1, time2);
    time1.display();
    time2.display();
    time3.display();
    return 0;
}