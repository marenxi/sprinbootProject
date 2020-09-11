package com.itszt.manager.serviceImpl;

import com.itszt.manager.dao.ClassfiedDao;
import com.itszt.manager.exception.ServiceException;
import com.itszt.manager.service.ClassifiedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClassifiedServiceImpl implements ClassifiedService {
    @Autowired
    ClassfiedDao classfiedDao;



    @Override
    public List<Map<String, Object>> findObjects() {
        List<Map<String, Object>> mapList = classfiedDao.findObjects();
        if(mapList==null||mapList.size()==0)
            throw new ServiceException("您查找的信息不存在");
        return mapList;
    }


    @Override
    public int deleteNodeById(Integer id) {
    if(id==null||id<=0)
        throw new  IllegalArgumentException("请先选择id");
    int count=classfiedDao.findRowsById(id);
    if(count>0)
        throw new ServiceException("请先删除子菜单，否则无法删除当前菜单");
    int rows=classfiedDao.deleteNodeById(id);
    if(rows==0)
        throw new ServiceException("您要删除的信息可能已经不存在");
    return rows;
    }

    @Override
    public int addSubName(String classfiedName,Integer parentId) {
        return classfiedDao.addSubName(classfiedName,parentId);
    }
}
