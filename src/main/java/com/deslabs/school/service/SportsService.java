package com.deslabs.school.service;

import com.deslabs.school.domain.Sports;

import java.util.List;

/*
 *Author: Desterio
 *Date: 11/20/2021
 *Year: 2021
 */
public interface SportsService {
    Sports registerSport(Sports sport);
    List<Sports>getAllSports();
    Sports getSports(Integer sportId);
    Object  deleteSport(Integer sportId);
    Sports addStudent(Integer regno, Integer sportId);
}
