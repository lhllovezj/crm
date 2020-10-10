package cn.andylhl.crm.workbench.dao;

import cn.andylhl.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

/***
 * @Title: ActivityDao
 * @Description:
 * @author: lhl
 * @date: 2020/10/9 15:07
 */
public interface ActivityDao {

    int save(Activity activity);

    int getTotalByCondition(Map<String, Object> conditionMap);

    List<Activity> getActivityByCondition(Map<String, Object> conditionMap);
}