package com.aynu.deptmanage.dao;

import com.aynu.entity.Dept;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class DeptProvider {

    public String selectRecordCount(final String name){
        //StringBuffer sb = new StringBuffer();
        String sql = new SQL(){
            {
                this.SELECT("count(*)");
                FROM("dept_inf");
                if(name != null && !"".equals(name)){
                    this.WHERE("name like '%' #{name} '%' ");
                }
            }
        }.toString();

        return sql;
    }

    public String selectDepts(final Map<String,Object> map){

      final   StringBuffer buffer = new StringBuffer();
        String sql = new SQL(){
            {
                this.SELECT("*");
                FROM("dept_inf");
                if(map.get("name") != null && !"".equals(map.get("name"))){
                    this.WHERE("name like '%' #{name} '%' ");
                }
                buffer.append(" limit #{start},#{pageSize}");
            }

        }.toString();
        return sql+buffer;
    }

    //修改
    public String updateDept(final Dept dept){
        return new SQL(){
            {
                this.UPDATE("dept_inf");
                if(dept.getName() != null && !"".equals(dept.getName()) && dept.getRemark() != null && !"".equals(dept.getRemark())){
                    this.SET("name = #{name},remark = #{remark}");
                }
                this.WHERE("id = #{id}");

            }
        }.toString();
    }

    //添加
    public String insertDept(final Dept dept){
        return new SQL(){
            {
                this.INSERT_INTO("dept_inf");
                if (dept.getName() != null && !"".equals(dept.getName()) && dept.getRemark() != null && !"".equals(dept.getRemark())){
                    this.VALUES("name,remark","#{name},#{remark}");
                }
            }
        }.toString();
    }
    //试一试list集合
    //删除
    public String deleteDepts(final Map mapIDS){

        final StringBuffer sql = new StringBuffer();
        sql.append("delete from dept_inf where id in (");
        String[] ids = (String[]) mapIDS.get("mapIDS");
        for (String id:ids
             ) {
            sql.append(id+",");
        }
        sql.deleteCharAt(sql.length()-1);
        sql.append(")");
        return  sql.toString();
    }
}
