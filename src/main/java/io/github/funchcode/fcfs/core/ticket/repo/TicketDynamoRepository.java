package io.github.funchcode.fcfs.core.ticket.repo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import io.github.funchcode.fcfs.core.common.ErrorCode;
import io.github.funchcode.fcfs.core.common.FcfsRuntimeException;
import io.github.funchcode.fcfs.core.subject.SubjectRepository;
import io.github.funchcode.fcfs.core.subject.repo.SubjectDao;
import io.github.funchcode.fcfs.core.ticket.Ticket;
import io.github.funchcode.fcfs.core.ticket.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class TicketDynamoRepository implements TicketRepository {

    private final DynamoDBMapper dynamoDBMapper;
    private final SubjectRepository subjectRepository;

    @Override
    public List<Ticket> findAllBySubjectId(String subjectId) {

        return null;
    }

    @Override
    public Ticket save(Ticket ticket) {
        TicketDao dao = new TicketDao(ticket);
        if (findBySubjectIdAndClientId(ticket.subject().getId(), ticket.clientId()).isEmpty()) {
            dao.setCreatedAt(LocalDateTime.now());
        }
        dynamoDBMapper.save(dao);
        return ticket;
    }

    @Override
    public Optional<Ticket> findBySubjectIdAndClientId(String subjectId, String clientId) {
        TicketDao dao = dynamoDBMapper.load(TicketDao.class, TicketDao.toPk(subjectId), TicketDao.toSk(clientId));
        if (dao == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(dao.toDomain(subjectRepository.findById(dao.getSubjectId())
                .orElseThrow(() -> new FcfsRuntimeException(ErrorCode.NOTFOUND_ENTITY).setExternalMessage("등록된 SUBJECT가 없습니다."))));
    }
}
