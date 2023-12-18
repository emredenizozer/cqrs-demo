package com.emredennis.account.query.api.queries;

import com.emredennis.account.query.api.dto.EqualityType;
import com.emredennis.cqrs.core.queries.BaseQuery;

public class FindAccountWithBalanceQuery extends BaseQuery {
    private EqualityType equalityType;
    private double balance;
}
