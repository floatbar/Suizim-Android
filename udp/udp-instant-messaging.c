#include <stdio.h>
#include <stdlib.h>
#include <winsock2.h>

// Suizim's UDP server...
// Consider verifying tokens and/or user accounts before any message operation.

int main(void) {
    WSADATA wsa;
    SOCKET sockfd;
    
    char[4096] buffer;
    struct sockaddr_in servaddr, clientaddr;
    
    int clientaddrlen = sizeof(clientaddr);

    if((WSAStartup(MAKEWORD(2, 2), &wsa)) == SOCKET_ERROR) {
        printf("Error: %d\n", WSAGetLastError());
        return 1;
    }
    
    if((sockfd = socket(AF_INET, SOCK_DGRAM, 0)) == SOCKET_ERROR) {
        printf("Error: %d\n", WSAGetLastError());
        return 1;
    }
    
    servaddr.sin_family = AF_INET;
    servaddr.sin_port = htons(6666);
    servaddr.sin_addr.s_addr = inet_addr("0.0.0.0");

    if (bind(sockfd, (struct sockaddr *)&servaddr, sizeof(servaddr)) == SOCKET_ERROR) {
        printf("Error: %d\n", WSAGetLastError());
        return 1;
    }
    
    printf("UDP instance is running...");
    
    int n;
    
    n = recvfrom(sockfd, buffer, 4096, 0, (struct sockaddr *)&clientaddr, &clientaddrlen);
    buffer[n] = '\0';
    printf("Message retrieved from Suizim or any other client: %s\n", buffer);
    
    sendto(sockfd, (const char *)buffer, strlen(buffer), MSG_CONFIRM, (const struct sockaddr *) &cliaddr, len);
    printf("The message has been sent to client without any issue.\n");
    
    WSACleanup();
    closesocket(sockfd);
    
    return 0;
}
