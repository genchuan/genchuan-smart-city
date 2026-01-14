package cn.iocoder.yudao.module.industry.service.park.discount.parkrechargepackage;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkrechargepackage.vo.ParkRechargePackagePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkrechargepackage.vo.ParkRechargePackageSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkrechargepackage.ParkRechargePackageDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 充值套餐 Service 接口
 *
 * @author lxs
 */
public interface ParkRechargePackageService {

    /**
     * 创建充值套餐
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkRechargePackage(@Valid ParkRechargePackageSaveReqVO createReqVO);

    /**
     * 更新充值套餐
     *
     * @param updateReqVO 更新信息
     */
    void updateParkRechargePackage(@Valid ParkRechargePackageSaveReqVO updateReqVO);

    /**
     * 删除充值套餐
     *
     * @param id 编号
     */
    void deleteParkRechargePackage(Long id);

    /**
     * 获得充值套餐
     *
     * @param id 编号
     * @return 充值套餐
     */
    ParkRechargePackageDO getParkRechargePackage(Long id);

    /**
     * 获得充值套餐分页
     *
     * @param pageReqVO 分页查询
     * @return 充值套餐分页
     */
    PageResult<ParkRechargePackageDO> getParkRechargePackagePage(ParkRechargePackagePageReqVO pageReqVO);

}
