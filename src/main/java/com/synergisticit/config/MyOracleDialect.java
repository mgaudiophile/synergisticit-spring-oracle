package com.synergisticit.config;

import org.springframework.data.relational.core.dialect.AnsiDialect;
import org.springframework.data.relational.core.dialect.LimitClause;

public class MyOracleDialect extends AnsiDialect {

    private static final LimitClause LIMIT_CLAUSE = new LimitClause() {

        @Override
        public String getLimit(long limit) {
            return String.format("FETCH NEXT %d ROWS ONLY", limit);
        }

        @Override
        public String getOffset(long offset) {
            return String.format("OFFSET %d ROWS", offset);
        }

        @Override
        public String getLimitOffset(long limit, long offset) {
            return String.format("OFFSET %d ROWS FETCH NEXT %d ROWS ONLY", offset, limit);
        }

        @Override
        public Position getClausePosition() {
            return Position.AFTER_ORDER_BY;
        }
    };

    @Override
    public LimitClause limit() {
        return LIMIT_CLAUSE;
    }

}
