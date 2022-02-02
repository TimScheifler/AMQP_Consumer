package com.example.AMQP_Receiver;

import java.io.Serializable;
import java.sql.Timestamp;

public class TimeObject implements Serializable {

    private final Timestamp start;
    private final Timestamp end;
    private final long timeTaken;

    public TimeObject(Timestamp start, Timestamp end) {
        this.start = start;
        this.end = end;
        this.timeTaken = end.getTime() - start.getTime();
    }

    public Timestamp getStart() {
        return start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public long getTimeTaken() {
        return timeTaken;
    }

    @Override
    public String toString() {
        return "TimeObject{" +
                "start=" + start +
                ", end=" + end +
                ", timeTaken=" + timeTaken +
                '}';
    }
}
