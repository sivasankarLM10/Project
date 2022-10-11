#include <iostream>
using namespace std;

class Counter
{
private:
    unsigned int count;

public:
    Counter() : count(0)
    { /*empty body*/
    }
    void inc_count()
    {
        count++;
    };
    int get_count()
    {
        return count;
    };
};

int main()
{
    Counter c1, c2;
    cout << "\nc1 =" << c1.get_count();
    cout << "\nc2 =" << c2.get_count();
    c1.inc_count();
    c2.inc_count();
    c2.inc_count();
    cout << "\nc1 =" << c1.get_count();
    cout << "\nc2 =" << c2.get_count();
    cout << endl;
    return 0;
}