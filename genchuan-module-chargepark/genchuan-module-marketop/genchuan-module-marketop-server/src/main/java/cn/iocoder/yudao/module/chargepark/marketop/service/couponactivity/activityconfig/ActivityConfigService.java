package cn.iocoder.yudao.module.chargepark.marketop.service.couponactivity.activityconfig;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.activityconfig.vo.ActivityConfigChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.activityconfig.vo.ActivityConfigCreateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.activityconfig.vo.ActivityConfigPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.activityconfig.vo.ActivityConfigUpdateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.ActivityConfigDO;
import jakarta.validation.Valid;

public interface ActivityConfigService {

    PageResult<ActivityConfigDO> getPage(ActivityConfigPageReqVO reqVO);

    ActivityConfigDO get(Long id);

    Long create(@Valid ActivityConfigCreateReqVO reqVO);

    void update(@Valid ActivityConfigUpdateReqVO reqVO);

    void enable(Long id);

    void disable(Long id);

    ActivityConfigChartRespVO getChart();

}
