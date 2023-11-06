package io.github.funchcode.fcfs.web.ticket;

import io.github.funchcode.fcfs.core.ticket.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping(path = "/v1/tickets")
@RestController
public final class TicketControllerV1 {

    private final TicketService ticketService;

    @GetMapping(path = "/first")
    @ResponseStatus(HttpStatus.OK)
    public String firstGetApi() {
        return "okay...";
    }

}
