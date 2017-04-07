package ch.heigvd.res.labs.mailbot.model.prank;

import java.io.*;
import java.util.ArrayList;

/**
 * Group of joke messages as list. With parser.
 *
 * @author Julien Baeriswyl    [CREATED BY] (julien.baeriswyl@heig-vd.ch,         julien-baeriswyl-heigvd)
 * @author Iando  Rafidimalala [CREATED BY] (iando.rafidimalalathevoz@heig-vd.ch, Mantha32)
 * @since  2017-04-06
 */
public class Prank extends ArrayList<String>
{
    public Prank ()
    {
        super();
    }

    /**
     * Construct list of data messages, based on stream content.
     *
     * @param stream     content to parse
     * @param separator  separator (alone on a line) of message
     * @throws IOException if reading stream failed
     */
    public Prank (InputStream stream, String separator) throws IOException
    {
        this();
        add(stream, separator);
    }

    /**
     * Append list of data messages, based on stream content.
     *
     * @param stream     content to parse
     * @param separator  separator (alone on a line) of message
     * @throws IOException if reading stream failed
     */
    public void add (InputStream stream, String separator) throws IOException
    {
        BufferedReader br  = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        StringBuilder  sb  = new StringBuilder();

        for (String s = br.readLine(); s != null; s = br.readLine())
        {
            if (s.equals(separator))
            {
                add(sb.toString());
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
            add(last);
        }
    }
}
