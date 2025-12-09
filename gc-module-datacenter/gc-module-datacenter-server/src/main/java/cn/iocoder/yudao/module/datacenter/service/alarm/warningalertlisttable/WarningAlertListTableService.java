package cn.iocoder.yudao.module.datacenter.service.alarm.warningalertlisttable;

import java.util.*;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.warningalertlisttable.vo.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.warningalertlisttable.WarningAlertListTableDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import org.springframework.web.multipart.MultipartFile;

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


    /**
     * 同步ThingsBoard告警到预警告警列表
     *
     * @param overwrite 是否覆盖已存在的记录
     * @return 同步结果
     */
    WarningAlertListTableSyncRespVO syncAllAlarmsFromThingsBoard(Boolean overwrite);

    /**
     * 验证并处理现场照片(Base64)
     * 将上传的文件转换为Base64格式
     */
    Map<String, Object> uploadScenePhotosBase64(List<MultipartFile> files);

    /**
     * 获取现场照片列表
     */
    List<String> getScenePhotos(Long alertId);

    /**
     * 删除现场照片
     */
    boolean deleteScenePhoto(Long alertId, Integer photoIndex);

    /**
     * 获取责任人告警统计
     */
    List<ResponsiblePersonStatisticsRespVO> getResponsiblePersonStatistics();

    /**
     * 获取责任人预警等级统计
     */
    List<ResponsiblePersonLevelStatisticsRespVO> getResponsiblePersonLevelStatistics(ResponsiblePersonLevelStatisticsReqVO reqVO);
}