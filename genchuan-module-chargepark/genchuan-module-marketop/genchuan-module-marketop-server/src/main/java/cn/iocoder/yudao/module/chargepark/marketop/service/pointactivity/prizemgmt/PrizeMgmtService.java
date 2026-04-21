package cn.iocoder.yudao.module.chargepark.marketop.service.pointactivity.prizemgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.prizemgmt.vo.PrizeMgmtChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.prizemgmt.vo.PrizeMgmtCreateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.prizemgmt.vo.PrizeMgmtPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.prizemgmt.vo.PrizeMgmtUpdateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PrizeMgmtDO;
import jakarta.validation.Valid;

public interface PrizeMgmtService {

    PageResult<PrizeMgmtDO> getPage(PrizeMgmtPageReqVO reqVO);

    PrizeMgmtDO get(Long id);

    Long create(@Valid PrizeMgmtCreateReqVO reqVO);

    void update(@Valid PrizeMgmtUpdateReqVO reqVO);

    void enable(Long id);

    void disable(Long id);

    PrizeMgmtChartRespVO getChart(String timeRange);

}
