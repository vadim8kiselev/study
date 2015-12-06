.model small
.stack 100h
.186
.data
        row db 10      
        column db 20   
        mode db (?)    
        current_char db 45h
 
.code
 
getMode proc                   
        mov     ah, 0Fh        
        int     10h            
        mov     mode, al       
        mov     ax, 0          
        mov     al, 3
        int     10h
        ret
getMode endp
 
clearScreen proc               
        pusha
        mov     ah, 06h        
        int     10h
        mov     bh, 01h        
        int     10h
        popa
        ret
clearScreen endp
 
setCursor proc                 
        pusha
        mov     ah, 02h        
        mov     bh, 0          
        mov     dh, row        
        mov     dl, column     
        int     10h
        popa
        ret
setCursor endp
 
printItem proc         
        pusha
        mov      ah, 9h        
        mov      al, current_char      
        mov      bh, 0         
        mov      cx, 1         
        int      10h
        popa
        ret
printItem endp
 
main:
        mov     ax,@data
        mov     ds, ax
        call    getMode        
        call    clearScreen    
        mov     bl, 05h        
point:
        call    setCursor      
        call    printItem      
        inc     column         
        cmp     column, 40     
        jc      point
        inc     current_char    
        inc     row            
        mov     column, 20      
        inc     bl             
        cmp     current_char, 4Fh      
        jc      point
        mov     ax,4C00h        
        int     21h
end main
