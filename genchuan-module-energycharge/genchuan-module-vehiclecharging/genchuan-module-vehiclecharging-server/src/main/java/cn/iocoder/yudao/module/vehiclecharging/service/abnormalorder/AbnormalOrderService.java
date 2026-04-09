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
    /**
     * 批量完成异常订单（仅已处理可完成）
     * @param reqVO 完成参数
     * @return 操作结果
     */
    Boolean completeAbnormalOrder(AbnormalOrderCompleteReqVO reqVO);
    /**
     * 批量退款异常订单（仅已完成可退款）
     * @param reqVO 退款参数
     * @return 操作结果
     */
    Boolean refundAbnormalOrder(AbnormalOrderRefundReqVO reqVO);
    /**
     * 修改异常订单备注
     * @param reqVO 修改参数
     */
    void updateAbnormalOrderRemark(AbnormalOrderRemarkReqVO reqVO);
    /**
     * 异常订单统计图表（柱状图+饼图+卡片）
     * @param reqVO 统计参数
     * @return 统计结果
     */
    AbnormalOrderChartRespVO getAbnormalOrderChart(AbnormalOrderChartReqVO reqVO);
    /**
     * 获取异常订单日统计
     * @param reqVO 统计参数
     * @return 统计结果
     */
    List<AbnormalOrderDailyCountRespVO> getAbnormalOrderDailyCount(AbnormalOrderDailyCountReqVO reqVO);
    /**
     * 获取异常订单类型占比
     * @param reqVO 统计参数
     * @return 统计结果
     */
    List<AbnormalOrderTypeRatioRespVO> getAbnormalOrderTypeRatio(AbnormalOrderDailyCountReqVO reqVO);

    /**
     * 获取异常订单处理数量
     * @param reqVO 统计参数
     * @return 统计结果
     */
    AbnormalOrderHandleCountRespVO getAbnormalOrderHandleCount(AbnormalOrderDailyCountReqVO reqVO);

}