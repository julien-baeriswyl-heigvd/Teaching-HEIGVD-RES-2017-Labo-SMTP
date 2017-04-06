package ch.heigvd.res.labs.mailbot.model.mail;

import java.util.List;
import java.util.ArrayList;
import java.io.File;

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
        this.people = new ArrayList<Person>();

        for (Person p : people)
        {
            this.people.add(p);
        }
    }

    public Group (List<Person> people)
    {
        this.people = new ArrayList<Person>(people);
    }

    public Group (File people)
    {

    }
}
