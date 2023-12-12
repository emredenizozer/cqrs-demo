package com.emredennis.account.common.events;

import com.emredennis.cqrs.core.events.BaseEvent;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class AccountClosedEvent extends BaseEvent {
}
