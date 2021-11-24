package com.deslabs.school.service;

import com.deslabs.school.domain.StudyYear;

import java.util.List;

/*
 *Author: Desterio
 *Date: 11/20/2021
 *Year: 2021
 */
public interface StudyYearService {
    StudyYear createStudyYear(StudyYear studyYear);
    List<StudyYear>getAllYears();
    StudyYear addStudent(Integer regno,Integer id);
    StudyYear addTeacher(Integer teacherId, Integer id);
}
