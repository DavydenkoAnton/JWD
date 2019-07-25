package by.davydenko.petbook.dao.util;

final public class DBHelper {
    public static final String DB_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/petbook?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String DB_LOGIN = "root";
    public static final String DB_PASSWORD = "admin";
    public static final int DB_POOL_START_SIZE = 1;
    public static final int DB_POOL_MAX_SIZE = 5;
    public static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 5;

    private DBHelper() {
    }

    public enum TableName {
        ADMIN("admin"),
        USERS("users"),
        PETS("pets"),
        MESSAGES("messages");
        TableName(String name) {
            this.name = name;
        }
        private String name;
        public String getName() {
            return name;
        }
    }

    public enum Users {
        ID("id"),
        LOGIN("login"),
        PASSWORD("password"),
        NAME("name"),
        EMAIL("email"),
        PHONE("phoneNumber"),
        AGE("age"),
        ROLE("role");
        Users(String name) {
            this.name = name;
        }
        private String name;
        public String getName() {
            return name;
        }
    }

}
