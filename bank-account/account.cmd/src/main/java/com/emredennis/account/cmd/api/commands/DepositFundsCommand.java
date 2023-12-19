package com.emredennis.account.cmd.api.commands;

import com.emredennis.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class DepositFundsCommand extends BaseCommand {
    private double amount;
}
