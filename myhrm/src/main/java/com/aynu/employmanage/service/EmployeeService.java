package com.aynu.employmanage.service;

import com.aynu.entity.Dept;
import com.aynu.entity.Employee;
import com.aynu.entity.Job;
import com.aynu.pages.PageModel;

import java.util.List;

public interface EmployeeService {
/*    int findAllCounts(Integer job_id, Integer dept_id, Integer sex, String phone, String name, String card_id);*/
    int findAllCounts(Employee employee);

    List<Job> findAllJobs();

    List<Dept> findAllDepts();

    List<Employee> findAllEmployees(Employee employee, PageModel pageModel);

    Employee findEmployeeById(Integer id);

    int modifyEmployees(Employee employee);

    int removeEmployee(Integer[] ids);

    int addEmployee(Employee employee);
}
