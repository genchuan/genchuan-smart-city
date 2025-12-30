package cn.iocoder.yudao.module.industry.service.park.pay.parkpay;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkpay.vo.*;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkpay.ParkPayDO;
import jakarta.validation.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 停车缴费服务 Service 接口
 *
 * @author lxs
 */
public interface ParkPayService {

    /**
     * 创建停车缴费服务
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkPay(@Valid ParkPaySaveReqVO createReqVO);

    /**
     * 更新停车缴费服务
     *
     * @param updateReqVO 更新信息
     */
    void updateParkPay(@Valid ParkPaySaveReqVO updateReqVO);

    /**
     * 删除停车缴费服务
     *
     * @param id 编号
     */
    void deleteParkPay(Long id);

    /**
     * 获得停车缴费服务
     *
     * @param id 编号
     * @return 停车缴费服务
     */
    ParkPayDO getParkPay(Long id);

    /**
     * 获得停车缴费服务分页
     *
     * @param pageReqVO 分页查询
     * @return 停车缴费服务分页
     */
    PageResult<ParkPayDO> getParkPayPage(ParkPayPageReqVO pageReqVO);

    void pay(ParkPayPayReqVO reqVO);

    PageResult<ParkPayDO> drillParkPay(ParkPayDrillReqVO drillReqVO);

    Long addParkPay(ParkPayAddReqVO addReqVO);

    void exitParkPay(ParkPayExitReqVO exitReqVO);
}
