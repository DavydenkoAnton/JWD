package by.javatr.transport.exception;

public class ParseException  extends Exception{
        public ParseException(){super();}
        public ParseException(String name, Throwable exception){super(name,exception);}
        public ParseException(String name){super(name);}
        public ParseException(Throwable exception){super(exception);}

}
