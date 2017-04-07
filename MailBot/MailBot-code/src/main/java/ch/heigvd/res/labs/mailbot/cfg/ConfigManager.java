package ch.heigvd.res.labs.mailbot.cfg;

import java.io.*;
import java.util.Properties;

/**
 * Load MailBot configuration and provide access to specific properties.
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

    /**
     * Construct configuration based on parsed file.
     *
     * @remark no control done over format
     *
     * @param cfg file to parse
     * @throws IOException if reading file failed
     */
    public ConfigManager (File cfg) throws IOException
    {
        load(cfg);
    }

    @Override
    public void load (File cfg) throws IOException
    {
        properties = new Properties();
        properties.load(new BufferedReader(new InputStreamReader(new FileInputStream(cfg), "UTF-8")));
    }

    /**
     * Get property from configuration
     *
     * @param name key to access property value
     * @return property value
     */
    public String getSetting (Setting name)
    {
        return properties.getProperty(name.name());
    }
}
