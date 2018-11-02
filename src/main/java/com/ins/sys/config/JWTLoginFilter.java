package com.ins.sys.config;

import com.ins.sys.exception.AuthenException;
import com.ins.sys.token.Token;
import com.ins.sys.token.TokenService;
import com.ins.sys.token.TokenServiceImpl;
import com.ins.sys.tools.EhcacheService;
import com.ins.sys.user.domain.SysUserInfoEntity;
import com.ins.sys.user.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.json.JSONObject;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author itguang
 * @create 2018-01-02 13:48
 **/
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private UserService userService;

    private EhcacheService ehcacheService;

    private AuthenticationManager authenticationManager;

    private TokenServiceImpl tokenService;

//    @PostConstruct
//    public void init() {
//        ehcache = cacheManager.getEhcache("token");
//    }

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * 接收并解析用户登陆信息  /login,
     *为已验证的用户返回一个已填充的身份验证令牌，表示成功的身份验证
     *返回null，表明身份验证过程仍在进行中。在返回之前，实现应该执行完成该过程所需的任何额外工作。
     *如果身份验证过程失败，就抛出一个AuthenticationException
     *
     *
     * @param request  从中提取参数并执行身份验证
     * @param response 如果实现必须作为多级身份验证过程的一部分(比如OpenID)进行重定向，则可能需要响应
     * @return 身份验证的用户令牌，如果身份验证不完整，则为null。
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {


        //得到用户登陆信息,并封装到 Authentication 中,供自定义用户组件使用.
        InputStream is = null;
        StringBuilder sb = new StringBuilder();
        try {
            is = request.getInputStream();
            byte[] b = new byte[4096];
            for (int n; (n = is.read(b)) != -1;)
            {
                sb.append(new String(b, 0, n));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
<<<<<<< HEAD
        System.out.println(sb.toString()+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
=======
>>>>>>> 800df9e499a75354f348ab3a9bb120d65adbca08
        JSONObject object = JSONObject.fromObject(sb.toString());
        String username = object.getString("username");
        String password = object.getString("password");

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();
        ServletContext sc = request.getSession().getServletContext();
        WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(sc);
        userService = (UserService) cxt.getBean("userService");
        SysUserInfoEntity sysUserInfoEntity = userService.loadUserByUsername(username);
        if(null!=sysUserInfoEntity) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(sysUserInfoEntity, sysUserInfoEntity.getUserId(), sysUserInfoEntity.getAuthorities());
            return authenticationToken;
        }else {
            return null;
        }
        //authenticate()接受一个token参数,返回一个完全经过身份验证的对象，包括证书.
        // 这里并没有对用户名密码进行验证,而是使用 AuthenticationProvider 提供的 authenticate 方法返回一个完全经过身份验证的对象，包括证书.
//        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

//UsernamePasswordAuthenticationToken 是 Authentication 的实现类

    }


    /**
     * 登陆成功后,此方法会被调用,因此我们可以在次方法中生成token,并返回给客户端
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) {
        ServletContext sc = request.getSession().getServletContext();
        WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(sc);
        tokenService = (TokenServiceImpl) cxt.getBean("tokenserviceimpl");
        System.out.println(JSONObject.fromObject(authResult));
        String token = Jwts.builder()
                .setSubject(authResult.getCredentials().toString())
                //有效期两小时
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 2 * 1000))
                //采用什么算法是可以自己选择的，不一定非要采用HS512
                .signWith(SignatureAlgorithm.HS384, "ins-sign")
                .compact();
        Token tokens = new Token();
        System.out.println(token);
        System.out.println(token.length());
        tokens.setTokenInfo(token);
        System.out.println(JSONObject.fromObject(authResult.getPrincipal()).toString().length());
        tokens.setTokenUser(JSONObject.fromObject(authResult.getPrincipal()).toString());
        try{
            tokenService.save(tokens);
        }catch (Exception e){
            throw new AuthenException("服务器异常");
        }
        response.addHeader("token", "ENS " + token);
        try {
            ServletOutputStream ow = response.getOutputStream();
            List<String> pers = new ArrayList<>();
            authResult.getAuthorities().forEach(m -> {
                pers.add(m.getAuthority());
            });
            JSONObject object = new JSONObject();
            object.put("permissions",pers);
            object.put("userinfo",authResult.getPrincipal());
            object.put("status","ok");
            object.put("auth","ENS " + token);
            OutputStreamWriter op = new OutputStreamWriter(ow,"UTF8");
            op.write(object.toString());
            op.flush();
            op.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
