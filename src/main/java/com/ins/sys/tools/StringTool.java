package com.ins.sys.tools;

/**
 * 用于处理字符串
 */
public class StringTool {

    public static boolean isnull(String param){
        return null==param||param.isEmpty();
    }

    public static String sqlLike(String param){
        return "%"+param+"%";
    }

    public static String templateSql(String ...param){
        StringBuilder sb = new StringBuilder("(");
        for (String s : param) {
            sb.append("'"+s+"',");
        }
        StringBuilder sbResult = new StringBuilder(sb.substring(0,sb.length()-1));
        sbResult.append("),");
        return sbResult.toString();
    }
}
