package com.aynu.deptmanage.controller;

import com.aynu.deptmanage.service.DeptService;
import com.aynu.entity.Dept;
import com.aynu.pages.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DeptController {

    @Autowired
    private DeptService deptService;
    //部门查询
    @RequestMapping("/findDepts.do")
    public String findDepts(@RequestParam(defaultValue = "1")Integer pageIndex, String name, Model model){
        //查询总条数
        int recordCount = deptService.findRecordCount(name);
        //分页
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        pageModel.setRecordCount(recordCount);
        //查询部门并进行分页
        List<Dept> deptList = deptService.findDepts(name,pageModel);
        /*for (Dept depts:deptList) {
            System.out.println("depts"+depts);
        }*/
        model.addAttribute("pageModel",pageModel);
        model.addAttribute("depts",deptList);
        model.addAttribute("name",name);
       return "/jsp/dept/dept.jsp";
        //return null;
    }
    //根据id查询
    @RequestMapping("/findDeptById.do")
    public String findDeptById(Integer id,Model model){

        Dept dept = deptService.findDeptById(id);
        model.addAttribute("dept",dept);
        //System.out.println("findDeptById="+dept);
        return "/jsp/dept/showUpdateDept.jsp";
    }
    //修改信息
    @RequestMapping("/modifyDept.do")
    @ResponseBody//用于接收json数据
    public String modifyDept(Dept dept,Model model){
        //System.out.println("ModifyDeptdept"+dept);
        int row = deptService.modifyDept(dept);
        if(row > 0){
            return "OK";
        }else {
            return "Fail";
        }
    }

    //添加
    @RequestMapping("/addDept.do")
    @ResponseBody
    public String addDept(Dept dept){
        int row = deptService.addDept(dept);
        if(row > 0){
            return "OK";
        } else {
            return "Fail";
        }
    }

    //删除
    //@RequestMapping(value = "/deleteDepts.do",produces = "text/html;charset=UTF-8")
    @RequestMapping("/removeDepts.do")
    @ResponseBody
    public String removeDepts(String ids){
        String[] idArray = ids.split(",");

        Map mapIDS = new HashMap();
        mapIDS.put("mapIDS",idArray);

        try {
            int rows = deptService.removeDepts(mapIDS);

            if (rows == idArray.length) {
                return "OK";
            }else {
                return "Fail";
            }
        }catch (DataIntegrityViolationException e){
            return "ERROR";
        }

    }
}
