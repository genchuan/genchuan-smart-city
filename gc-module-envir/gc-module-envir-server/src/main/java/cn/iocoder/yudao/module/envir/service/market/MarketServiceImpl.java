package cn.iocoder.yudao.module.envir.service.market;

import cn.iocoder.yudao.module.envir.dal.dataobject.garbagecollection.GarbageCollectionDetailDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.market.MarketDetailDO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.envir.controller.admin.market.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.market.MarketDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envir.dal.mysql.market.MarketMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envir.enums.ErrorCodeConstants.*;

/**
 * 集贸市场 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MarketServiceImpl implements MarketService {

    @Resource
    private MarketMapper marketMapper;

    @Override
    public Long createMarket(MarketSaveReqVO createReqVO) {
        // 插入
        MarketDO market = BeanUtils.toBean(createReqVO, MarketDO.class);
        marketMapper.insert(market);
        // 返回
        return market.getId();
    }

    @Override
    public void updateMarket(MarketSaveReqVO updateReqVO) {
        // 校验存在
        validateMarketExists(updateReqVO.getId());
        // 更新
        MarketDO updateObj = BeanUtils.toBean(updateReqVO, MarketDO.class);
        marketMapper.updateById(updateObj);
    }

    @Override
    public void deleteMarket(Long id) {
        // 校验存在
        validateMarketExists(id);
        // 删除
        marketMapper.deleteById(id);
    }

    private void validateMarketExists(Long id) {
        if (marketMapper.selectById(id) == null) {
            throw exception(MARKET_NOT_EXISTS);
        }
    }

    @Override
    public MarketDO getMarket(Long id) {
        return marketMapper.selectById(id);
    }

    @Override
    public PageResult<MarketDO> getMarketPage(MarketPageReqVO pageReqVO) {
        return marketMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MarketDetailDO> getMarketListDetail() {
        return marketMapper.selectListDetail();
    }
}