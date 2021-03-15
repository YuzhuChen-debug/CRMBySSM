package com.bjpowernode.workbench.services;

import com.bjpowernode.VO.ListAndTotalCount;
import com.bjpowernode.exceptions.activityException.ActivityException;
import com.bjpowernode.exceptions.activityException.AddActivityErrorException;
import com.bjpowernode.workbench.domain.Activity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ActivityService {
    boolean addActivity(Activity a) throws AddActivityErrorException;

    ListAndTotalCount getActivityListByFuzzySearch(Map<String,Object> map) throws ActivityException;
}
