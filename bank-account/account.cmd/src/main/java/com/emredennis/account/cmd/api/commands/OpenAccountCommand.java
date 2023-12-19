package com.emredennis.account.cmd.api.commands;

import com.emredennis.account.common.dto.AccountType;
import com.emredennis.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class OpenAccountCommand extends BaseCommand {
    private String accountHolder;
    private AccountType accountType;
    private double openingBalance;
}
