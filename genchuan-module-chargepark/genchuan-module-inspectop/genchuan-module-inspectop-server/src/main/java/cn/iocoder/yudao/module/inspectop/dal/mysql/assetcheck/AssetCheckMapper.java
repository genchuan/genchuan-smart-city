package cn.iocoder.yudao.module.inspectop.dal.mysql.assetcheck;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.assetcheck.AssetCheckDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.inspectop.controller.admin.assetcheck.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 资产盘点 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface AssetCheckMapper extends BaseMapperX<AssetCheckDO> {

    default PageResult<AssetCheckDO> selectPage(AssetCheckPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AssetCheckDO>()
                .eqIfPresent(AssetCheckDO::getType, reqVO.getType())
                .betweenIfPresent(AssetCheckDO::getCheckTime, reqVO.getCheckTime())
                .eqIfPresent(AssetCheckDO::getProgress, reqVO.getProgress())
                .eqIfPresent(AssetCheckDO::getStatus, reqVO.getStatus())
                .eqIfPresent(AssetCheckDO::getConfirmUserId, reqVO.getConfirmUserId())
                .betweenIfPresent(AssetCheckDO::getConfirmTime, reqVO.getConfirmTime())
                .eqIfPresent(AssetCheckDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(AssetCheckDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(AssetCheckDO::getCreator, reqVO.getCreator())
                .eqIfPresent(AssetCheckDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(AssetCheckDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(AssetCheckDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(AssetCheckDO::getId));
    }

    /**
     * 获取折线图趋势数据
     *
     * @param reqVO 查询条件
     * @return 趋势数据列表
     */
    List<TrendData> selectTrendData(@Param("reqVO") AssetCheckChartReqVO reqVO);

    /**
     * 获取卡片统计数据
     *
     * @param reqVO 查询条件
     * @return 卡片统计数据
     */
    CardData selectCardData(@Param("reqVO") AssetCheckChartReqVO reqVO);

    // 内部类定义
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class TrendData {
        private String time;
        private Integer progress;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class CardData {
        private Integer checkCount;
        private Double checkFinishRate;
    }

}