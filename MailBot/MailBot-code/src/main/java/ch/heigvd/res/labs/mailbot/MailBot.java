package ch.heigvd.res.labs.mailbot;

import ch.heigvd.res.labs.mailbot.cfg.ConfigManager;
import ch.heigvd.res.labs.mailbot.model.mail.Group;
import ch.heigvd.res.labs.mailbot.model.prank.Prank;
import ch.heigvd.res.labs.mailbot.model.prank.PrankGenerator;

import java.io.*;

/**
 * MailBot main program. Load files and launch prank campaign.
 * 
 * @author Julien Baeriswyl    [CREATED BY] (julien.baeriswyl@heig-vd.ch,         julien-baeriswyl-heigvd)
 * @author Iando  Rafidimalala [CREATED BY] (iando.rafidimalalathevoz@heig-vd.ch, Mantha32)
 * @since  2017-04-06
 */
public class MailBot
{
    public static void main (String[] args) throws IllegalArgumentException, IOException
    {
        final char   CSV_SEPARATOR = ',';
        final String MSG_SEPARATOR = "----";

        final String CFG_FILENAME     = "client.properties",
                     MSG_FILENAME     = "messages.utf8",
                     VICTIMS_FILENAME = "victims.utf8";

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

        PrankGenerator pg = new PrankGenerator(new ConfigManager(new File(cfgDir.getPath() + File.separator + CFG_FILENAME)));
        pg.generate(
            new Prank(new FileInputStream(new File(dataDir.getPath() + File.separator + MSG_FILENAME)), MSG_SEPARATOR),
            new Group(new FileInputStream(new File(dataDir.getPath() + File.separator + VICTIMS_FILENAME)), CSV_SEPARATOR)
        );
    }
}
