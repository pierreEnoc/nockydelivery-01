package com.pierre.nockydelivery.delivery.traking.infrastructure.resilence4j;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.core.registry.EntryAddedEvent;
import io.github.resilience4j.core.registry.EntryRemovedEvent;
import io.github.resilience4j.core.registry.EntryReplacedEvent;
import io.github.resilience4j.core.registry.RegistryEventConsumer;
import io.github.resilience4j.retry.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Resilence4jRetryEventConsumer implements RegistryEventConsumer<Retry> {

    @Override
    public void onEntryAddedEvent(EntryAddedEvent<Retry> entryAddedEvent) {
       entryAddedEvent.getAddedEntry().getEventPublisher()
                .onRetry(event -> log.info("Retry event: {} for retry name: {}",
                        event.getEventType(), event.getName()));
    }

    @Override
    public void onEntryRemovedEvent(EntryRemovedEvent<Retry> entryRemoveEvent) {

    }

    @Override
    public void onEntryReplacedEvent(EntryReplacedEvent<Retry> entryReplacedEvent) {

    }
}
