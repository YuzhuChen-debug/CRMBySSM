package com.bjpowernode.workbench.services.impl;

import com.bjpowernode.exceptions.activityException.AddActivityErrorException;
import com.bjpowernode.workbench.dao.ActivityDao;
import com.bjpowernode.workbench.dao.ActivityRemarkDao;
import com.bjpowernode.workbench.domain.Activity;
import com.bjpowernode.workbench.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private ActivityRemarkDao activityRemarkDao;

    @Override
    public boolean addActivity(Activity a) throws AddActivityErrorException {
        int  count = activityDao.addActivity(a);
        if(1 != count){
            throw new AddActivityErrorException("添加市场活动列表失败");
        }
        return true;
    }
}
