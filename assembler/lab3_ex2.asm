.model small

.stack 100h

.386

.code

printDigit proc
	push eax
	mov ah, 02h
	add dl, '0'
	int 21h
	pop eax
	ret
printDigit endp


main:	mov eax, 65536
	mov ebx, 10
	mov cx, 0

pl:	mov edx, 0
	div ebx
	push edx
    inc cx
	cmp eax, 0
	jne pl

ps:	pop edx
	call printDigit
	loop ps

	mov eax, 4C00h
	int 21h
end main	
