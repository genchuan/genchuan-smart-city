package cn.iocoder.yudao.module.vehiclecharging.service.abnormalorder;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.abnormalorder.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.abnormalorder.AbnormalOrderDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 异常订单 Service 接口
 *
 * @author 亘川智城
 */
public interface AbnormalOrderService {

    /**
     * 创建异常订单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAbnormalOrder(@Valid AbnormalOrderSaveReqVO createReqVO);

    /**
     * 更新异常订单
     *
     * @param updateReqVO 更新信息
     */
    void updateAbnormalOrder(@Valid AbnormalOrderSaveReqVO updateReqVO);

    /**
     * 删除异常订单
     *
     * @param id 编号
     */
    void deleteAbnormalOrder(Long id);

    /**
     * 批量删除异常订单
     *
     * @param ids 编号
     */
    void deleteAbnormalOrderListByIds(List<Long> ids);

    /**
     * 获得异常订单
     *
     * @param id 编号
     * @return 异常订单
     */
    AbnormalOrderDO getAbnormalOrder(Long id);

    /**
     * 获得异常订单分页
     *
     * @param pageReqVO 分页查询
     * @return 异常订单分页
     */
    PageResult<AbnormalOrderDO> getAbnormalOrderPage(AbnormalOrderPageReqVO pageReqVO);

    /**
     * 获得异常订单分页
     *
     * @param reqVO 分页查询
     * @return 异常订单分页
     */
    PageResult<NewAbnormalOrderRespVO> newgetAbnormalOrderPage(NewAbnormalOrderPageReqVO reqVO);

    /**
     * 批量核实异常订单
     * @param reqVO 核实参数
     * @return 操作结果
     */
    Boolean verifyAbnormalOrder(AbnormalOrderVerifyReqVO reqVO);

    /**
     * 批量处理异常订单（仅已核实可处理）
     * @param reqVO 处理参数
     * @return 操作结果
     */
    Boolean handleAbnormalOrder(AbnormalOrderHandleReqVO reqVO);

}