package io.github.funchcode.web.ticket;

import io.github.funchcode.core.fcfs.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public final class TicketController {

    private final TicketService ticketService;

    @GetMapping(value = "/ticket")
    @ResponseStatus(HttpStatus.OK)
    public String ticketing() {
        ticketService.ticketing("B");
        return "okay...";
    }

}
