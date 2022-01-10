package com.deslabs.school.service;

import com.deslabs.school.domain.Parent;

import java.util.List;

/*
 *Author: Desterio
 *Date: 12/20/2021
 *Year: 2021
 */
public interface ParentService {
    Parent registerParent(Parent parent);
    Parent getParent(Integer parentId);
    void deleteParent(Integer parentId);
    Parent updateParent(Parent parent);
    List<Parent>getAllParents();
}
