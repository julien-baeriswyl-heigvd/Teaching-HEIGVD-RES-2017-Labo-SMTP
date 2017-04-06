package ch.heigvd.res.labs.mailbot.net.client;

import ch.heigvd.res.labs.mailbot.model.mail.Mail;
import ch.heigvd.res.labs.mailbot.net.protocol.SMTP;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * 
 * 
 * @author Julien Baeriswyl    [CREATED BY] (julien.baeriswyl@heig-vd.ch,         julien-baeriswyl-heigvd)
 * @author Iando  Rafidimalala [CREATED BY] (iando.rafidimalalathevoz@heig-vd.ch, Mantha32)
 * @since  2017-04-06
 */
public class SMTPClient implements ISMTPClient
{
    private static final Logger LOG = Logger.getLogger(SMTPClient.class.getName());

    private Socket         clientSocket;
    private PrintWriter    pw;
    private BufferedReader br;

    @Override
    public void connect (String server, int port) throws IOException
    {
        clientSocket = new Socket(server, port);
        if (isConnected())
        {
            // JBL: open input and output stream
            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            pw = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            // JBL: destroy initial text message send by server
            br.readLine();
        }
    }

    @Override
    public void disconnect() throws IOException
    {
        // JBL: ?
        if (true)
        {
            pw.close();
            br.close();
            clientSocket.close();

            pw = null;
            br = null;
            clientSocket = null;
        }
    }

    @Override
    public boolean isConnected ()
    {
        // JBL: test if client socket exists and is connected
        return clientSocket != null && clientSocket.isConnected();
    }

    @Override
    public boolean sendMail (Mail mail) throws IOException
    {
        return false;
    }
}
