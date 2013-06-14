#include<stdio.h>
#include<conio.h>
#include<string.h>


int main(){
	FILE *fd;
	int i, j, find=0, contline;
	char caracter;
	char line[150];
	char rfc[10];// = {'2','3','4','5','6','A','B','C','D','E'};
	fflush(stdin);
    scanf("%s", &rfc);
	//printf("%s\n", rfc);
	fd = fopen("file.txt", "r");
	while(!feof(fd)){
		for(i=0;i<100;i++){
			line[i] = fgetc(fd);
			if(line[i] == '\n'){
				break;
				}
		}
		char fline[i];
		for(contline=0;contline<i;contline++){
			fline[contline] = line[contline];
		}
		for(j=0;j<strlen(fline);j++){
			if(fline[j] == '|'){
				j++;
				for(contline=0;contline<10;contline++){
					if(fline[j+contline] == rfc[contline])
						find++;
				}
			}
		}
		if(find == 10){
            for(j=0;j<i;j++)
                printf("%c", fline[j]);
            printf("\n");
		}
		find=0;
	}
	fclose(fd);
}
