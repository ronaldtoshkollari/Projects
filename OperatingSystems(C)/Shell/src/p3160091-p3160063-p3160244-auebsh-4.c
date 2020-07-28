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

	while(1){
		printf("auebsh4>");

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

		exec_pipe(args);
		
		free(command); //free memory used by getCommand()
		free(args);
	}
	return 0;
}
