package com.ins.sys;

import com.ins.sys.tools.StringTool;
import com.ins.sys.user.domain.SysUserInfoEntity;
import com.ins.sys.user.web.UserController;
import com.ins.sys.user.web.UserWeb;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysApplicationTests {

    @Autowired
    private UserController userController;

    @Test
    public void contextLoads() {
//    @Test
//    public void contextLoads() {
        String userName = "gxh11";
        String pwd = "admin";
        SysUserInfoEntity sysUserInfoEntity = new SysUserInfoEntity();
        sysUserInfoEntity.setUserPwd(pwd);
        sysUserInfoEntity.setUserAccount(userName);
        sysUserInfoEntity.setPhone(15620952172L);
        sysUserInfoEntity.setAddress("天津");
        UserWeb userWeb = new UserWeb();
        userWeb.setUserInfoEntity(sysUserInfoEntity);
        userWeb.setOrganId("123");
        userController.insertUser(userWeb);
    }




}
