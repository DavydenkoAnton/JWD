package by.javatr.transport.exception;

public class RepositoryException extends Exception {

        public RepositoryException(){super();}
        public RepositoryException(String name, Throwable exception){super(name,exception);}
        public RepositoryException(String name){super(name);}
        public RepositoryException(Throwable exception){super(exception);}
    }
