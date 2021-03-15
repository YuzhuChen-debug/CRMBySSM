package com.bjpowernode.workbench.services;

import com.bjpowernode.VO.ListAndTotalCount;
import com.bjpowernode.exceptions.activityException.ActivityException;
import com.bjpowernode.exceptions.activityException.AddActivityErrorException;
import com.bjpowernode.workbench.domain.Activity;
import org.springframework.stereotype.Service;

@Service
public interface ActivityService {
    boolean addActivity(Activity a) throws AddActivityErrorException;

    ListAndTotalCount getActivityListByFuzzySearch(Activity activity, int pageCount, int pageSize) throws ActivityException;
}
