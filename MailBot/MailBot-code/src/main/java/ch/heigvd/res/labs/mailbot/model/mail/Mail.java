package ch.heigvd.res.labs.mailbot.model.mail;

/**
 * Represent mail structure with people and data.
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

    /**
     * Prepare formatted header.
     *
     * @param sb     String stream to write in
     * @param header text value of header
     * @param people concerned by header (To, Cc, Bcc)
     */
    private void prepareHeader (StringBuilder sb, String header, Group people)
    {
        if (people.isEmpty())
        {
            return;
        }

        sb.append(header);
        sb.append(people.get(0));
        for (int i = 1; i < people.size(); ++i)
        {
            sb.append(", ");
            sb.append(people.get(i));
        }

        sb.append("\r\n");
    }

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

    @Override
    public String toString () // get mail body
    {
        StringBuilder sb = new StringBuilder();
        sb.append("From: ");
        sb.append(from);
        sb.append("\r\n");

        prepareHeader(sb, "To: ", to);
        prepareHeader(sb, "Cc: ", cc);

        sb.append(data);
        return sb.toString();
    }
}
