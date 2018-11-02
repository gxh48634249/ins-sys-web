package com.ins.sys;


import com.ins.sys.exception.AuthenException;
import com.ins.sys.tools.EhcacheService;
import com.ins.sys.tools.ListUtill;
import com.ins.sys.tools.StringTool;
import com.ins.sys.user.domain.SysUserInfoEntity;
import net.sf.ehcache.Element;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 自定义权限过滤器
 */
public class RoleFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(RoleFilter.class);

    @Value("${functionPermission}")
    boolean functionPermission;

    @Autowired
    private EhcacheService ehcacheService;

    @Autowired
    @Qualifier("ehcachenamager-ens")
    private CacheManager cacheManager;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String url = httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean flag = false;
        if(null!=authentication&&!authentication.getPrincipal().equals("anonymousUser")) {
            SysUserInfoEntity sysUserInfoEntity = (SysUserInfoEntity) authentication.getPrincipal();
            if (null!=sysUserInfoEntity){
                logger.info("USER ["+sysUserInfoEntity.getUserAccount()+"] ACCESS "+url);
                Integer end = url.lastIndexOf(".");
                if(end>0) {
                    url = url.substring(0,end);
                }
                List<String> resource = Arrays.asList(url.split("/"));
                if(!ListUtill.isnull(resource)){
                    List<GrantedAuthority> pers = (List<GrantedAuthority> )sysUserInfoEntity.getAuthorities();
                    JSONArray array = JSONArray.fromObject(sysUserInfoEntity.getAuthorities());
                    List<String> perCodes = new ArrayList<>();
                    pers.forEach(m->{
                        perCodes.add(m.getAuthority());
                    });
                    if(!functionPermission) {
                        resource = resource.subList(0,resource.size()-1);
                    }
                    flag = valudate(resource,perCodes);
                    logger.info("USER ["+sysUserInfoEntity.getUserAccount()+"] HAVE PERS:"+array);
                }
            }
        }
        if(flag) {
            chain.doFilter(request,response);
        }else {
            ((HttpServletResponse) response).setStatus(403);
            throw new AuthenException("权限不足！");
        }
    }

    @Override
    public void destroy() {
        this.ehcacheService.clean();
    }

    private boolean valudate(List<String> resource,List<String> perCodes){
        System.out.println(perCodes);
        for (String temp:resource){
            System.out.println(temp+">>>>>>>>>>>>>>>>>>>>>>>>>>");
            if(!StringTool.isnull(temp)&&!perCodes.contains(temp.toUpperCase())) {
                return false;
            }
        }
        return true;
    }
}
