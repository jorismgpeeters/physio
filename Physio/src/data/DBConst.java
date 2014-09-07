
package data;

/**
 * Klasse met constanten voor het gebruikte DBMS
 * @author lievenp
 */
public class DBConst {
    public static final String PADNAAM = "/Users/lievenp/HomePhysio/physio2/Physio/HomePhysioDB.fdb";
    public static final String DRIVERNAAM = "org.firebirdsql.jdbc.FBDriver";
    public static final String URL = "jdbc:firebirdsql://localhost/" + PADNAAM;
    public static final String GEBRUIKERSNAAM = "SYSDBA";
    public static final String WACHTWOORD = "masterkey";
}
