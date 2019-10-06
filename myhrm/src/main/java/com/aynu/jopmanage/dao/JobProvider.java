package com.aynu.jopmanage.dao;

import com.aynu.entity.Job;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class JobProvider {

    //查询总记录条数
    public String selectJobCounts(final String name){

       return new SQL(){
            {
                this.SELECT("count(*)");
                this.FROM("job_inf");
                if(name != null && !"".equals(name)){
                    this.WHERE("name like '%' #{name} '%'");
                }
            }
        }.toString();

    }
    //查询数据库数据
    public String selectAllJobs(final Map map){

        StringBuffer buffer = new StringBuffer();
        String sql = new SQL(){
            {
                this.SELECT("*");
                this.FROM("job_inf");
                if(map.get("name") != null && !"".equals(map.get("name"))){
                    this.WHERE("name like '%' #{name} '%'");
                }
            }
        }.toString();
        buffer.append(" limit #{start},#{pageSize}");
        return sql+buffer;
    }
    //根据传入的指定id做修改
    public String updateJobById(final Job job){
        String sql = new SQL(){
            {
                this.UPDATE("job_inf");
                if(job.getName() != null && !"".equals(job.getName()) && job.getRemark() != null && !"".equals(job.getRemark())){
                    this.SET("name = #{name},remark = #{remark}");
                }
                this.WHERE("id = #{id}");
            }
        }.toString();
        return sql;
    }
    //添加
    public String insertJob(final Job job){

        return new SQL(){
            {
                this.INSERT_INTO("job_inf");
                if(job.getName() != null && !"".equals(job.getName()) && job.getRemark() != null && !"".equals(job.getRemark())){
                    this.VALUES("name,remark","#{name},#{remark}");
                }
            }
        }.toString();
    }
    //删除
    public String deleteJobs(final Map map){
        StringBuffer buffer = new StringBuffer();
        /*String sql = new SQL(){
            {
                this.DELETE_FROM("job_inf");
            }
        }.toString();*/
        buffer.append("delete from job_inf where id in (");
        Integer[] idsArray = (Integer[]) map.get("ids");
        for (Integer id:idsArray
             ) {
            buffer.append(id+",");
        }
        buffer.deleteCharAt(buffer.length()-1);
        buffer.append(")");
        return buffer.toString();
    }
}
