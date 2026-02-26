package cn.iocoder.yudao.module.park.service.park.pricing.periodpackage;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.periodpackage.vo.PeriodPackagePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.periodpackage.vo.PeriodPackageSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.periodpackage.PeriodPackageDO;
import jakarta.validation.Valid;

/**
 * 期卡套餐 Service 接口
 *
 * @author 亘川智城
 */
public interface PeriodPackageService {

    /**
     * 创建期卡套餐
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPeriodPackage(@Valid PeriodPackageSaveReqVO createReqVO);

    /**
     * 更新期卡套餐
     *
     * @param updateReqVO 更新信息
     */
    void updatePeriodPackage(@Valid PeriodPackageSaveReqVO updateReqVO);

    /**
     * 删除期卡套餐
     *
     * @param id 编号
     */
    void deletePeriodPackage(Long id);

    /**
     * 获得期卡套餐
     *
     * @param id 编号
     * @return 期卡套餐
     */
    PeriodPackageDO getPeriodPackage(Long id);

    /**
     * 获得期卡套餐分页
     *
     * @param pageReqVO 分页查询
     * @return 期卡套餐分页
     */
    PageResult<PeriodPackageDO> getPeriodPackagePage(PeriodPackagePageReqVO pageReqVO);

}
