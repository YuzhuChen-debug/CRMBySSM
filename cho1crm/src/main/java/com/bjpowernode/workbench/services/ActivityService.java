package com.bjpowernode.workbench.services;

import com.bjpowernode.exceptions.activityException.AddActivityErrorException;
import com.bjpowernode.workbench.domain.Activity;
import org.springframework.stereotype.Service;

@Service
public interface ActivityService {
    boolean addActivity(Activity a) throws AddActivityErrorException;
}
