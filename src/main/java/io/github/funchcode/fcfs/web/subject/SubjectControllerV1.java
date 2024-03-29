package io.github.funchcode.fcfs.web.subject;

import io.github.funchcode.fcfs.core.subject.SubjectService;
import io.github.funchcode.fcfs.web.subject.dto.SubjectRegisterRequest;
import io.github.funchcode.fcfs.web.subject.dto.SubjectTicketRequireRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping(path = "/v1/subjects")
@RestController
public final class SubjectControllerV1 {

    private final SubjectService subjectService;

    @PostMapping(path = "/{subjectId}/tickets/acquire")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean acquire(
            @PathVariable("subjectId") String subjectId,
            @RequestBody SubjectTicketRequireRequest request
    ) {
        return subjectService.acquireTicket(subjectId, request.clientId());
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody SubjectRegisterRequest request) {
        return subjectService.register(request.limitedQuantityOf(), request.openDateTime().toLocalDateTime(), request.deadlineDateTime().toLocalDateTime()).getId();
    }

}
