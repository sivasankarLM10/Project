
def palin(x):
    sum=0
    tmp=x
    while x>0:
        r=x%10
        sum=sum*10
        sum=sum+r
        x=int(x/10)
    if tmp==sum:
        print("Yes")
    else:
        print("No")


n=int(input())

for i in range (n):
    num=int(input())
    
    palin(num)