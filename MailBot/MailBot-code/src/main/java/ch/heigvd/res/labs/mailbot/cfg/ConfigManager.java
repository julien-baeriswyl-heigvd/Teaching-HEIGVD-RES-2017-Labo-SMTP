package ch.heigvd.res.labs.mailbot.cfg;

import java.io.*;
import java.util.Properties;

/**
 * 
 * 
 * @author Julien Baeriswyl    [CREATED BY] (julien.baeriswyl@heig-vd.ch,         julien-baeriswyl-heigvd)
 * @author Iando  Rafidimalala [CREATED BY] (iando.rafidimalalathevoz@heig-vd.ch, Mantha32)
 * @since  2017-04-06
 */

public class ConfigManager implements IConfigManager
{
    public enum Setting
    {
        SmtpServerAddress,
        SmtpServerPort,
        NumberOfGroups,
        WitnessToCC;
    };

    private Properties properties;

    public ConfigManager(File cfg) throws IOException
    {
        load(cfg);
    }

    @Override
    public void load (File cfg) throws IOException
    {
        properties = new Properties();
        properties.load(new BufferedReader(new InputStreamReader(new FileInputStream(cfg), "UTF-8")));
    }

    public String getSetting (Setting name)
    {
        return properties.getProperty(name.name());
    }
}
