#include <iostream>
using namespace std;
class doctor{
private:
	int salary;
public:
	void getdata(){
		cout<<"Enter doctor salary:";cin>>salary;
	}
	friend void incometax();
} inp1;
class engineer{
	private:
		int salary;
		public:void getdata(){
			cout<<"Enter engineer salary:";cin>>salary;
		}
		friend void incometax();
	} inp2;
void incometax(){
	double doc, eng = 0;
	doc = inp1.salary;
	if(doc<=50000.00)
		{doc=0;}
	else if(doc<=100000.00)
		{doc=(doc-50000.00)*10/100;}
	else if (doc<=250000.00)
		{doc=(doc-100000.00)*20/100+5000;}
	else{doc=(doc-250000.00)*30/100+5000+30000;}
	cout <<"tax for doctor : "<<doc<<endl;
	if(eng<=50000.00){eng=0;}
	else if (eng <= 100000.00)
		{eng=(eng-50000.00)*10/100;}
	else if (eng <= 250000.00)
		{eng=(eng-100000.00)*20/100+5000;}
	else{eng=(eng-250000.00)*30/100+5000+30000;}
	cout <<"tax for engineer: "<<eng<<endl;
};
	int main()
	{
		inp1.getdata();
		inp2.getdata();
		incometax();
		return 0;
	};