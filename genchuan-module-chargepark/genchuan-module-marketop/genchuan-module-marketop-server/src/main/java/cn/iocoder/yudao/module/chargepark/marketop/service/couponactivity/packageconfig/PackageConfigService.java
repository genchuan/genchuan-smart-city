package cn.iocoder.yudao.module.chargepark.marketop.service.couponactivity.packageconfig;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.packageconfig.vo.PackageConfigChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.packageconfig.vo.PackageConfigCreateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.packageconfig.vo.PackageConfigPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.packageconfig.vo.PackageConfigUpdateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.PackageConfigDO;
import jakarta.validation.Valid;

public interface PackageConfigService {

    PageResult<PackageConfigDO> getPage(PackageConfigPageReqVO reqVO);

    PackageConfigDO get(Long id);

    Long create(@Valid PackageConfigCreateReqVO reqVO);

    void update(@Valid PackageConfigUpdateReqVO reqVO);

    void enable(Long id);

    void disable(Long id);

    PackageConfigChartRespVO getChart(Long startTime, Long endTime);

}
