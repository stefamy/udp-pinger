import static java.lang.System.exit;

import java.net.DatagramPacket;
import java.net.SocketException;
import socketdata.SocketData;

public class ClientPingApp {

  private static final int NUM_OPTIONAL_ARGS = 2;


  public static void run(UDPClient client) {
    for (Integer count = 1; count <= 10; count++) {
      String message = count.toString();
      System.out.println(String.format("\n- - - - - - Test %s - - - - - -", message));
      System.out.println("\nPing...");
      Long start = System.nanoTime();
      client.send(message);
      DatagramPacket received = client.receive(message.getBytes());
      Long finish = System.nanoTime();
      if (received != null) {
        System.out.print("...Pong! ");
        Long nanos = finish - start;
        String timeTaken = nanos.toString();
        System.out.print(String.format("(RTT: %s nanoseconds)\n", timeTaken));
      }
    }
    System.out.println("\nProgram complete.");
    client.close();
    exit(0);
  }

  /**
   * Main program class. Can optionally pass command lne arg with port number and host address to
   * bind to. If one or both are missing, defaults to port 1500 on localhost address.
   *
   * @param args - Command line arguments. Optionally, can supply two args: args[0]: Port number
   * args[1]: Host address to bind server to If one or both are missing or invalid, defaults to port
   * number 1500 on localhost address.
   */
  public static void main(String[] args) throws SocketException {
    // Set up socket data:
    SocketData socketData = new SocketData();

    // If optional args supplied, process the data:
    if (args.length == NUM_OPTIONAL_ARGS) {
      socketData.setPort(args[0]);
      socketData.setAddress(args[1]);
    }

    // Run the program:
    ClientPingApp.run(new UDPClient(socketData, 1));
  }
}
