/**
 * Credentials is the POJO used for the simple XMl file example.
 *
 * @author: Group 7
 * @since: February 2022
 *
 */

public class Credentials {
    private String host;
    private String port;
    private String user;
    private String password;

    public Credentials(String host, String port, String user, String password) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
    }
    public Credentials(){}

    //getters

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
