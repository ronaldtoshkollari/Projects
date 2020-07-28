# Author: Toshkollari Ronald
# Date: 11/7/2018
# Descrisption: This is a small programm witten in assembly
#				that reads two integers from the user and 
#				asks user to finde the highest common factor of these two numbers.
# Registers:
# -$t0 is storing variable y each time.
# -$t1 is storing variable num1(a) each time.
# -$t2 is storing variable num2(b) each time.
# -$t3 is storing variable that user gave us each time.
# -$v0 is used to execute print and read string or int. 
# -$a0 is used to store string and int each time.

	.text
	
	.globl main
	
	#-----------------------------------------------
	#	print("Give A:");
	#	a = read(a);
	#	print("Give B:");
	#	b = read(b);
	#-----------------------------------------------
	
main:

	
	
	
	#print("Give A:");
	#-----------------------------------------------
	la $a0, printA
	li $v0, 4	#asking user to give the 1st number, print(printA)
	syscall
	#-----------------------------------------------
	
	
	#a = readA();
	#-----------------------------------------------
	li $v0, 5
	syscall
	sw $v0, num1
	#-----------------------------------------------
	
	
	#print("Give B:);
	#-----------------------------------------------
	la $a0, printB
	li $v0, 4	#asking user to give the 2nd number, print(printB)
	syscall
	#-----------------------------------------------
	
	
	#b = readB();
	#-----------------------------------------------
	li $v0, 5
	syscall
	sw $v0, num2
	
	
	#nextLine print
	#-----------------------------------------------
	la $a0, nextL
	li $v0, 4
	syscall
	#-----------------------------------------------
	
	
	#print("Can you find HCF of" + a + "and" + b);
	#-----------------------------------------------
	la $a0, quest	#can you find the common facto of
	li $v0, 4
	syscall
	#-----------------------------------------------
	
	#-----------------------------------------------
	lw $t1, num1
	move $a0, $t1	#a
	li $v0, 1
	syscall
	#-----------------------------------------------
	
	#-----------------------------------------------
	la $a0, andP		#and
	li $v0, 4
	syscall
	#-----------------------------------------------
	
	#-----------------------------------------------
	lw $t2, num2
	move $a0, $t2
	li $v0, 1		#b
	syscall
	#-----------------------------------------------
	
	#-----------------------------------------------
	la $a0, qMark
	li $v0, 4
	syscall
	#-----------------------------------------------
	
	
	#y = a%b
	#-----------------------------------------------
	rem $t0, $t1, $t2 
	sw $t0, y
	#-----------------------------------------------
	
	#-----------------------------------------------
	#while(y != 0){
	#	a = b;
	#	b = y;
	#	y = a%b;
	#}
	#-----------------------------------------------
again:

	
	beq $t0 ,0, askUser #if(y == 0)
	
	add $t1, $t2, 0			#a = b;
	add $t2, $t0, 0			#b = y;
	#y = a%b
	rem $t0, $t1, $t0 
	sw $t0, y
	j again
	#-----------------------------------------------
	
	#-----------------------------------------------
askUser:

	
	#s = read(s);
	li $v0, 5
	syscall
	sw $v0, s
	lw $t0, num2
	lw $t3, s
	beq $t2, $t3, exit
	j again1
	#-----------------------------------------------
	
	#-----------------------------------------------
	#	while(s != b){
	#		print("Wrong! Plese try again! :) );
	#		print("Can you fing HCF");
	#		s = read(s);
	#	}
	#-----------------------------------------------
	
	
	#-----------------------------------------------
again1:
	
	lw $t0, num2
	lw $t3, s
	beq $t2, $t3, exit #if(s == b)
	
	#print("Wrong! Please try again! :);
	la $a0, wrongM1
	li $v0, 4
	syscall
	
	#nextLine print
	la $a0, nextL
	li $v0, 4
	syscall
	
	#print(Which is HCF?)
	la $a0, wrongM2
	li $v0, 4
	syscall
	
	#s = read(s);
	li $v0, 5
	syscall
	sw $v0, s
	
	j again1
	#-----------------------------------------------
	
	#-----------------------------------------------
	#print("Congratulations");
	#-----------------------------------------------
exit:

	#print("Congratulations");
	la $a0, endP
	li $v0, 4
	syscall
	
	#end of programm
	li $v0, 10
	syscall



	.data

printA: .asciiz "Please enter A:"
printB: .asciiz "Please enter B:"
quest: .asciiz	"Can you find highest common factor of "
andP: .asciiz " and "
qMark: .asciiz " ? "
wrongM1: .asciiz	"Wrong, Plese try again ! :)"
wrongM2: .asciiz	"Which is HCF:"
nextL: .asciiz "\n"
endP: .asciiz "Congratulations! you found it !"
num1:	.word 0
num2:	.word 0
y: 	.word 0
s: 	.word 0