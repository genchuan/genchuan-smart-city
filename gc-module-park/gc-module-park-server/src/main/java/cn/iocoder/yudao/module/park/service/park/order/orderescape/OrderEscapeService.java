package cn.iocoder.yudao.module.park.service.park.order.orderescape;

import java.util.*;

import cn.iocoder.yudao.module.park.controller.admin.park.order.orderescape.vo.OrderEscapePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.order.orderescape.vo.OrderEscapeSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.order.orderescape.OrderEscapeDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 逃费订单 Service 接口
 *
 * @author 亘川智城
 */
public interface OrderEscapeService {

    /**
     * 创建逃费订单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOrderEscape(@Valid OrderEscapeSaveReqVO createReqVO);

    /**
     * 更新逃费订单
     *
     * @param updateReqVO 更新信息
     */
    void updateOrderEscape(@Valid OrderEscapeSaveReqVO updateReqVO);

    /**
     * 删除逃费订单
     *
     * @param id 编号
     */
    void deleteOrderEscape(Long id);

    /**
     * 获得逃费订单
     *
     * @param id 编号
     * @return 逃费订单
     */
    OrderEscapeDO getOrderEscape(Long id);

    /**
     * 获得逃费订单分页
     *
     * @param pageReqVO 分页查询
     * @return 逃费订单分页
     */
    PageResult<OrderEscapeDO> getOrderEscapePage(OrderEscapePageReqVO pageReqVO);

}
