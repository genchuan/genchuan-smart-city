package cn.iocoder.yudao.module.envirhealth.dal.mysql.river;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.river.RiverPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.river.RiverDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.river.RiverDetailDO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 河道 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface RiverMapper extends BaseMapperX<RiverDO> {

    default PageResult<RiverDO> selectPage(RiverPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RiverDO>()
                .eqIfPresent(RiverDO::getRiverId, reqVO.getRiverId())
                .likeIfPresent(RiverDO::getName, reqVO.getName())
                .eqIfPresent(RiverDO::getResponsibilitySection, reqVO.getResponsibilitySection())
                .eqIfPresent(RiverDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(RiverDO::getLength, reqVO.getLength())
                .eqIfPresent(RiverDO::getManagerId, reqVO.getManagerId())
                .eqIfPresent(RiverDO::getOperationStatusId, reqVO.getOperationStatusId())
                .eqIfPresent(RiverDO::getCleaningCoverage, reqVO.getCleaningCoverage())
                .eqIfPresent(RiverDO::getWaterQualityRate, reqVO.getWaterQualityRate())
                .eqIfPresent(RiverDO::getWasteFishingVolume, reqVO.getWasteFishingVolume())
                .eqIfPresent(RiverDO::getProblemCompleteRate, reqVO.getProblemCompleteRate())
                .eqIfPresent(RiverDO::getCleaningTypeId, reqVO.getCleaningTypeId())
                .eqIfPresent(RiverDO::getWaterCleaningFrequency, reqVO.getWaterCleaningFrequency())
                .betweenIfPresent(RiverDO::getCleaningTime, reqVO.getCleaningTime())
                .eqIfPresent(RiverDO::getStaffIds, reqVO.getStaffIds())
                .eqIfPresent(RiverDO::getToolIds, reqVO.getToolIds())
                .eqIfPresent(RiverDO::getWasteFishingEstimate, reqVO.getWasteFishingEstimate())
                .eqIfPresent(RiverDO::getMonitorTypeId, reqVO.getMonitorTypeId())
                .eqIfPresent(RiverDO::getWaterQualityCycle, reqVO.getWaterQualityCycle())
                .eqIfPresent(RiverDO::getMonitorIndicators, reqVO.getMonitorIndicators())
                .eqIfPresent(RiverDO::getMonitorBy, reqVO.getMonitorBy())
                .betweenIfPresent(RiverDO::getPlanMonitorTime, reqVO.getPlanMonitorTime())
                .eqIfPresent(RiverDO::getMonitorStatusId, reqVO.getMonitorStatusId())
                .betweenIfPresent(RiverDO::getLastMonitorTime, reqVO.getLastMonitorTime())
                .betweenIfPresent(RiverDO::getNextMonitorRemindTime, reqVO.getNextMonitorRemindTime())
                .eqIfPresent(RiverDO::getMonitorDataQualifiedRate, reqVO.getMonitorDataQualifiedRate())
                .eqIfPresent(RiverDO::getWarningCount, reqVO.getWarningCount())
                .eqIfPresent(RiverDO::getProblemTypeId, reqVO.getProblemTypeId())
                .eqIfPresent(RiverDO::getProblemLocation, reqVO.getProblemLocation())
                .eqIfPresent(RiverDO::getProblemDesc, reqVO.getProblemDesc())
                .eqIfPresent(RiverDO::getReportBy, reqVO.getReportBy())
                .betweenIfPresent(RiverDO::getReportTime, reqVO.getReportTime())
                .eqIfPresent(RiverDO::getProblemMediaUrl, reqVO.getProblemMediaUrl())
                .eqIfPresent(RiverDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(RiverDO::getHandleBy, reqVO.getHandleBy())
                .betweenIfPresent(RiverDO::getDispatchTime, reqVO.getDispatchTime())
                .eqIfPresent(RiverDO::getHandleStatusId, reqVO.getHandleStatusId())
                .eqIfPresent(RiverDO::getIsTimeout, reqVO.getIsTimeout())
                .betweenIfPresent(RiverDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RiverDO::getId));
    }

    /**
     * 查询全局最大序号（用于river_id）
     */
    @Select("SELECT IFNULL(MAX(SUBSTRING_INDEX(river_id, '-', -1)), 0) FROM river")
    Integer selectMaxSeq();

    List<RiverDetailDO> selectDetailPage(@Param("reqVO") RiverPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") RiverPageReqVO pageReqVO);

    /**
     * 查询总河道数
     */
    @Select("SELECT COUNT(*) FROM river WHERE deleted = 0")
    Long selectTotalRiverCount();

    /**
     * 查询保洁覆盖达标数
     */
    @Select("SELECT COUNT(*) FROM river WHERE deleted = 0 AND cleaning_coverage >= 90")
    Long selectCleaningCoverageMetCount();

    /**
     * 查询水质达标数
     */
    @Select("SELECT COUNT(*) FROM river WHERE deleted = 0 AND water_quality_rate >= 85")
    Long selectWaterQualityMetCount();

    /**
     * 查询问题办结数
     */
    @Select("SELECT COUNT(*) FROM river WHERE deleted = 0 AND problem_complete_rate >= 95")
    Long selectProblemCompletedCount();

    /**
     * 查询所属区域分布
     */
    @Select("SELECT COALESCE(a.area_name, '未知') as name, COUNT(*) as value " +
            "FROM river r " +
            "   LEFT JOIN sys_area a ON a.area_code = r.area_code " +
            "WHERE r.deleted = 0 " +
            "GROUP BY COALESCE(a.area_code, 'unknown'), COALESCE(a.area_name, '未知')")
    List<PieItemVO> selectAreaDistribution();

    /**
     * 查询运营状态分布
     */
    @Select("SELECT COALESCE(os.name, '未知') as name, COUNT(*) as value " +
            "FROM river r " +
            "   LEFT JOIN sys_operation_status os ON os.sys_operation_status_id = r.operation_status_id " +
            "WHERE r.deleted = 0 " +
            "GROUP BY COALESCE(r.operation_status_id, 'unknown'), COALESCE(os.name, '未知')")
    List<PieItemVO> selectOperationStatusDistribution();

    /**
     * 查询不同河道水质达标率
     */
    @Select("SELECT COALESCE(name, '未知') as name, " +
            "       COALESCE(water_quality_rate, 0) as value " +
            "FROM river " +
            "WHERE deleted = 0 " +
            "ORDER BY water_quality_rate DESC NULLS LAST")
    List<BarItemVO> selectWaterQualityRateByRiver();
}