package cn.iocoder.yudao.module.industry.service.park.vas.parkchargereservation;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkchargereservation.vo.ParkChargeReservationPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkchargereservation.vo.ParkChargeReservationSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkchargereservation.ParkChargeReservationDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 充电预约 Service 接口
 *
 * @author lxs
 */
public interface ParkChargeReservationService {

    /**
     * 创建充电预约
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkChargeReservation(@Valid ParkChargeReservationSaveReqVO createReqVO);

    /**
     * 更新充电预约
     *
     * @param updateReqVO 更新信息
     */
    void updateParkChargeReservation(@Valid ParkChargeReservationSaveReqVO updateReqVO);

    /**
     * 删除充电预约
     *
     * @param id 编号
     */
    void deleteParkChargeReservation(Long id);

    /**
     * 获得充电预约
     *
     * @param id 编号
     * @return 充电预约
     */
    ParkChargeReservationDO getParkChargeReservation(Long id);

    /**
     * 获得充电预约分页
     *
     * @param pageReqVO 分页查询
     * @return 充电预约分页
     */
    PageResult<ParkChargeReservationDO> getParkChargeReservationPage(ParkChargeReservationPageReqVO pageReqVO);

}
