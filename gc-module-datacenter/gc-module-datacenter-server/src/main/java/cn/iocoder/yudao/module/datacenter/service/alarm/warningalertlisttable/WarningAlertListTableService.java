package cn.iocoder.yudao.module.datacenter.service.alarm.warningalertlisttable;

import java.util.*;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.warningalertlisttable.vo.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.warningalertlisttable.WarningAlertListTableDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 预警告警列表 Service 接口
 *
 * @author 亘川智城
 */
public interface WarningAlertListTableService {

    /**
     * 创建预警告警列表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWarningAlertListTable(@Valid WarningAlertListTableSaveReqVO createReqVO);

    /**
     * 更新预警告警列表
     *
     * @param updateReqVO 更新信息
     */
    void updateWarningAlertListTable(@Valid WarningAlertListTableSaveReqVO updateReqVO);

    /**
     * 删除预警告警列表
     *
     * @param id 编号
     */
    void deleteWarningAlertListTable(Long id);

    /**
     * 获得预警告警列表
     *
     * @param id 编号
     * @return 预警告警列表
     */
    WarningAlertListTableDO getWarningAlertListTable(Long id);


    /**
     * 获得预警告警列表分页
     *
     * @param pageReqVO 分页查询
     * @return 预警告警列表分页
     */
    PageResult<WarningAlertListTableDO> getWarningAlertListTablePage(WarningAlertListTablePageReqVO pageReqVO);

    /**
     * 获取预警等级分布统计
     */
    List<WarningAlertListTableStatisticsRespVO> getWarningLevelStatistics();

    /**
     * 获取预警状态分布统计
     */
    List<WarningAlertListTableStatisticsRespVO> getWarningStatusStatistics();

    /**
     * 批量导入历史预警记录
     * @param importList 导入记录列表
     * @param allowUpdate 若存在相同告警编号是否允许更新
     * @return 导入结果
     */
    WarningAlertListTableImportRespVO importWarningAlertList(List<WarningAlertListTableImportExcelVO> importList, boolean allowUpdate);

    /**
     *业务流程
     * @param id
     * @return
     */
    Long createWarningAlertListTable(@Valid Long id);


}