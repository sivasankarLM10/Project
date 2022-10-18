
;<Program title>

jmp start

;data


;code
start: MVI a,2H
lda 2041h
mov b,a
lda 2042h
add b
sta 2040h
out 3h
hlt