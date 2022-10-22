n=int(input())
for i in range (n):
    par=list(map(int,input().split()))
    print(min(par[0]-par[1],par[1]))
    par = []
    
    