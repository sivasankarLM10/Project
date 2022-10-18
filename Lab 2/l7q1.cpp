#include <bits/stdc++.h>
using namespace std;
  
struct node
{
	int data;
	node* next;
};

node* newNode(int data)
{
	struct node* temp = new node;
	temp->data = data;
	temp->next = NULL;
	return temp;
}

node* merge(node* a,node* b)
{
	node* res=NULL;
	if(a==NULL)
	{
		return b;
	}
	if(b==NULL)
	{
		return a;
	}
	
	if(a->data <= b->data)
	{
		res=a;
		res->next=merge(a->next,b);
	}
	else
	{
		res=b;
		res->next=merge(a,b->next);
	}
	return res;
}

node* mergeLists(node* arr[],int l)
{
	while(l!=0)
	{
		int i=0;
		int j=l;
		while(i<j)
		{
			arr[i] = merge(arr[i],arr[j]);
			
			i++;
			j--;
			if(i>=j)
			{
				l=j;
			}
		}
		
	}
	return arr[0];
}

void printNodes(node* node)
{
	while(node!=NULL)
	{
		cout<<node->data<<"-> ";
		node=node->next;
	}
	cout<<"NULL";
}

int main()
{
	node* arr[3];
	arr[0]=newNode(1);
	arr[0]->next=newNode(4);
	arr[0]->next->next=newNode(5);
	
	arr[1]=newNode(1);
	arr[1]->next=newNode(3);
	arr[1]->next->next=newNode(4);
	
	arr[2]=newNode(2);
	arr[2]->next=newNode(6);
	node* result = mergeLists(arr,2);
	printNodes(result);
	return 0;
}
