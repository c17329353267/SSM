package com.aynu.employmanage.controller;

import com.aynu.employmanage.service.EmployeeService;
import com.aynu.entity.Dept;
import com.aynu.entity.Employee;
import com.aynu.entity.Job;
import com.aynu.pages.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/findAllEmployees.do")
    public String findEmployees(@RequestParam(defaultValue = "1")int pageIndex, Model model,
                                Employee employee,Integer dept_id,Integer job_id){

       this.assocation(job_id,dept_id,employee);
        int recordCount = employeeService.findAllCounts(employee);
        //System.out.println("recordCount="+recordCount);
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        pageModel.setPageSize(2);
        //查询数据库中的所有数据
        pageModel.setRecordCount(recordCount);
        //查询所有职位job
        List<Job> jobs = employeeService.findAllJobs();
        //查询所有的部门
        List<Dept> depts = employeeService.findAllDepts();
        //查询数据并做分页
        List<Employee> employees = employeeService.findAllEmployees(employee,pageModel);
        //查询出后放入request
        model.addAttribute("employees",employees);
        model.addAttribute("jobs",jobs);
        model.addAttribute("depts",depts);
        model.addAttribute("pageModel",pageModel);
        model.addAttribute("employee",employee);
        return "/jsp/employee/employee.jsp";
    }
    //根据id查找
    @RequestMapping("/findEmployeeById.do")
    public String findEmployeeById(Integer id,Model model) throws ParseException {

        Employee employee = employeeService.findEmployeeById(id);
        System.out.println("fBId="+employee);
        List<Job> jobs = employeeService.findAllJobs();
        List<Dept> depts = employeeService.findAllDepts();

        model.addAttribute("employee",employee);
        model.addAttribute("jobs",jobs);
        model.addAttribute("depts",depts);
        return "/jsp/employee/showUpdateEmployee.jsp";
    }
    //修改
    @RequestMapping("/modifyEmployee.do")
    public String modifyEmployee(Employee employee,Integer job_id,Integer dept_id,Model model){
        this.assocation(job_id,dept_id,employee);
        //System.out.println("modifE="+employee);
        int rows = employeeService.modifyEmployees(employee);
        System.out.println("rows"+rows);
        if(rows > 0){
           return "findAllEmployees.do";
        }else {
            model.addAttribute("fail","员工信息修改失败！");
            return "/jsp/fail.jsp";
        }


    }
    //删除
    @RequestMapping("/removeEmployee.do")
    public String removeEmployee(Integer[] ids,Model model){
        //根据ids进行删除
        int rows = employeeService.removeEmployee(ids);
        //删除的长度等于数组的长度
        if(rows == ids.length){
            //删除成功
            return "findAllEmployees.do";
        }else{
            model.addAttribute("fail","删除失败");
            return "/fail.jsp";
        }

    }
    //添加员工前要把Job和Dept信息查出来显示到添加页面
    @RequestMapping("/findJobAndDept.do")
    public String findJobAndDept(Model model){

        List<Job> jobs = employeeService.findAllJobs();
        //查询所有的部门
        List<Dept> depts = employeeService.findAllDepts();
        model.addAttribute("jobs",jobs);
        model.addAttribute("depts",depts);
        return "/jsp/employee/showAddEmployee.jsp";
    }
    //员工添加
    @RequestMapping("/addEmployee.do")
    public String addEmployee(Employee employee,Model model){
        System.out.println("employeeAdd="+employee);
        int rows = employeeService.addEmployee(employee);
        if(rows >0){
            return "/findAllEmployees.do";
        }else{
            model.addAttribute("fail","添加员工失败");
            return "/jsp/fail.jsp";
        }

    }
    public void assocation(Integer job_id,Integer dept_id,Employee employee){
        Job job = new Job();

        if(job_id != null){
            job.setId(job_id);
            employee.setJob(job);
        }
        Dept dept = new Dept();
        if(dept_id != null){
            dept.setId(dept_id);
            employee.setDept(dept);
        }
    }

}
