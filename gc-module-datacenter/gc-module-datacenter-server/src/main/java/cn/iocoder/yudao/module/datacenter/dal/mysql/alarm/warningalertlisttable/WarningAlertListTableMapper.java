package cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.warningalertlisttable;

import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.SortingField;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.warningalertlisttable.vo.ResponsiblePersonLevelStatisticsRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.warningalertlisttable.vo.ResponsiblePersonStatisticsRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.warningalertlisttable.vo.WarningAlertListTablePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.warningalertlisttable.vo.WarningAlertListTableStatisticsRespVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.warningalertlisttable.WarningAlertListTableDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预警告警列表 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface WarningAlertListTableMapper extends BaseMapperX<WarningAlertListTableDO> {
    default PageResult<WarningAlertListTableDO> selectPage(WarningAlertListTablePageReqVO reqVO) {

        // 第一步：统一构建完整的查询条件
        LambdaQueryWrapperX<WarningAlertListTableDO> wrapper = buildFullQueryWrapper(reqVO);

        // 第二步：处理特殊排序逻辑
        if ("warningLevel".equals(reqVO.getOrderByColumn())) {
            String caseSql = "CASE warning_level " +
                    "WHEN 'emergency' THEN 4 " +
                    "WHEN 'important' THEN 3 " +
                    "WHEN 'general'   THEN 1 " +
                    "ELSE 99 END " + reqVO.getIsAsc();
            wrapper.last("ORDER BY " + caseSql);
            return selectPage(reqVO, null, wrapper);
        }
        else if ("triggertime".equals(reqVO.getOrderByColumn())) {
            wrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), WarningAlertListTableDO::getTriggerTime);
            return selectPage(reqVO, null, wrapper);
        }
        else if ("requiredcompletetime".equals(reqVO.getOrderByColumn())) {
            wrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), WarningAlertListTableDO::getRequiredCompleteTime);
            return selectPage(reqVO, null, wrapper);
        }

        // 第三步：处理普通排序 - 修复空指针问题
        String orderByColumn = reqVO.getOrderByColumn();
        String isAsc = reqVO.getIsAsc();

        // 添加空值检查和默认排序
        if (orderByColumn == null || orderByColumn.trim().isEmpty()) {
            // 如果没有指定排序字段，使用默认排序（如按ID倒序）
            wrapper.orderByDesc(WarningAlertListTableDO::getId);
            return selectPage(reqVO, null, wrapper);
        }

        // 如果有有效的排序字段，继续原来的逻辑
        SortingField sortingField = new SortingField();
        sortingField.setField(orderByColumn);
        sortingField.setOrder(isAsc != null ? isAsc : "desc"); // 为排序方向提供默认值
        List<SortingField> sortingFields = new ArrayList<>();
        sortingFields.add(sortingField);
        return selectPage(reqVO, sortingFields, wrapper);
    }

    // 提取完整的查询条件构建方法
    private LambdaQueryWrapperX<WarningAlertListTableDO> buildFullQueryWrapper(WarningAlertListTablePageReqVO reqVO) {
        return new LambdaQueryWrapperX<WarningAlertListTableDO>()
                .eqIfPresent(WarningAlertListTableDO::getAlertCode, reqVO.getAlertCode())
                .eqIfPresent(WarningAlertListTableDO::getRelatedObjectType, reqVO.getRelatedObjectType())
                .eqIfPresent(WarningAlertListTableDO::getRelatedObjectId, reqVO.getRelatedObjectId())
                .likeIfPresent(WarningAlertListTableDO::getRelatedObjectName, reqVO.getRelatedObjectName())
                .eqIfPresent(WarningAlertListTableDO::getWarningField, reqVO.getWarningField())
                .eqIfPresent(WarningAlertListTableDO::getWarningType, reqVO.getWarningType())
                .eqIfPresent(WarningAlertListTableDO::getWarningLevel, reqVO.getWarningLevel())
                .eqIfPresent(WarningAlertListTableDO::getWarningStatus, reqVO.getWarningStatus())
                .eqIfPresent(WarningAlertListTableDO::getStatus, reqVO.getStatus()) // 新增status条件
                .eqIfPresent(WarningAlertListTableDO::getTriggerReason, reqVO.getTriggerReason())
                .eqIfPresent(WarningAlertListTableDO::getRelatedEventCode, reqVO.getRelatedEventCode())
                .eqIfPresent(WarningAlertListTableDO::getDispatchDepartment, reqVO.getDispatchDepartment())
                .eqIfPresent(WarningAlertListTableDO::getResponsiblePerson, reqVO.getResponsiblePerson())
                .eqIfPresent(WarningAlertListTableDO::getResponsiblePersonPhone, reqVO.getResponsiblePersonPhone())
                .betweenIfPresent(WarningAlertListTableDO::getTriggerTime, reqVO.getTriggerTime())
                .betweenIfPresent(WarningAlertListTableDO::getRequiredCompleteTime, reqVO.getRequiredCompleteTime())
                .eqIfPresent(WarningAlertListTableDO::getDisposalProgressDesc, reqVO.getDisposalProgressDesc())
                .eqIfPresent(WarningAlertListTableDO::getDisposalAttachmentPath, reqVO.getDisposalAttachmentPath())
                .eqIfPresent(WarningAlertListTableDO::getReviewOpinion, reqVO.getReviewOpinion())
                .eqIfPresent(WarningAlertListTableDO::getReviewer, reqVO.getReviewer())
                .betweenIfPresent(WarningAlertListTableDO::getReviewTime, reqVO.getReviewTime())
                .eqIfPresent(WarningAlertListTableDO::getExtendCategory1, reqVO.getExtendCategory1())
                .eqIfPresent(WarningAlertListTableDO::getExtendCategory2, reqVO.getExtendCategory2())
                .eqIfPresent(WarningAlertListTableDO::getExtendCategory3, reqVO.getExtendCategory3())
                .betweenIfPresent(WarningAlertListTableDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(WarningAlertListTableDO::getDeviceId, reqVO.getDeviceId())
                .eqIfPresent(WarningAlertListTableDO::getProcessInstanceId,reqVO.getProcessInstanceId())
                .eqIfPresent(WarningAlertListTableDO::getWarningTypeId,reqVO.getWarningTypeId())
                .eqIfPresent(WarningAlertListTableDO::getRegionCode, reqVO.getRegionCode())
                .eqIfPresent(WarningAlertListTableDO::getRegionName, reqVO.getRegionName())
                .eqIfPresent(WarningAlertListTableDO::getGridId, reqVO.getGridId())
                .eqIfPresent(WarningAlertListTableDO::getGridName, reqVO.getGridName())
                .eqIfPresent(WarningAlertListTableDO::getAddress, reqVO.getAddress())
                .eqIfPresent(WarningAlertListTableDO::getEventDescription, reqVO.getEventDescription())
                .eqIfPresent(WarningAlertListTableDO::getScenePhotos, reqVO.getScenePhotos())
                .eqIfPresent(WarningAlertListTableDO::getUploaderName, reqVO.getUploaderName())
                .eqIfPresent(WarningAlertListTableDO::getUploaderPhone, reqVO.getUploaderPhone())
                .likeIfPresent(WarningAlertListTableDO::getTitle, reqVO.getTitle());
    }

    List<WarningAlertListTableStatisticsRespVO> selectWarningLevelStatistics();

    List<WarningAlertListTableStatisticsRespVO> selectWarningStatusStatistics();

    default List<WarningAlertListTableDO> selectListByAlertCode(String alertCode) {
        return selectList(new LambdaQueryWrapperX<WarningAlertListTableDO>()
                .eq(WarningAlertListTableDO::getAlertCode, alertCode));
    }

    /**
     * 根据告警编号查询预警告警记录
     */
    default WarningAlertListTableDO selectByAlertCode(String alertCode) {
        return selectOne(new LambdaQueryWrapperX<WarningAlertListTableDO>()
                .eq(WarningAlertListTableDO::getAlertCode, alertCode));
    }

    /**
     * 批量插入预警告警记录
     */
    int insertBatch(List<WarningAlertListTableDO> list);

    /**
     * 批量更新预警告警记录
     */
    int updateBatch(List<WarningAlertListTableDO> list);

    /**
     * 获取责任人告警统计
     */
    List<ResponsiblePersonStatisticsRespVO> selectResponsiblePersonStatistics();

    /**
     * 获取责任人预警等级统计
     */
    List<ResponsiblePersonLevelStatisticsRespVO> selectResponsiblePersonLevelStatistics(String responsiblePerson,
                                                                                        LocalDateTime startTime,
                                                                                        LocalDateTime endTime,
                                                                                        String warningStatus);
}