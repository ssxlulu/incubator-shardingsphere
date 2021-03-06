/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.core.parse.parser.dialect;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.shardingsphere.core.parse.lexer.LexerEngine;
import org.apache.shardingsphere.core.parse.parser.clause.expression.AliasExpressionParser;
import org.apache.shardingsphere.core.parse.parser.clause.expression.BasicExpressionParser;
import org.apache.shardingsphere.core.parse.parser.dialect.mysql.clause.expression.MySQLAliasExpressionParser;
import org.apache.shardingsphere.core.parse.parser.dialect.oracle.clause.expression.OracleAliasExpressionParser;
import org.apache.shardingsphere.core.parse.parser.dialect.postgresql.clause.expression.PostgreSQLAliasExpressionParser;
import org.apache.shardingsphere.core.parse.parser.dialect.sqlserver.clause.expression.SQLServerAliasExpressionParser;

/**
 * Expression parser factory.
 *
 * @author zhangliang
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExpressionParserFactory {
    
    /**
     * Create alias parser instance.
     * 
     * @param lexerEngine lexical analysis engine.
     * @return alias parser instance
     */
    public static AliasExpressionParser createAliasExpressionParser(final LexerEngine lexerEngine) {
        switch (lexerEngine.getDatabaseType()) {
            case H2:
                return new MySQLAliasExpressionParser(lexerEngine);
            case MySQL:
                return new MySQLAliasExpressionParser(lexerEngine);
            case Oracle:
                return new OracleAliasExpressionParser(lexerEngine);
            case SQLServer:
                return new SQLServerAliasExpressionParser(lexerEngine);
            case PostgreSQL:
                return new PostgreSQLAliasExpressionParser(lexerEngine);
            default:
                throw new UnsupportedOperationException(String.format("Cannot support database type: %s", lexerEngine.getDatabaseType()));
        }
    }
    
    /**
     * Create expression parser instance.
     *
     * @param lexerEngine lexical analysis engine.
     * @return expression parser instance
     */
    public static BasicExpressionParser createBasicExpressionParser(final LexerEngine lexerEngine) {
        return new BasicExpressionParser(lexerEngine);
    }
}
