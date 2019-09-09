package by.davydenko.petbook.dao.pool;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;

import by.davydenko.petbook.dao.util.DBHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionPool {

    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);

    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> givenAwayConQueue;
    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    private static final ConnectionPool instance = new ConnectionPool();
    private ReentrantLock locker = new ReentrantLock();


    private ConnectionPool() {

    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public void init() throws ConnectionPoolException {
        this.driverName = DBHelper.DB_DRIVER_CLASS;
        this.url = DBHelper.DB_URL;
        this.user = DBHelper.DB_LOGIN;
        this.password = DBHelper.DB_PASSWORD;
        this.poolSize = DBHelper.DB_POOL_MAX_SIZE;
        try {
            Class.forName(driverName);
            givenAwayConQueue = new ArrayBlockingQueue<>(poolSize);
            connectionQueue = new ArrayBlockingQueue<>(poolSize);

            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                PooledConnection pooledConnection = new PooledConnection(connection);
                connectionQueue.add(pooledConnection);
            }

        } catch (SQLException e) {
            logger.error("too many connections");
            throw new ConnectionPoolException(e);
        } catch (ClassNotFoundException e) {
            logger.error("ClassNotFoundException in ConnectionPool ", e);
            throw new ConnectionPoolException(e);
        }
    }

    public void dispose()throws ConnectionPoolException {
        clearConnectionQueue();
    }

    private void clearConnectionQueue() throws ConnectionPoolException{
        try {
            closeConnectionsQueue(givenAwayConQueue);
            closeConnectionsQueue(connectionQueue);
        } catch (ConnectionPoolException e) {
            logger.error("SQLException in ConnectionPool/clearConnectionQueue, ", e);
        }
    }

    public Connection takeConnection() throws ConnectionPoolException {
        Connection connection = null;
        try {
            connection = connectionQueue.take();
            givenAwayConQueue.add(connection);

        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Error connecting to the data source.", e);
        }
        return connection;
    }

    public void closeConnection(Connection con, Statement st, ResultSet rs) throws ConnectionPoolException {
        try {
            con.close();
        } catch (SQLException e) {
            logger.error("Connection isn't return to the pool.");
            throw new ConnectionPoolException(e);
        }
        try {
            rs.close();
        } catch (SQLException e) {
            logger.error("ResultSet isn't closed.");
            throw new ConnectionPoolException(e);
        }
        try {
            st.close();
        } catch (SQLException e) {
            logger.error("Statement isn't closed.");
            throw new ConnectionPoolException(e);
        }
    }

    public void closeConnection(Connection con, Statement st)throws ConnectionPoolException {
        try {
            con.close();
        } catch (SQLException e) {
            throw new ConnectionPoolException("Connection isn't return to the pool.",e);
        }
        try {
            st.close();
        } catch (SQLException e) {
            throw new ConnectionPoolException("Statement isn't closed.",e);
        }
    }

    private void closeConnectionsQueue(BlockingQueue<Connection> queue)throws ConnectionPoolException  {
        Connection connection;
        try {
            while ((connection = queue.poll()) != null) {
                if (!connection.getAutoCommit()) {
                    connection.commit();
                }
                ((PooledConnection) connection).reallyClose();
            }
        }catch (SQLException e){
            throw new ConnectionPoolException(e);
        }
    }

    /**
     * Inner private class PooledConnection of class ConnectionPool
     * @author Davydenko
     * @implNote Connection
     */
    private class PooledConnection implements Connection {
        private Connection connection;

        public PooledConnection(Connection c) throws SQLException {
            this.connection = c;
            this.connection.setAutoCommit(true);
            connection.isValid(5);
        }

        public void reallyClose() throws SQLException {
            connection.close();
        }

        @Override
        public void clearWarnings() throws SQLException {
            connection.clearWarnings();
        }

        @Override
        public void close() throws SQLException {
            if (connection.isClosed()) {
                logger.error("Attempting to close closed connection.");
                throw new SQLException("Attempting to close closed connection.");
            }
            if (connection.isReadOnly()) {
                connection.setReadOnly(false);
            }
            if (!givenAwayConQueue.remove(this)) {
                logger.error("Error deleting connection from the given away connections pool.");
                throw new SQLException("Error deleting connection from the given away connections pool.");
            }
            if (!connectionQueue.offer(this)) {
                logger.error("Error allocating connection in the pool.");
                throw new SQLException("Error allocating connection in the pool.");
            }
        }

        @Override
        public void commit() throws SQLException {
            connection.commit();
        }

        @Override
        public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
            return connection.createArrayOf(typeName, elements);
        }

        @Override
        public Blob createBlob() throws SQLException {
            return connection.createBlob();
        }

        @Override
        public Clob createClob() throws SQLException {
            return connection.createClob();
        }

        @Override
        public NClob createNClob() throws SQLException {
            return connection.createNClob();
        }

        @Override
        public SQLXML createSQLXML() throws SQLException {
            return connection.createSQLXML();
        }

        @Override
        public Statement createStatement() throws SQLException {
            return connection.createStatement();
        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency);
        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
                throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
            return connection.createStruct(typeName, attributes);
        }

        @Override
        public boolean getAutoCommit() throws SQLException {
            return connection.getAutoCommit();
        }

        @Override
        public String getCatalog() throws SQLException {
            return connection.getCatalog();
        }

        @Override
        public Properties getClientInfo() throws SQLException {
            return connection.getClientInfo();
        }

        @Override
        public String getClientInfo(String name) throws SQLException {
            return connection.getClientInfo(name);
        }

        @Override
        public int getHoldability() throws SQLException {
            return connection.getHoldability();
        }

        @Override
        public DatabaseMetaData getMetaData() throws SQLException {
            return connection.getMetaData();
        }

        @Override
        public int getTransactionIsolation() throws SQLException {
            return connection.getTransactionIsolation();
        }

        @Override
        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return connection.getTypeMap();
        }

        @Override
        public SQLWarning getWarnings() throws SQLException {
            return connection.getWarnings();
        }

        @Override
        public boolean isClosed() throws SQLException {
            return connection.isClosed();
        }

        @Override
        public boolean isReadOnly() throws SQLException {
            return connection.isReadOnly();
        }

        @Override
        public boolean isValid(int timeout) throws SQLException {
            return connection.isValid(timeout);
        }

        @Override
        public String nativeSQL(String sql) throws SQLException {
            return connection.nativeSQL(sql);
        }

        @Override
        public CallableStatement prepareCall(String sql) throws SQLException {
            return connection.prepareCall(sql);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
                                             int resultSetHoldability) throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public PreparedStatement prepareStatement(String sql) throws SQLException {
            return connection.prepareStatement(sql);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
            return connection.prepareStatement(sql, autoGeneratedKeys);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
            return connection.prepareStatement(sql, columnIndexes);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
            return connection.prepareStatement(sql, columnNames);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
                throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
                                                  int resultSetHoldability) throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public void rollback() throws SQLException {
            connection.rollback();
        }

        @Override
        public void setAutoCommit(boolean autoCommit) throws SQLException {
            connection.setAutoCommit(autoCommit);
        }

        @Override
        public void setCatalog(String catalog) throws SQLException {
            connection.setCatalog(catalog);
        }

        @Override
        public void setClientInfo(String name, String value) throws SQLClientInfoException {
            connection.setClientInfo(name, value);
        }

        @Override
        public void setHoldability(int holdability) throws SQLException {
            connection.setHoldability(holdability);
        }

        @Override
        public void setReadOnly(boolean readOnly) throws SQLException {
            connection.setReadOnly(readOnly);
        }

        @Override
        public Savepoint setSavepoint() throws SQLException {
            return connection.setSavepoint();
        }

        @Override
        public Savepoint setSavepoint(String name) throws SQLException {
            return connection.setSavepoint(name);
        }

        @Override
        public void setTransactionIsolation(int level) throws SQLException {
            connection.setTransactionIsolation(level);
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return connection.isWrapperFor(iface);
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            return connection.unwrap(iface);
        }

        @Override
        public void abort(Executor arg0) throws SQLException {
            connection.abort(arg0);
        }

        @Override
        public int getNetworkTimeout() throws SQLException {
            return connection.getNetworkTimeout();
        }

        @Override
        public String getSchema() throws SQLException {
            return connection.getSchema();
        }

        @Override
        public void releaseSavepoint(Savepoint arg0) throws SQLException {
            connection.releaseSavepoint(arg0);
        }

        @Override
        public void rollback(Savepoint arg0) throws SQLException {
            connection.rollback(arg0);
        }

        @Override
        public void setClientInfo(Properties arg0) throws SQLClientInfoException {
            connection.setClientInfo(arg0);
        }

        @Override
        public void setNetworkTimeout(Executor arg0, int arg1) throws SQLException {
            connection.setNetworkTimeout(arg0, arg1);
        }

        @Override
        public void setSchema(String arg0) throws SQLException {
            connection.setSchema(arg0);
        }

        @Override
        public void setTypeMap(Map<String, Class<?>> arg0) throws SQLException {
            connection.setTypeMap(arg0);
        }
    }
}