package com.bjpowernode.workbench.web.controller;

import com.bjpowernode.VO.ListAndTotalCount;
import com.bjpowernode.exceptions.activityException.ActivityException;
import com.bjpowernode.exceptions.activityException.AddActivityErrorException;
import com.bjpowernode.exceptions.userException.UserCouldNotFoundException;
import com.bjpowernode.settings.domain.User;
import com.bjpowernode.settings.services.UserService;
import com.bjpowernode.utils.DateTimeUtil;
import com.bjpowernode.utils.UUIDUtil;
import com.bjpowernode.workbench.domain.Activity;
import com.bjpowernode.workbench.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/workbench/activity")
public class ActivityController {
    @Autowired
 private ActivityService activityService;
    @Autowired
 private UserService userService;
   @RequestMapping("/getuList")
   @ResponseBody
   public Map<String,Object> getuList() throws UserCouldNotFoundException {
        Map<String,Object> map = new HashMap<>();
        List<User> uList = userService.getuList();
        map.put("success",true);
        map.put("uList",uList);
        return map;
   }


    @RequestMapping("/addActivity")
    @ResponseBody
    public Map<String,Object> addActivity(Activity activity, HttpServletRequest request) throws AddActivityErrorException {
       Activity a = activity;
        a.setId(UUIDUtil.getUUID());
        a.setCreateTime(DateTimeUtil.getSysTime());
        a.setCreateBy(((User)request.getSession().getAttribute("user")).getName());
        Map<String,Object> map = new HashMap<>();
        boolean success = activityService.addActivity(a);
        map.put("success",success);
        return map;
    }


    @RequestMapping("/getActivityListByFuzzySearch")
    @ResponseBody
    public Map<String,Object> getActivityListByFuzzySearch(Activity activity,String PageNo,String PageSize,HttpServletRequest request) throws ActivityException {
        int pageNo = Integer.parseInt(PageNo);
        int pageSize =Integer.parseInt(PageSize);
        int pageCount = pageSize*(pageNo-1);
        Map<String,Object> map = new HashMap<>();
        ListAndTotalCount LATVO = activityService.getActivityListByFuzzySearch(activity,pageCount,pageSize);
        map.put("success",true);
        map.put("LATVO",LATVO);
        return map;
    }
}
