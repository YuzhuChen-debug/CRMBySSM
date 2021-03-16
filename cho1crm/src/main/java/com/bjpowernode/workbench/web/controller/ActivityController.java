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
import org.springframework.web.servlet.ModelAndView;

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


    @RequestMapping("/goIndex.do")
    public ModelAndView goLogin()  {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("workbench/activity/index");
        return mv;
    }
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
    public Map<String,Object> getActivityListByFuzzySearch(Activity activity,String pageNoStr,String pageSizeStr,HttpServletRequest request) throws ActivityException {
        int pageNo = Integer.parseInt(pageNoStr);
        int pageSize =Integer.parseInt(pageSizeStr);
        int pageCount = pageSize*(pageNo-1);
        Map<String,Object> map = new HashMap<>();
        map.put("name",activity.getName());
        map.put("owner",activity.getOwner());
        map.put("startDate",activity.getStartDate());
        map.put("endDate",activity.getEndDate());
        map.put("pageCount",pageCount);
        map.put("pageSize",pageSize);
        ListAndTotalCount LATVO = activityService.getActivityListByFuzzySearch(map);
        Map<String,Object> map1 = new HashMap<>();
        map1.put("success",true);
        map1.put("LATVO",LATVO);
        return map1;
    }
}
