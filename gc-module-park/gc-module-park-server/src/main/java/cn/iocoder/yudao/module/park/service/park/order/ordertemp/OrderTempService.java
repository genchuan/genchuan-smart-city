package cn.iocoder.yudao.module.park.service.park.order.ordertemp;

import java.util.*;

import cn.iocoder.yudao.module.park.controller.admin.park.order.ordertemp.vo.OrderTempGenerateReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.order.ordertemp.vo.OrderTempPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.order.ordertemp.vo.OrderTempSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.order.ordertemp.OrderTempDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 临停订单 Service 接口
 *
 * @author 亘川智城
 */
public interface OrderTempService {

    /**
     * 创建临停订单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOrderTemp(@Valid OrderTempSaveReqVO createReqVO);

    /**
     * 更新临停订单
     *
     * @param updateReqVO 更新信息
     */
    void updateOrderTemp(@Valid OrderTempSaveReqVO updateReqVO);

    /**
     * 删除临停订单
     *
     * @param id 编号
     */
    void deleteOrderTemp(Long id);

    /**
     * 获得临停订单
     *
     * @param id 编号
     * @return 临停订单
     */
    OrderTempDO getOrderTemp(Long id);

    /**
     * 获得临停订单分页
     *
     * @param pageReqVO 分页查询
     * @return 临停订单分页
     */
    PageResult<OrderTempDO> getOrderTempPage(OrderTempPageReqVO pageReqVO);

    Long generateOrderTemp(OrderTempGenerateReqVO reqVO);
}
