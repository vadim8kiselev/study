stak segment stack 'stack'
    db 256 dup (?)
stak ends

data segment 'data'
    value dw 1234
    base dw 10
data ends

code segment 'code'
assume CS:code,DS:data,SS:stak

printDigit proc
    push ax
    mov ah,02h
    add dx, 30h
	int 21h
	pop ax
	ret
printDigit endp

main: 	mov ax,data
        mov ds,ax
        mov ax,value
        mov cx, 0

pl:     mov dx, 0
        div base         
        push dx
        inc cx
        cmp ax, 0
        jne pl
        
ps:     pop dx   
        call printDigit
        loop ps
        mov AX,4C00h
        int 21h
code ends
end main
