package cn.iocoder.yudao.module.chargepark.marketop.service.couponactivity.couponmgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.couponmgmt.vo.CouponMgmtChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.couponmgmt.vo.CouponMgmtCreateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.couponmgmt.vo.CouponMgmtPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.couponmgmt.vo.CouponMgmtUpdateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.CouponMgmtDO;
import jakarta.validation.Valid;

public interface CouponMgmtService {

    PageResult<CouponMgmtDO> getPage(CouponMgmtPageReqVO reqVO);

    CouponMgmtDO get(Long id);

    Long create(@Valid CouponMgmtCreateReqVO reqVO);

    void update(@Valid CouponMgmtUpdateReqVO reqVO);

    void send(Long id, java.util.List<Long> userIds);

    void verify(Long id);

    void resend(Long id);

    CouponMgmtChartRespVO getChart(String timeRange);

}
