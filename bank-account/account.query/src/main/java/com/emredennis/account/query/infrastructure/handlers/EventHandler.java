package com.emredennis.account.query.infrastructure.handlers;

import com.emredennis.account.common.events.AccountClosedEvent;
import com.emredennis.account.common.events.AccountOpenedEvent;
import com.emredennis.account.common.events.FundsDepositedEvent;
import com.emredennis.account.common.events.FundsWithdrawnEvent;

public interface EventHandler {
    void on(AccountOpenedEvent event);
    void on(FundsDepositedEvent event);
    void on(FundsWithdrawnEvent event);
    void on(AccountClosedEvent event);
}
