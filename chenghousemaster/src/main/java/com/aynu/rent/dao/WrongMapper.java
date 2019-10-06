package com.aynu.rent.dao;



import com.aynu.entity.Wrong;

import java.util.List;
import java.util.Map;


public interface WrongMapper {
  //查看房屋出现的障碍
   List<Wrong> findwrong(Map map);
  int selectNotSolveWrongCounts(Integer id);
   Wrong findbyid(Integer id);
   void insertwrong(Wrong wrong);
   void deletewrong(Integer id);

    int selectNotSolveAllCounts();
}
