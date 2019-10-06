package com.aynu.rent.dao;


import com.aynu.entity.Hetong;

public interface HetongMapper {
	
	void inserthetong(Hetong hetong);
	Hetong findhetong(String house_id);
	void updatehetong(Hetong hetong);
	void deletehetong(String house_id);
}
