package dev.notcacha.inferius.mongo.credentials;

public class Credentials {

    private final String host;
    private final int port;
    private final String userName;
    private final String password;

    public Credentials(String host, int port, String userName, String password) {
        this.host = host;
        this.port = port;
        this.userName = userName;
        this.password = password;
    }

    /**
     * @return host from connection
     * */

    public String getHost() {
        return host;
    }

    /**
     * @return port from connection
     * */


    public int getPort() {
        return port;
    }

    /**
     * @return username from connection
     * */


    public String getUserName() {
        return userName;
    }

    /**
     * @return password from connection
     * */


    public String getPassword() {
        return password;
    }

}
