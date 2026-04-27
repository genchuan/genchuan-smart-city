package cn.iocoder.yudao.module.usermerchant.service.merchantmgmt.merchantrecharge;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantrecharge.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.merchantmgmt.merchantrecharge.MerchantRechargeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 商户充值 Service 接口
 *
 * @author 亘川智城
 */
public interface MerchantRechargeService {

    /**
     * 商户支付
     *
     * @param payReqVO 支付信息
     * @return 编号
     */
    Boolean payMerchantRecharge(@Valid MerchantRechargePayReqVO payReqVO);

    /**
     * 创建商户支付
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Boolean createMerchantRecharge(@Valid MerchantRechargeCreateReqVO createReqVO);

    /**
     * 更新商户充值
     *
     * @param updateReqVO 更新信息
     */
    void updateMerchantRecharge(@Valid MerchantRechargeSaveReqVO updateReqVO);

    /**
     * 删除商户充值
     *
     * @param id 编号
     */
    void deleteMerchantRecharge(Long id);

    /**
    * 批量删除商户充值
    *
    * @param ids 编号
    */
    void deleteMerchantRechargeListByIds(List<Long> ids);

    /**
     * 获得商户充值
     *
     * @param id 编号
     * @return 商户充值
     */
    MerchantRechargeDO getMerchantRecharge(Long id);

    /**
     * 获得商户充值分页
     *
     * @param pageReqVO 分页查询
     * @return 商户充值分页
     */
    PageResult<MerchantRechargeDO> getMerchantRechargePage(MerchantRechargePageReqVO pageReqVO);

    /**
     * 确认或取消支付
     *
     * @param payReqVO 支付查询
     * @param code value
     * @return 布尔值
     */
    Boolean cashMerchantRecharge(@Valid MerchantRechargePayReqVO payReqVO, String code);

    /**
     * 商户支付统计可视化
     *
     * @param chartReqVO 时间范围
     * @return 统计信息
     */
    MerchantRechargeChartRespVO getMerchantRechargeChart(MerchantRechargeChartReqVO chartReqVO);
}