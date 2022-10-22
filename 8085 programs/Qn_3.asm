START: LXI H, 2050H
       LXI D, 2070H
       MVI B, 0AH
NEXT:  MOV A, M
       STAX D
       INX H
        INX D
        DCR B
        JNZ NEXT
	  HLT