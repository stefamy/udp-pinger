import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/**
 * UDP UDPClient implementation with associate methods to send and receive data.
 */
public class UDPClient {

  private DatagramSocket socket;

  /**
   * Constructs a new UDP Client.
   */
  public UDPClient() {
    this.socket = null;
  }

  /**
   * Returns the DatagramSocket for this client.
   *
   * @return socket for this client, as a DatagramSocket.
   */
  public DatagramSocket getSocket() {
    return this.socket;
  }

  /**
   * Given a timeout length (in seconds), sets the socket for this client as an open
   * DatagramSocket.
   *
   * @param timeoutLength Timeout length for the client to wait for packets from server (in
   * seconds).
   */
  public void openSocket(int timeoutLength) {
    try {
      this.socket = new DatagramSocket();
      this.socket.setSoTimeout(timeoutLength);
    } catch (SocketException e) {
      System.out.println("Error creating datagram socket.");
    }
  }

  /**
   * Given an output message and server socket information, creates a DatagramPacket and sends it
   * from this client to the passed socket info.
   *
   * @param outputMsg - The message to send to the server, as a String.
   * @param socket - The server socket to send the message to, as a SocketData object.
   * @return Boolean, true if packet was sent, false if exception occurs.
   */
  public Boolean send(String outputMsg, SocketData socket) {
    byte[] buffer = outputMsg.getBytes();
    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, socket.getAddress(),
        socket.getPort());
    try {
      this.socket.send(packet);
      return true;
    } catch (IOException e) {
      System.out.println("I/O error encountered. Packet not sent.");
      return false;
    }
  }

  /**
   * Receives DatagramPacket at this socket and returns the result.
   *
   * @param buffer - The buffer to use for the received DatagramPacket, as a byte[].
   * @return The Datagram packet received by this client (may be null if timeout length reached and
   * no packet received).
   */
  public DatagramPacket receive(byte[] buffer) {
    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
    try {
      this.socket.receive(packet);
      return packet;
    } catch (SocketTimeoutException e) {
      System.out.println("Packet not received.");
    } catch (IOException e) {
      System.out.println("I/O error encountered when receiving packet.");
    }
    return null;
  }

  /**
   * Closes the socket for this client.
   */
  public void closeSocket() {
    this.socket.close();
  }
}

