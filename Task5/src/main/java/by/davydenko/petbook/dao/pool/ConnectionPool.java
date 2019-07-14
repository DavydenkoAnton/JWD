package by.davydenko.petbook.dao.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {

    private static Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionPool instance = new ConnectionPool();
    private BlockingQueue<PetBookConnection> freeConnections = new LinkedBlockingQueue<>();
    private Set<PetBookConnection> usedConnections = new ConcurrentSkipListSet<>();
    private String url;
    private String user;
    private String password;
    private int maxSize;
    private int checkConnectionTimeout;


    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public Connection getConnection() throws ConnectionPoolException {
        PetBookConnection petBookconnection = null;

        while (petBookconnection == null) {
            try {
                if (!freeConnections.isEmpty()) {
                    petBookconnection = freeConnections.take();
                    if (!petBookconnection.isValid(checkConnectionTimeout)) {
                        try {
                            petBookconnection.getConnection().close();
                        } catch (SQLException e) {
                        }
                        petBookconnection = null;
                    }
                } else if (usedConnections.size() < maxSize) {
                    petBookconnection = createConnection();
                } else {
                    logger.error("Limit of connections");
                    throw new ConnectionPoolException();
                }
            } catch (InterruptedException | SQLException e) {
                logger.error("It is impossible to connect to a database", e);
                throw new ConnectionPoolException(e);
            }
        }
        usedConnections.add(petBookconnection);
        logger.debug(String.format("Connection was received from pool. Current pool size: %d used connections; %d free connection", usedConnections.size(), freeConnections.size()));
        return petBookconnection;
    }

    private PetBookConnection createConnection() throws SQLException {
        return new PetBookConnection(DriverManager.getConnection(url, user, password));
    }

    public void destroy() throws ConnectionPoolException {
        usedConnections.addAll(freeConnections);
        freeConnections.clear();
        for (PetBookConnection connection : usedConnections) {
            try {
                connection.getConnection().close();
            } catch (SQLException e) {
                throw new ConnectionPoolException(e);
            }
        }
        usedConnections.clear();
    }

    @Override
    protected void finalize() throws ConnectionPoolException {
        destroy();
    }

    void freeConnection(PetBookConnection connection) {
        try {
            if (connection.isValid(checkConnectionTimeout)) {
                connection.clearWarnings();
                connection.setAutoCommit(true);
                usedConnections.remove(connection);
                freeConnections.put(connection);
                logger.debug(String.format("Connection was returned into pool. Current pool size: %d used connections; %d free connection", usedConnections.size(), freeConnections.size()));
            }
        } catch (SQLException | InterruptedException e) {
            logger.error(e);
            try {
                connection.getConnection().close();
            } catch (SQLException e2) {
            }
        }
    }

    public void init(String driverClass, String url, String user, String password, int startSize, int maxSize, int checkConnectionTimeout) throws ConnectionPoolException {
        try {
            destroy();
            Class.forName(driverClass);
            this.url = url;
            this.user = user;
            this.password = password;
            this.maxSize = maxSize;
            this.checkConnectionTimeout = checkConnectionTimeout;
            for (int counter = 0; counter < startSize; counter++) {
                freeConnections.put(createConnection());
            }
        } catch (ClassNotFoundException | SQLException | InterruptedException e) {
            logger.fatal("It is impossible to initialize connection pool", e);
            throw new ConnectionPoolException(e);
        }
    }


}
