package cn.iocoder.yudao.module.facility.service.manhole.disposalorder;

import jakarta.validation.*;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.disposalorder.vo.*;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.disposalorder.DisposalOrderDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 处置工单 Service 接口
 *
 * @author 亘川智城
 */
public interface DisposalOrderService {

    /**
     * 创建处置工单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOrder(@Valid DisposalOrderSaveReqVO createReqVO);

    /**
     * 更新处置工单
     *
     * @param updateReqVO 更新信息
     */
    void updateOrder(@Valid DisposalOrderSaveReqVO updateReqVO);

    /**
     * 删除处置工单
     *
     * @param id 编号
     */
    void deleteOrder(Long id);

    /**
     * 获得处置工单
     *
     * @param id 编号
     * @return 处置工单
     */
    DisposalOrderDO getOrder(Long id);

    /**
     * 获得处置工单分页
     *
     * @param pageReqVO 分页查询
     * @return 处置工单分页
     */
    PageResult<DisposalOrderDO> getOrderPage(DisposalOrderPageReqVO pageReqVO);

    /**
     * 获取工单列表
     *
     * @param reqVO 列表查询
     * @return 工单列表
     */
    PageResult<ManholeCoverRepairOrderPageRespVO> page(ManholeCoverRepairOrderPageReqVO reqVO);

}