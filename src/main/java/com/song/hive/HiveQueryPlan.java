//package com.song.hive;
//
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.hive.conf.HiveConf;
//import org.apache.hadoop.hive.ql.Context;
//import org.apache.hadoop.hive.ql.QueryPlan;
//import org.apache.hadoop.hive.ql.exec.Utilities;
//import org.apache.hadoop.hive.ql.parse.ASTNode;
//import org.apache.hadoop.hive.ql.parse.BaseSemanticAnalyzer;
//import org.apache.hadoop.hive.ql.parse.ParseDriver;
//import org.apache.hadoop.hive.ql.parse.ParseUtils;
//import org.apache.hadoop.hive.ql.parse.SemanticAnalyzerFactory;
//import org.apache.hadoop.hive.ql.session.SessionState;
//
///**
// * lxw的大数据田地 -- lxw1234.com
// *
// * @author lxw1234
// */
//public class HiveQueryPlan {
//    public static void main(String[] args) throws Exception {
//        HiveConf conf = new HiveConf();
//        conf.addResource(new Path("file:///usr/local/apache-hive-0.13.1-bin/conf/hive-site.xml"));
//        conf.addResource(new Path("file:///usr/local/apache-hive-0.13.1-bin/conf/hive-default.xml.template"));
//        conf.set("javax.jdo.option.ConnectionURL",
//                "jdbc:mysql://127.0.0.1:3306/hive?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf-8");
//        conf.set("hive.metastore.local", "true");
//        conf.set("javax.jdo.option.ConnectionDriverName", "com.mysql.jdbc.Driver");
//        conf.set("javax.jdo.option.ConnectionUserName", "hive");
//        conf.set("javax.jdo.option.ConnectionPassword", "hive");
//        conf.set("hive.stats.dbclass", "jdbc:mysql");
//        conf.set("hive.stats.jdbcdriver", "com.mysql.jdbc.Driver");
//        conf.set("hive.exec.dynamic.partition.mode", "nonstrict");
//        String command = args[0];
//        SessionState.start(conf);
//        Context ctx = new Context(conf);
//        ParseDriver pd = new ParseDriver();
//        ASTNode tree = pd.parse(command, ctx);
////        tree = ParseUtils.findRootNonNullToken(tree);
//        BaseSemanticAnalyzer sem = SemanticAnalyzerFactory.get(conf, tree);
//        sem.analyze(tree, ctx);
//        sem.validate();
//        QueryPlan queryPlan = new QueryPlan(command, sem, 0l,"","");
//        int jobs = Utilities.getMRTasks(queryPlan.getRootTasks()).size();
//        System.out.println("Total jobs = " + jobs);
//    }
//}