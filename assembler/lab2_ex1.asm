stak segment stack 'stack'
  db 256 dup (?)
stak ends 

data segment 'data'
	value1 db 9h
	value2 db 5h
data ends

code segment 'code'
assume CS:code,DS:data,SS:stak
print proc
		push ax
		mov ah,02h
		int 21h
		pop ax
		ret
print endp
start:                         
		mov AX,data
		mov DS,AX
		mov al,value1
		mov dl,al      ; move value1 to dx 
		add dl,30h     ; get symbol from digit
		call print     ; print it
		mov dl,20h     ; get ‘space’ symbol
		call print     ; print ‘space’
		mov bl, value2 ; and again
		mov dl,bl
		add dl,30h
		call print     ; print second digit
		mov AX,4C00h
		int 21h
code ends

end start
