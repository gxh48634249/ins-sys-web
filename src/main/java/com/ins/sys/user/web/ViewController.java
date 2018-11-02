//package com.ins.sys.user.web;
//
//import com.ins.sys.role.domain.RoleInfoEntity;
//import com.ins.sys.role.web.RoleController;
//import com.ins.sys.tools.PageInfo;
//import com.ins.sys.tools.Result;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//import springfox.documentation.annotations.ApiIgnore;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("ins/manage")
//@ApiIgnore
//public class ViewController {
//
//    @Autowired
//    private UserController userController;
//
//    @Autowired
//    private RoleController roleController;
//
//    @RequestMapping("user")
//    public ModelAndView user(ModelAndView modelAndView){
//        Result result = roleController.selectRole(new RoleInfoEntity(),new PageInfo());
//        List<RoleInfoEntity> list = (List<RoleInfoEntity>)result.getData();
//        modelAndView.addObject("role",list);
//        return modelAndView;
//    }
//}
