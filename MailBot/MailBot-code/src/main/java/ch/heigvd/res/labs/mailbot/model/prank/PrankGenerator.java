package ch.heigvd.res.labs.mailbot.model.prank;

import ch.heigvd.res.labs.mailbot.cfg.ConfigManager;
import ch.heigvd.res.labs.mailbot.model.mail.Group;
import ch.heigvd.res.labs.mailbot.model.mail.Mail;
import ch.heigvd.res.labs.mailbot.model.mail.Person;
import ch.heigvd.res.labs.mailbot.net.client.SMTPClient;

import java.io.IOException;

/**
 *
 *
 * @author Julien Baeriswyl    [CREATED BY] (julien.baeriswyl@heig-vd.ch,         julien-baeriswyl-heigvd)
 * @author Iando  Rafidimalala [CREATED BY] (iando.rafidimalalathevoz@heig-vd.ch, Mantha32)
 * @since  2017-04-06
 */
public class PrankGenerator
{
    public static final int GROUP_SIZE_MIN = 3;

    private ConfigManager cfg;

    public PrankGenerator (ConfigManager cfg)
    {
        this.cfg = cfg;
    }

    public void generate (Prank pranks, Group victims) throws IOException, IllegalArgumentException
    {
        if (victims.size() < GROUP_SIZE_MIN)
        {
            throw new IllegalArgumentException("group size is less than " + GROUP_SIZE_MIN);
        }

        SMTPClient client = new SMTPClient();
        client.connect(
            cfg.getSetting(ConfigManager.Setting.SmtpServerAddress),
            Integer.parseInt(cfg.getSetting(ConfigManager.Setting.SmtpServerPort))
        );

        int    idx    = (int)(Math.random() * victims.size());
        Person sender = victims.get(idx);

        victims.remove(idx);
        client.sendMail(new Mail(sender, victims, new Group(new Person("", "", cfg.getSetting(ConfigManager.Setting.WitnessToCC))), new Group(), pranks.get((int)(Math.random() * pranks.size()))));

        client.disconnect();
    }
}
