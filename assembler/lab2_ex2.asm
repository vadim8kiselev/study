stak segment stack 'stack'
  db 256 dup (?)
stak ends
data segment 'data'
	value1 db 9h
	value2 db 5h
	next db '', 0Ah, 0Dh, '$'
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
	mov al, value1
	mov dl,al
	add dl,30h
	call print
	mov dl,20h
	call print
	mov bl, value2
	mov dl,bl
	add dl,30h
	call print

	mov ah,09h
	lea dx, next
	int 21h
	xchg al,bl

	mov dl,al
	add dl,30h
	call print
	mov dl,20h
	call print
	mov dl,bl
	add dl,30h
	call print
	mov AX,4C00h
	int 21h
code ends
end start
