#include <iostream>
using namespace std;

class complex
{
private:
    int real;
    int img;

public:
    complex(int tempreal = 0, int tempimag = 0)
    {
        real = tempreal;
        img = tempimag;
    }

    complex operator+(complex c2)
    {
        complex temp;
        temp.real = real + c2.real;
        temp.img = img + c2.img;
        return temp;
    }

    complex operator-(complex c2)
    {
        complex temp;
        temp.real = real - c2.real;
        temp.img = img - c2.img;
        return temp;
    }

    void printing()
    {
        cout << "\nThe Result : " << real << " + " << img << "i";
    }
};
int main()
{
    complex c1(1, 3);
    complex c2(4, 2);
    complex c3 = c1 + c2;
    complex c4 = c1 - c2;
    c3.printing();
    c4.printing();

    return 0;
}