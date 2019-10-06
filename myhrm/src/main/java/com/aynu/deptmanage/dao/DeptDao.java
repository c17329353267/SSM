package com.aynu.deptmanage.dao;

import com.aynu.entity.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface DeptDao {
    @SelectProvider(method = "selectRecordCount",type =DeptProvider.class )
    int selectRecordCount(String name);

    @SelectProvider(method = "selectDepts",type = DeptProvider.class)
    List<Dept> selectDepts(Map<String, Object> map);

    @Select("select * from dept_inf where id = #{id}")
    Dept selectDeptById(Integer id);
    //为了防止前端未加校验，在后台不能提交空的字符到数据库，
    @UpdateProvider(type=DeptProvider.class,method = "updateDept")
    int updateDept(Dept dept);
    //添加
    @InsertProvider(type = DeptProvider.class,method = "insertDept")
    int insertDept(Dept dept);

    @DeleteProvider(type = DeptProvider.class,method = "deleteDepts")
    int deleteDepts(Map mapIDS);
    /*//删除
    @DeleteProvider(type = DeptProvider.class,method = "deleteDepts")
    int deleteDepts(List<Integer> idlist);*/
}
