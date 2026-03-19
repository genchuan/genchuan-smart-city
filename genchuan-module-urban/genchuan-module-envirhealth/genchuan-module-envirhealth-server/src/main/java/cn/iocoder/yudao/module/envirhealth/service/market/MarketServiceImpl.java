package cn.iocoder.yudao.module.envirhealth.service.market;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.market.vo.MarketPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.market.vo.MarketSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.market.MarketDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.MarketDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.market.MarketMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.codegenerator.market.MarketCodeGenerator;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.MARKET_NOT_EXISTS;

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

    @Resource
    private MarketCodeGenerator codeGenerator;

    @Override
    public Long createMarket(MarketSaveReqVO createReqVO) {
        // 插入
        MarketDO market = BeanUtils.toBean(createReqVO, MarketDO.class);

        market.setId(null);
        market.setMarketId(codeGenerator.generateMarketId());

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
    public PageResult<MarketDetailDO> getMarketDetailPage(MarketPageReqVO pageReqVO) {
        Long total = marketMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<MarketDetailDO> list = marketMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }

}