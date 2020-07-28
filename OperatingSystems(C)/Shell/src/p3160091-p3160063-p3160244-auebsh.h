#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>


//methods that are used in main
char *getCommand();
char **parseCommand(char *command);
int execute(char **args, char *inFile, char *outFile);
int exec_pipe(char **args);
void redirect(char *inFile, char *outFile);
void redirectAndExec(char **args);
int exec_pipe2(char **args, char *inFile, char *outFile);