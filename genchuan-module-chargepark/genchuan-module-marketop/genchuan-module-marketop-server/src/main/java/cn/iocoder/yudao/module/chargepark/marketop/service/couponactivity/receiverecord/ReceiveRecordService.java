package cn.iocoder.yudao.module.chargepark.marketop.service.couponactivity.receiverecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.receiverecord.vo.ReceiveRecordChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.receiverecord.vo.ReceiveRecordPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.receiverecord.vo.ReceiveRecordRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.ReceiveRecordDO;

public interface ReceiveRecordService {

    PageResult<ReceiveRecordDO> getPage(ReceiveRecordPageReqVO reqVO);

    ReceiveRecordDO get(Long id);

    void check(Long id, String checkResult);

    ReceiveRecordChartRespVO getChart();

    PageResult<ReceiveRecordRespVO> getPageWithJoin(ReceiveRecordPageReqVO reqVO);

    ReceiveRecordRespVO getWithJoin(Long id);

}
