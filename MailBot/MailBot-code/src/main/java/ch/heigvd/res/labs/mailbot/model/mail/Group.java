package ch.heigvd.res.labs.mailbot.model.mail;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Group of people, as list. With file parser.
 * 
 * @author Julien Baeriswyl    [CREATED BY] (julien.baeriswyl@heig-vd.ch,         julien-baeriswyl-heigvd)
 * @author Iando  Rafidimalala [CREATED BY] (iando.rafidimalalathevoz@heig-vd.ch, Mantha32)
 * @since  2017-04-06
 */
public class Group extends ArrayList<Person>
{
    public Group (List<Person> people)
    {
        super();
        addAll(people);
    }

    public Group (Person... people)
    {
        this(Arrays.asList(people));
    }

    /**
     * Construct group based on stream content with format 'firstname[separator]lastname[separator]email' on each line.
     *
     * @remark no control done over format
     *
     * @param stream          content to parse
     * @param fieldSeparator  separate each data field
     * @throws IOException if reading stream failed
     */
    public Group (InputStream stream, char fieldSeparator) throws IOException
    {
        BufferedReader br    = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

        for (String s = br.readLine(); s != null; s = br.readLine())
        {
            int idx0 = s.indexOf(fieldSeparator),
                idx1 = s.indexOf(fieldSeparator, idx0 + 1);
            add(new Person(s.substring(0, idx0), s.substring(idx0 + 1, idx1), s.substring(idx1 + 1)));
        }
    }

    /**
     * Build subgroup from current group.
     *
     * @param fromIndex index on first person (included)
     * @param toIndex   index on last  person (excluded)
     * @return subgroup
     */
    public Group subGroup (int fromIndex, int toIndex)
    {
        return new Group(subList(fromIndex, toIndex));
    }
}
