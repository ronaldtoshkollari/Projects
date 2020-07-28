#include "p3160091-p3160063-p3160244-auebsh.h"


//read Command structure
#define COMMAND_LENGTH  255


//method to get
char *getCommand(){


	//variables 
	int counter = 0;
	int c;
	char *command = malloc(sizeof(char) * COMMAND_LENGTH);

	//checking if there is enough memory
	if(command == NULL){
		perror("ERROR: Not enough memory.");
		exit(EXIT_FAILURE);
	}


	//reading user's chars and saving them to a table named command
	while(1){
		c = getchar();
		if(c == EOF || c == '\n'){ //if char is \n(enter) or EOF make it NULL and return table
 			command[counter] = '\0';
      			return (char*) command;
		}else {
      			command[counter] = c;
    		}

    		counter++;

    		//Checking if user's command >= 255
    		if(counter >= COMMAND_LENGTH){
    			perror("ERROR: Not Enough memory");
    			exit(EXIT_FAILURE);
    		}
	}//end while

}//end readCommand


#define TOKENSIZE 50

char **parseCommand(char *command){

	//split delims
	const char *split[] = {" ", "<", ">", "|", "\n"};

	//int tokenSize = 50;
	char *token;
	char **tokens = malloc(sizeof(char*) * TOKENSIZE);

	if(tokens == NULL){
		perror("ERROR: Not enough memory.");
		exit(EXIT_FAILURE);
	}

	int counter = 0;

	//Tokenizing commands
	token = strtok(command, *split);
	while(token != NULL){
		tokens[counter] = token;
		counter++;

		token = strtok(NULL, *split);
	}

	tokens[counter] = NULL;
	return tokens;

}//end parseCommand


//execute command
int execute(char **args, char *inFile,char *outFile)
{

	//variables
	pid_t pid,waitPid;
	int status;

	//creating a proccess
	pid = fork();

	//Child process
	if(pid ==  0) {

		//checking if the user's command has redirections
		redirect(inFile,outFile);

		//executing user's command
		if(execvp(args[0], args) == -1) {
			perror("ERROR: Execvp failed.\n");
		}
		exit(EXIT_FAILURE);

	}else if (pid < 0) { //fork error
		//Error forking
		perror("ERROR: Fork failed.\n");
		exit(1);
	}else {

		//Parent process
		waitPid = wait(&status); //waiting child proccess to finish
		if(waitPid == -1) {
			perror("ERROR: Waitpid failed.\n");
			return -1;
		}
	}

	return 1;

}//end of execute

int exec_pipe2(char **args, char *inFile, char *outFile){
	
	if(inFile == NULL && outFile == NULL){
		exec_pipe(args);
		return 1;
	}

	#define BUF_SIZE 4096
	char buffer[BUF_SIZE]; //buffer holds the data that will be written to archive
	pid_t pid;
	int fd[2];

	pid = fork();
	
	if(pipe(fd) < 0){
		perror("Pipe failes!\n");
		exit(EXIT_FAILURE);
	}
	
	if(pid == 0){
				
		if(outFile != NULL){
			fd[0] = open(outFile, O_RDWR | O_APPEND | O_CREAT | O_EXCL, 00700); 
		}

		dup2(fd[0],STDOUT_FILENO);
		close(fd[0]);//write end
		write(fd[1],buffer[BUF_SIZE],BUF_SIZE); //write data to the archive
		
		if(execvp(args[0],args) == -1){
			perror("ERROR: Execvp failed.\n");
			exit(1);
		}
		exit(EXIT_FAILURE);
	}else{
		
		
		if(pid == 0){
				
			if(outFile != NULL){
				fd[1] = open(outFile, O_RDWR | O_APPEND | O_CREAT | O_EXCL, 00700); //00700 stands for free to use (users can do what they want)
			}

			dup2(fd[1],STDIN_FILENO);
			close(fd[1]);//read end
			read(fd[0],buffer[BUF_SIZE],BUF_SIZE);
			
			
			if(execvp(args[0],args) == -1){
				perror("ERROR: Execvp failed.\n");
				exit(1);
			}
			exit(EXIT_FAILURE);
		}else{
			
			int status;
			close(fd[1]);//read end
			close(fd[0]);//write end
			waitpid(pid, &status, 0);
		}
	}

	return 1;
}//end exec_pipe

