package ch.heigvd.res.labs.mailbot.net.protocol;

/**
 * SMTP Protocol model.
 * 
 * @author Julien Baeriswyl    [CREATED BY] (julien.baeriswyl@heig-vd.ch,         julien-baeriswyl-heigvd)
 * @author Iando  Rafidimalala [CREATED BY] (iando.rafidimalalathevoz@heig-vd.ch, Mantha32)
 * @since  2017-04-06
 */
public class SMTP
{
    public static class ReturnCode
    {
        public static final int SERVICE_READY      = 220,
                                SERVICE_CLOSING    = 221,
                                REQUEST_OK         = 250,
                                USR_NOT_LOCAL      = 251,
                                USR_NOT_VRFY       = 252,
                                START_INPUT        = 354,
                                CMD_SYNTAX_ERROR   = 500,
                                PARAM_SYNTAX_ERROR = 501,
                                CMD_NOT_EXISTS     = 502,
                                BAD_SEQ            = 503,
                                CMD_NO_PARAM       = 504,
                                REJECTED           = 550,
                                TRANSACTION_FAILED = 554;
    }

    /**
     * Represents commands of SMTP protocol.
     */
    public static class Command
    {
        private static final String ENTER = "\r\n";
        private String  mnemonic;
        private int     codeSuccess;
        private boolean hasField;

        private Command (String mnemonic, int codeSuccess, boolean hasField)
        {
            this.mnemonic    = mnemonic;
            this.codeSuccess = codeSuccess;
            this.hasField    = hasField;
        }

        public int getCodeSuccess() {
            return codeSuccess;
        }

        @Override
        public String toString ()
        {
            return mnemonic;
        }

        /**
         * Return prepared command string, in a line (line break included)
         *
         * @param data field append to mnemonic, if required
         * @return command string
         */
        public String prepare (String data)
        {
            return mnemonic + " " + (hasField ? data : "") + ENTER;
        }

        /**
         * Extract return code from given server response.
         *
         * @param response received by server
         * @return code
         */
        public static int parseCode (String response)
        {
            return Integer.parseInt(response.substring(0, 3));
        }
    }

    // JBL: list of known command
    public static final Command EHLO      = new Command("EHLO", ReturnCode.REQUEST_OK, true),
                                MAIL_FROM = new Command("MAIL FROM:", ReturnCode.REQUEST_OK, true),
                                RCPT_TO   = new Command("RCPT TO:", ReturnCode.REQUEST_OK, true),
                                DATA      = new Command("DATA", ReturnCode.START_INPUT, false),
                                ENDDATA   = new Command(Command.ENTER+"."+Command.ENTER, ReturnCode.REQUEST_OK, false),
                                QUIT      = new Command("QUIT", ReturnCode.SERVICE_CLOSING, false);
}
