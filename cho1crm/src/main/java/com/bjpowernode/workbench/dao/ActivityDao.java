package com.bjpowernode.workbench.dao;

import com.bjpowernode.workbench.domain.Activity;

import java.util.List;

public interface ActivityDao {
    int addActivity(Activity a);

    int getTotal(Activity activity);

    List<Activity> getActivityListByFuzzySearch(Activity activity, int pageCount, int pageSize);
}
