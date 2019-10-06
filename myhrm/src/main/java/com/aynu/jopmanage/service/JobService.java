package com.aynu.jopmanage.service;

import com.aynu.entity.Job;
import com.aynu.pages.PageModel;

import java.util.List;

public interface JobService {
    int findJobCounts(String name);

    List<Job> findJobs(String name, PageModel pageModel);

    Job findJobById(Integer id);

    int modifyJobById(Job job);

    int addJob(Job job);

    int removeJobs(Integer[] ids);
}
