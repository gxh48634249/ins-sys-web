package com.ins.sys.library.web;

import com.ins.sys.library.domain.LibraryInfoEntity;
import com.ins.sys.library.domain.LibraryTree;
import com.ins.sys.tools.Constant;
import com.ins.sys.tools.ListUtill;
import com.ins.sys.tools.Result;
import freemarker.ext.beans.HashAdapter;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("ins/manage")
public class LibraryFTL {

    @Autowired
    private LibraryController libraryController;

    @RequestMapping("library")
    public ModelAndView library(ModelAndView modelAndView){
        Result result = this.libraryController.findAll();
        List<LibraryInfoEntity> libraryInfoEntities = new ArrayList<>();
        if(result.getStatue()==Constant.SUCCESS_STATUE) {
            libraryInfoEntities = (List<LibraryInfoEntity>) result.getData();
        }
        Map<String,List<LibraryInfoEntity>> map = new HashMap<>();
        if(!ListUtill.isnull(libraryInfoEntities)) {
            List<LibraryTree> libraryTrees = new ArrayList<>();
            libraryInfoEntities.forEach(m->{
                String temp = m.getParentCode();
                LibraryTree tree = new LibraryTree(m);
                if(temp.equals("0")) {
                    libraryTrees.add(new LibraryTree(m));
                    if(!map.containsKey(m.getLibraryCode())) {
                        map.put(m.getLibraryCode(),new ArrayList<>());
                    }
                }else {
                    if(map.containsKey(m.getParentCode())) {
                        List<LibraryInfoEntity> temp2 =  map.get(m.getParentCode());
                        temp2.add(m);
                        map.put(m.getParentCode(),temp2);
                    }else {
                        List<LibraryInfoEntity> temp2 =  new ArrayList<>();
                        temp2.add(m);
                        map.put(m.getParentCode(),temp2);
                    }
                }
            });
            System.out.print(map);
            List<LibraryTree> results = new ArrayList<>();
            libraryTrees.forEach(m->{
                if(m.getParentCode().equals("0")) {
                    m.setChild(map.get(m.getLibraryCode()));
                    results.add(m);
                }
            });
            System.out.print(JSONArray.fromObject(results));
            modelAndView.addObject("MyData",JSONArray.fromObject(results).toString());
            modelAndView.addObject("da",123);
        }
        return modelAndView;
    }

}
