package ch.heigvd.res.labs.mailbot.model.prank;

import ch.heigvd.res.labs.mailbot.cfg.ConfigManager;
import ch.heigvd.res.labs.mailbot.model.mail.Group;
import ch.heigvd.res.labs.mailbot.model.mail.Mail;
import ch.heigvd.res.labs.mailbot.model.mail.Person;
import ch.heigvd.res.labs.mailbot.net.client.SMTPClient;

import java.io.IOException;

/**
 * Generate forged mail pseudo-randomly, based on configuration and victims.
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
        // JBL: compute subgroups sizes
        int groupNB     = Integer.parseInt(cfg.getSetting(ConfigManager.Setting.NumberOfGroups)),
            groupSize   = victims.size() / groupNB,
            remainGroup = victims.size() % groupNB;

        if (groupSize < GROUP_SIZE_MIN)
        {
            throw new IllegalArgumentException("at least one group size is less than " + GROUP_SIZE_MIN);
        }

        // JBL: connect to server specified in configuration
        SMTPClient client = new SMTPClient();
        client.connect(
            cfg.getSetting(ConfigManager.Setting.SmtpServerAddress),
            Integer.parseInt(cfg.getSetting(ConfigManager.Setting.SmtpServerPort))
        );

        // JBL: Loop variables
        Group  cc = new Group(new Person("", "", cfg.getSetting(ConfigManager.Setting.WitnessToCC))),
               subGroup;
        int    idx;
        Person sender;

        // JBL: prepare groups and send mails
        for (int fromIdx = 0, toIdx = groupSize + remainGroup; toIdx <= victims.size(); fromIdx += (toIdx - fromIdx), toIdx += groupSize)
        {
            subGroup = victims.subGroup(fromIdx, toIdx);
            idx      = (int)(Math.random() * subGroup.size());
            sender   = subGroup.get(idx);
            subGroup.remove(idx);
            client.sendMail(new Mail(sender, subGroup, cc, new Group(), pranks.get((int)(Math.random() * pranks.size()))));
        }

        client.disconnect();
    }
}
