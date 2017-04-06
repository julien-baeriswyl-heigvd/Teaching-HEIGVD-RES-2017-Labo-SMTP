package ch.heigvd.res.labs.mailbot.model.mail;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**
 * 
 * 
 * @author Julien Baeriswyl    [CREATED BY] (julien.baeriswyl@heig-vd.ch,         julien-baeriswyl-heigvd)
 * @author Iando  Rafidimalala [CREATED BY] (iando.rafidimalalathevoz@heig-vd.ch, Mantha32)
 * @since  2017-04-06
 */

public class Group implements Iterable<Person>
{
    private List<Person> people;

    public Group (Person... people)
    {
        this.people = new ArrayList<>();

        for (Person p : people)
        {
            this.people.add(p);
        }
    }

    public Group (List<Person> people)
    {
        this.people = new ArrayList<>(people);
    }

    public int size ()
    {
        return people.size();
    }

    public Iterator<Person> iterator ()
    {
        return people.iterator();
    }
}
