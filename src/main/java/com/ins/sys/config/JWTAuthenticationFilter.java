package com.ins.sys.config;

import com.ins.sys.exception.AuthenException;
import com.ins.sys.token.Token;
import com.ins.sys.token.TokenServiceImpl;
import com.ins.sys.user.domain.SysUserInfoEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    private TokenServiceImpl tokenService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    /**
     * 在此方法中检验客户端请求头中的token,
     * 如果存在并合法,就把token中的信息封装到 Authentication 类型的对象中,
     * 最后使用  SecurityContextHolder.getContext().setAuthentication(authentication); 改变或删除当前已经验证的 pricipal
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String token = request.getHeader("token");
        ServletContext sc = request.getSession().getServletContext();
        WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(sc);
        tokenService = (TokenServiceImpl) cxt.getBean("tokenserviceimpl");
        //判断是否有token
        if (token != null && token.startsWith("ENS ")) {
            try {
                SysUserInfoEntity sysUserInfoEntity = tokenService.findUserByToken(token);
                if(sysUserInfoEntity!=null) {
                    Token token1 = new Token();
                    token1.setTokenInfo(token.substring(4));
                    tokenService.modify(token1);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(sysUserInfoEntity, sysUserInfoEntity.getUserId(), sysUserInfoEntity.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    chain.doFilter(request, response);
                    return;
                }
            }catch (Exception e) {
                e.printStackTrace();
                response.setStatus(403);
                return;
//                throw new ServletException("无访问权限");
            }
        }else {
            response.setStatus(403);
            return;
//            throw new ServletException("无访问权限");
        }

//        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(token);
//
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        //放行
//        chain.doFilter(request, response);
    }

    /**
     * 解析token中的信息,并判断是否过期
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String token,TokenServiceImpl tokenService) {


        Claims claims = Jwts.parser().setSigningKey("ins-sign")
                .parseClaimsJws(token.replace("ENS ", ""))
                .getBody();

        //得到用户名
        String username = claims.getSubject();

        //得到过期时间
        Date expiration = claims.getExpiration();

        //判断是否过期
        Date now = new Date();

        if (now.getTime() > expiration.getTime()) {

            throw new AuthenException("该账号已过期,请重新登陆");
        }

        if (username != null) {
            return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
        }
        return null;
    }


}

