package cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagecollection;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.GarbageAbnormalPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.card.review.GarbageAbnormalCardReviewRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.circle.abnormal.GarbageAbnormalCircleAbnormalVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.circle.review.GarbageAbnormalCircleReviewVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.column.abnormal.GarbageAbnormalColumnAbnormalVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.column.review.GarbageAbnormalColumnHandleTimeVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.GarbageAbnormalDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.detail.GarbageAbnormalDetailDO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 垃圾异常记录 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface GarbageAbnormalMapper extends BaseMapperX<GarbageAbnormalDO> {

    default PageResult<GarbageAbnormalDO> selectPage(GarbageAbnormalPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GarbageAbnormalDO>()
                .eqIfPresent(GarbageAbnormalDO::getAbnormalId, reqVO.getAbnormalId())
                .eqIfPresent(GarbageAbnormalDO::getPlanId, reqVO.getPlanId())
                .eqIfPresent(GarbageAbnormalDO::getAbnormalTypeId, reqVO.getAbnormalTypeId())
                .eqIfPresent(GarbageAbnormalDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(GarbageAbnormalDO::getReportBy, reqVO.getReportBy())
                .betweenIfPresent(GarbageAbnormalDO::getReportTime, reqVO.getReportTime())
                .eqIfPresent(GarbageAbnormalDO::getPriority, reqVO.getPriority())
                .eqIfPresent(GarbageAbnormalDO::getHandlerId, reqVO.getHandlerId())
                .eqIfPresent(GarbageAbnormalDO::getHandleStatus, reqVO.getHandleStatus())
                .eqIfPresent(GarbageAbnormalDO::getIsTimeout, reqVO.getIsTimeout())
                .eqIfPresent(GarbageAbnormalDO::getHandleDesc, reqVO.getHandleDesc())
                .eqIfPresent(GarbageAbnormalDO::getHandlePhotoUrl, reqVO.getHandlePhotoUrl())
                .eqIfPresent(GarbageAbnormalDO::getReviewStatus, reqVO.getReviewStatus())
                .eqIfPresent(GarbageAbnormalDO::getReviewBy, reqVO.getReviewBy())
                .betweenIfPresent(GarbageAbnormalDO::getReviewTime, reqVO.getReviewTime())
                .betweenIfPresent(GarbageAbnormalDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GarbageAbnormalDO::getId));
    }

    /**
     * 查询全局最大序号（用于abnormal_id）
     */
    @Select("SELECT IFNULL(MAX(SUBSTRING_INDEX(abnormal_id, '-', -1)), 0) FROM garbage_abnormal")
    Integer selectMaxSeq();

    List<GarbageAbnormalDetailDO> selectDetailPage(@Param("reqVO") GarbageAbnormalPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") GarbageAbnormalPageReqVO pageReqVO);

    /**
     * 获取异常类型占比统计（只统计待处置的异常）
     * @return 异常类型占比列表
     */
    @Select("SELECT " +
            "sat.abnormal_name as name, " +
            "COUNT(ga.id) as value, " +
            "ROUND(COUNT(ga.id) * 100.0 / NULLIF((SELECT COUNT(*) FROM garbage_abnormal WHERE deleted = 0 AND handle_status = '待处置'), 0), 2) as proportion " +
            "FROM garbage_abnormal ga " +
            "LEFT JOIN sys_abnormal_type sat ON ga.abnormal_type_id = sat.abnormal_type_id " +
            "WHERE ga.deleted = 0 AND ga.handle_status = '待处置' " +
            "GROUP BY ga.abnormal_type_id, sat.abnormal_name " +
            "ORDER BY value DESC")
    List<GarbageAbnormalCircleAbnormalVO> selectAbnormalTypeCircle();

    /**
     * 获取区域分布占比统计（只统计待处置的异常）
     * @return 区域分布占比列表
     */
    @Select("SELECT " +
            "sa.area_name as name, " +
            "COUNT(ga.id) as value, " +
            "ROUND(COUNT(ga.id) * 100.0 / NULLIF((SELECT COUNT(*) FROM garbage_abnormal WHERE deleted = 0 AND handle_status = '待处置'), 0), 2) as proportion " +
            "FROM garbage_abnormal ga " +
            "LEFT JOIN sys_area sa ON ga.area_code = sa.area_code " +
            "WHERE ga.deleted = 0 AND ga.handle_status = '待处置' " +
            "GROUP BY ga.area_code, sa.area_name " +
            "ORDER BY value DESC")
    List<GarbageAbnormalCircleAbnormalVO> selectAreaDistributionCircle();

    /**
     * 获取不同责任人的待处置异常数量对比（柱状图）
     * 返回责任人名称和对应的待处置异常数量
     */
    @Select("SELECT " +
            "su.user_name as name, " +
            "COUNT(ga.id) as value " +
            "FROM garbage_abnormal ga " +
            "LEFT JOIN sys_user su ON ga.handler_id = su.user_id " +
            "WHERE ga.deleted = 0 " +
            "AND ga.handle_status = '待处置' " +
            "AND su.user_name IS NOT NULL " +
            "GROUP BY ga.handler_id, su.user_name " +
            "ORDER BY value DESC")
    List<GarbageAbnormalColumnAbnormalVO> selectHandlerAbnormalColumn();

    /**
     * 查询待复核统计卡片数据（待复核数、已通过数、已退回数）
     */
    @Select("""
        SELECT
            SUM(CASE WHEN handle_status = '待复核' THEN 1 ELSE 0 END) AS reviewTotal,
            SUM(CASE WHEN review_status = '通过' THEN 1 ELSE 0 END) AS passedCount,
            SUM(CASE WHEN review_status = '退回' THEN 1 ELSE 0 END) AS returnCount
        FROM garbage_abnormal
        WHERE deleted = 0
    """)
    GarbageAbnormalCardReviewRespVO selectCardReview();

    /**
     * 获取复核结果占比统计（仅待复核/通过/退回）
     * @return 复核结果占比列表
     */
    @Select("SELECT " +
            "ga.review_status as name, " +
            "COUNT(ga.id) as value, " +
            "ROUND(COUNT(ga.id) * 100.0 / " +
            "  (SELECT COUNT(*) FROM garbage_abnormal WHERE deleted = 0 AND review_status IN ('待复核', '通过', '退回')), 2) as proportion " +
            "FROM garbage_abnormal ga " +
            "WHERE ga.deleted = 0 AND ga.review_status IN ('待复核', '通过', '退回') " +
            "GROUP BY ga.review_status " +
            "ORDER BY FIELD(ga.review_status, '待复核', '通过', '退回')")
    List<GarbageAbnormalCircleReviewVO> selectReviewResultCircle();

    /**
     * 获取异常类型占比统计（只统计待复核的异常）
     * @return 异常类型占比列表
     */
    @Select("SELECT " +
            "sat.abnormal_name as name, " +
            "COUNT(ga.id) as value, " +
            "ROUND(COUNT(ga.id) * 100.0 / NULLIF((SELECT COUNT(*) FROM garbage_abnormal WHERE deleted = 0 AND handle_status = '待复核'), 0), 2) as proportion " +
            "FROM garbage_abnormal ga " +
            "LEFT JOIN sys_abnormal_type sat ON ga.abnormal_type_id = sat.abnormal_type_id " +
            "WHERE ga.deleted = 0 AND ga.handle_status = '待复核' " +
            "GROUP BY ga.abnormal_type_id, sat.abnormal_name " +
            "ORDER BY value DESC")
    List<GarbageAbnormalCircleAbnormalVO> selectAbnormalTypeCircleForReview();

    /**
     * 查询各区域异常处置平均时长（柱状图）
     * 说明：handle_time为处置耗时字段
     */
    @Select("SELECT " +
            "    sa.area_name AS name, " +
            "    ROUND(AVG(TIMESTAMPDIFF(HOUR, ga.report_time, ga.review_time)), 2) AS avgHandleHours " +
            "FROM garbage_abnormal ga " +
            "LEFT JOIN sys_area sa ON ga.area_code = sa.area_code " +
            "WHERE ga.deleted = 0 " +
            "  AND ga.handle_status = '待复核' " +
            "  AND ga.review_time IS NOT NULL " +
            "GROUP BY ga.area_code, sa.area_name " +
            "ORDER BY avgHandleHours DESC")
    List<GarbageAbnormalColumnHandleTimeVO> selectAvgHandleTimeByArea();

    /**
     * 查询 handle_status = 待处置 的数量
     */
    @Select("SELECT COUNT(*) FROM garbage_abnormal WHERE deleted = 0 AND handle_status = '待处置'")
    Long countHandlePending();

    /**
     * 查询 handle_status = 待复核 的数量
     */
    @Select("SELECT COUNT(*) FROM garbage_abnormal WHERE deleted = 0 AND handle_status = '待复核'")
    Long countReviewPending();
}