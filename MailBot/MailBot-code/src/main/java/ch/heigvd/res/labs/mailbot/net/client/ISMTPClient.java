package ch.heigvd.res.labs.mailbot.net.client;

import ch.heigvd.res.labs.mailbot.model.mail.Mail;
import java.io.IOException;

/**
 * 
 * 
 * @author Julien Baeriswyl    [CREATED BY] (julien.baeriswyl@heig-vd.ch,         julien-baeriswyl-heigvd)
 * @author Iando  Rafidimalala [CREATED BY] (iando.rafidimalalathevoz@heig-vd.ch, Mantha32)
 * @since  2017-04-06
 */
public interface ISMTPClient
{
    boolean sendMail (Mail mail) throws IOException;
}
