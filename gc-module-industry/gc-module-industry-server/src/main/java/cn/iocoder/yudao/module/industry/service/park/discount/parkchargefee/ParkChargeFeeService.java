package cn.iocoder.yudao.module.industry.service.park.discount.parkchargefee;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkchargefee.vo.ParkChargeFeePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkchargefee.vo.ParkChargeFeeSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkchargefee.ParkChargeFeeDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 充电收费 Service 接口
 *
 * @author lxs
 */
public interface ParkChargeFeeService {

    /**
     * 创建充电收费
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkChargeFee(@Valid ParkChargeFeeSaveReqVO createReqVO);

    /**
     * 更新充电收费
     *
     * @param updateReqVO 更新信息
     */
    void updateParkChargeFee(@Valid ParkChargeFeeSaveReqVO updateReqVO);

    /**
     * 删除充电收费
     *
     * @param id 编号
     */
    void deleteParkChargeFee(Long id);

    /**
     * 获得充电收费
     *
     * @param id 编号
     * @return 充电收费
     */
    ParkChargeFeeDO getParkChargeFee(Long id);

    /**
     * 获得充电收费分页
     *
     * @param pageReqVO 分页查询
     * @return 充电收费分页
     */
    PageResult<ParkChargeFeeDO> getParkChargeFeePage(ParkChargeFeePageReqVO pageReqVO);

}
