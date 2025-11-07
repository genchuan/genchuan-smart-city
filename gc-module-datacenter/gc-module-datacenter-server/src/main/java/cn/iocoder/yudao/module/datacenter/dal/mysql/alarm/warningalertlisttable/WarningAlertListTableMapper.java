package cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.warningalertlisttable;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.SortingField;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
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
            return selectPage(reqVO, null, wrapper); // 使用完整的wrapper
        }
        else if ("triggertime".equals(reqVO.getOrderByColumn())) {
            wrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), WarningAlertListTableDO::getTriggerTime);
            return selectPage(reqVO, null, wrapper); // 使用完整的wrapper
        }
        else if ("requiredcompletetime".equals(reqVO.getOrderByColumn())) {
            wrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), WarningAlertListTableDO::getRequiredCompleteTime);
            return selectPage(reqVO, null, wrapper); // 使用完整的wrapper
        }

        // 第三步：处理普通排序
        SortingField sortingField = new SortingField();
        sortingField.setField(reqVO.getOrderByColumn());
        sortingField.setOrder(reqVO.getIsAsc());
        List<SortingField> sortingFields = new ArrayList<>();
        sortingFields.add(sortingField);
        return selectPage(reqVO, sortingFields, wrapper); // 使用完整的wrapper
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
                .eqIfPresent(WarningAlertListTableDO::getWarningTypeId,reqVO.getWarningTypeId());
    }
//    default PageResult<WarningAlertListTableDO> selectPage(WarningAlertListTablePageReqVO reqVO) {
//
//        /* ---------- 其它条件照旧 ---------- */
//        LambdaQueryWrapperX<WarningAlertListTableDO> wrapper =
//                new LambdaQueryWrapperX<WarningAlertListTableDO>()
//                        .eqIfPresent(WarningAlertListTableDO::getAlertCode, reqVO.getAlertCode())
//                        // … 其余条件
//                        .eqIfPresent(WarningAlertListTableDO::getDeviceId, reqVO.getDeviceId());
//
//        /* ---------- 预警等级自定义排序 ---------- */
//        if ("warningLevel".equals(reqVO.getOrderByColumn())) {
//            String caseSql = "CASE warning_level " +
//                    "WHEN 'emergency' THEN 4 " +
//                    "WHEN 'important' THEN 3 " +
//                    "WHEN 'general'   THEN 1 " +
////                    "WHEN 'secondary' THEN 4 " +
//                    "ELSE 99 END " + reqVO.getIsAsc();   // asc / desc
//            // 原样拼到 SQL 最后
//            wrapper.last("ORDER BY " + caseSql);
//            return selectPage(reqVO, null, wrapper);
//        }
//        else if ("triggertime".equals(reqVO.getOrderByColumn())) {
//            // 直接按时间字段排序，不需要CASE转换
//            wrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), WarningAlertListTableDO::getTriggerTime);
//            return selectPage(reqVO, null, wrapper);
//        }
//        else if ("requiredcompletetime".equals(reqVO.getOrderByColumn())) {
//            // 直接按时间字段排序，不需要CASE转换
//            wrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), WarningAlertListTableDO::getRequiredCompleteTime);
//            return selectPage(reqVO, null, wrapper);
//        }
//
//        SortingField sortingField = new SortingField();
//        sortingField.setField(reqVO.getOrderByColumn());
//        sortingField.setOrder(reqVO.getIsAsc());
//        List<SortingField> sortingFields = new ArrayList<>();
//        sortingFields.add(sortingField);
//        return selectPage(reqVO, sortingFields,new LambdaQueryWrapperX<WarningAlertListTableDO>()
//                .eqIfPresent(WarningAlertListTableDO::getAlertCode, reqVO.getAlertCode())
//                .eqIfPresent(WarningAlertListTableDO::getRelatedObjectType, reqVO.getRelatedObjectType())
//                .eqIfPresent(WarningAlertListTableDO::getRelatedObjectId, reqVO.getRelatedObjectId())
//                .likeIfPresent(WarningAlertListTableDO::getRelatedObjectName, reqVO.getRelatedObjectName())
//                .eqIfPresent(WarningAlertListTableDO::getWarningField, reqVO.getWarningField())
//                .eqIfPresent(WarningAlertListTableDO::getWarningType, reqVO.getWarningType())
//                .eqIfPresent(WarningAlertListTableDO::getWarningLevel, reqVO.getWarningLevel())
//                .eqIfPresent(WarningAlertListTableDO::getWarningStatus, reqVO.getWarningStatus())
//                .eqIfPresent(WarningAlertListTableDO::getTriggerReason, reqVO.getTriggerReason())
//                .eqIfPresent(WarningAlertListTableDO::getRelatedEventCode, reqVO.getRelatedEventCode())
//                .eqIfPresent(WarningAlertListTableDO::getDispatchDepartment, reqVO.getDispatchDepartment())
//                .eqIfPresent(WarningAlertListTableDO::getResponsiblePerson, reqVO.getResponsiblePerson())
//                .eqIfPresent(WarningAlertListTableDO::getResponsiblePersonPhone, reqVO.getResponsiblePersonPhone())
//                .betweenIfPresent(WarningAlertListTableDO::getTriggerTime, reqVO.getTriggerTime())
//                .betweenIfPresent(WarningAlertListTableDO::getRequiredCompleteTime, reqVO.getRequiredCompleteTime())
//                .eqIfPresent(WarningAlertListTableDO::getDisposalProgressDesc, reqVO.getDisposalProgressDesc())
//                .eqIfPresent(WarningAlertListTableDO::getDisposalAttachmentPath, reqVO.getDisposalAttachmentPath())
//                .eqIfPresent(WarningAlertListTableDO::getReviewOpinion, reqVO.getReviewOpinion())
//                .eqIfPresent(WarningAlertListTableDO::getReviewer, reqVO.getReviewer())
//                .betweenIfPresent(WarningAlertListTableDO::getReviewTime, reqVO.getReviewTime())
//                .eqIfPresent(WarningAlertListTableDO::getExtendCategory1, reqVO.getExtendCategory1())
//                .eqIfPresent(WarningAlertListTableDO::getExtendCategory2, reqVO.getExtendCategory2())
//                .eqIfPresent(WarningAlertListTableDO::getExtendCategory3, reqVO.getExtendCategory3())
//                .betweenIfPresent(WarningAlertListTableDO::getCreateTime, reqVO.getCreateTime())
//                .eqIfPresent(WarningAlertListTableDO::getWarningStatus, reqVO.getWarningStatus())
//                .eqIfPresent(WarningAlertListTableDO::getDeviceId, reqVO.getDeviceId()));
//    }


    List<WarningAlertListTableStatisticsRespVO> selectWarningLevelStatistics();

    List<WarningAlertListTableStatisticsRespVO> selectWarningStatusStatistics();

    default List<WarningAlertListTableDO> selectListByAlertCode(String alertCode) {
        return selectList(new LambdaQueryWrapperX<WarningAlertListTableDO>()
                .eq(WarningAlertListTableDO::getAlertCode, alertCode));
    }


}