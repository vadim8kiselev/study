.model small
.stack 100h
.186
.data
	array dw 10 dup (?)                          
	result db "    $"
	nextLine db '', 0Ah, 0Dh, '$'

.code  

digitToString proc
	pusha
	mov cx, 0
	mov bx, 10
	mov si, 0

conv:	mov dx, 0
	div bx                                       
	push dx
	inc cx
	cmp ax, 0
	jne conv 

insert:	pop dx
	add dl, 30h
	mov result[si], dl 
	inc si                          
	loop insert
	
	lea dx, result                            
	mov ah, 09h
	int 21h
	popa
	ret                                   
digitToString endp

;////////////////////////////
;////////////////////////////

printArray proc
	mov cx, 10
	mov si, 0

print:	mov ax, array[si]
	add si, 2
	call digitToString
	loop print
	ret
printArray endp

;////////////////////////////
;////////////////////////////
                                                   
main:                         
	mov ax, @data
	mov ds, ax

;////////////////////////////
	mov cx, 10                                   
	mov bx, 1
	mov si, 0	

fill:	mov array[si], bx                             
	add bx, 2
	add si, 2
	loop fill

	call printArray
;////////////////////////////	  

	lea dx, nextLine                            
	mov ah, 09h
	int 21h	
		
	mov result[1], ' '

;////////////////////////////
	mov cx, 10
	mov si, 0

change:	mov ax, array[si]
	imul ax
	mov array[si], ax
	add si, 2
	loop change

	call printArray
;////////////////////////////
	 
	mov AX,4C00h
	int 21h
end main
