package com.aynu.jopmanage.controller;

import com.aynu.entity.Job;
import com.aynu.jopmanage.service.JobService;
import com.aynu.pages.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

//职位
@Controller
public class JonController {
    @Autowired
    private JobService jobService;
    //查询职位信息并进行分页
    @RequestMapping("/findJobs.do")
    public String findJobs(@RequestParam(defaultValue = "1")Integer pageIndex, String name, Model model){
        //查询记录总条数
        int recordCount = jobService.findJobCounts(name);
        //创建分页对象
        PageModel pageModel = new PageModel();
        //拿到起始页数，总记录数，分页实体规定一页四条记录，进行分页
        pageModel.setRecordCount(recordCount);
        pageModel.setPageIndex(pageIndex);
        //查询所有职位
        List<Job> jobs = jobService.findJobs(name,pageModel);
        //拿到所有职位进行遍历
       /* for (Job myjob:jobs
             ) {
            System.out.println("myjob="+myjob);
        }*/
        model.addAttribute("jobs",jobs);
        model.addAttribute("name",name);
        model.addAttribute("pageModel",pageModel);
        return "/jsp/job/job.jsp";
    }
    //根据id查找职位信息
    @RequestMapping("/findJobById.do")
    public String findJobById(Integer id,Model model,Integer pageIndex){
        Job job = jobService.findJobById(id);
        model.addAttribute("job",job);
        model.addAttribute("pageIndex",pageIndex);
        return "jsp/job/showUpdateJob.jsp";
    }
    //修改
    @RequestMapping(value = "/modifyJobById.do",produces = "text/html;charset=UTF-8")
    @ResponseBody//传递json数据
    public String modifyJobById(Job job) {
       // System.out.println("进入了modify job="+job);
        int row = jobService.modifyJobById(job);
        if (row > 0) {
            return "修改成功";
        } else {
            return "小猪佩奇，修改失败！";

        }
    }
    //添加
    @RequestMapping(value = "/addJob.do",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addJob(Job job, HttpSession session){
        int row = jobService.addJob(job);
        if(row > 0){
           /* model.addAttribute("addJobName",job.getName());*/
            session.setAttribute("addJobName",job.getName());
            return "添加成功";
        }else {
            return "小猪佩奇，添加失败！";
        }
    }
    //删除
    @RequestMapping("/removeJobs.do")
    @ResponseBody
    public String removeJobs(Integer[] ids){

       /* for (Integer id:ids
             ) {
            System.out.println("id="+id);
        }*/
       try {
           int rows = jobService.removeJobs(ids);
       if(rows == ids.length){
           return "OK";
       }else{
           return "Fail";
        }

       }catch (DataIntegrityViolationException e){
           return "Error";
       }

    }

}
