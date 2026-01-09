package cn.iocoder.yudao.module.industry.service.park.user.parkmerchant;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmerchant.vo.ParkMerchantPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmerchant.vo.ParkMerchantSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkmerchant.ParkMerchantDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 商户 Service 接口
 *
 * @author lxs
 */
public interface ParkMerchantService {

    /**
     * 创建商户
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkMerchant(@Valid ParkMerchantSaveReqVO createReqVO);

    /**
     * 更新商户
     *
     * @param updateReqVO 更新信息
     */
    void updateParkMerchant(@Valid ParkMerchantSaveReqVO updateReqVO);

    /**
     * 删除商户
     *
     * @param id 编号
     */
    void deleteParkMerchant(Long id);

    /**
     * 获得商户
     *
     * @param id 编号
     * @return 商户
     */
    ParkMerchantDO getParkMerchant(Long id);

    /**
     * 获得商户分页
     *
     * @param pageReqVO 分页查询
     * @return 商户分页
     */
    PageResult<ParkMerchantDO> getParkMerchantPage(ParkMerchantPageReqVO pageReqVO);

}
