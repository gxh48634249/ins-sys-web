package com.ins.sys.config;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.stereotype.Component;

@Component
class SessionDestroyedEventListener implements ApplicationListener<HttpSessionDestroyedEvent> {

    private static Logger logger = LoggerFactory.getLogger(SessionDestroyedEventListener.class);

    @Override
    public void onApplicationEvent(HttpSessionDestroyedEvent event) {
        logger.info("DISTORY SESSION "+ event.getSession().getId());
        try {
//            logger.info(JSONObject.fromObject(event.getSession()).toString());
        } catch (Exception e) {
            logger.error(String.format("失效session:[%s]发生异常.", event.getId()), e);
        }
    }
}
