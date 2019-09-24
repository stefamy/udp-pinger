import static java.lang.System.exit;

import java.net.DatagramPacket;
import socketdata.SocketData;

/**
 * UDP Client Program.
 *
 * Creates a UDP client, generates packets to send to ping server to track response time and number
 * of packets lost.
 */
public class ClientPingApp {

  private static final int TIMEOUT_LENGTH_SECONDS = 1;
  private static final int TOTAL_NUM_PINGS = 10;
  private static final String PING = "Ping";

  /**
   * Runs through the following steps using the passed client, terminating the program once
   * complete:
   * - Creates and sends "ping" message packet to the server socket passed.
   * - Waits up to 1s to receive a response packet back from the server.
   * - Tracks the round trip time (RTT) for each send-response.
   * - Tracks if packets are lost (no message received within 1s).
   * - Prints messages to the console regarding the results.
   * - Repeats above steps until 10 pings sent and tracked.
   * - Ends the program.
   *
   * @param client - The client to run the program on.
   * @param serverSocketData - The SocketData (address and port) to send the pings to.
   */
  public static void run(UDPClient client, SocketData serverSocketData) {
    Long start, finish, nanos;
    DatagramPacket received;
    for (int count = 1; count <= TOTAL_NUM_PINGS; count++) {
      System.out.print(String.format("\n%d) %s... ", count, PING));
      start = System.nanoTime();
      client.send(PING, serverSocketData);
      received = client.receive(PING.getBytes());
      finish = System.nanoTime();
      if (received != null) {
        System.out.print("Pong!");
        nanos = finish - start;
        System.out.print(String.format(" (RTT: %s nanoseconds)\n", nanos.toString()));
      }
    }
  }

  /**
   * Prints a message to the console, closes the socket passed, and exits the program.
   *
   * @param client - Client to close the socket for.
   */
  public static void end(UDPClient client) {
    System.out.println("\nProgram complete.");
    client.closeSocket();
    exit(0);
  }

  /**
   * Main program class. Sets up data, runs program, and terminates program.
   */
  public static void main(String[] args) {
    // Set up UDP Client and server data (with localhost socket address and default port number):
    SocketData serverSocketData = new SocketData();
    UDPClient client = new UDPClient();
    client.openSocket(TIMEOUT_LENGTH_SECONDS);
    System.out.println("Client ready.\n");
    System.out.println(String.format("Pinging server address %s on port %s",
        serverSocketData.getAddress(), serverSocketData.getPort()));

    // Open the socket with the 1s timeout:
    if (client.getSocket() != null) {
      // Run the program:
      ClientPingApp.run(client, serverSocketData);
      ClientPingApp.end(client);
    } else {
      System.out.println("Datagram creation unsuccessful. Exiting.");
      exit(1);
    }
  }
}
