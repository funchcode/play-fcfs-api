package io.github.funchcode.fcfs.core.ticket;

import io.github.funchcode.fcfs.core.subject.Subject;

import java.util.UUID;

public record Ticket(Subject subject, String clientId) {

    public static Ticket newInstance(Subject subject, String clientId) {
        return new Ticket(subject, clientId);
    }

}
