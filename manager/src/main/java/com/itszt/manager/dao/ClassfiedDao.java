package com.itszt.manager.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ClassfiedDao {
    /*查询所有的分类信息*/
    List<Map<String,Object>> findObjects();

    /*根据id查询出任意节点处是否还有下级*/
     int findRowsById(Integer id);

    /*根据分类的id删除分类节点信息*/
     int deleteNodeById(Integer id);

    /*点击添加下级按钮，添加下级名称到数据库*/
     int addSubName(@Param("classfiedName") String classfiedNames,@Param("parentId") Integer parentId);

     /*根据工种名称来查询分类树，显示该工种及所有的父子节点*/
     List<Map<String,Object>> findObjectsByClassFy(String classfiedName);
}
