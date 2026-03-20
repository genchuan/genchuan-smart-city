package cn.iocoder.yudao.module.appearance.dal.mysql.outdoorad;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.appearance.dal.dataobject.outdoorad.OutdoorAdDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.all.vo.*;

/**
 * 户外广告 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface OutdoorAdMapper extends BaseMapperX<OutdoorAdDO> {

    default PageResult<OutdoorAdDO> selectPage( OutdoorAdPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OutdoorAdDO>()
                .likeIfPresent(OutdoorAdDO::getName, reqVO.getName())
                .likeIfPresent(OutdoorAdDO::getLocation, reqVO.getLocation())//eqif被修改为likeif
                .eqIfPresent(OutdoorAdDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(OutdoorAdDO::getWarningTypeId, reqVO.getWarningTypeId())
                .eqIfPresent(OutdoorAdDO::getAdStatusId, reqVO.getAdStatusId())
                .eqIfPresent(OutdoorAdDO::getDamageStatusId, reqVO.getDamageStatusId())
                .betweenIfPresent(OutdoorAdDO::getWarningTime, reqVO.getWarningTime())
                .betweenIfPresent(OutdoorAdDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(OutdoorAdDO::getId));
    }

    /**
     * 关联查询户外广告列表
     */
    List<OutdoorAdDO> selectPageWithRelations( OutdoorAdPageReqVO reqVO);

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