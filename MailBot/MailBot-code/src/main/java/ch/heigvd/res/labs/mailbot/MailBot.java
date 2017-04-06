package ch.heigvd.res.labs.mailbot;

import ch.heigvd.res.labs.mailbot.cfg.ConfigManager;
import ch.heigvd.res.labs.mailbot.model.mail.Person;
import ch.heigvd.res.labs.mailbot.model.mail.Group;
import ch.heigvd.res.labs.mailbot.model.mail.Mail;
import ch.heigvd.res.labs.mailbot.net.client.SMTPClient;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author Julien Baeriswyl    [CREATED BY] (julien.baeriswyl@heig-vd.ch,         julien-baeriswyl-heigvd)
 * @author Iando  Rafidimalala [CREATED BY] (iando.rafidimalalathevoz@heig-vd.ch, Mantha32)
 * @since  2017-04-06
 */
public class MailBot
{
    private static List<Person> victimsFromFile (File file) throws IOException
    {
        BufferedReader br      = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        List<Person>   victims = new ArrayList<>();

        for (String s = br.readLine(); s != null; s = br.readLine())
        {
            victims.add(new Person(s));
        }

        return victims;
    }

    private static List<String> messagesFromFile (File file) throws IOException
    {
        String separator = "----";
        BufferedReader br  = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        List<String>   msg = new ArrayList<>();
        StringBuilder  sb  = new StringBuilder();

        for (String s = br.readLine(); s != null; s = br.readLine())
        {
            if (s.equals(separator))
            {
                msg.add(sb.toString());
                sb = new StringBuilder();
            }
            else
            {
                sb.append(s);
                sb.append(String.format("%n"));
            }
        }

        String last = sb.toString();

        if (!last.isEmpty())
        {
            msg.add(last);
        }

        return msg;
    }

    public static void main (String[] args) throws IllegalArgumentException, IOException
    {
        if (args.length < 2)
        {
            throw new IllegalArgumentException("invalid number of arguments, received: " + args.length + ", expected: 2");
        }

        File cfgDir  = new File(args[0]),
             dataDir = new File(args[1]);

        if (!cfgDir.isDirectory() || !dataDir.isDirectory())
        {
            throw new IllegalArgumentException("invalid CLI arguments, folders does not exists");
        }

        ConfigManager cfg     = new ConfigManager(new File(cfgDir.getPath() + File.separator + "client.properties"));
        List<Person>  victims = victimsFromFile(new File(dataDir.getPath() + File.separator + "victims.utf8"));
        List<String>  msg     = messagesFromFile(new File(dataDir.getPath() + File.separator + "messages.utf8"));

        SMTPClient client = new SMTPClient();
        client.connect(cfg.getSetting(ConfigManager.Setting.SmtpServerAddress), Integer.parseInt(cfg.getSetting(ConfigManager.Setting.SmtpServerPort)));

        int gsize     = victims.size() / msg.size(),
            remainder = victims.size() % msg.size();

        for (int i = 1; i < msg.size(); ++i)
        {
            Group g = new Group();
            for (int j = 1; j < gsize; ++j)
            {
                g.add(victims.get((i - 1) * gsize + j));
            }
            client.sendMail(new Mail(victims.get((i - 1) * gsize), g, new Group(), new Group(), msg.get(i)));
        }

        client.disconnect();
    }
}
