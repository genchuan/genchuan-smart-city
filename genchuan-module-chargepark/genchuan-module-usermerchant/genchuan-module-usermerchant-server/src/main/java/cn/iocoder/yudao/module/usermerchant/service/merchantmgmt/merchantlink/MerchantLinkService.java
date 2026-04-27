package cn.iocoder.yudao.module.usermerchant.service.merchantmgmt.merchantlink;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantlink.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.merchantmgmt.merchantlink.MerchantLinkDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 商户对接 Service 接口
 *
 * @author 亘川智城
 */
public interface MerchantLinkService {

    /**
     * 创建商户对接
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Boolean createMerchantLink(@Valid MerchantLinkSaveReqVO createReqVO);

    /**
     * 更新商户对接
     *
     * @param updateReqVO 更新信息
     */
    void updateMerchantLink(@Valid MerchantLinkSaveReqVO updateReqVO);

    /**
     * 删除商户对接
     *
     * @param id 编号
     */
    void deleteMerchantLink(Long id);

    /**
    * 批量删除商户对接
    *
    * @param ids 编号
    */
    void deleteMerchantLinkListByIds(List<Long> ids);

    /**
     * 获得商户对接
     *
     * @param id 编号
     * @return 商户对接
     */
    MerchantLinkDO getMerchantLink(Long id);

    /**
     * 获得商户对接分页
     *
     * @param pageReqVO 分页查询
     * @return 商户对接分页
     */
    PageResult<MerchantLinkDO> getMerchantLinkPage(MerchantLinkPageReqVO pageReqVO);

    /**
     * 保存商户对接
     *
     * @param saveReqVO 创建信息
     * @return 编号
     */
    Boolean saveMerchantLink(MerchantLinkSaveReqVO saveReqVO);

    /**
     * 批量修改商户对接
     *
     * @param reqVO 编号数组
     * @param status 状态
     */
    void linkMerchantLink(@Valid MerchantLinkLinkReqVO reqVO, String status);

    /**
     * 商户对接统计可视化
     *
     * @param chartReqVO 时间范围
     * @return 统计信息
     */
    MerchantLinkChartRespVO getMerchantLinkChart(@Valid MerchantLinkChartReqVO chartReqVO);
}