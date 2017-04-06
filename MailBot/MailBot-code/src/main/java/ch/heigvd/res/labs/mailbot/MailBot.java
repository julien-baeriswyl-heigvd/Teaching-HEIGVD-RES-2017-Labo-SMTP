package ch.heigvd.res.labs.mailbot;

import ch.heigvd.res.labs.mailbot.cfg.ConfigManager;
import ch.heigvd.res.labs.mailbot.model.mail.Person;

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

    private static List<String> messageFromFile (File file) throws IOException
    {
        List<String> msg = new ArrayList<>();
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

        ConfigManager cfg = new ConfigManager(new File(cfgDir.getPath() + File.separator + "client.properties"));

        for (ConfigManager.Setting s : ConfigManager.Setting.values())
        {
            System.out.println(cfg.getSetting(s));
        }

        List<Person> victims = victimsFromFile(new File(dataDir.getPath() + File.separator + "victims.utf8"));

        for (Person p : victims)
        {
            System.out.println(p);
        }
    }
}
