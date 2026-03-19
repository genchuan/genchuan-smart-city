package cn.iocoder.yudao.module.envirhealth.service.market;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.market.vo.MarketDashboardVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.market.vo.MarketPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.market.vo.MarketSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.market.MarketDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.MarketDetailDO;
import jakarta.validation.Valid;

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
     * 获得集贸市场分页
     *
     * @param pageReqVO 分页查询
     * @return 集贸市场分页
     */
    PageResult<MarketDetailDO> getMarketDetailPage(MarketPageReqVO pageReqVO);

    /**
     * 获取集贸市场看板统计数据
     *
     * @return 看板统计VO
     */
    MarketDashboardVO getMarketDashboardData();
}