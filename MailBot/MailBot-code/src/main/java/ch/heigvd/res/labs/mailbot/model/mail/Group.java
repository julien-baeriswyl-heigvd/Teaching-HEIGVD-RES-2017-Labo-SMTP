package ch.heigvd.res.labs.mailbot.model.mail;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * 
 * 
 * @author Julien Baeriswyl    [CREATED BY] (julien.baeriswyl@heig-vd.ch,         julien-baeriswyl-heigvd)
 * @author Iando  Rafidimalala [CREATED BY] (iando.rafidimalalathevoz@heig-vd.ch, Mantha32)
 * @since  2017-04-06
 */

public class Group
{
    private List<Person> people;

    public Group (Person... people)
    {
        this.people = new ArrayList<>();

        for (Person p : people)
        {
            add(p);
        }
    }

    public Group (List<Person> people)
    {
        this.people = people;
    }

    public void add (Person p)
    {
        people.add(p);
    }

    public int size ()
    {
        return people.size();
    }

    public List<Person> getList ()
    {
        return people;
    }
}
