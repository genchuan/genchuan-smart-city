package cn.iocoder.yudao.module.datacenter.service.alarmlist;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarmlist.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarmlist.AlarmListDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 预警告警列 Service 接口
 *
 * @author 亘川智城
 */
public interface AlarmListService {

    /**
     * 创建预警告警列
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAlarmList(@Valid AlarmListSaveReqVO createReqVO);

    /**
     * 更新预警告警列
     *
     * @param updateReqVO 更新信息
     */
    void updateAlarmList(@Valid AlarmListSaveReqVO updateReqVO);

    /**
     * 删除预警告警列
     *
     * @param id 编号
     */
    void deleteAlarmList(Long id);

    /**
     * 获得预警告警列
     *
     * @param id 编号
     * @return 预警告警列
     */
    AlarmListDO getAlarmList(Long id);

    /**
     * 获得预警告警列分页
     *
     * @param pageReqVO 分页查询
     * @return 预警告警列分页
     */
    PageResult<AlarmListDO> getAlarmListPage(AlarmListPageReqVO pageReqVO);

}