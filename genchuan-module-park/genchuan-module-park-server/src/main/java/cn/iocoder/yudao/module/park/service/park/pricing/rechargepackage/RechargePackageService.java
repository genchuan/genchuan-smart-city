package cn.iocoder.yudao.module.park.service.park.pricing.rechargepackage;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.rechargepackage.vo.RechargePackagePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.rechargepackage.vo.RechargePackageSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.rechargepackage.RechargePackageDO;
import jakarta.validation.Valid;

/**
 * 充值套餐 Service 接口
 *
 * @author 亘川智城
 */
public interface RechargePackageService {

    /**
     * 创建充值套餐
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRechargePackage(@Valid RechargePackageSaveReqVO createReqVO);

    /**
     * 更新充值套餐
     *
     * @param updateReqVO 更新信息
     */
    void updateRechargePackage(@Valid RechargePackageSaveReqVO updateReqVO);

    /**
     * 删除充值套餐
     *
     * @param id 编号
     */
    void deleteRechargePackage(Long id);

    /**
     * 获得充值套餐
     *
     * @param id 编号
     * @return 充值套餐
     */
    RechargePackageDO getRechargePackage(Long id);

    /**
     * 获得充值套餐分页
     *
     * @param pageReqVO 分页查询
     * @return 充值套餐分页
     */
    PageResult<RechargePackageDO> getRechargePackagePage(RechargePackagePageReqVO pageReqVO);

}
