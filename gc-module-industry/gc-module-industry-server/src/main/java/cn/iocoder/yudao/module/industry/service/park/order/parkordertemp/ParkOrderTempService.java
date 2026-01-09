package cn.iocoder.yudao.module.industry.service.park.order.parkordertemp;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkordertemp.vo.ParkOrderTempPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkordertemp.vo.ParkOrderTempSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkordertemp.ParkOrderTempDO;
import jakarta.validation.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 临停订单 Service 接口
 *
 * @author lxs
 */
public interface ParkOrderTempService {

    /**
     * 创建临停订单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkOrderTemp(@Valid ParkOrderTempSaveReqVO createReqVO);

    /**
     * 更新临停订单
     *
     * @param updateReqVO 更新信息
     */
    void updateParkOrderTemp(@Valid ParkOrderTempSaveReqVO updateReqVO);

    /**
     * 删除临停订单
     *
     * @param id 编号
     */
    void deleteParkOrderTemp(Long id);

    /**
     * 获得临停订单
     *
     * @param id 编号
     * @return 临停订单
     */
    ParkOrderTempDO getParkOrderTemp(Long id);

    /**
     * 获得临停订单分页
     *
     * @param pageReqVO 分页查询
     * @return 临停订单分页
     */
    PageResult<ParkOrderTempDO> getParkOrderTempPage(ParkOrderTempPageReqVO pageReqVO);

}
