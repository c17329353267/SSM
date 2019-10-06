package com.aynu.jopmanage.service;

import com.aynu.jopmanage.dao.JobDao;
import com.aynu.entity.Job;
import com.aynu.pages.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobDao jobDao;
    @Override
    public int findJobCounts(String name) {
        return jobDao.selectJobCounts(name);
    }

    @Override
    public List<Job> findJobs(String name, PageModel pageModel) {
        Map map = new HashMap();
        map.put("name",name);
        /*map.put("pageModel",pageModel);*/
        //获取起始索引
        map.put("start",pageModel.getFirstLimitParam());
        //设置分页大小
        map.put("pageSize",pageModel.getPageSize());
        List<Job> joblist = jobDao.selectAllJobs(map);
        return joblist;
    }

    @Override
    public Job findJobById(Integer id) {
        return jobDao.selectJobById(id);
    }

    @Override
    public int modifyJobById(Job job) {
        return jobDao.updateJobById(job);
    }

    @Override
    public int addJob(Job job) {
        return jobDao.insertJob(job);
    }

    @Override
    public int removeJobs(Integer[] ids) {
        //由于sqlProvider 不支持数组类型
        //转换成map
        Map map = new HashMap();
        map.put("ids",ids);

        return jobDao.deleteJobs(map);
    }
}
