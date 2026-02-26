package cn.iocoder.yudao.module.envir.dal.mysql.market;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envir.dal.dataobject.market.MarketDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.market.MarketDetailDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.area.AreaDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.user.UserDO;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.envir.controller.admin.market.vo.*;

/**
 * 集贸市场 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface MarketMapper extends BaseMapperX<MarketDO> {

    default PageResult<MarketDO> selectPage(MarketPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MarketDO>()
                .eqIfPresent(MarketDO::getMarketId, reqVO.getMarketId())
                .likeIfPresent(MarketDO::getName, reqVO.getName())
                .eqIfPresent(MarketDO::getAddress, reqVO.getAddress())
                .eqIfPresent(MarketDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(MarketDO::getStallCount, reqVO.getStallCount())
                .eqIfPresent(MarketDO::getStallCleaningRule, reqVO.getStallCleaningRule())
                .eqIfPresent(MarketDO::getCleaningFrequency, reqVO.getCleaningFrequency())
                .eqIfPresent(MarketDO::getWasteTransferInterval, reqVO.getWasteTransferInterval())
                .eqIfPresent(MarketDO::getSewageDisposalWay, reqVO.getSewageDisposalWay())
                .eqIfPresent(MarketDO::getManagerId, reqVO.getManagerId())
                .eqIfPresent(MarketDO::getAbnormalCreateBy, reqVO.getAbnormalCreateBy())
                .betweenIfPresent(MarketDO::getAbnormalCreateTime, reqVO.getAbnormalCreateTime())
                .betweenIfPresent(MarketDO::getAbnormalUpdateTime, reqVO.getAbnormalUpdateTime())
                .eqIfPresent(MarketDO::getHygieneRate, reqVO.getHygieneRate())
                .eqIfPresent(MarketDO::getWasteVolume, reqVO.getWasteVolume())
                .eqIfPresent(MarketDO::getSewageRate, reqVO.getSewageRate())
                .eqIfPresent(MarketDO::getSewagePhotoUrl, reqVO.getSewagePhotoUrl())
                .eqIfPresent(MarketDO::getHygieneCheckPhotoUrl, reqVO.getHygieneCheckPhotoUrl())
                .eqIfPresent(MarketDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(MarketDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(MarketDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(MarketDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(MarketDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MarketDO::getId));
    }

    default List<MarketDetailDO> selectListDetail() {
        return selectJoinList(MarketDetailDO.class, new MPJLambdaWrapper<MarketDO>()
                .selectAll(MarketDO.class)
                .selectAs(AreaDO::getAreaName, MarketDetailDO::getAreaName)
                .selectAs(UserDO::getUserName, MarketDetailDO::getManagerName)
                .leftJoin(AreaDO.class, AreaDO::getAreaCode, MarketDO::getAreaCode)
                .leftJoin(UserDO.class, UserDO::getUserId, MarketDO::getManagerId)
        );
    }
}