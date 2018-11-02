package com.ins.sys.esentity.web;

import com.ins.sys.esentity.domain.ENSRepository;
import com.ins.sys.esentity.domain.ENSWeb;
import com.ins.sys.esentity.domain.ESResoueceRepository;
import com.ins.sys.esentity.domain.ESResource;
import com.ins.sys.tools.StringTool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@Api(tags = "ES测试示例")
public class ESController {

    @Autowired
    private ESResoueceRepository esResoueceRepository;

    @Autowired
    private ElasticsearchTemplate template;

    @RequestMapping("/findByCOntentLike")
    @ApiOperation(value = "根据内容查找（给定条件分词查询）",httpMethod = "POST")
    public Object findByCOntentLike(String content){
        try{
            PageRequest request = PageRequest.of(0,20);
            Iterable<ESResource> resources = esResoueceRepository.findESResourceByContentLike(content);
            System.out.print(JSONArray.fromObject(resources).toString());
            List<String> list = new ArrayList<>();
            resources.forEach(m->{
                list.add(m.getId());
            });
            return this.esResoueceRepository.findAllById(list);
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    @RequestMapping("/findByID")
    @ApiOperation(value = "根据索引精确查找",httpMethod = "POST")
    public Object findByID(String id){
        try{
            PageRequest request = PageRequest.of(0,20);
            return esResoueceRepository.findAll(request);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/mathcsearch")
    @ApiOperation(value = "多条件查询",httpMethod = "POST")
    public Object mathcsearch(String... must){
        try{
            PageRequest request = PageRequest.of(0,20);
            SearchQuery query = new NativeSearchQueryBuilder()
                    .withQuery(QueryBuilders.termsQuery("content",must))
                    .withPageable(request)
                    .build();
            List<String> list = new ArrayList<>();
            Page<ESResource> resources = template.queryForPage(query,ESResource.class);
            System.out.print("本次查询总条数为："+resources.getTotalElements()+"<<<<<<<<<<<<<<");
            resources.forEach(m->{
                list.add(m.getId());
            });
            return this.esResoueceRepository.findAllById(list);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/mathcsearch2")
    @ApiOperation(value = "多条件查询2（给定范围查询）",httpMethod = "POST")
    public Object mathcsearch2(String... must){
        try{
            PageRequest request = PageRequest.of(0,20);
            List<String> list = new ArrayList<>();
            Page<ESResource> resources = this.esResoueceRepository.findByContentIn(request,Arrays.asList(must));
            System.out.print("本次查询总条数为："+resources.getTotalElements()+"<<<<<<<<<<<<<<");
            resources.forEach(m->{
                list.add(m.getId());
            });
            return this.esResoueceRepository.findAllById(list);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/mathcsearch3")
    @ApiOperation(value = "多条件查询3",httpMethod = "POST")
    public Object mathcsearch3(String... must){
        try{
            PageRequest request = PageRequest.of(0,20);
            List<String> list = new ArrayList<>();
            Page<ESResource> resources = this.esResoueceRepository.findByContentIsIn(request,Arrays.asList(must));
            System.out.print("本次查询总条数为："+resources.getTotalElements()+"<<<<<<<<<<<<<<");
            resources.forEach(m->{
                list.add(m.getId());
            });
            return this.esResoueceRepository.findAllById(list);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/mathcsearch4")
    @ApiOperation(value = "多条件查询4",httpMethod = "POST")
    public Object mathcsearch4(String... must){
        try{
            PageRequest request = PageRequest.of(0,20);
            List<String> list = new ArrayList<>();
            Page<ESResource> resources = this.esResoueceRepository.findByContentContains(request,must);
            System.out.print("本次查询总条数为："+resources.getTotalElements()+"<<<<<<<<<<<<<<");
            resources.forEach(m->{
                list.add(m.getId());
            });
            return this.esResoueceRepository.findAllById(list);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/mathcsearch5")
    @ApiOperation(value = "多条件查询5（不分词）",httpMethod = "POST")
    public Object mathcsearch5(String must){
        try{
            PageRequest request = PageRequest.of(0,20);
            List<String> list = new ArrayList<>();
            Page<ESResource> resources = this.esResoueceRepository.findESResourceByContent(must,request);
//            HighlightBuilder highlightBuilder = new HighlightBuilder().field("*").requireFieldMatch(false);
//            highlightBuilder.preTags("<span style=\"color:red\">");
//            highlightBuilder.postTags("</span>");
            System.out.print("本次查询总条数为："+resources.getTotalElements()+"<<<<<<<<<<<<<<");
            resources.forEach(m->{
                list.add(m.getId());
            });
            List<ESResource> list1 = new ArrayList<>();
            String replace = "<a>"+must+"</a>";
            this.esResoueceRepository.findAllById(list).forEach(m -> {
                ESResource temp = new ESResource();
                temp.setId(m.getId());
                temp.setContent(m.getContent().replace(must,replace));
                temp.setPublishTime(m.getPublishTime());
                temp.setSpiderTime(m.getSpiderTime());
                temp.setTitle(m.getTitle().replace(must,replace));
                temp.setUrl(m.getUrl());
                list1.add(temp);
            });
            return list1;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/mathcsearch56")
    @ApiOperation(value = "多条件查询6",httpMethod = "POST")
    public Object mathcsearch6(String must,String c2,String c3){
        try{
            PageRequest request = PageRequest.of(0,20);
            List<String> list = new ArrayList<>();
            Page<ESResource> resources = this.esResoueceRepository.findESResourceByContentAndContentAndContent(must,c2,c3,request);
            System.out.print("本次查询总条数为："+resources.getTotalElements()+"<<<<<<<<<<<<<<");
            resources.forEach(m->{
                list.add(m.getId());
            });
            return this.esResoueceRepository.findAllById(list);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/mathcsearch7")
    @ApiOperation(value = "多条件查询7",httpMethod = "POST")
    public Object mathcsearch6(String must,String... c3){
        try{
            PageRequest request = PageRequest.of(0,20);
            List<String> list = new ArrayList<>();
            Page<ESResource> resources = this.esResoueceRepository.findESResourceByContentInAndAndContent(Arrays.asList(c3),must,request);
            System.out.print("本次查询总条数为："+resources.getTotalElements()+"<<<<<<<<<<<<<<");
            resources.forEach(m->{
                list.add(m.getId());
            });
            return this.esResoueceRepository.findAllById(list);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/mathcsearch8")
    @ApiOperation(value = "多条件查询8",httpMethod = "POST")
    public Object mathcsearch8(String must,String must2,String must3){
        try{
            PageRequest request = PageRequest.of(0,20);
            List<String> list = new ArrayList<>();
            Page<ESResource> resources = this.esResoueceRepository.findESResourceByContents(must,must2,must3,request);
            System.out.print("本次查询总条数为："+resources.getTotalElements()+"<<<<<<<<<<<<<<");
            resources.forEach(m->{
                list.add(m.getId());
            });
            return this.esResoueceRepository.findAllById(list);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/mathcsearch9")
    @ApiOperation(value = "多条件查询9（不分词）",httpMethod = "POST")
    public Object mathcsearch9(String must,String... not){
        try{
            PageRequest request = PageRequest.of(0,20);
            List<String> list = new ArrayList<>();
            Page<ESResource> resources;
            if(null==not) {
                resources = this.esResoueceRepository.findESResourceByContent(must,request);
            }else {
                resources =this.esResoueceRepository.findESResourceByContentAndContentIsNotIn(must,Arrays.asList(not),request);
            }
            System.out.print("本次查询总条数为："+resources.getTotalElements()+"<<<<<<<<<<<<<<");
            resources.forEach(m->{
                list.add(m.getId());
            });
            return this.esResoueceRepository.findAllById(list);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Autowired
    private ENSRepository ensRepository;

    @RequestMapping("/ENS-WEB")
    @ApiOperation(value = "ENS-WEB单词查询/不分词",httpMethod = "POST")
    public Object ENSWEB(String must){
        try{
            PageRequest request = PageRequest.of(0,20);
            List<String> list = new ArrayList<>();
            Page<ENSWeb> resources = this.ensRepository.findENSWebByContent(must,request);
            System.out.print("本次查询总条数为："+resources.getTotalElements()+"<<<<<<<<<<<<<<");
            System.out.print(resources);
            resources.forEach(m->{
                list.add(m.getId());
            });
            List<ENSWeb> list1 = new ArrayList<>();
            String replace = "<span>"+must+"</span>";
            this.ensRepository.findAllById(list).forEach(m -> {
                m.setContent(m.getContent().replace(must,replace));
                list1.add(m);
            });
            return list1;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
