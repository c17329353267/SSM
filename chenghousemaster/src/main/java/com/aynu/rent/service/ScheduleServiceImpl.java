package com.aynu.rent.service;


import com.aynu.entity.Schedule;
import com.aynu.rent.dao.ScheduleMapper;
import com.aynu.utils.PageModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
	@Resource
	private ScheduleMapper scheduleMapper;
	@Override
	public void insertschedule(Schedule schedule) {
		scheduleMapper.insertschedule(schedule);
		
	}

	@Override
	public List<Schedule> selectAll(PageModel pageModel) {
		List<Schedule> list=scheduleMapper.selectAll(pageModel);
		return list;
	}

	@Override
	public void deleteschedule(Integer id) {
		scheduleMapper.deleteschedule(id);
		
	}

	@Override
	public void updateschedule(Schedule schedule) {
		scheduleMapper.updateschedule(schedule);
		
	}

	@Override
	public Schedule selectbyid(Integer id) {
		Schedule schedule=scheduleMapper.selectbyid(id);
		return schedule;
	}

	@Override
	public int selectAllScheduleCounts() {
		return scheduleMapper.selectAllScheduleCounts();
	}

}
