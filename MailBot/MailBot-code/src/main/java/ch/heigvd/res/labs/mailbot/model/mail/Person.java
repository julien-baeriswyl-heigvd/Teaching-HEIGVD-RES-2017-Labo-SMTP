package ch.heigvd.res.labs.mailbot.model.mail;

/**
 * Model of people, with name and email.
 * 
 * @author Julien Baeriswyl    [CREATED BY] (julien.baeriswyl@heig-vd.ch,         julien-baeriswyl-heigvd)
 * @author Iando  Rafidimalala [CREATED BY] (iando.rafidimalalathevoz@heig-vd.ch, Mantha32)
 * @since  2017-04-06
 */
public class Person
{
    private String firstname,
                   lastname,
                   email;

    public Person (String firstname, String lastname, String email)
    {
        this.firstname = firstname;
        this.lastname  = lastname;
        this.email     = email;
    }

    public String getFirstname ()
    {
        return firstname;
    }

    public String getLastname ()
    {
        return lastname;
    }

    public String getEmail ()
    {
        return email;
    }

    @Override
    public String toString ()
    {
        return firstname + " " + lastname + " <" + email + ">";
    }
}
