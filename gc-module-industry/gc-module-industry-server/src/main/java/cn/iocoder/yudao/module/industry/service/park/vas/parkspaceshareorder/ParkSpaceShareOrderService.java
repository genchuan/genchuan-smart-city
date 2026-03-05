package cn.iocoder.yudao.module.industry.service.park.vas.parkspaceshareorder;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkspaceshareorder.vo.ParkSpaceShareOrderPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkspaceshareorder.vo.ParkSpaceShareOrderSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkspaceshareorder.ParkSpaceShareOrderDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 车位共享订单 Service 接口
 *
 * @author lxs
 */
public interface ParkSpaceShareOrderService {

    /**
     * 创建车位共享订单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkSpaceShareOrder(@Valid ParkSpaceShareOrderSaveReqVO createReqVO);

    /**
     * 更新车位共享订单
     *
     * @param updateReqVO 更新信息
     */
    void updateParkSpaceShareOrder(@Valid ParkSpaceShareOrderSaveReqVO updateReqVO);

    /**
     * 删除车位共享订单
     *
     * @param id 编号
     */
    void deleteParkSpaceShareOrder(Long id);

    /**
     * 获得车位共享订单
     *
     * @param id 编号
     * @return 车位共享订单
     */
    ParkSpaceShareOrderDO getParkSpaceShareOrder(Long id);

    /**
     * 获得车位共享订单分页
     *
     * @param pageReqVO 分页查询
     * @return 车位共享订单分页
     */
    PageResult<ParkSpaceShareOrderDO> getParkSpaceShareOrderPage(ParkSpaceShareOrderPageReqVO pageReqVO);

}
