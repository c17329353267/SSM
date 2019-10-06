package com.aynu.jopmanage.dao;

import com.aynu.entity.Job;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface JobDao {
    //查询记录条数
    @SelectProvider(type = JobProvider.class,method = "selectJobCounts")
    int selectJobCounts(String name);
    //查询总记录
    @SelectProvider(type = JobProvider.class,method = "selectAllJobs")
    List<Job> selectAllJobs(Map map);
    //根据id查询指定的job
    @Select("select * from job_inf where id = #{id}")
    Job selectJobById(Integer id);

    //防止前端未加修改条件为空的判断条件，在此做数据判断
    @UpdateProvider(type = JobProvider.class,method = "updateJobById")
    int updateJobById(Job job);

    //添加操作
    @InsertProvider(type = JobProvider.class,method = "insertJob")
    int insertJob(Job job);

    //删除
    @DeleteProvider(type = JobProvider.class,method = "deleteJobs")
    int deleteJobs(Map map);
}
