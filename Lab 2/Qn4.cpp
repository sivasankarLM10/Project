#include <iostream>
using namespace std;

class solid
{
private:
    int cube;
    float sphere;
    float cylinder;
    int rectangle;

public:
    int volume(int side)
    {
        cube = side * side * side;
        return cube;
    };
    int volume(float radius)
    {
        sphere = (4 / 3) * 3.14 * radius * radius * radius;
        return sphere;
    };
    float volume(float radius, float height)
    {
        cylinder = 3.14 * radius * radius * height;
        return cylinder;
    };
    int volume(int length, int breadth, int height)
    {
        rectangle = height * breadth * length;
        return rectangle;
    };
    void display()
    {
        cout << "1.Volume of a cube : " << cube << endl;
        cout << "2.Volume of a Sphere : " << sphere << endl;
        cout << "3.Volume of a cylinder : " << cylinder << endl;
        cout << "4.Volume of a rectangle : " << rectangle << endl;
    }
};

int main()
{
    solid s1;
    s1.volume(4);
    s1.volume(float(1.4));
    s1.volume(44.2, 2.4);
    s1.volume(4, 6, 2);
    s1.display();

    return 0;
}