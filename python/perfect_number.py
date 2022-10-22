n=int(input())
sum=0
for i in  range (1,int(n/2)+2):
    if n%i==0:
        sum+=i
if sum==n:
    print("Yes")
else:
    print("No")
