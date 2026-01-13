package cn.iocoder.yudao.module.industry.service.park.discount.parkperiodpackage;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkperiodpackage.vo.ParkPeriodPackagePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkperiodpackage.vo.ParkPeriodPackageSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkperiodpackage.ParkPeriodPackageDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 期卡套餐 Service 接口
 *
 * @author lxs
 */
public interface ParkPeriodPackageService {

    /**
     * 创建期卡套餐
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkPeriodPackage(@Valid ParkPeriodPackageSaveReqVO createReqVO);

    /**
     * 更新期卡套餐
     *
     * @param updateReqVO 更新信息
     */
    void updateParkPeriodPackage(@Valid ParkPeriodPackageSaveReqVO updateReqVO);

    /**
     * 删除期卡套餐
     *
     * @param id 编号
     */
    void deleteParkPeriodPackage(Long id);

    /**
     * 获得期卡套餐
     *
     * @param id 编号
     * @return 期卡套餐
     */
    ParkPeriodPackageDO getParkPeriodPackage(Long id);

    /**
     * 获得期卡套餐分页
     *
     * @param pageReqVO 分页查询
     * @return 期卡套餐分页
     */
    PageResult<ParkPeriodPackageDO> getParkPeriodPackagePage(ParkPeriodPackagePageReqVO pageReqVO);

}
