package cn.iocoder.yudao.module.envirhealth.dal.mysql.park;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.park.vo.ParkPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.park.ParkDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.park.ParkDetailDO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 公园 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ParkMapper extends BaseMapperX<ParkDO> {

    default PageResult<ParkDO> selectPage(ParkPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkDO>()
                .eqIfPresent(ParkDO::getParkId, reqVO.getParkId())
                .likeIfPresent(ParkDO::getName, reqVO.getName())
                .eqIfPresent(ParkDO::getAddress, reqVO.getAddress())
                .eqIfPresent(ParkDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(ParkDO::getCleaningFrequency, reqVO.getCleaningFrequency())
                .eqIfPresent(ParkDO::getGreenMaintenanceCycle, reqVO.getGreenMaintenanceCycle())
                .eqIfPresent(ParkDO::getManagerId, reqVO.getManagerId())
                .eqIfPresent(ParkDO::getOperationStatusId, reqVO.getOperationStatusId())
                .eqIfPresent(ParkDO::getCleaningRate, reqVO.getCleaningRate())
                .eqIfPresent(ParkDO::getGreenSurvivalRate, reqVO.getGreenSurvivalRate())
                .eqIfPresent(ParkDO::getFacilityRate, reqVO.getFacilityRate())
                .eqIfPresent(ParkDO::getEnvironmentRate, reqVO.getEnvironmentRate())
                .eqIfPresent(ParkDO::getWasteTransferCompleteRate, reqVO.getWasteTransferCompleteRate())
                .eqIfPresent(ParkDO::getCleaningArea, reqVO.getCleaningArea())
                .eqIfPresent(ParkDO::getCleaningStandard, reqVO.getCleaningStandard())
                .eqIfPresent(ParkDO::getStaffIds, reqVO.getStaffIds())
                .eqIfPresent(ParkDO::getGreenTypeIds, reqVO.getGreenTypeIds())
                .eqIfPresent(ParkDO::getGreenArea, reqVO.getGreenArea())
                .eqIfPresent(ParkDO::getGreenMaintenanceContent, reqVO.getGreenMaintenanceContent())
                .eqIfPresent(ParkDO::getGreenStaffIds, reqVO.getGreenStaffIds())
                .eqIfPresent(ParkDO::getWasteCollectionPoints, reqVO.getWasteCollectionPoints())
                .eqIfPresent(ParkDO::getWasteTransferFrequency, reqVO.getWasteTransferFrequency())
                .betweenIfPresent(ParkDO::getWasteTransferTime, reqVO.getWasteTransferTime())
                .eqIfPresent(ParkDO::getVehicleId, reqVO.getVehicleId())
                .eqIfPresent(ParkDO::getWasteVolume, reqVO.getWasteVolume())
                .eqIfPresent(ParkDO::getFacilityIds, reqVO.getFacilityIds())
                .eqIfPresent(ParkDO::getFacilityLocation, reqVO.getFacilityLocation())
                .eqIfPresent(ParkDO::getFacilityDamageDesc, reqVO.getFacilityDamageDesc())
                .eqIfPresent(ParkDO::getReportBy, reqVO.getReportBy())
                .betweenIfPresent(ParkDO::getReportTime, reqVO.getReportTime())
                .eqIfPresent(ParkDO::getFacilityPhotoUrl, reqVO.getFacilityPhotoUrl())
                .betweenIfPresent(ParkDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ParkDO::getId));
    }

    /**
     * 查询全局最大序号（用于park_id）
     */
    @Select("SELECT IFNULL(MAX(SUBSTRING_INDEX(park_id, '-', -1)), 0) FROM park")
    Integer selectMaxSeq();

    List<ParkDetailDO> selectDetailPage(@Param("reqVO") ParkPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") ParkPageReqVO pageReqVO);

    // ========== 卡片数据 ==========
    /**
     * 查询总公园数
     */
    @Select("SELECT COUNT(id) FROM park WHERE deleted = 0")
    Long selectTotalParkCount();

    /**
     * 查询正常运营数
     */
    @Select("SELECT COUNT(id) FROM park WHERE deleted= 0 AND operation_status_id = 'uuid-op-status-001'")
    Long selectNormalOperationCount();

    /**
     * 查询保洁达标数
     */
    @Select("SELECT COUNT(id) FROM park WHERE deleted = 0 AND cleaning_rate >= 95")
    Long selectCleaningStandardMetCount();

    /**
     * 查询绿化存活达标数
     */
    @Select("SELECT COUNT(id) FROM park WHERE deleted = 0 AND green_survival_rate >= 95")
    Long selectGreeningSurvivalStandardMetCount();

    /**
     * 查询设施完好数
     */
    @Select("SELECT COUNT(id) FROM park WHERE deleted = 0 AND facility_rate >= 95")
    Long selectFacilityIntactCount();

// ========== 圆环图数据 ==========
    /**
     * 查询运营状态分布占比
     */
    @Select("""
        SELECT 
            IFNULL(sos.name, '未知') AS name,  -- 处理运营状态NULL值
            COUNT(p.id) AS value
        FROM park p
        LEFT JOIN sys_operation_status sos 
            ON p.operation_status_id = sos.sys_operation_status_id
        WHERE p.deleted = 0 
        GROUP BY IFNULL(sos.name, '未知'), sos.sys_operation_status_id, sos.id
        ORDER BY sos.id desc 
        """)
    List<PieItemVO> selectOperationStatusDistribution();

    /**
     * 查询所属区域分布占比
     * 说明：name 为区域名称，value 为对应公园数量
     */
    @Select("""
        SELECT
            IFNULL(sa.area_name, '未知') AS name,  -- 处理区域名称NULL值
            COUNT(p.id) AS value
        FROM park p
        LEFT JOIN sys_area sa
            ON p.area_code = sa.area_code
        WHERE p.deleted = 0
        GROUP BY IFNULL(sa.area_name, '未知'), sa.area_code
        """)
    List<PieItemVO> selectAreaDistribution();

// ========== 柱状图数据 ==========
    /**
     * 查询不同公园环境达标率对比
     */
    @Select("""
        SELECT
            p.name AS name,
            ROUND(IFNULL(p.environment_rate, 0.0), 1) AS value
        FROM park p
        WHERE p.deleted = 0
        AND p.name IS NOT NULL
        ORDER BY p.id DESC
        """)
    List<BarItemVO> selectEnvironmentComplianceRateByPark();
}