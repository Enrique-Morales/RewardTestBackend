package net.app.connection.publicinterface;

import java.sql.Connection;

public interface ConnectionInterface {

    /**
     *
     * @return
     * @throws Exception
     */
    public Connection newConnection() throws Exception;

    public void disposeConnection() throws Exception;

}
