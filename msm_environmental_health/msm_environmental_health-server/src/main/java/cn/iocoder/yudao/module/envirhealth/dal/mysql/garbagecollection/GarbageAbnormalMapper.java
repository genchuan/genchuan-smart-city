package cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagecollection;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.GarbageAbnormalPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.GarbageCollectionPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.area.AreaDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.detail.GarbageCollectionDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.UserDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.GarbageAbnormalDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.GarbageCollectionDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.detail.GarbageAbnormalDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.AbnormalTypeDO;

import cn.iocoder.yudao.module.envirhealth.util.circle.vo.CircleVO;
import cn.iocoder.yudao.module.envirhealth.util.column.vo.ColumnVO;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
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
                .eqIfPresent(GarbageAbnormalDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(GarbageAbnormalDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(GarbageAbnormalDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(GarbageAbnormalDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(GarbageAbnormalDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GarbageAbnormalDO::getId));
    }

    List<GarbageAbnormalDetailDO> selectDetailPage(@Param("reqVO") GarbageAbnormalPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") GarbageAbnormalPageReqVO pageReqVO);

    /**
     * 获取异常类型占比统计
     * @return 异常类型占比列表
     */
    @Select("SELECT " +
            "sat.abnormal_name as name, " +
            "COUNT(ga.id) as value, " +
            "ROUND(COUNT(ga.id) * 100.0 / (SELECT COUNT(*) FROM garbage_abnormal WHERE deleted = 0), 2) as proportion " +
            "FROM garbage_abnormal ga " +
            "LEFT JOIN sys_abnormal_type sat ON ga.abnormal_type_id = sat.abnormal_type_id " +
            "WHERE ga.deleted = 0 " +
            "GROUP BY ga.abnormal_type_id, sat.abnormal_name " +
            "ORDER BY value DESC")
    List<CircleVO> selectAbnormalTypeCircle();

    /**
     * 获取区域分布占比统计
     * @return 区域分布占比列表
     */
    @Select("SELECT " +
            "sa.area_name as name, " +
            "COUNT(ga.id) as value, " +
            "ROUND(COUNT(ga.id) * 100.0 / (SELECT COUNT(*) FROM garbage_abnormal WHERE deleted = 0), 2) as proportion " +
            "FROM garbage_abnormal ga " +
            "LEFT JOIN sys_area sa ON ga.area_code = sa.area_code " +
            "WHERE ga.deleted = 0 " +
            "GROUP BY ga.area_code, sa.area_name " +
            "ORDER BY value DESC")
    List<CircleVO> selectAreaDistributionCircle();

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
    List<ColumnVO> selectHandlerAbnormalColumn();
}