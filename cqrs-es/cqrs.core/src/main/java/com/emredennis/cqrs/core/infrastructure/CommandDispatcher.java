package com.emredennis.cqrs.core.infrastructure;

import com.emredennis.cqrs.core.commands.BaseCommand;
import com.emredennis.cqrs.core.commands.CommandHandlerMethod;

public interface CommandDispatcher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    void send(BaseCommand command);
}
