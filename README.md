# UDPClient Ping Program  

This client ping program sends a simple ping message to a server, receiving a corresponding pong message back and determining the delay between when the client sent the ping message and received the pong message. The client will wait up to 1s to receive the pong, or will assume the packet was lost.

## To run:  

1) Run server
   - **cd** into the **udp-pinger/udpserver/** directory  
   - Type **python udp-server.py** and hit **return**
   - You should now see a message that the UDP server is running
   
2) Compile client code
   - Open another command line window
   - **cd** into the **udp-pinger/udpclient/src** directory  
   - Type **javac ClientPingApp.java** and hit **return**
   - You should now see three new .class files in the src directory
   
3) Run client
   - In the same command line window as step 2):
   - Type **java ClientPingApp**  
   - The client program should now run and terminate when complete, printing the results to the console.  
     (Example output included in screenshot below.)

4) Close server
   - When complete, return to the server command line window and exit by typing **ctrl+c** and hitting **return**