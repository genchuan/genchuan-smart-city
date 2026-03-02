package cn.iocoder.yudao.module.park.service.park.pricing.promotion;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.promotion.vo.PromotionPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.promotion.vo.PromotionSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.promotion.PromotionDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.pricing.promotion.PromotionMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.PROMOTION_NOT_EXISTS;

/**
 * 优惠活动 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class PromotionServiceImpl implements PromotionService {

    @Resource
    private PromotionMapper promotionMapper;

    @Override
    public Long createPromotion(PromotionSaveReqVO createReqVO) {
        // 插入
        PromotionDO promotion = BeanUtils.toBean(createReqVO, PromotionDO.class);
        promotionMapper.insert(promotion);
        // 返回
        return promotion.getId();
    }

    @Override
    public void updatePromotion(PromotionSaveReqVO updateReqVO) {
        // 校验存在
        validatePromotionExists(updateReqVO.getId());
        // 更新
        PromotionDO updateObj = BeanUtils.toBean(updateReqVO, PromotionDO.class);
        promotionMapper.updateById(updateObj);
    }

    @Override
    public void deletePromotion(Long id) {
        // 校验存在
        validatePromotionExists(id);
        // 删除
        promotionMapper.deleteById(id);
    }

    private void validatePromotionExists(Long id) {
        if (promotionMapper.selectById(id) == null) {
            throw exception(PROMOTION_NOT_EXISTS);
        }
    }

    @Override
    public PromotionDO getPromotion(Long id) {
        return promotionMapper.selectById(id);
    }

    @Override
    public PageResult<PromotionDO> getPromotionPage(PromotionPageReqVO pageReqVO) {
        return promotionMapper.selectPage(pageReqVO);
    }

}
