package cn.iocoder.yudao.module.industry.service.park.order.parkorderperiod;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderperiod.vo.ParkOrderPeriodPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderperiod.vo.ParkOrderPeriodSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkorderperiod.ParkOrderPeriodDO;
import jakarta.validation.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 期卡订单 Service 接口
 *
 * @author lxs
 */
public interface ParkOrderPeriodService {

    /**
     * 创建期卡订单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkOrderPeriod(@Valid ParkOrderPeriodSaveReqVO createReqVO);

    /**
     * 更新期卡订单
     *
     * @param updateReqVO 更新信息
     */
    void updateParkOrderPeriod(@Valid ParkOrderPeriodSaveReqVO updateReqVO);

    /**
     * 删除期卡订单
     *
     * @param id 编号
     */
    void deleteParkOrderPeriod(Long id);

    /**
     * 获得期卡订单
     *
     * @param id 编号
     * @return 期卡订单
     */
    ParkOrderPeriodDO getParkOrderPeriod(Long id);

    /**
     * 获得期卡订单分页
     *
     * @param pageReqVO 分页查询
     * @return 期卡订单分页
     */
    PageResult<ParkOrderPeriodDO> getParkOrderPeriodPage(ParkOrderPeriodPageReqVO pageReqVO);

}
