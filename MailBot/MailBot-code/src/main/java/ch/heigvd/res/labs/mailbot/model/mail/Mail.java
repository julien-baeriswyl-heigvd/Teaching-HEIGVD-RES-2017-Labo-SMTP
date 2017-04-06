package ch.heigvd.res.labs.mailbot.model.mail;

/**
 * 
 * 
 * @author Julien Baeriswyl    [CREATED BY] (julien.baeriswyl@heig-vd.ch,         julien-baeriswyl-heigvd)
 * @author Iando  Rafidimalala [CREATED BY] (iando.rafidimalalathevoz@heig-vd.ch, Mantha32)
 * @since  2017-04-06
 */
public class Mail
{
    private Person from;
    private Group  to,
                   cc,
                   bcc;

    private String data;

    public Mail (Person from, Group to, Group cc, Group bcc, String data)
    {
        this.from = from;
        this.to   = to;
        this.cc   = cc;
        this.bcc  = bcc;
        this.data = data;
    }

    public Person getFrom ()
    {
        return from;
    }

    public Group getTo ()
    {
        return to;
    }

    public Group getCc ()
    {
        return cc;
    }

    public Group getBcc ()
    {
        return bcc;
    }

    public String getData ()
    {
        return data;
    }
}
