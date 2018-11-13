//package com.ins.sys;
//
//import com.ins.sys.tools.StringTool;
//import com.ins.sys.user.domain.SysUserInfoEntity;
//import com.ins.sys.user.web.UserController;
//import net.sf.json.JSONObject;
//import org.apache.http.HttpEntity;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.mime.MultipartEntityBuilder;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.List;
//import java.util.Map;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class SysApplicationTests {
//
//    @Autowired
//    private UserController userController;
//
//<<<<<<< HEAD
//    @Test
//    public void contextLoads() {
////        String userName = "zh";
//=======
////    @Test
////    public void contextLoads() {
////        String userName = "gxh11";
//>>>>>>> 800df9e499a75354f348ab3a9bb120d65adbca08
////        String pwd = "admin";
//////        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//////        pwd = encoder.encode(pwd);
////        SysUserInfoEntity sysUserInfoEntity = new SysUserInfoEntity();
////        sysUserInfoEntity.setUserPwd(pwd);
////        sysUserInfoEntity.setUserAccount(userName);
////        sysUserInfoEntity.setPhone(15620952172L);
//<<<<<<< HEAD
////        UserWeb userWeb = new UserWeb();
////        userWeb.setUserInfoEntity(sysUserInfoEntity);
////        userWeb.setOrganId("123");
////        userController.insertUser(userWeb);
////        this.upload();
//    }
//
//
//    public static void main(String[] arg){
//        try {
//            upload2();
//        }catch (Exception e){
//
//        }
//    }
//
////    public static void upload() {
////        HttpClient client = new DefaultHttpClient();
////        String result = "";
////        try {
////            HttpPost post = new HttpPost("https://app.xunjiepdf.com/api/Upload?tasktype=ocr&phonenumber=&loginkey=&machineid=f586ec3ef02a4a27b4631326b9ee9320&token=22292b4f882ad4fdecf5f5bcab5ce0b1&limitsize=2048&pdfname=1539920832(1).jpg&queuekey=ab1626100a2a49a1913521e3095ae3f5&uploadtime=&filecount=1&fileindex=1&pagerange=&picturequality=&outputfileextension=docx&picturerotate=0&filesequence=0&filepwd=&iconsize=&picturetoonepdf=&isshare=1&softname=pdfonlineconverter&softversion=V5.0&validpagescount=20&limituse=1&filespwdlist=&fileCountwater=1&languagefrom=&languageto=&cadverchose=0&pictureforecolor=&picturebackcolor=&id=WU_FILE_0&name=1539920832(1).jpg&type=image%2Fjpeg&lastModifiedDate=Fri+Oct+19+2018+11%3A47%3A12+GMT%2B0800+(%E4%B8%AD%E5%9B%BD%E6%A0%87%E5%87%86%E6%97%B6%E9%97%B4)&size=50598");
//////            post.setHeader("Host","app.xunjiepdf.com");
//////            post.setHeader("Connection","keep-alive");
//////            post.setHeader("Origin","https://app.xunjiepdf.com");
//////            post.setHeader("Content-Type","image/jpeg");
//////            post.setHeader("Accept","*/*");
//////            post.setHeader("Referer","https://app.xunjiepdf.com/ocr");
//////            post.setHeader("Accept-Encoding","gzip, deflate, br");
//////            post.setHeader("Accept-Language","zh-CN,zh;q=0.9");
//////            post.setHeader("Cookie","xunjieUserTag=f586ec3ef02a4a27b4631326b9ee9320; Hm_lvt_6c985cbff8f72b9fad12191c6d53668d=1539920785; xunjieTempFileList=05626e5be07e4ec0bf0e597cb70b7ed4%7c1d964a713be64f439796e5efd878dd37; Hm_lpvt_6c985cbff8f72b9fad12191c6d53668d=1539921054");
////            FileInputStream fi = new FileInputStream(new File("/home/gxh/2018-10-19 09-39-46 的屏幕截图.png"));
//////            BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
//////            basicHttpEntity.setContent(fi);
////            post.setEntity(basicHttpEntity);
////            response = httpClient.execute(post);
////            System.out.println(JSONObject.fromObject(response).toString());
////        }catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
//
//    public static void upload2() throws ClientProtocolException, IOException {
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        CloseableHttpResponse httpResponse = null;
//        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000000).build();
//        HttpPost httpPost = new HttpPost("https://app.xunjiepdf.com/api/Upload?tasktype=ocr&phonenumber=&loginkey=&machineid=f586ec3ef02a4a27b4631326b9ee9320&token=22292b4f882ad4fdecf5f5bcab5ce0b1&limitsize=2048&pdfname=1539920832(1).jpg&queuekey=ab1626100a2a49a1913521e3095ae3f5&uploadtime=&filecount=1&fileindex=1&pagerange=&picturequality=&outputfileextension=docx&picturerotate=0&filesequence=0&filepwd=&iconsize=&picturetoonepdf=&isshare=1&softname=pdfonlineconverter&softversion=V5.0&validpagescount=20&limituse=1&filespwdlist=&fileCountwater=1&languagefrom=&languageto=&cadverchose=0&pictureforecolor=&picturebackcolor=&id=WU_FILE_0&name=1539920832(1).jpg&type=image%2Fjpeg&lastModifiedDate=Fri+Oct+19+2018+11%3A47%3A12+GMT%2B0800+(%E4%B8%AD%E5%9B%BD%E6%A0%87%E5%87%86%E6%97%B6%E9%97%B4)&size=50598");
//        httpPost.setHeader("Host","app.xunjiepdf.com");
//        httpPost.setHeader("Connection","keep-alive");
//        httpPost.setHeader("Origin","https://app.xunjiepdf.com");
//        httpPost.setHeader("Content-Type","image/jpeg");
//        httpPost.setHeader("Accept","*/*");
//        httpPost.setHeader("Referer","https://app.xunjiepdf.com/ocr");
//        httpPost.setHeader("Accept-Encoding","gzip, deflate, br");
//        httpPost.setHeader("Accept-Language","zh-CN,zh;q=0.9");
//        httpPost.setHeader("Cookie","xunjieUserTag=f586ec3ef02a4a27b4631326b9ee9320; Hm_lvt_6c985cbff8f72b9fad12191c6d53668d=1539920785; xunjieTempFileList=05626e5be07e4ec0bf0e597cb70b7ed4%7c1d964a713be64f439796e5efd878dd37; Hm_lpvt_6c985cbff8f72b9fad12191c6d53668d=1539921054");
//        httpPost.setConfig(requestConfig);
//        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
//        File file = new File("/home/gxh/图片/2018-10-18 11-46-22 的屏幕截图.png");
//        multipartEntityBuilder.addBinaryBody("file",file);
//        HttpEntity httpEntity = multipartEntityBuilder.build();
//        httpPost.setEntity(httpEntity);
//        httpResponse = httpClient.execute(httpPost);
//        HttpEntity responseEntity = httpResponse.getEntity();
//        int statusCode= httpResponse.getStatusLine().getStatusCode();
//        if(statusCode == 200){
//            BufferedReader reader = new BufferedReader(new InputStreamReader(responseEntity.getContent()));
//            StringBuffer buffer = new StringBuffer();
//            String str = "";
//            while(!StringTool.isnull(str = reader.readLine())) {
//                buffer.append(str);
//            }
//            System.out.println(buffer.toString());
//        }
//        httpClient.close();
//        if(httpResponse!=null){
//            httpResponse.close();
//        }
//    }
//
//=======
////        userController.insertUser(sysUserInfoEntity,"123","");
////    }
//
//
//    public static void main(String[] arg){
//        SysUserInfoEntity sysUserInfoEntity = new SysUserInfoEntity();
//        sysUserInfoEntity.setGender(1);
//        sysUserInfoEntity.setUserAccount("gxh");
//        sysUserInfoEntity.setUserStatue(1);
//        Map<String,Object> map = JSONObject.fromObject(sysUserInfoEntity);
//        map.forEach((k,v)->{
//
//            System.out.print(k+">>");
//            if(v instanceof Number){
//                System.out.println(v+"<<");
//            }
//            else if(v instanceof String) {
//                if(!StringTool.isnull(v.toString())){
//                    System.out.println(v+"<<");
//                }else {
//                    System.out.println("空的<<");
//                }
//            }else if(v instanceof Boolean) {
//                if(!StringTool.isnull(v.toString())){
//                    System.out.println(v+"<<");
//                }else {
//                    System.out.println("空的<<");
//                }
//            }else if(v instanceof List){
//                System.out.println(v+"<<");
//            }
//        });
//    }
//>>>>>>> 800df9e499a75354f348ab3a9bb120d65adbca08
//}
