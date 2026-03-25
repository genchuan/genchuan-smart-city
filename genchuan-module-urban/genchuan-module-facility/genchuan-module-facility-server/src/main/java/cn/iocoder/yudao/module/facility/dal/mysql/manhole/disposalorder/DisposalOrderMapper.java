package cn.iocoder.yudao.module.facility.dal.mysql.manhole.disposalorder;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.disposalorder.DisposalOrderDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.disposalorder.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 处置工单 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface DisposalOrderMapper extends BaseMapperX<DisposalOrderDO> {

    default PageResult<DisposalOrderDO> selectPage(DisposalOrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DisposalOrderDO>()
                .eqIfPresent(DisposalOrderDO::getWarnId, reqVO.getWarnId())
                .eqIfPresent(DisposalOrderDO::getCoverId, reqVO.getCoverId())
                .eqIfPresent(DisposalOrderDO::getAbnormalType, reqVO.getAbnormalType())
                .eqIfPresent(DisposalOrderDO::getRiskLevelId, reqVO.getRiskLevelId())
                .eqIfPresent(DisposalOrderDO::getAssignStaffId, reqVO.getAssignStaffId())
                .betweenIfPresent(DisposalOrderDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(DisposalOrderDO::getDealLimit, reqVO.getDealLimit())
                .eqIfPresent(DisposalOrderDO::getProcessStatus, reqVO.getProcessStatus())
                .betweenIfPresent(DisposalOrderDO::getCompleteTime, reqVO.getCompleteTime())
                .eqIfPresent(DisposalOrderDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DisposalOrderDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(DisposalOrderDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(DisposalOrderDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(DisposalOrderDO::getId));
    }

    /**
     * 查询窨井盖的故障记录列表（从 disposal_order 表，一对多关系）
     */
    @Select("SELECT d.id, d.warn_id, d.cover_id, d.abnormal_type, d.risk_level_id, " +
            "d.assign_staff_id, d.create_time, d.deal_limit, d.process_status, d.complete_time, " +
            "COALESCE(s.nickname, '未分配人员') as assignStaffName, " +  // 替换IFNULL为COALESCE，补充默认值
            "COALESCE(r.level_name, '未配置风险等级') as riskLevelName " +
            "FROM munifacility.disposal_order d " +  // 补充模式前缀
            "LEFT JOIN munifacility.sys_user s ON d.assign_staff_id = s.id AND s.deleted = B'0' " +  // 修正bit类型判断
            "LEFT JOIN munifacility.sys_risk_level r ON d.risk_level_id = r.id " +  // 补充模式前缀
            "WHERE d.cover_id = #{coverId} AND d.deleted = B'0' " +  // 修正bit类型判断
            "ORDER BY d.create_time DESC")
    List<DisposalOrderDO> selectFaultRecordsByCoverId(@Param("coverId") Long coverId);

}