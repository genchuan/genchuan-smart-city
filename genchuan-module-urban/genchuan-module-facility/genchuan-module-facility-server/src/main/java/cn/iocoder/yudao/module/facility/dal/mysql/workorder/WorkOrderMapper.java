package cn.iocoder.yudao.module.facility.dal.mysql.workorder;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.facility.controller.admin.workorder.vo.WorkOrderPageReqVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.workorder.WorkOrderDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工单 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface WorkOrderMapper extends BaseMapperX<WorkOrderDO> {

    default PageResult<WorkOrderDO> selectPage(WorkOrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WorkOrderDO>()
                .eqIfPresent(WorkOrderDO::getFacilityType, reqVO.getFacilityType())
                .eqIfPresent(WorkOrderDO::getOrderNo, reqVO.getOrderNo())
                .eqIfPresent(WorkOrderDO::getWarnId, reqVO.getWarnId())
                .eqIfPresent(WorkOrderDO::getWarnNo, reqVO.getWarnNo())
                .eqIfPresent(WorkOrderDO::getFacilityId, reqVO.getFacilityId())
                .likeIfPresent(WorkOrderDO::getFacilityName, reqVO.getFacilityName())
                .eqIfPresent(WorkOrderDO::getAssignStaffId, reqVO.getAssignStaffId())
                .likeIfPresent(WorkOrderDO::getAssignStaffName, reqVO.getAssignStaffName())
                .eqIfPresent(WorkOrderDO::getOrderType, reqVO.getOrderType())
                .eqIfPresent(WorkOrderDO::getBizType, reqVO.getBizType())
                .eqIfPresent(WorkOrderDO::getDealLimit, reqVO.getDealLimit())
                .betweenIfPresent(WorkOrderDO::getArriveTime, reqVO.getArriveTime())
                .betweenIfPresent(WorkOrderDO::getRemindTime, reqVO.getRemindTime())
                .betweenIfPresent(WorkOrderDO::getCompleteTime, reqVO.getCompleteTime())
                .likeIfPresent(WorkOrderDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(WorkOrderDO::getAreaFullCode, reqVO.getAreaFullCode())
                .eqIfPresent(WorkOrderDO::getPriorityLevel, reqVO.getPriorityLevel())
                .eqIfPresent(WorkOrderDO::getRiskLevel, reqVO.getRiskLevel())
                .eqIfPresent(WorkOrderDO::getProcessStatus, reqVO.getProcessStatus())
                .eqIfPresent(WorkOrderDO::getProcessDesc, reqVO.getProcessDesc())
                .eqIfPresent(WorkOrderDO::getDealContent, reqVO.getDealContent())
                .eqIfPresent(WorkOrderDO::getSuperviseOpinion, reqVO.getSuperviseOpinion())
                .eqIfPresent(WorkOrderDO::getSiteDataUrlListStr, reqVO.getSiteDataUrlListStr())
                .eqIfPresent(WorkOrderDO::getFileDesc, reqVO.getFileDesc())
                .eqIfPresent(WorkOrderDO::getAfterIndexValue,reqVO.getAfterIndexValue())
                .betweenIfPresent(WorkOrderDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(WorkOrderDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(WorkOrderDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(WorkOrderDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(WorkOrderDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(WorkOrderDO::getId));
    }

    WorkOrderDO selectByOrderNo(String orderNo);


    int countByOrderNo(String orderNo);
}
