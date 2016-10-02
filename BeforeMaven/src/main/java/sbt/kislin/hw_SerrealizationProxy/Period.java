package sbt.kislin.hw_SerrealizationProxy;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created by axel on 08.09.2016.
 */
public final class Period implements Serializable {
    private final Date start;
    private final Date end;

    @Override
    public String toString() {
        return "Period on " + start + "| to |" + end;
    }

    public Period(Date start, Date end) {
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());

        if (this.start.compareTo(this.end) < 0) {
            throw new IllegalArgumentException(start + "позже" + end);
        }
    }

    private Object writeReplace() {
        return new SerializationProxy(this);
    }

    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }

    private static class SerializationProxy implements Serializable {
        private static final long serialVersionUID = 1L;
        private final Date start;
        private final Date end;

        SerializationProxy(Period p) {
            this.start = p.start;
            this.end = p.end;
        }

        private Object readResolve() {
            return new Period(start, end);
        }
    }
}
