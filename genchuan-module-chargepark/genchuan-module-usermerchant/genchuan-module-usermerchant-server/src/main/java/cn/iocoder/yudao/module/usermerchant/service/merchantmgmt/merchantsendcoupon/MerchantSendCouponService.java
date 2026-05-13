package cn.iocoder.yudao.module.usermerchant.service.merchantmgmt.merchantsendcoupon;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantsendcoupon.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.merchantmgmt.merchantsendcoupon.MerchantSendCouponDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 商户发券 Service 接口
 *
 * @author 亘川智城
 */
public interface MerchantSendCouponService {

    /**
     * 创建商户发券
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMerchantSendCoupon(@Valid MerchantSendCouponSaveReqVO createReqVO);

    /**
     * 更新商户发券
     *
     * @param updateReqVO 更新信息
     */
    void updateMerchantSendCoupon(@Valid MerchantSendCouponSaveReqVO updateReqVO);

    /**
     * 删除商户发券
     *
     * @param id 编号
     */
    void deleteMerchantSendCoupon(Long id);

    /**
    * 批量删除商户发券
    *
    * @param ids 编号
    */
    void deleteMerchantSendCouponListByIds(List<Long> ids);

    /**
     * 获得商户发券
     *
     * @param id 编号
     * @return 商户发券
     */
    MerchantSendCouponDO getMerchantSendCoupon(Long id);

    /**
     * 获得商户发券分页
     *
     * @param pageReqVO 分页查询
     * @return 商户发券分页
     */
    PageResult<MerchantSendCouponDO> getMerchantSendCouponPage(MerchantSendCouponPageReqVO pageReqVO);

    /**
     * 商户发券统计可视化
     *
     * @param chartReqVO 时间范围
     * @return 统计信息
     */
    MerchantSendCouponChartRespVO getMerchantSendCouponChart(@Valid MerchantSendCouponChartReqVO chartReqVO);

    /**
     * 发券（创建发券记录）
     *
     * @param sendReqVO 发券请求，包含商户ID、优惠券ID、发放数量、执行时间、备注等
     */
    void sendCoupon(MerchantSendCouponSendReqVO sendReqVO);

    /**
     * 执行发券（批量）
     *
     * @param ids 发券记录ID列表
     */
    void executeCoupon(List<Long> ids);

    /**
     * 取消发券（批量）
     *
     * @param ids 发券记录ID列表
     */
    void cancelCoupon(List<Long> ids);

}