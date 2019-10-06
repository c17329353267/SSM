package com.aynu.employmanage.dao;

import com.aynu.entity.Dept;
import com.aynu.entity.Employee;
import com.aynu.entity.Job;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeDao {
    //查询员工管理表的条数
    /*int selectEmpInfCounts(@Param("job_id") Integer job_id, @Param("dept_id") Integer dept_id, Integer sex, String phone, String name, String card_id);*/
    int selectEmpInfCounts(Employee employee);
    //查询所有的职位信息
    List<Job> selectAllJobInf();
    //查询所有的部门信息
    List<Dept> selectAllDeptInf();
    //查询所有的员工管理信息
    List<Employee> selectAllEmployees(Map map);

    Employee selectEmployeeById(Integer id);

    int updateEmployee(Employee employee);

    int deleteEmployee(@Param("ids") Integer[] ids);

    int insertEmployee(Employee employee);
}
