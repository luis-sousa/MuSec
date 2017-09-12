/*
 * ESTGF - Escola Superior de Tecnologia e Gestão de Felgueiras 
 * IPP - Instituto Politécnico do Porto 
 * MEI - Mestrado em Engenharia Informática
 * Tese 2015/16 
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class represents an creation and apresentation of a new Window (About
 * Application)
 *
 * @author Luís Sousa - 8090228@estgf.ipp.pt
 * @version 1.0 Jul 15, 2016
 */
public class DB {

    private static final String DRIVERCLASSNAME = "com.mysql.jdbc.Driver";
    private static final String DBNAME = "/alienvault_siem";
    private static final int PORT = 3306;

    /**
     * Construtor.
     */
    public DB() {
    }

    /**
     * Make a connection with Ossim Server Database.
     *
     * @param host Database server IP.
     * @param userName Database username.
     * @param password Database password.
     * @return Database Connection.
     */
    public static Connection getConnection(String host, String userName, String password) {
        Connection con = null;
        try {
            System.setProperty("javax.net.ssl.keyStore", "src/cert/keystore");
            System.setProperty("javax.net.ssl.keyStorePassword", "ossimestg2016");
            System.setProperty("javax.net.ssl.trustStore", "src/cert/truststore");
            System.setProperty("javax.net.ssl.trustStorePassword", "ossimestg2016");
            //System.setProperty("javax.net.debug", "ssl");

            Class.forName(DRIVERCLASSNAME);
            DriverManager.setLoginTimeout(10);
            //create connection
            con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + PORT + DBNAME + "?verifyServerCertificate=true&useSSL=true&requireSSL=true", userName, password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return con;
    }
}
