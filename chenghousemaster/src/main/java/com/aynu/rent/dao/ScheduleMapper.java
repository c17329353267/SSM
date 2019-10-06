package com.aynu.rent.dao;



import com.aynu.entity.Schedule;
import com.aynu.utils.PageModel;

import java.util.List;

public interface ScheduleMapper {
	 void insertschedule(Schedule schedule);
	 List<Schedule> selectAll(PageModel pageModel);
	 void deleteschedule(Integer id);
	 void updateschedule(Schedule schedule);
	 Schedule selectbyid(Integer id);

	int selectAllScheduleCounts();
}
