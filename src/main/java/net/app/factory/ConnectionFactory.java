package net.app.factory;

import net.app.connection.publicinterface.ConnectionInterface;
import net.app.connection.specificimplementation.DBCPConnectionSpecificImplementation;
import net.app.connection.specificimplementation.BoneCPConnectionSpecificImplementation;
import net.app.connection.specificimplementation.C3P0ConnectionSpecificImplementation;
import net.app.connection.specificimplementation.HikariConnectionSpecificImplementation;
import net.app.connection.specificimplementation.ViburConnectionSpecificImplementation;
import net.app.constant.ConnectionConstants;

public class ConnectionFactory {

    public static ConnectionInterface getConnection(ConnectionConstants.EnumConstans enumConnection) {
        ConnectionInterface oConnectionInterface = null;
        switch (enumConnection) {
            case Hikari:
                oConnectionInterface = new HikariConnectionSpecificImplementation();
                break;
            case DBCP:
                oConnectionInterface = new DBCPConnectionSpecificImplementation();
                break;
            case BoneCP:
                oConnectionInterface = new BoneCPConnectionSpecificImplementation();
                break;
            case C3P0:
                oConnectionInterface = new C3P0ConnectionSpecificImplementation();
                break;
            case Vibur:
                oConnectionInterface = new ViburConnectionSpecificImplementation();
                break;
            default:
                oConnectionInterface = new HikariConnectionSpecificImplementation();
                break;
        }
        return oConnectionInterface;

    }
}
