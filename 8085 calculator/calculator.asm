LDA 2000h 
MOV c, a 

MVI a,01h 
CMP c 
JZ addition 

MVI a,02h 
CMP c 
JZ subtraction 

MVI a,03h 
CMP c 
JZ multiply 

MVI a,04h 
CMP c 
JZ division 

MVI a,05h 
CMP c 
JZ AND
MVI a,06h 

CMP c 
JZ OR

MVI a,07h 
CMP c 
JZ XOR

addition:    	lda 2001h 
	       	mov b,a 
		lda 2002h 
        	add b 
        	sta 2003h 
        	hlt 

subtraction:    lda 2001h 
        	mov b,a 
        	lda 2002h 
        	sub b 
        	sta 2003h 
        	hlt 

 

multiply:    	lda 2001h 
        	mov b,a 
        	lda 2002h 
        	mov c,a 
        	dcr b 
loop:		add c 
            	dcr b 
            	jnz loop 
 		sta 2003h 
         	hlt 

division:  	lda 2001h      
         	mov b,a   
          	lda 2002h      
          	mvi c, 00h     
Next: 	      cmp b      
         	jc Loop  
                inr C      
                sub B      
                jmp Next 
Loop: 	      sta 2003h  
                  mov a,c  
                  sta 2004h  
                  hlt 
         
AND:            lda 2001h 
                mov b,a 
                lda 2002h 
                ana b 
                sta 2003h 
                hlt

OR:             lda 2001h 
                mov b,a 
                lda 2002h 
                ora b 
                sta 2003h 
                hlt 

NOT:              LDA 2501H 
                  ANA A 
                  STA 2502H 
                  HLT 

XOR:              lda 2001h 
                  mov b,a 
                  lda 2002h 
                  xra b 
                  sta 2003H 
                  hlt 