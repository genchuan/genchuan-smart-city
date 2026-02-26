package cn.iocoder.yudao.module.envir.service.market;

import java.util.*;

import cn.iocoder.yudao.module.envir.dal.dataobject.garbagecollection.GarbageCollectionDetailDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.market.MarketDetailDO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envir.controller.admin.market.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.market.MarketDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 集贸市场 Service 接口
 *
 * @author 芋道源码
 */
public interface MarketService {

    /**
     * 创建集贸市场
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMarket(@Valid MarketSaveReqVO createReqVO);

    /**
     * 更新集贸市场
     *
     * @param updateReqVO 更新信息
     */
    void updateMarket(@Valid MarketSaveReqVO updateReqVO);

    /**
     * 删除集贸市场
     *
     * @param id 编号
     */
    void deleteMarket(Long id);

    /**
     * 获得集贸市场
     *
     * @param id 编号
     * @return 集贸市场
     */
    MarketDO getMarket(Long id);

    /**
     * 获得集贸市场分页
     *
     * @param pageReqVO 分页查询
     * @return 集贸市场分页
     */
    PageResult<MarketDO> getMarketPage(MarketPageReqVO pageReqVO);

    /**
     * 获得集贸市场列表(详情)
     *
     */
    List<MarketDetailDO> getMarketListDetail();
}