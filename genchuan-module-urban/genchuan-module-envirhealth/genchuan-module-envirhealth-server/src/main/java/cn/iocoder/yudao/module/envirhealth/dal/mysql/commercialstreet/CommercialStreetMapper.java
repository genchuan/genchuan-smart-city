package cn.iocoder.yudao.module.envirhealth.dal.mysql.commercialstreet;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.commercialstreet.vo.CommercialStreetPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.commercialstreet.CommercialStreetDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.commercialstreet.CommercialStreetDetailDO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 商业街 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CommercialStreetMapper extends BaseMapperX<CommercialStreetDO> {

    default PageResult<CommercialStreetDO> selectPage(CommercialStreetPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CommercialStreetDO>()
                .eqIfPresent(CommercialStreetDO::getStreetId, reqVO.getStreetId())
                .likeIfPresent(CommercialStreetDO::getName, reqVO.getName())
                .eqIfPresent(CommercialStreetDO::getAddress, reqVO.getAddress())
                .eqIfPresent(CommercialStreetDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(CommercialStreetDO::getCleaningFrequency, reqVO.getCleaningFrequency())
                .eqIfPresent(CommercialStreetDO::getTransferInterval, reqVO.getTransferInterval())
                .eqIfPresent(CommercialStreetDO::getManagerId, reqVO.getManagerId())
                .eqIfPresent(CommercialStreetDO::getOperationStatusId, reqVO.getOperationStatusId())
                .eqIfPresent(CommercialStreetDO::getCleaningCoverage, reqVO.getCleaningCoverage())
                .eqIfPresent(CommercialStreetDO::getFacilityRate, reqVO.getFacilityRate())
                .eqIfPresent(CommercialStreetDO::getDisposalDuration, reqVO.getDisposalDuration())
                .eqIfPresent(CommercialStreetDO::getCollectionCompleteRate, reqVO.getCollectionCompleteRate())
                .eqIfPresent(CommercialStreetDO::getPatrolInterval, reqVO.getPatrolInterval())
                .betweenIfPresent(CommercialStreetDO::getCleaningTime, reqVO.getCleaningTime())
                .eqIfPresent(CommercialStreetDO::getCleanerIds, reqVO.getCleanerIds())
                .eqIfPresent(CommercialStreetDO::getResponsibilityArea, reqVO.getResponsibilityArea())
                .eqIfPresent(CommercialStreetDO::getCollectionPoints, reqVO.getCollectionPoints())
                .eqIfPresent(CommercialStreetDO::getAbnormalCount, reqVO.getAbnormalCount())
                .eqIfPresent(CommercialStreetDO::getFacilityIds, reqVO.getFacilityIds())
                .eqIfPresent(CommercialStreetDO::getFacilityLocation, reqVO.getFacilityLocation())
                .eqIfPresent(CommercialStreetDO::getDamageDesc, reqVO.getDamageDesc())
                .eqIfPresent(CommercialStreetDO::getReportBy, reqVO.getReportBy())
                .betweenIfPresent(CommercialStreetDO::getReportTime, reqVO.getReportTime())
                .eqIfPresent(CommercialStreetDO::getProblemPhotoUrl, reqVO.getProblemPhotoUrl())
                .eqIfPresent(CommercialStreetDO::getHandleBy, reqVO.getHandleBy())
                .betweenIfPresent(CommercialStreetDO::getDispatchTime, reqVO.getDispatchTime())
                .eqIfPresent(CommercialStreetDO::getMaintainStatusId, reqVO.getMaintainStatusId())
                .betweenIfPresent(CommercialStreetDO::getExpectedCompleteTime, reqVO.getExpectedCompleteTime())
                .eqIfPresent(CommercialStreetDO::getProblemTypeId, reqVO.getProblemTypeId())
                .eqIfPresent(CommercialStreetDO::getProblemLocation, reqVO.getProblemLocation())
                .eqIfPresent(CommercialStreetDO::getProblemDesc, reqVO.getProblemDesc())
                .eqIfPresent(CommercialStreetDO::getHandleStatusId, reqVO.getHandleStatusId())
                .eqIfPresent(CommercialStreetDO::getHandleResult, reqVO.getHandleResult())
                .betweenIfPresent(CommercialStreetDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CommercialStreetDO::getId));
    }

    /**
     * 查询全局最大序号（用于street_id）
     */
    @Select("SELECT IFNULL(MAX(SUBSTRING_INDEX(street_id, '-', -1)), 0) FROM commercial_street")
    Integer selectMaxSeq();

    List<CommercialStreetDetailDO> selectDetailPage(@Param("reqVO") CommercialStreetPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") CommercialStreetPageReqVO pageReqVO);

    // ========== 卡片数据查询 ==========
    /**
     * 查询总商业街数
     */
    @Select("SELECT COUNT(id) FROM commercial_street WHERE deleted = 0")
    Long selectTotalStreetCount();

    /**
     * 查询保洁覆盖达标数（保洁覆盖率≥达标阈值的商业街数）
     */
    @Select("SELECT COUNT(id) FROM commercial_street WHERE deleted = 0 AND cleaning_coverage >= 90")
    Long selectCleaningCoverageMetCount();

    /**
     * 查询设施完好数（设施完好率≥达标阈值的商业街数）
     */
    @Select("SELECT COUNT(id) FROM commercial_street WHERE deleted = 0 AND facility_rate >= 90")
    Long selectFacilityIntactCount();

    /**
     * 查询收运完成数（收运完成率≥达标阈值的商业街数）
     */
    @Select("SELECT COUNT(id) FROM commercial_street WHERE deleted = 0 AND collection_complete_rate >= 90")
    Long selectCollectionCompletedCount();

    // ========== 圆环图数据查询 ==========
    /**
     * 查询商业街区域分布占比
     */
    @Select("""
            SELECT sa.area_name AS name, COUNT(cs.id) AS value 
            FROM commercial_street cs
            LEFT JOIN sys_area sa ON cs.area_code = sa.area_code
            WHERE cs.deleted = 0
            GROUP BY sa.id, sa.area_name
            ORDER BY value DESC
        """)
    List<PieItemVO> selectAreaDistributionPie();

    /**
     * 查询商业街运营状态占比
     */
    @Select("""
            SELECT sos.name AS name, COUNT(cs.id) AS value 
            FROM commercial_street cs
            LEFT JOIN sys_operation_status sos ON cs.operation_status_id = sos.sys_operation_status_id
            WHERE cs.deleted = 0
            GROUP BY sos.sys_operation_status_id, sos.name
            ORDER BY value DESC
        """)
    List<PieItemVO> selectOperationStatusDistributionPie();

    // ========== 柱状图数据查询 ==========
    /**
     * 查询不同商业街问题处置时长对比
     */
    @Select("SELECT name AS name, AVG(disposal_duration) AS value FROM commercial_street WHERE deleted = 0 GROUP BY name")
    List<BarItemVO> selectProblemDisposalDurationByStreetBar();
}