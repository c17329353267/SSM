package com.aynu.rent.service;


import com.aynu.entity.Hetong;

public interface HetongService {

	void inserthetong(Hetong hetong);
	Hetong findhetong(String house_id);
	void updatehetong(Hetong hetong);
	void deletehetong(String house_id);
}
