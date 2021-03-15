package com.bjpowernode.workbench.services.impl;

import com.bjpowernode.VO.ListAndTotalCount;
import com.bjpowernode.exceptions.activityException.ActivityException;
import com.bjpowernode.exceptions.activityException.AddActivityErrorException;
import com.bjpowernode.exceptions.activityException.SearchActivityCountErrorException;
import com.bjpowernode.exceptions.activityException.SearchActivityListException;
import com.bjpowernode.workbench.dao.ActivityDao;
import com.bjpowernode.workbench.dao.ActivityRemarkDao;
import com.bjpowernode.workbench.domain.Activity;
import com.bjpowernode.workbench.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.LambdaConversionException;
import java.util.List;

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

    @Override
    public ListAndTotalCount getActivityListByFuzzySearch(Activity activity, int pageCount, int pageSize) throws ActivityException {
        ListAndTotalCount<Activity> LATVO = new ListAndTotalCount<>();
        int total = activityDao.getTotal(activity);
        if(total==-1){
            throw new SearchActivityCountErrorException("查询市场活动条数错误");
        }
        List<Activity> aList = activityDao.getActivityListByFuzzySearch(activity,pageCount,pageSize);
        if(aList==null){
            throw new SearchActivityListException("查询市场活动列表错误");
        }
        LATVO.setDataList(aList);
        LATVO.setTotal(total);
        return LATVO;
    }
}
