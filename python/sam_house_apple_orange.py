house=input().split(" ")
trees=input().split(" ")
ap_or=input().split(" ")

house=list(map(int,house))
trees=list(map(int,trees))
ap_or=list(map(int,ap_or))
# print("house pos",house)
# print("trees pos",trees)
# print("ap_or pos",ap_or)


apple=list(map(int,input().split(" ")[:ap_or[0]]))
orange=list(map(int,input().split(" ")[:ap_or[1]]))

# print("apple", apple)
# print("orange ",orange)

counta=0
counto=0


for i in range (len(apple)):
    if ((apple[i]+trees[0] >= house[0]) and (apple[i]+trees[0] <=house[1])):
        counta+=1
for i in range (len(orange)):
    if ((orange[i]+trees[1] >= house[0]) and (orange[i]+trees[1] <=house[1])):
        counto+=1

print(counta)
print(counto)
