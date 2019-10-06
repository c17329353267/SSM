package com.aynu.employmanage.service;

import com.aynu.employmanage.dao.EmployeeDao;
import com.aynu.entity.Dept;
import com.aynu.entity.Employee;
import com.aynu.entity.Job;
import com.aynu.pages.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;


    @Override
    public int findAllCounts(Employee employee) {
        return employeeDao.selectEmpInfCounts(employee);
    }

    @Override
    public List<Job> findAllJobs() {
        return employeeDao.selectAllJobInf();
    }

    @Override
    public List<Dept> findAllDepts() {
        return employeeDao.selectAllDeptInf();
    }

    @Override
    public List<Employee> findAllEmployees(Employee employee, PageModel pageModel) {
        Map map = new HashMap();
        map.put("employee",employee);
        map.put("pageModel",pageModel);
        //分页只需要起始页码start , 页面大小 pageSize
        return employeeDao.selectAllEmployees(map);
    }

    @Override
    public Employee findEmployeeById(Integer id) {
        return employeeDao.selectEmployeeById(id);
    }

    @Override
    public int modifyEmployees(Employee employee) {

        return employeeDao.updateEmployee(employee);
    }

    @Override
    public int removeEmployee(Integer[] ids) {
        return employeeDao.deleteEmployee(ids);
    }

    @Override
    public int addEmployee(Employee employee) {
        employee.setCreate_date(new Date());
        return employeeDao.insertEmployee(employee);
    }
}
