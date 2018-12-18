package com.ins.sys;

import com.ins.sys.permission.domain.PermissionInfoEntity;
import com.ins.sys.permission.web.PermissionController;
import com.ins.sys.resource.domain.ResourceInfoEntity;
import com.ins.sys.resource.web.ResourceController;
import com.ins.sys.user.domain.SysUserInfoEntity;
import com.ins.sys.user.web.UserController;
import com.ins.sys.user.web.UserWeb;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysApplicationTests {

    @Autowired
    private UserController userController;

    @Autowired
    private ResourceController resourceController;

    @Autowired
    private PermissionController permissionController;

    @Test
    public void contextLoads() {
        createUser();
    }

    private void createUser() {
        String userName = "admin";
        String pwd = "gxh11";
        SysUserInfoEntity sysUserInfoEntity = new SysUserInfoEntity();
        sysUserInfoEntity.setUserPwd(pwd);
        sysUserInfoEntity.setUserAccount(userName);
        sysUserInfoEntity.setPhone(15620952172L);
        sysUserInfoEntity.setAddress("天津");
        UserWeb userWeb = new UserWeb();
        userWeb.setUserInfoEntity(sysUserInfoEntity);
        userWeb.setOrganId("123");
        String temps = createPro(createResource());
        System.out.println(temps);
        userWeb.setPermissionIds(temps);
        userController.insertUser(userWeb);
    }

    private List<String> createResource() {
        List<String> resource = resourceController.selectAllSysResource();
        List<String> resulr = new ArrayList<>();
        resource.forEach(item -> {
            item = item.substring(1);
            Integer index = item.indexOf("/");
            String resourceT;
            if(index>0) {
                resourceT = item.substring(0,index);
            } else {
                resourceT = item;
            }

            ResourceInfoEntity resourceInfoEntity = new ResourceInfoEntity();
            resourceInfoEntity.setCreateTime(new Date().getTime());
            resourceInfoEntity.setResourceId(resourceT);
            resourceInfoEntity.setResourceCode(resourceT);
            resourceInfoEntity.setResourceDes("系统自动创建");
            resourceInfoEntity.setResourcePath(item.replace("/",","));
            resourceInfoEntity.setResourceParentCode("0");
            resourceInfoEntity.setResourceType("model");
            if (!resulr.contains(resourceT)) {
                resulr.add(resourceT);
            }
            resourceController.insertResource(resourceInfoEntity);
            List<String> chile = new ArrayList<>();
            chile = Arrays.asList(item.split("/"));
            for (int i = 0; i< chile.size(); i++) {
                if (i > 0) {
                    ResourceInfoEntity resourceInfoEntity1 = new ResourceInfoEntity();
                    resourceInfoEntity1.setCreateTime(new Date().getTime());
                    resourceInfoEntity1.setResourceId(chile.get(i));
                    resourceInfoEntity1.setResourceCode(chile.get(i));
                    resourceInfoEntity1.setResourceDes("系统自动创建");
                    resourceInfoEntity1.setResourcePath(item.replace("/",","));
                    resourceInfoEntity1.setResourceParentCode(chile.get(i-1));
                    resourceInfoEntity1.setResourceType("function");
                    System.out.println(""+i);
                    if (!resulr.contains(chile.get(i))){
                        resulr.add(chile.get(i));
                    }
                    resourceController.insertResource(resourceInfoEntity1);
                }
            }
        });
        return resulr;
    }

    private String createPro(List<String> pro) {
        String result = "";
        for(String temp : pro) {
            PermissionInfoEntity permissionInfoEntity = new PermissionInfoEntity();
            permissionInfoEntity.setPermissionId(temp);
            permissionInfoEntity.setCreateTime(new Date().getTime());
            permissionInfoEntity.setPermissionCode(temp);
            permissionInfoEntity.setPermissionDesc("系统自动创建");
            permissionInfoEntity.setResourceId(temp);
            permissionController.insertPer(permissionInfoEntity);
            result += (","+temp);
        }
        return result;
    }


}
