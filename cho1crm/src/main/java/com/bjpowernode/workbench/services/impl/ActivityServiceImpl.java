package com.bjpowernode.workbench.services.impl;

import com.bjpowernode.VO.ListAndTotalCount;
import com.bjpowernode.exceptions.activityException.ActivityException;
import com.bjpowernode.exceptions.activityException.AddActivityErrorException;
import com.bjpowernode.exceptions.activityException.SearchActivityCountErrorException;
import com.bjpowernode.exceptions.activityException.SearchActivityListException;
import com.bjpowernode.exceptions.userException.UserCouldNotFoundException;
import com.bjpowernode.settings.dao.UserDao;
import com.bjpowernode.settings.domain.User;
import com.bjpowernode.workbench.dao.ActivityDao;
import com.bjpowernode.workbench.dao.ActivityRemarkDao;
import com.bjpowernode.workbench.domain.Activity;
import com.bjpowernode.workbench.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private UserDao userDao;
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
    public ListAndTotalCount getActivityListByFuzzySearch(Map<String,Object> map) throws ActivityException {
        ListAndTotalCount<Activity> LATVO = new ListAndTotalCount<>();
        int total = activityDao.getTotal(map);
        if(total==-1){
            throw new SearchActivityCountErrorException("查询市场活动条数错误");
        }
        List<Activity> aList = activityDao.getActivityListByFuzzySearch(map);
        if(aList==null){
            throw new SearchActivityListException("查询市场活动列表错误");
        }
        LATVO.setDataList(aList);
        LATVO.setTotal(total);
        return LATVO;
    }

    @Override
    public Map<String, Object> getUserListAndActivityById(String id) throws UserCouldNotFoundException, SearchActivityListException {
        System.out.println(2);
        Map<String,Object> map = new HashMap<>();
        List<User> uList = userDao.getuList();
        if(uList==null){
            throw new UserCouldNotFoundException("搜索用户列表失败");
        }
        Activity a = activityDao.getActivityById(id);
        if(a==null){
            throw new SearchActivityListException("查询市场活动异常");
        }
        map.put("uList",uList);
        map.put("a",a);
        System.out.println(4);
        return map;
    }
}
