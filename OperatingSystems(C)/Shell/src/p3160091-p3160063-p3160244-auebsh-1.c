#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>
#include "p3160091-p3160063-p3160244-auebsh.h"



int main(){

	char *command; 
	char **args;
	int i,j;
	char *inFile = NULL;
	char *outFile = NULL;
	



	while(1){

		printf("auebsh1>");
		command = getCommand(); // reading command

		if(strcmp(command, "exit") == 0){
			printf("\n");
			break;
		}
		if(strcmp(command, "\0") == 0){
			printf("\n");
			break;
		}

		//printf("The user gave the command %s\n", command);
		args = parseCommand(command); //tokenizing it if there are param

		i = 0;
		while(args[i] != NULL)
		{
			if(strchr(args[i], '-'))//args[i] contains '-'
			{  
				j = i;
				while(args[j+1] != NULL)
				{
					args[j] = args[j+1];
					j++;
				}
				args[j] = NULL;
			}
			i++;	
		} 			




		execute(args, inFile, outFile); //execute the program

		free(command); //free memory used by getCommand()
		free(args);

	}//end while

	return 0;

}//end main
