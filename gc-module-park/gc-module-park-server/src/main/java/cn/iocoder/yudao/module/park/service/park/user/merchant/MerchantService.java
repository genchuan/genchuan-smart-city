package cn.iocoder.yudao.module.park.service.park.user.merchant;

import java.util.*;

import cn.iocoder.yudao.module.park.controller.admin.park.user.merchant.vo.MerchantPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.merchant.vo.MerchantSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.merchant.MerchantDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 商户 Service 接口
 *
 * @author 亘川智城
 */
public interface MerchantService {

    /**
     * 创建商户
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMerchant(@Valid MerchantSaveReqVO createReqVO);

    /**
     * 更新商户
     *
     * @param updateReqVO 更新信息
     */
    void updateMerchant(@Valid MerchantSaveReqVO updateReqVO);

    /**
     * 删除商户
     *
     * @param id 编号
     */
    void deleteMerchant(Long id);

    /**
     * 获得商户
     *
     * @param id 编号
     * @return 商户
     */
    MerchantDO getMerchant(Long id);

    /**
     * 获得商户分页
     *
     * @param pageReqVO 分页查询
     * @return 商户分页
     */
    PageResult<MerchantDO> getMerchantPage(MerchantPageReqVO pageReqVO);

}
