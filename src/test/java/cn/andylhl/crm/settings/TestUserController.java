package cn.andylhl.crm.settings;

import cn.andylhl.crm.exception.ActivityExecption;
import cn.andylhl.crm.exception.ActivityRemarkExecption;
import cn.andylhl.crm.settings.dao.DicTypeDao;
import cn.andylhl.crm.settings.dao.UserDao;
import cn.andylhl.crm.settings.domain.DicType;
import cn.andylhl.crm.settings.domain.DicValue;
import cn.andylhl.crm.settings.domain.User;
import cn.andylhl.crm.settings.service.DicService;
import cn.andylhl.crm.settings.service.UserService;
import cn.andylhl.crm.utils.MD5Util;
import cn.andylhl.crm.utils.UUIDUtil;
import cn.andylhl.crm.vo.PaginationVO;
import cn.andylhl.crm.workbench.dao.ActivityDao;
import cn.andylhl.crm.workbench.dao.ActivityRemarkDao;
import cn.andylhl.crm.workbench.domain.Activity;
import cn.andylhl.crm.workbench.domain.ActivityRemark;
import cn.andylhl.crm.workbench.domain.Clue;
import cn.andylhl.crm.workbench.service.ActivityService;
import cn.andylhl.crm.workbench.service.ClueService;
import org.apache.ibatis.javassist.util.HotSwapAgent;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/***
 * @Title: TestUserController
 * @Description:
 * @author: lhl
 * @date: 2020/10/8 18:31
 */
public class TestUserController {

    @Test
    public void test01(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//        UserService userService = (UserService) ac.getBean("userServiceImpl");
//        System.out.println("--UserService--" + userService.getClass().getName());
//        userService.login("", "","" );
        UserDao userDao = (UserDao) ac.getBean("userDao");
        Map<String, Object> map = new HashMap<>();
        map.put("loginAct", "zs");
        map.put("loginPwd", MD5Util.getMD5("123"));
        User user = userDao.login(map);
        System.out.println("*--查询结果--");
        System.out.println(user);

    }

    @Test
    public void test02(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = (UserDao) ac.getBean("userDao");
        List<User> userList = userDao.getUerList();
        for (User u : userList){
            System.out.println(u);
        }
    }

    @Test
    public void test03(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        ActivityService service = (ActivityService) ac.getBean("activityServiceImpl");
        System.out.println("servcice: "+ service.getClass().getName());
        Activity activity = new Activity();
        activity.setId(UUIDUtil.getUUID());
        try {
            service.save(activity);
            System.out.println("没有异常出现");
        } catch (ActivityExecption activityExecption) {
            activityExecption.printStackTrace();
            System.out.println("异常已经被捕捉");
        }
    }

    @Test
    public void test04(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        ActivityDao activityDao = (ActivityDao) ac.getBean("activityDao");
        Map<String, Object> conditionMap = new HashMap<>();
//        String name = request.getParameter("name");
//        String owner = request.getParameter("owner");
//        String startDate = request.getParameter("startDate");
//        String endDate = request.getParameter("endDate");
//        String pageNoStr = request.getParameter("pageNo");
//        String pageSizeStr = request.getParameter("pageSize");
//        int pageNo = Integer.valueOf(pageNoStr);
//        int pageSize = Integer.valueOf(pageSizeStr);
        conditionMap.put("pageNo", 0);
        conditionMap.put("pageSize", 10);
        conditionMap.put("name", "2");
//        conditionMap.put("owner", "李四");
//        conditionMap.put("startDate", "2020-11-09");
//        conditionMap.put("endDate", );

        int total = activityDao.getTotalByCondition(conditionMap);
        List<Activity> dataList = activityDao.getActivityByCondition(conditionMap);
        System.out.println("------总记录条数：----" + total);
        for (Activity a : dataList){
            System.out.println(a);
        }

    }

    @Test
    public void test05(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        ActivityService service = (ActivityService) ac.getBean("activityServiceImpl");
        String[] ids = { "6c6392eadc7849ffad8ce8c1afa4a4fe"};
//        int cnt1 = activityRemarkDao.getCountByIds(ids);
//        System.out.println("---------查询备注数目：" + cnt1);
//        int cnt2 = activityRemarkDao.deleteActRemByIds(ids);
//        System.out.println("---------删除备注数目：" + cnt2);
        try {
            service.deleteAct(ids);
            System.out.println("----删除成功，无异常---");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("----删除失败，无异常---");
        }

    }

    @Test
    public void test06(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        ActivityService service = (ActivityService) ac.getBean("activityServiceImpl");
        Activity a = service.getActDetatilById("1b2c8961708c4e1785c1627b3cabfe6c");
        System.out.println(a);
    }

    @Test
    public void test07(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        ActivityService service = (ActivityService) ac.getBean("activityServiceImpl");
        List<ActivityRemark> remarkList = service.getRemarkListByAid("1b2c8961708c4e1785c1627b3cbfe6c");
        System.out.println("返回值："+ remarkList);
        for (ActivityRemark ar : remarkList){
            System.out.println(ar);
        }
    }

    @Test
    public void test08(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        ActivityService service = (ActivityService) ac.getBean("activityServiceImpl");
        try {
            service.deleteRemark("A0002");
            System.out.println("-------删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------删除失败");
        }
    }

    @Test
    public void test09(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        DicService service = (DicService) ac.getBean("dicServiceImpl");
//        DicTypeDao dicTypeDao = (DicTypeDao) ac.getBean("dicTypeDao");
//        List<DicType> typeList = dicTypeDao.getAll();
//        for (DicType type : typeList){
//            System.out.println(type);
//        }
        Map<String, List<DicValue>> map = service.getAll();
        Set<String> set = map.keySet();
        for (String s : set){
            System.out.println("++++++");
            List<DicValue> valueList = map.get(s);
            for (DicValue value : valueList){
                System.out.println(value);
            }
        }
    }

    @Test
    public void test10(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        ClueService service = (ClueService) ac.getBean("clueServiceImpl");

        Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("pageNo", 0);
        conditionMap.put("pageSize", 3);
//        conditionMap.put("fullname", );
//        conditionMap.put("company", "万");
//        conditionMap.put("phone", "155");
//        conditionMap.put("source", "员工介绍");
        conditionMap.put("owner", "张三");
//        conditionMap.put("mphone", mphone);
//        conditionMap.put("state", state);
        PaginationVO<Clue> paginationVO = service.pageList(conditionMap);
        System.out.println("====查询结果====");
        Integer total = paginationVO.getTotal();
        List<Clue> dataList = paginationVO.getDataList();
        System.out.println("总记录条数: "+total);
        for (Clue clue : dataList){
            System.out.println(clue);
        }

    }
}
