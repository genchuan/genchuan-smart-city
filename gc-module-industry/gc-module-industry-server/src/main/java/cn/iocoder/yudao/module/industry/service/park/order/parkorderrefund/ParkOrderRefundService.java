package cn.iocoder.yudao.module.industry.service.park.order.parkorderrefund;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderrefund.vo.ParkOrderRefundPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderrefund.vo.ParkOrderRefundSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkorderrefund.ParkOrderRefundDO;
import jakarta.validation.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 退款订单 Service 接口
 *
 * @author lxs
 */
public interface ParkOrderRefundService {

    /**
     * 创建退款订单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOrderRefund(@Valid ParkOrderRefundSaveReqVO createReqVO);

    /**
     * 更新退款订单
     *
     * @param updateReqVO 更新信息
     */
    void updateOrderRefund(@Valid ParkOrderRefundSaveReqVO updateReqVO);

    /**
     * 删除退款订单
     *
     * @param id 编号
     */
    void deleteOrderRefund(Long id);

    /**
     * 获得退款订单
     *
     * @param id 编号
     * @return 退款订单
     */
    ParkOrderRefundDO getOrderRefund(Long id);

    /**
     * 获得退款订单分页
     *
     * @param pageReqVO 分页查询
     * @return 退款订单分页
     */
    PageResult<ParkOrderRefundDO> getOrderRefundPage(ParkOrderRefundPageReqVO pageReqVO);

}
