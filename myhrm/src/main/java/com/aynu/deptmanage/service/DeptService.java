package com.aynu.deptmanage.service;


import com.aynu.entity.Dept;
import com.aynu.pages.PageModel;

import java.util.List;
import java.util.Map;

public interface DeptService {
    int findRecordCount(String name);

    List<Dept> findDepts(String name, PageModel pageModel);

    Dept findDeptById(Integer id);

    int modifyDept(Dept dept);

    int addDept(Dept dept);

    int removeDepts(Map mapIDS);

    //int removeDepts(List<Integer> idlist);
}
