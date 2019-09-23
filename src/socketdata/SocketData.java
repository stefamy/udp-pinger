package socketdata;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Class holding socket data (port and address).
 */
public class SocketData {

  private static final int DEFAULT_PORT_NUMBER = 12000;
  private static final int MAX_PORT_NUMBER = 65535;
  private int port;
  private InetAddress address;

  /**
   * Constructs a new SocketData object using the default port number and address.
   */
  public SocketData() {
    this.port = DEFAULT_PORT_NUMBER;
    this.address = InetAddress.getLoopbackAddress();
  }

  /**
   * Constructs a new SocketData object using the passed port number and address.
   *
   * @param port - Port number to set this SocketData to, as an int.
   * @param address - Address to set this SocketData to, as an InetAddress object.
   */
  public SocketData(int port, InetAddress address) {
    this.address = address;
    if (this.portInRange(port)) {
      this.port = port;
    } else {
      System.out.println(String.format("An out of range port number was passed. Defaulting to port "
          + "%d.", DEFAULT_PORT_NUMBER));
      this.port = DEFAULT_PORT_NUMBER;
    }
  }

  /**
   * Returns the port number for this SocketData object.
   *
   * @return Port number, as an int.
   */
  public int getPort() {
    return this.port;
  }

  /**
   * Given a string value of a port number, checks if it can be converted to a number, and if so,
   * sets the port number as it. If not, prints appropriate error message and takes no action.
   *
   * @param portInput, port number to set this SocketData's port number to, as a String.
   */
  public void setPort(String portInput) {
    try {
      int portInt = Integer.valueOf(portInput);
      if (this.portInRange(portInt)) {
        this.port = portInt;
      } else {
        System.out
            .println(String.format("An out of range port number was passed. Defaulting to port "
                + "%d.", DEFAULT_PORT_NUMBER));
      }
    } catch (NumberFormatException e) {
      System.out.println(String.format("An invalid port was passed. Defaulting to port %d.",
          DEFAULT_PORT_NUMBER));
    }
  }

  /**
   * Returns the InetAddress for this SocketData object.
   *
   * @return Address, as an InetAddress object.
   */
  public InetAddress getAddress() {
    return this.address;
  }

  /**
   * Given a string value of a host address, checks if it can be converted to an InetAddress, and if
   * so, sets address as it. If not, prints appropriate error message and takes no action.
   *
   * @param hostname, hostname to set this SocketData's InetAddress to, as a String.
   */
  public void setAddress(String hostname) {
    try {
      this.address = InetAddress.getByName(hostname);
    } catch (UnknownHostException e) {
      System.out.println("An unknown host was passed. Defaulting to localhost address.");
    }
  }

  /**
   * Checks if a given port is in valid range (must be between 0 and 65535, inclusive).
   *
   * @param port - Port number to check for validity, as an int.
   * @return Boolean true or false if port is valid or not.
   */
  private Boolean portInRange(int port) {
    return (port >= 0 && port <= MAX_PORT_NUMBER);
  }

}