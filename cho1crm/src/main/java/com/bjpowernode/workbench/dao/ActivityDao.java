package com.bjpowernode.workbench.dao;

import com.bjpowernode.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityDao {
    int addActivity(Activity a);

    int getTotal(Map<String,Object> map);

    List<Activity> getActivityListByFuzzySearch(Map<String,Object> map);

    Activity getActivityById(String id);
}
