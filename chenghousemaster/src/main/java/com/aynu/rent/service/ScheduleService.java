package com.aynu.rent.service;

import com.aynu.entity.Schedule;
import com.aynu.utils.PageModel;

import java.util.List;


public interface ScheduleService {
	 void insertschedule(Schedule schedule);
	 List<Schedule> selectAll(PageModel pageModel);
	 void deleteschedule(Integer id);
	 void updateschedule(Schedule schedule);
	 Schedule selectbyid(Integer id);

	int selectAllScheduleCounts();
}
