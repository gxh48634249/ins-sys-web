package com.ins.sys.tools;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Configuration
public class Long2DateTime implements TemplateMethodModelEx {
    @Override
    public Object exec(List list) throws TemplateModelException {
        SimpleDateFormat sf = new SimpleDateFormat((String)list.get(0));
        return sf.format(new Date(Long.parseLong((String)list.get(1))));
    }
}
