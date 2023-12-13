package com.emredennis.cqrs.core.producers;

import com.emredennis.cqrs.core.events.BaseEvent;

public interface EventProducer {
    void produce(String topic, BaseEvent event);
}
