package cn.iocoder.yudao.module.envirhealth.dal.mysql.urbanvillage;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage.vo.UrbanVillagePageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.urbanvillage.UrbanVillageDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.urbanvillage.UrbanVillageDetailDO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 城中村 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface UrbanVillageMapper extends BaseMapperX<UrbanVillageDO> {

    default PageResult<UrbanVillageDO> selectPage(UrbanVillagePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UrbanVillageDO>()
                .eqIfPresent(UrbanVillageDO::getVillageId, reqVO.getVillageId())
                .likeIfPresent(UrbanVillageDO::getName, reqVO.getName())
                .eqIfPresent(UrbanVillageDO::getAddress, reqVO.getAddress())
                .eqIfPresent(UrbanVillageDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(UrbanVillageDO::getResponsibilityAreas, reqVO.getResponsibilityAreas())
                .eqIfPresent(UrbanVillageDO::getRoadCleaningFrequency, reqVO.getRoadCleaningFrequency())
                .eqIfPresent(UrbanVillageDO::getManagerId, reqVO.getManagerId())
                .eqIfPresent(UrbanVillageDO::getOperationStatusId, reqVO.getOperationStatusId())
                .eqIfPresent(UrbanVillageDO::getCleaningRate, reqVO.getCleaningRate())
                .eqIfPresent(UrbanVillageDO::getProblemRate, reqVO.getProblemRate())
                .eqIfPresent(UrbanVillageDO::getReviewPassRate, reqVO.getReviewPassRate())
                .eqIfPresent(UrbanVillageDO::getAssessmentScore, reqVO.getAssessmentScore())
                .likeIfPresent(UrbanVillageDO::getResponsibilityAreaName, reqVO.getResponsibilityAreaName())
                .eqIfPresent(UrbanVillageDO::getCleaningStandard, reqVO.getCleaningStandard())
                .eqIfPresent(UrbanVillageDO::getStaffIds, reqVO.getStaffIds())
                .eqIfPresent(UrbanVillageDO::getProblemLocation, reqVO.getProblemLocation())
                .eqIfPresent(UrbanVillageDO::getProblemDesc, reqVO.getProblemDesc())
                .eqIfPresent(UrbanVillageDO::getReportBy, reqVO.getReportBy())
                .betweenIfPresent(UrbanVillageDO::getReportTime, reqVO.getReportTime())
                .eqIfPresent(UrbanVillageDO::getProblemPhotoUrl, reqVO.getProblemPhotoUrl())
                .eqIfPresent(UrbanVillageDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(UrbanVillageDO::getHandleBy, reqVO.getHandleBy())
                .betweenIfPresent(UrbanVillageDO::getDispatchTime, reqVO.getDispatchTime())
                .eqIfPresent(UrbanVillageDO::getHandleStatusId, reqVO.getHandleStatusId())
                .eqIfPresent(UrbanVillageDO::getIsTimeout, reqVO.getIsTimeout())
                .eqIfPresent(UrbanVillageDO::getHandleDesc, reqVO.getHandleDesc())
                .eqIfPresent(UrbanVillageDO::getReformPhotoUrl, reqVO.getReformPhotoUrl())
                .eqIfPresent(UrbanVillageDO::getReviewBy, reqVO.getReviewBy())
                .betweenIfPresent(UrbanVillageDO::getReviewTime, reqVO.getReviewTime())
                .eqIfPresent(UrbanVillageDO::getReviewResultId, reqVO.getReviewResultId())
                .eqIfPresent(UrbanVillageDO::getReviewOpinion, reqVO.getReviewOpinion())
                .betweenIfPresent(UrbanVillageDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(UrbanVillageDO::getId));
    }

    /**
     * 查询全局最大序号（用于village_id）
     */
    @Select("SELECT IFNULL(MAX(SUBSTRING_INDEX(village_id, '-', -1)), 0) FROM urban_village")
    Integer selectMaxSeq();

    List<UrbanVillageDetailDO> selectDetailPage(@Param("reqVO") UrbanVillagePageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") UrbanVillagePageReqVO pageReqVO);

    // ====================== 卡片数据 SQL ======================
    /**
     * 查询总城中村数
     */
    @Select("SELECT COUNT(id) FROM urban_village WHERE deleted = 0")
    Long selectTotalVillageCount();

    /**
     * 查询正常运营数
     */
    @Select("""
            SELECT COUNT(uv.id)
            FROM urban_village uv
            LEFT JOIN sys_operation_status sos ON uv.operation_status_id = sos.sys_operation_status_id
            WHERE uv.deleted = 0
            AND uv.operation_status_id = 'uuid-op-status-001'
            """)
    Long selectNormalOperationCount();

    /**
     * 查询保洁达标数（≥95视为达标）
     */
    @Select("SELECT COUNT(id) FROM urban_village WHERE deleted = 0 AND cleaning_rate >= 95")
    Long selectCleaningStandardMetCount();

    /**
     * 查询问题处置完成数
     */
    @Select("""
            SELECT COUNT(uv.id)
            FROM urban_village uv
                LEFT JOIN sys_handle_status hs ON uv.handle_status_id = hs.sys_handle_status_id
            WHERE uv.deleted = 0
            AND handle_status_id = 'uuid-handle-003'
            """)
    Long selectProblemHandledCount();

    /**
     * 查询复核通过数
     */
    @Select("""
            SELECT COUNT(uv.id)
            FROM urban_village uv
                LEFT JOIN sys_review_result r ON r.review_result_id = uv.review_result_id
            WHERE uv.deleted = 0
            AND uv.review_result_id = 'uuid-review-001'
            """)
    Long selectReviewPassedCount();

    // ====================== 圆环图数据 SQL ======================
    /**
     * 查询运营状态分布占比
     */
    @Select("""
        SELECT 
            IFNULL(sos.name, '未知') AS name,
            COUNT(uv.id) AS value
        FROM urban_village uv
        LEFT JOIN sys_operation_status sos 
            ON uv.operation_status_id = sos.sys_operation_status_id
        WHERE uv.deleted = 0 
        GROUP BY IFNULL(sos.name, '未知'), sos.sys_operation_status_id, sos.id
        ORDER BY sos.id desc
        """)
    List<PieItemVO> selectOperationStatusDistribution();

    /**
     * 查询所属区域分布占比
     */
    @Select("""
            SELECT
                IFNULL(sa.area_name, '未知') AS name,
                COUNT(uv.id) AS value
            FROM urban_village uv
            LEFT JOIN sys_area sa
                ON uv.area_code = sa.area_code
            WHERE uv.deleted = 0
            GROUP BY IFNULL(sa.area_name, '未知'), sa.area_code
            """)
    List<PieItemVO> selectAreaDistribution();

    // ====================== 柱状图数据 SQL ======================
    /**
     * 查询不同城中村考核得分对比
     */
    @Select("""
            SELECT 
                uv.name AS name,
                ROUND(IFNULL(uv.assessment_score, 0.0), 1) AS value
            FROM urban_village uv
            WHERE uv.deleted = 0
            AND uv.name IS NOT NULL
            ORDER BY uv.assessment_score DESC
            """)
    List<BarItemVO> selectAssessmentScoreByVillage();
}