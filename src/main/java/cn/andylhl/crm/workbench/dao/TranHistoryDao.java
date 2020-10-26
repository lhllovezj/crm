package cn.andylhl.crm.workbench.dao;

import cn.andylhl.crm.workbench.domain.TranHistory;

/***
 * @Title: TranHistory
 * @Description: 交易历史dao
 * @author: lhl
 * @date: 2020/10/25 21:42
 */
public interface TranHistoryDao {

    //保存交易历史信息
    int save(TranHistory tranHistory);
}
