package cn.iocoder.yudao.module.chargepark.marketop.service.pointactivity.pointlottery;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointlottery.vo.PointLotteryChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointlottery.vo.PointLotteryPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointlottery.vo.PointLotteryRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PointLotteryDO;

public interface PointLotteryService {

    PageResult<PointLotteryDO> getPage(PointLotteryPageReqVO reqVO);

    PointLotteryDO get(Long id);

    void check(Long id, String checkResult);

    PointLotteryChartRespVO getChart();

    PageResult<PointLotteryRespVO> getPageWithJoin(PointLotteryPageReqVO reqVO);

    PointLotteryRespVO getWithJoin(Long id);

}
