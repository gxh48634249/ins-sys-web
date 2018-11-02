package com.ins.sys.config;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.web.session.HttpSessionCreatedEvent;
import org.springframework.stereotype.Component;

@Component
class HttpSessionCreatedEventListener implements ApplicationListener<HttpSessionCreatedEvent> {

    private static Logger logger = LoggerFactory.getLogger(HttpSessionCreatedEventListener.class);

    @Override
    public void onApplicationEvent(HttpSessionCreatedEvent event) {
        logger.info("CREATE SESSION: "+event.getSession().getId());
        try {
//            logger.info(JSONObject.fromObject(event.getSession()).toString());
        } catch (Exception e) {
            logger.info(String.format("添加session:[%s]出现异常.", event.getSession().getId()), e);
        }
    }
}
