package com.emredennis.account.cmd.infrastructure;

import com.emredennis.account.cmd.domain.AccountAggregate;
import com.emredennis.account.cmd.domain.EventStoreRepository;
import com.emredennis.cqrs.core.events.BaseEvent;
import com.emredennis.cqrs.core.events.EventModel;
import com.emredennis.cqrs.core.exceptions.AggregateNotFoundException;
import com.emredennis.cqrs.core.exceptions.ConcurrencyException;
import com.emredennis.cqrs.core.infrastructure.EventStore;
import com.emredennis.cqrs.core.producers.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountEventStore implements EventStore {
    @Autowired
    private EventProducer eventProducer;

    @Autowired
    private EventStoreRepository eventStoreRepository;

    @Value("${spring.kafka.topic}")
    private String topic;

    @Override
    public void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (expectedVersion != -1 && eventStream.get(eventStream.size() - 1).getVersion() != expectedVersion) {
            throw new ConcurrencyException();
        }
        var version = expectedVersion;
        for (var event: events) {
            version++;
            event.setVersion(version);
            var eventModel = EventModel.builder()
                    .timestamp(new Date())
                    .aggregateIdentifier(aggregateId)
                    .aggregateType(AccountAggregate.class.getTypeName())
                    .version(version)
                    .eventType(event.getClass().getTypeName())
                    .eventData(event)
                    .build();
            var persistedEvent = eventStoreRepository.save(eventModel);

            if (!persistedEvent.getId().isEmpty()) {
                eventProducer.produce(topic, event);
            }
        }
    }

    @Override
    public List<BaseEvent> getEvents(String aggregateId) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (eventStream == null || eventStream.isEmpty()) {
            throw new AggregateNotFoundException("Incorrect account ID provided!");
        }

        return eventStream.stream().map(EventModel::getEventData).collect(Collectors.toList());
    }

    @Override
    public List<String> getAggregateIds() {
        var eventStream = eventStoreRepository.findAll();
        if (eventStream.isEmpty()) {
            throw new IllegalStateException("Could not retrieve event stream from the event store!");
        }
        return eventStream.stream().map(EventModel::getAggregateIdentifier).distinct().collect(Collectors.toList());
    }
}
