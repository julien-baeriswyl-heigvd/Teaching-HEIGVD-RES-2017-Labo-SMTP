package ch.heigvd.res.labs.mailbot.net.client;

import ch.heigvd.res.labs.mailbot.model.mail.Mail;
import ch.heigvd.res.labs.mailbot.model.mail.Person;
import ch.heigvd.res.labs.mailbot.net.protocol.SMTP;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * SMTP client, proceeding sequences of commands to connect, disconnect and send mail.
 *
 * @author Julien Baeriswyl    [CREATED BY] (julien.baeriswyl@heig-vd.ch,         julien-baeriswyl-heigvd)
 * @author Iando  Rafidimalala [CREATED BY] (iando.rafidimalalathevoz@heig-vd.ch, Mantha32)
 * @since  2017-04-06
 */
public class SMTPClient implements ISMTPClient
{
    private Socket         clientSocket;
    private PrintWriter    pw;
    private BufferedReader br;
    private String         response;

    /**
     * Retrieve response code.
     *
     * @return parsed server response code
     * @throws IOException if reading answer failed
     */
    private int retrieveCode () throws IOException
    {
        // JBL: wait for line with format <code><space><something>
        do
        {
            response = br.readLine();
        }
        while (response.charAt(3) != ' ');

        // JBL: extract return code
        return SMTP.Command.parseCode(response);
    }

    /**
     * Send command to server through client socket.
     *
     * @param cmd  command token send to server
     * @param data field to send with command
     * @return <code>true</code> if send operation succeed, else <code>false</code>
     * @throws IOException if write operation to server failed
     */
    private boolean sendCommand (SMTP.Command cmd, String data) throws IOException
    {
        // JBL: control socket connexion
        if (!isConnected())
        {
            throw new IOException("client is not connected");
        }

        // JBL: send command
        pw.print(cmd.prepare(data));
        pw.flush();
        if (pw.checkError())
        {
            throw new IOException("failed to send command - `" + cmd + "`");
        }

        // JBL: check server answered properly
        return retrieveCode() == cmd.getCodeSuccess();
    }

    /**
     * Send command to server through client socket.
     *
     * @param cmd  command token send to server
     * @return <code>true</code> if send operation succeed, else <code>false</code>
     * @throws IOException if write operation to server failed
     */
    private boolean sendCommand (SMTP.Command cmd) throws IOException
    {
        return sendCommand(cmd, "");
    }

    @Override
    public void connect (String server, int port) throws IOException
    {
        if (!isConnected())
        {
            clientSocket = new Socket(server, port);

            // JBL: open input and output stream
            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
            pw = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));

            // JBL: check if connection init succeed
            if (retrieveCode() != SMTP.ReturnCode.SERVICE_READY || !sendCommand(SMTP.EHLO, SMTPClient.class.getSimpleName()))
            {
                throw new IOException("SMTP server failed handshake");
            }
        }
    }

    @Override
    public void disconnect() throws IOException
    {
        // JBL: if connected, then disconnect and free resources
        if (isConnected() && sendCommand(SMTP.QUIT))
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
    public void sendMail (Mail mail) throws IOException
    {
        String body = mail.toString();

        for (Person to : mail.getTo())
        {
            sendCommand(SMTP.MAIL_FROM, mail.getFrom().getEmail());
            sendCommand(SMTP.RCPT_TO, to.getEmail());
            sendCommand(SMTP.DATA);
            pw.println(body);
            pw.flush();
            sendCommand(SMTP.ENDDATA);
        }

        for (Person cc : mail.getCc())
        {
            sendCommand(SMTP.MAIL_FROM, mail.getFrom().getEmail());
            sendCommand(SMTP.RCPT_TO, cc.getEmail());
            sendCommand(SMTP.DATA);
            pw.println(body);
            pw.flush();
            sendCommand(SMTP.ENDDATA);
        }

        for (Person bcc : mail.getBcc())
        {
            sendCommand(SMTP.MAIL_FROM, mail.getFrom().getEmail());
            sendCommand(SMTP.RCPT_TO, bcc.getEmail());
            sendCommand(SMTP.DATA);
            pw.println(body);
            pw.flush();
            sendCommand(SMTP.ENDDATA);
        }
    }
}
