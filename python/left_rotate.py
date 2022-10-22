para=list(map(int,input().split(" ")))
arr=list(map(int,input().split(" ")[:para[0]]))

arrl=arr[:para[1]]
arrr=arr[para[1]:]
# print("left",arrl)
# print("right",arrr)
# arr=[]
arr=arrr+arrl

for i in range (len(arr)):
    print(arr[i],end=" ")


