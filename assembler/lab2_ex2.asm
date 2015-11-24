stak segment stack 'stack'
  db 256 dup (?)
stak ends
data segment 'data'
	value1 dw 2h
	value2 dw 8h
	next db '', 0Ah, 0Dh, '$'
data ends
code segment 'code'
assume CS:code,DS:data,SS:stak

printDigit proc
	push ax
	mov ah,02h
	int 21h
	pop ax
	ret
printDigit endp

printRow proc
	pop bp
	pop dx
	add dl, 30h
	call printDigit
	mov dl, 20h
	call printDigit
	pop dx
	add dl, 30h
	call printDigit
	push bp
	ret
printRow endp

start:                         
	mov ax, data
	mov ds, ax
	mov ax, 0
	mov dx, 0
	push value2 
	push value1	
	call printRow

	mov ah,09h
	lea dx, next
	int 21h	
         
	push value1
	push value2
	call printRow

	mov AX,4C00h
	int 21h
code ends
end start