void redirectAndExec(char **args){

	char *inFile = NULL;
	char *outFile = NULL;
	int pipe = 0;

	int i = 0;

	while(args[i] != NULL){
		if(strcmp(args[i], "<") == 0){
			args[i] = NULL;
			i++;
			inFile = args[i];
		}else if(strcmp(args[i], ">") == 0){
			args[i] = NULL;
			i++;
			outFile = args[i];
		}else if(strcmp(args[i], "|") == 0){
			args[i] = NULL;
			pipe = 1;
			i++;
		}
		i++;
	}

	if(pipe) exec_pipe2(args, inFile, outFile);
	else execute(args, inFile, outFile);

}//end of redirectAndExec

//method to exec command with pipe
int exec_pipe(char **args){

	//variables and memory checking
	char **args1 = malloc(sizeof(char*) * 255);

	if(args1 == NULL){
		perror("ERROR: Not enough memory.");
		exit(EXIT_FAILURE);
	}

	char **args2 = malloc(sizeof(char*) * 255);

	if(args2 == NULL){
		perror("ERROR: Not enough memory.");
		exit(EXIT_FAILURE);
	}
	int i = 0;
	int found = 0;
	int j = 0;


	//finding commands before and after pipe and adding them to args1 and args2 
	while(args[i] != NULL){
		if(strcmp(args[i], "|") != 0 && found == 0){
			args1[i] = args[i];
		}

		if( strcmp(args[i], "|") == 0 ){
			i++;
			found = 1;
			continue;
		}

		if(found == 1){
			args2[j] = args[i];
			j++;
		}

		i++;

	}//end of while


	//checking if first command(args1) has I/O
	char *inFile1 = NULL;
	char *outFile1 = NULL;

	i = 0;

	while(args1[i] != NULL){
		if(strcmp(args1[i], "<") == 0){
			args1[i] = NULL;
			i++;
			inFile1 = args1[i];
		}else if(strcmp(args1[i], ">") == 0){
			args1[i] = NULL;
			i++;
			outFile1 = args1[i];
		}
		i++;
	}


	//checking if second command(args2) has I/O
	char *inFile2 = NULL;
	char *outFile2 = NULL;

	i = 0;

	while(args2[i] != NULL){
		if(strcmp(args2[i], "<") == 0){
			args2[i] = NULL;
			i++;
			inFile2 = args2[i];
		}else if(strcmp(args2[i], ">") == 0){
			args2[i] = NULL;
			i++;
			outFile2 = args2[i];
		}
		i++;
	}


	//if there is a pipe exec args1 and args2
	if(found == 1){
		//file descriptors of pipes
		int fd[2];

		//Piping
		int p = pipe(fd);

		if(p == -1){
			perror("ERROR: Pipe error");
			exit(EXIT_FAILURE);
		}

		//the two proccesses
		pid_t pid1 , pid2;

		//Making procces for the 1st command
		pid2 = fork();

		if(pid2 == -1){
			perror("ERROR: Fork failed");
			exit(EXIT_FAILURE);
		}

		if(pid2 == 0){
			//child
			close(fd[1]);

			dup2(fd[0], 0);

			redirect(inFile2, outFile2);

			execvp(args2[0], args2);
		}

		//making proccess for the 2nd command
		pid1 = fork();

		if(pid1 == -1){
			perror("ERROR: Fork failed");
			exit(EXIT_FAILURE);
		}

		if(pid1 == 0){
			//child
			close(fd[0]);

			dup2(fd[1], 1);

			redirect(inFile1, outFile1);

			execvp(args1[0], args1);
		}

		close(fd[1]);
		close(fd[0]);


	}else{ //if there is no pipe
		redirectAndExec(args);
	}

	//free memory
	free(args1);
	free(args2);

	return 1;

}//end of exec_pipe


//method to redirect I/O file descriptors
void redirect(char *inFile, char *outFile){

	int in,out;

	//fd od input
	if(inFile != NULL){
			
		in = open(inFile, O_RDWR);

		//ERROR MSG

		dup2(in, STDIN_FILENO);
		close(in);

	}

	//fd of output
	if(outFile != NULL){
			
		out = open(outFile, O_RDWR | O_APPEND | O_CREAT | O_EXCL, 00700); //00700 stands for free to use (users can do what they want)

		//ERROR MSG

		dup2(out, STDOUT_FILENO);
		close(out);
	}

}//end of redirect
