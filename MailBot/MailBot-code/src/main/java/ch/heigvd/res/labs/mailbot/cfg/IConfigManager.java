package ch.heigvd.res.labs.mailbot.cfg;

import java.io.File;
import java.io.IOException;

/**
 * 
 * 
 * @author Julien Baeriswyl    [CREATED BY] (julien.baeriswyl@heig-vd.ch,         julien-baeriswyl-heigvd)
 * @author Iando  Rafidimalala [CREATED BY] (iando.rafidimalalathevoz@heig-vd.ch, Mantha32)
 * @since  2017-04-06
 */
public interface IConfigManager
{
    void load (File cfg) throws IOException;
}
