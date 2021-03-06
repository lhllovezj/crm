package cn.andylhl.crm.workbench.dao;

import cn.andylhl.crm.workbench.domain.ActivityRemark;

import java.util.List;

/***
 * @Title: ActivityRemarkDao
 * @Description:
 * @author: lhl
 * @date: 2020/10/9 15:08
 */
public interface ActivityRemarkDao {

    //根据id获取备注数量
    int getCountByIds(String[] ids);

    //根据id删除市场活动备注
    int deleteActRemByIds(String[] ids);

    List<ActivityRemark> getRemarkListByAid(String id);

    //根据备注id删除备注（根据主键删除）
    int deleteRemark(String id);

    //保存备注信息
    int saveRemark(ActivityRemark remark);

    //更新备注信息
    int updateRemark(ActivityRemark remark);
}
