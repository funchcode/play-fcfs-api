package io.github.funchcode.fcfs.core.ticket;

import io.github.funchcode.fcfs.core.subject.Subject;

public final class Ticket {

    private final Subject subject;
    private final String clientId;

    public Ticket(Subject subject, String clientId) {
        this.subject = subject;
        this.clientId = clientId;
    }

    public Subject getSubject() {
        return subject;
    }

    public String getClientId() {
        return clientId;
    }

}
