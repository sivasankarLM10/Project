#include <stdio.h>
#include <iostream>
using namespace std;

class Complex
{
private:
    int real, imaginary;

public:

    void setReal(int tempReal)
    {
        real = tempReal;
    }
    int getReal()
    {
        return real;
    }

    void setImg(int tempImaginary)
    {
        imaginary = tempImaginary;
    }
    int getImg()
    {
        return imaginary;
    }

    Complex Add(Complex C1, Complex C2)
    {
        Complex temp;
        temp.setReal(C1.getReal() + C2.getReal());
        temp.setImg(C1.getImg() + C2.getImg());
        return temp;
    }
    Complex Multi(Complex C1, Complex C2)
    {
        Complex temp;
        temp.setReal(C1.getReal() * C2.getReal());
        temp.setImg(C1.getImg() * C2.getImg());
        return temp;
    }
};

int main()
{

    Complex C1;
    C1.setReal(3);
    C1.setImg(2);

    Complex C2;
    C2.setReal(3);
    C2.setImg(2);

    Complex C3;
    C3 = C3.Add(C1, C2);

    Complex C4;
    C4 = C4.Multi(C1, C2);

    cout << "Sum of complex number : "
         << C3.getReal() << " + i"
         << C3.getImg() << endl;
    
    cout << "Multiple of complex number : "
         << C4.getReal() << " + i"
         << C4.getImg();
   return 0;
}
