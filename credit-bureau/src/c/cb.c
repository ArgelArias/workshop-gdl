/**
 * Este aplicacion representa a una entidad crediticia la cual
 * concentra todos los creditos otorgados a los clientes de
 * diferentes entidades.
 */

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <winsock2.h>
#include <unistd.h>

#define PORT 3550 /* El puerto que será abierto */
#define BACKLOG 2 /* El número de conexiones permitidas */

void doprocessing (int sock)
{
    int n;
    char buffer[256];

    memset(&(buffer), '0', 256);
    int recvMsgSize;

    /* Receive message from client */
    if ((recvMsgSize = recv(sock, buffer, 256, 0)) < 0)
        perror("ERROR reading to socket1");

    if(buffer[3] == '|') //checks if add a credit to the Loan file
        addcredit(buffer);
    else
        if(buffer[4] == '|') //checks if edit a credit from the Loan file
            editcredit(buffer);
        else
            searchrfc(buffer, sock); //search for the credits based in the rfc


    /* Send received string and receive again until end of transmission */
    while (recvMsgSize > 0)      /* zero indicates end of transmission */
    {
        /* Echo message back to client */
        if (send(sock, buffer, recvMsgSize, 0) != recvMsgSize)
            perror("ERROR writing to socket");

        /* See if there is more data to receive */
        if ((recvMsgSize = recv(sock, buffer, 256, 0)) < 0)
            perror("ERROR reading to socket2");

    }

    closesocket(sock);    /* Close client socket */
}

BOOL initW32()
{
		WSADATA wsaData;
		WORD version;
		int error;

		version = MAKEWORD( 2, 0 );

		error = WSAStartup( version, &wsaData );

		/* check for error */
		if ( error != 0 )
		{
		    /* error occured */
		    return FALSE;
		}

		/* check for correct version */
		if ( LOBYTE( wsaData.wVersion ) != 2 ||
		     HIBYTE( wsaData.wVersion ) != 0 )
		{
		    /* incorrect WinSock version */
		    WSACleanup();
		    return FALSE;
		}
}

searchrfc(char *rfc, int sock){
    FILE *fd;
	int i, j, contline;
	char caracter;
	char line[150];
	rfc[10] = '\0';
	fd = fopen("file.txt", "r");
	while(!feof(fd)){
        //gets the length of the line
		for(i=0;i<100;i++){
			line[i] = fgetc(fd);
			if(line[i] == '\n' || feof(fd)){
				break;
				}
		}
		char fline[i];
        //make a line without garbage
		for(contline=0;contline<i;contline++){
			fline[contline] = line[contline];
		}

        //check if the rfc is in the line and send it to the server
        if(strstr(fline, rfc)){
            printf("%s\n", fline);
            send(sock,fline,i,0);
            send(sock,"@",1,0);
        }
	}
	fclose(fd);
}

addcredit(char *credit){
    printf("add");
    int i;
    FILE *fd;
    fd = fopen("file.txt", "a+");
    fseek(fd,0,2); //go to the EOF to write there
    fprintf(fd,"\n");
    //erase from the line the 4 first chars "add|"
    for(i=0;i<256;i++)
        credit[i] = credit[i+4];
    //erase the garbage from the line
    for(i=0;i<256;i++){
        if((credit[i] == '|' && credit[i+1] == 'Y' && credit[i+2] == 13) || (credit[i] == '|' && credit[i+1] == 'N' && credit[i+2] == 13))
            //printf("%i", credit[i+2]);
            credit[i+2] = '\0';
    }
    //write the credit in the file
    fprintf(fd,credit);
    fclose(fd);

}

editcredit(char *credit){
    printf("edit");
    FILE *fd;
    fd = fopen("file.txt", "r+");
    int i,j;
    char line[256];
    //erase from the line the 5 first chars "edit|"
    for(i=0;i<256;i++)
        credit[i] = credit[i+5];
    //erase the garbage from the line
    for(i=0;i<256;i++){
        if((credit[i] == '|' && credit[i+1] == 'Y' && credit[i+2] == 13) || (credit[i] == '|' && credit[i+1] == 'N' && credit[i+2] == 13))
            credit[i+2] = '\0';
    }
    //search to the credit to edit *still on build*
    while(!feof(fd)){
        for(i=0;i<256;i++){
            line[i] = fgetc(fd);
            if(line[i] == '\n' || feof(fd))
                break;
        }

        char fline[i];
        for(j=0;j<i;j++){
			fline[j] = line[j];
		}

        if(strstr(fline, credit)){
            if(line[i] == '\n'){
                fseek(fd,-3,1);
                fputc('N', fd);
                fseek(fd,2,1);
            }
            else
                if(line[i] == -1){
                    fseek(fd,-1,1);
                    fputc('N', fd);
                    fseek(fd,0,2);
                }
        }
    }
    fclose(fd);
}

int main()
{

	 initW32(); /* Necesaria para compilar en Windows */

   int fd, fd2; /* los descriptores de archivos */

   /* para la información de la dirección del servidor */
   struct sockaddr_in server;

   /* para la información de la dirección del cliente */
   struct sockaddr_in client;

   unsigned int sin_size;

   pid_t pid;

   /* A continuación la llamada a socket() */
   if ((fd=socket(AF_INET, SOCK_STREAM, 0)) == -1 ) {
      printf("error en socket()\n");
      exit(-1);
   }

   server.sin_family = AF_INET;

   server.sin_port = htons(PORT);

   server.sin_addr.s_addr = INADDR_ANY;
   /* INADDR_ANY coloca nuestra dirección IP automáticamente */

   //bzero(&(server.sin_zero),8);
   memset(&(server.sin_zero), '0', 8);
   /* escribimos ceros en el reto de la estructura */


   /* A continuación la llamada a bind() */
   if(bind(fd,(struct sockaddr*)&server, sizeof(struct sockaddr))==-1) {
      printf("error en bind() \n");
      exit(-1);
   }

   if(listen(fd,BACKLOG) == -1) {  /* llamada a listen() */
      printf("error en listen()\n");
      exit(-1);
   }

   while(1) {
      sin_size=sizeof(struct sockaddr_in);
      /* A continuación la llamada a accept() */
      if ((fd2 = accept(fd,(struct sockaddr *)&client, &sin_size))==-1) {
         printf("error en accept()\n");
         exit(-1);
      }

      printf("Se obtuvo una conexión desde %s\n", inet_ntoa(client.sin_addr) );
      /* que mostrará la IP del cliente */


      //send(fd2,"Bienvenido a mi servidor.\n",256,0);
      /* que enviará el mensaje de bienvenida al cliente */

      doprocessing(fd2);

   } /* end while */
}



