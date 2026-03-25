package cn.iocoder.yudao.module.envirhealth.dal.mysql.publictoilet;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcomplaint.ToiletComplaintPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletComplaintDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletComplaintDetailDO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 公厕投诉 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ToiletComplaintMapper extends BaseMapperX<ToiletComplaintDO> {

    default PageResult<ToiletComplaintDO> selectPage(ToiletComplaintPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ToiletComplaintDO>()
                .eqIfPresent(ToiletComplaintDO::getComplaintId, reqVO.getComplaintId())
                .eqIfPresent(ToiletComplaintDO::getToiletId, reqVO.getToiletId())
                .eqIfPresent(ToiletComplaintDO::getComplaintTypeId, reqVO.getComplaintTypeId())
                .eqIfPresent(ToiletComplaintDO::getContent, reqVO.getContent())
                .likeIfPresent(ToiletComplaintDO::getComplaintName, reqVO.getComplaintName())
                .eqIfPresent(ToiletComplaintDO::getPhone, reqVO.getPhone())
                .betweenIfPresent(ToiletComplaintDO::getComplaintTime, reqVO.getComplaintTime())
                .eqIfPresent(ToiletComplaintDO::getDispatchStatus, reqVO.getDispatchStatus())
                .eqIfPresent(ToiletComplaintDO::getHandlerId, reqVO.getHandlerId())
                .eqIfPresent(ToiletComplaintDO::getIsTimeout, reqVO.getIsTimeout())
                .eqIfPresent(ToiletComplaintDO::getHandleMeasure, reqVO.getHandleMeasure())
                .eqIfPresent(ToiletComplaintDO::getHandleResult, reqVO.getHandleResult())
                .eqIfPresent(ToiletComplaintDO::getReformPhoto, reqVO.getReformPhoto())
                .eqIfPresent(ToiletComplaintDO::getFeedbackContent, reqVO.getFeedbackContent())
                .betweenIfPresent(ToiletComplaintDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ToiletComplaintDO::getId));
    }

    /**
     * 查询全局最大序号（用于complaint_id）
     */
    @Select("SELECT IFNULL(MAX(SUBSTRING_INDEX(complaint_id, '-', -1)), 0) FROM public_toilet_complaint")
    Integer selectMaxSeq();

    List<ToiletComplaintDetailDO> selectDetailPage(@Param("reqVO") ToiletComplaintPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") ToiletComplaintPageReqVO pageReqVO);

    /**
     * 统计待处置的投诉数量
     * 待处置：dispatch_status = '待派单', '已派单'
     */
    @Select("SELECT COUNT(*) FROM public_toilet_complaint " +
            "WHERE deleted = 0 " +
            "AND dispatch_status IN ('待派单', '已派单')")
    Long countPendingDisposal();

    /**
     * 统计无未办结投诉的公厕数量
     * 逻辑：总公厕数 - 有未办结投诉（待派单/已派单）的公厕数
     */
    @Select("SELECT COUNT(DISTINCT t.toilet_id) " +
            "FROM public_toilet t " +
            "LEFT JOIN public_toilet_complaint c ON t.toilet_id = c.toilet_id " +
            "AND c.dispatch_status IN ('待派单', '已派单') AND c.deleted = 0 " +
            "WHERE t.deleted = 0 AND c.toilet_id IS NULL")
    Integer countToiletWithNoPendingComplaint();

    /**
     * 投诉待处置的数量
     */
    @Select("""
            SELECT COUNT(1)
            FROM public_toilet_complaint t
            WHERE t.deleted = b'0'
              AND (t.dispatch_status IS NOT NULL AND t.dispatch_status <> '已处置')
            """)
    Long countPendingTotal();

    /**
     * 投诉待处置的数量(按投诉类型)
     */
    @Select("""
            SELECT COUNT(DISTINCT t.complaint_type_id)
            FROM public_toilet_complaint t
            WHERE t.deleted = b'0'
              AND (t.dispatch_status IS NOT NULL AND t.dispatch_status <> '已处置')
            """)
    Long countPendingTypeDistinct();

    /**
     * 投诉待处置的数量(超时)
     */
    @Select("""
            SELECT COUNT(1)
            FROM public_toilet_complaint t
            WHERE t.deleted = b'0'
              AND (t.dispatch_status IS NOT NULL AND t.dispatch_status <> '已处置')
              AND t.is_timeout = '是'
            """)
    Long countTimeoutUnHandled();

    /**
     * 投诉待处置环状图数据(投诉类型)
     */
    @Select("""
            SELECT
              COALESCE(NULLIF(TRIM(ct.complaint_name), ''), '未知') AS name,
              COUNT(1) AS value
            FROM public_toilet_complaint t
            LEFT JOIN sys_complaint_type ct ON t.complaint_type_id = ct.complaint_type_id
            WHERE t.deleted = b'0'
                AND (t.dispatch_status IS NOT NULL AND t.dispatch_status <> '已处置')
            GROUP BY COALESCE(NULLIF(TRIM(ct.complaint_name), ''), '未知')
            ORDER BY value DESC
            """)
    List<PieItemVO> selectComplaintTypePie();

    /**
     * 投诉待处置环状图数据(区域)
     */
    @Select("""
            SELECT
              COALESCE(NULLIF(TRIM(a.area_name), ''), '未知') AS name,
              COUNT(1) AS value
            FROM public_toilet_complaint c
            LEFT JOIN public_toilet pt ON pt.toilet_id = c.toilet_id
            LEFT JOIN sys_area a ON a.area_code = pt.area_code
            WHERE c.deleted = b'0'
                AND (c.dispatch_status IS NOT NULL AND c.dispatch_status <> '已处置')
            GROUP BY COALESCE(NULLIF(TRIM(a.area_name), ''), '未知')
            ORDER BY value DESC
            """)
    List<PieItemVO> selectAreaPie();

    /**
     * 投诉待处置柱状图数据(区域)
     */
    @Select("""
            SELECT
              COALESCE(NULLIF(TRIM(a.area_name), ''), '未知') AS name,
              CAST(COUNT(1) AS DECIMAL(10,2)) AS value
            FROM public_toilet_complaint c
            LEFT JOIN public_toilet pt ON pt.toilet_id = c.toilet_id
            LEFT JOIN sys_area a ON a.area_code = pt.area_code
            WHERE c.deleted = b'0'
                AND (c.dispatch_status IS NOT NULL AND c.dispatch_status <> '已处置')
            GROUP BY COALESCE(NULLIF(TRIM(a.area_name), ''), '未知')
            ORDER BY value DESC
            """)
    List<BarItemVO> selectAreaBar();
}