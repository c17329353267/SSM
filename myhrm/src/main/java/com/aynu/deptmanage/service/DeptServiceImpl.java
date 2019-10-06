package com.aynu.deptmanage.service;

import com.aynu.deptmanage.dao.DeptDao;
import com.aynu.entity.Dept;
import com.aynu.pages.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;
    @Override
    public int findRecordCount(String name) {
        return deptDao.selectRecordCount(name);
    }

    @Override
    public List<Dept> findDepts(String name, PageModel pageModel) {//参数名称不属于统一个对象，用map封装
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("start",pageModel.getFirstLimitParam());
        map.put("pageSize",pageModel.getPageSize());
        List<Dept> deptList = deptDao.selectDepts(map);
        return deptList;
    }

    @Override
    public Dept findDeptById(Integer id) {

        return deptDao.selectDeptById(id);
    }

    @Override
    public int modifyDept(Dept dept) {
        return deptDao.updateDept(dept);
    }

    @Override
    public int addDept(Dept dept) {
        return deptDao.insertDept(dept);
    }

    @Override
    public int removeDepts(Map mapIDS) {

        return deptDao.deleteDepts(mapIDS);
    }

   /* @Override
    public int removeDepts(List<Integer> idlist) {
        return deptDao.deleteDepts(idlist);
    }*/

}
