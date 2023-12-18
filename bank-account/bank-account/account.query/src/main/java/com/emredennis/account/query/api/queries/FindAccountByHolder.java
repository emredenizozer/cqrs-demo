package com.emredennis.account.query.api.queries;

import com.emredennis.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountByHolder extends BaseQuery {
    private String accountHolder;
}
