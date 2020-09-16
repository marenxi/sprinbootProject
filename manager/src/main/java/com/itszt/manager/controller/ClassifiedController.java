package com.itszt.manager.controller;

import com.itszt.manager.entity.DataResponse;
import com.itszt.manager.service.ClassifiedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class ClassifiedController {

    @Autowired
    private ClassifiedService service;

    @GetMapping("/classified")
    public String order() {
        System.out.println("返回分类管理页面");
        return "/classified/classified";
    }

    /*查询出所有的分类信息*/
    @RequestMapping("fingClassifiedObjects")
    @ResponseBody
    public DataResponse fingClassifiedObjects(){
        DataResponse dataResponse = new DataResponse();
        List<Map<String, Object>> objects = service.findObjects();
        System.out.println(objects);
        dataResponse.setData(objects);
        dataResponse.setCount(objects.size());
        dataResponse.setMsg("ok");
        return dataResponse;
    }



    /*根据分类id删除分类的节点信息*/
    @RequestMapping(value = "/deleteObjectById/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public DataResponse deleteObjectById(@PathVariable Integer id){
        DataResponse response = new DataResponse();
        int rows=service.deleteNodeById(id);
        System.out.println(rows);
        response.setMsg("删除该节点成功");
        response.setCount(response.getCount());
        return response;
    }


    /*根据id来添加下级*/
    @RequestMapping("doAddDownMenu")
    public String doAddDownMenu(){
        return "classified/addClassify";
    }

    /*根据点击添加下级和*/
    @RequestMapping(value = "insertMenu/{classfiedName}/{parentId}",method = RequestMethod.POST)
    @ResponseBody
    public DataResponse doInsertMenu(@PathVariable String classfiedName,@PathVariable Integer parentId){
        DataResponse response=new DataResponse();
        service.addSubName(classfiedName,parentId);
        response.setMsg("您新增的下级是："+classfiedName);
        return  response;
    }

    /*根据分类名称来查找员工及其所有的父节点*/
    @RequestMapping("findWorkerByClassfy")
    @ResponseBody
    public DataResponse findWorkerByClassfy(String classfiedName){
        DataResponse dataResponse = new DataResponse();
        List<Map<String, Object>> list = service.findObjectsByClassFy(classfiedName);
        System.out.println(list);
        dataResponse.setData(list);
        dataResponse.setCount(list.size());
        dataResponse.setMsg("ok");
        return dataResponse;
    }
}
