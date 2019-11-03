package com.song.druid;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.util.JdbcConstants;

import java.util.List;

public class Test {

    public static void main(String[] args) {

        String sql = "SELECT ID, NAME, AGE FROM USER WHERE ID = ?";

//        StringBuilder out = new StringBuilder();
//        MySqlOutputVisitor visitor = new MySqlOutputVisitor(out);
//        MySqlStatementParser parser = new MySqlStatementParser(sql);
//        List<SQLStatement> statementList = parser.parseStatementList();
//        for (SQLStatement statement : statementList) {
//            statement.accept(visitor);
//            visitor.println();
//        }

//        System.out.println(out);

        List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, JdbcConstants.MYSQL);

        System.out.println(SQLUtils.toSQLString(stmtList,JdbcConstants.MYSQL));
    }
}
