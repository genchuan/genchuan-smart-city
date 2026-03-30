package cn.iocoder.yudao.module.park.service.park.pricing.promotion;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.promotion.vo.PromotionPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.promotion.vo.PromotionSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.promotion.PromotionDO;
import jakarta.validation.Valid;

/**
 * 优惠活动 Service 接口
 *
 * @author 亘川智城
 */
public interface PromotionService {

    /**
     * 创建优惠活动
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPromotion(@Valid PromotionSaveReqVO createReqVO);

    /**
     * 更新优惠活动
     *
     * @param updateReqVO 更新信息
     */
    void updatePromotion(@Valid PromotionSaveReqVO updateReqVO);

    /**
     * 删除优惠活动
     *
     * @param id 编号
     */
    void deletePromotion(Long id);

    /**
     * 获得优惠活动
     *
     * @param id 编号
     * @return 优惠活动
     */
    PromotionDO getPromotion(Long id);

    /**
     * 获得优惠活动分页
     *
     * @param pageReqVO 分页查询
     * @return 优惠活动分页
     */
    PageResult<PromotionDO> getPromotionPage(PromotionPageReqVO pageReqVO);

}
