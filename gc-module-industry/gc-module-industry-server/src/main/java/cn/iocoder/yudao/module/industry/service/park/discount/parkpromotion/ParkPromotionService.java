package cn.iocoder.yudao.module.industry.service.park.discount.parkpromotion;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkpromotion.vo.ParkPromotionPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkpromotion.vo.ParkPromotionSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkpromotion.ParkPromotionDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 优惠活动 Service 接口
 *
 * @author lxs
 */
public interface ParkPromotionService {

    /**
     * 创建优惠活动
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkPromotion(@Valid ParkPromotionSaveReqVO createReqVO);

    /**
     * 更新优惠活动
     *
     * @param updateReqVO 更新信息
     */
    void updateParkPromotion(@Valid ParkPromotionSaveReqVO updateReqVO);

    /**
     * 删除优惠活动
     *
     * @param id 编号
     */
    void deleteParkPromotion(Long id);

    /**
     * 获得优惠活动
     *
     * @param id 编号
     * @return 优惠活动
     */
    ParkPromotionDO getParkPromotion(Long id);

    /**
     * 获得优惠活动分页
     *
     * @param pageReqVO 分页查询
     * @return 优惠活动分页
     */
    PageResult<ParkPromotionDO> getParkPromotionPage(ParkPromotionPageReqVO pageReqVO);

}
