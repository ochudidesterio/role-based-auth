package com.deslabs.school.service;

/*
 *Author: Desterio
 *Date: 11/20/2021
 *Year: 2021
 */

import com.deslabs.school.domain.Sports;
import com.deslabs.school.domain.Student;
import com.deslabs.school.repository.SportsDao;
import com.deslabs.school.repository.StudentDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional

@Service
public class SportsServiceImpl implements SportsService {
    private final SportsDao sportsDao;
    private final StudentDao studentDao;
@Autowired
    public SportsServiceImpl(SportsDao sportsDao, StudentDao studentDao) {
        this.sportsDao = sportsDao;
        this.studentDao = studentDao;
    }

    @Override
    public Sports registerSport(Sports sport) {
        return sportsDao.save(sport);
    }

    @Override
    public List<Sports> getAllSports() {
        return sportsDao.findAll();
    }

    @Override
    public Sports getSports(Integer sportId) {
        return sportsDao.findById(sportId).get();
    }

    @Override
    public Object deleteSport(Integer sportId) {
        sportsDao.deleteById(sportId);
        return null;
    }

    @Override
    public Sports addStudent(Integer regno, Integer sportId) {
        Student student = studentDao.findById(regno).get();
        Sports sports = getSports(sportId);
        List<Student>students = sports.getStudents();
        students.add(student);
        sports.setStudents(students);
        return sportsDao.save(sports);
    }
}
