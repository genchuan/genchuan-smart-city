package cn.iocoder.yudao.module.appearance.dal.mysql.outdoorad.all;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.appearance.dal.dataobject.outdoorad.all.OutdoorAdDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.all.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 户外广告 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface OutdoorAdMapper extends BaseMapperX<OutdoorAdDO> {

    default PageResult<OutdoorAdDO> selectPage( OutdoorAdPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OutdoorAdDO>()
                .likeIfPresent(OutdoorAdDO::getName, reqVO.getAdName())
                .eqIfPresent(OutdoorAdDO::getType, reqVO.getAdType())
                .eqIfPresent(OutdoorAdDO::getApprovalStatus, reqVO.getApprovalStatus())
                .geIfPresent(OutdoorAdDO::getStartApprovalTime, reqVO.getStartApprovalTime())
                .leIfPresent(OutdoorAdDO::getEndApprovalTime, reqVO.getEndApprovalTime())
                .orderByDesc(OutdoorAdDO::getId));
    }

    /**
     * 关联查询户外广告列表
     */
    List<OutdoorAdDO> selectPageWithRelations( OutdoorAdPageReqVO reqVO);

    /**
     * 关联查询户外广告详情
     */
    OutdoorAdDO selectOneWithRelations( @Param("reqVO") OutdoorAdGetReqVO reqVO);

    /**
     * 统计总广告数
     */
    Integer countTotalAds();

    /**
     * 统计预警广告数
     */
    Integer countWarningAds();

    /**
     * 统计待处置工单数
     */
    Integer countPendingOrders();

    /**
     * 统计已闭环工单数
     */
    Integer countClosedOrders();

    /**
     * 获取区域预警趋势
     */
    List<Map<String, Object>> getAreaWarningTrend();

    /**
     * 获取近30日预警趋势
     */
    List<Map<String, Object>> getRecentWarningTrend();

    /**
     * 获取广告状态占比
     */
    List<Map<String, Object>> getAdStatusDistribution();

    /**
     * 获取预警类型占比
     */
    List<Map<String, Object>> getWarningTypeDistribution();

    /**
     * 获取复核结果占比
     */
    List<Map<String, Object>> getReviewResultDistribution();

}