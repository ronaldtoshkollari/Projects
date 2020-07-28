#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include "p3160091-p3160063-p3160244-auebsh.h"

int main(){

	char *command; 
	char **args;
	int i,j;
	
	while(1){
		
		printf("auebsh3>");

		command = getCommand(); // reading command

		if(strcmp(command, "exit") == 0){
			printf("\n");
			break;
		}
		if(strcmp(command, "\0") == 0){
			printf("\n");
			break;
		}

		args = parseCommand(command); //tokenizing it if there are param
		
		i = 0;
		while((int)args[i] != NULL)
		{
			if(strchr(args[i], '-'))//args[i] contains '-'
			{  
				j = i;
				while((int)args[j+1] != NULL)
				{
					args[j] = args[j+1];
					j++;
				}
				args[j] = NULL;
			}
			i++;	
		} 				

		redirectAndExec(args);

		free(command); //free memory used by getCommand()
		free(args);
	}//end shell loop

	return 0;
}//end main
