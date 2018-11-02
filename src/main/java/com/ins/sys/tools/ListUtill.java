package com.ins.sys.tools;

import java.util.Arrays;
import java.util.List;

/**
 * 判断数组是否为空
 */
public class ListUtill {

    public static boolean isnull(List<?> list) {
        return null==list||list.size()<1;
    }

    public static String append(StringBuilder sb,String params,String param){
        List<String> perIds = Arrays.asList(params.split(","));
        perIds.forEach(s -> {
            sb.append(StringTool.templateSql(param,s));
        });
        return sb.substring(0,sb.length()-1);
    }
}
