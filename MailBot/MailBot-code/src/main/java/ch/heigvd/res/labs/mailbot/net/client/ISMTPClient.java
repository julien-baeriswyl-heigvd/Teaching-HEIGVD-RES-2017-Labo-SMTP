package ch.heigvd.res.labs.mailbot.net.client;

import ch.heigvd.res.labs.mailbot.model.mail.Mail;
import java.io.IOException;

/**
 * SMTP client interface.
 * 
 * @author Julien Baeriswyl    [CREATED BY] (julien.baeriswyl@heig-vd.ch,         julien-baeriswyl-heigvd)
 * @author Iando  Rafidimalala [CREATED BY] (iando.rafidimalalathevoz@heig-vd.ch, Mantha32)
 * @since  2017-04-06
 */
public interface ISMTPClient
{
    /**
     * Establishes a connection with the server, given its IP address or DNS name
     * and its port.
     *
     * @param server the IP address or DNS name of the servr
     * @param port the TCP port on which the server is listening
     * @throws java.io.IOException
     */
    void connect (String server, int port) throws IOException;

    /**
     * Disconnects from the server by issuing the 'QUIT' command.
     *
     * @throws IOException
     */
    void disconnect () throws IOException;

    /**
     * Checks if the client is connected with the server
     *
     * @return true if the client is connected with the server
     */
    boolean isConnected ();

    /**
     * Process mail sending, from sender to recipients (To, Cc, Bcc).
     *
     * @param mail to send
     * @throws IOException if sending mail on network failed
     */
    void sendMail (Mail mail) throws IOException;
}
