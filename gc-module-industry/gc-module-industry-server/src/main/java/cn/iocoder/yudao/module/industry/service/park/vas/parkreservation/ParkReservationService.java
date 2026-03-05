package cn.iocoder.yudao.module.industry.service.park.vas.parkreservation;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkreservation.vo.ParkReservationPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkreservation.vo.ParkReservationSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkreservation.ParkReservationDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 停车预约 Service 接口
 *
 * @author lxs
 */
public interface ParkReservationService {

    /**
     * 创建停车预约
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkReservation(@Valid ParkReservationSaveReqVO createReqVO);

    /**
     * 更新停车预约
     *
     * @param updateReqVO 更新信息
     */
    void updateParkReservation(@Valid ParkReservationSaveReqVO updateReqVO);

    /**
     * 删除停车预约
     *
     * @param id 编号
     */
    void deleteParkReservation(Long id);

    /**
     * 获得停车预约
     *
     * @param id 编号
     * @return 停车预约
     */
    ParkReservationDO getParkReservation(Long id);

    /**
     * 获得停车预约分页
     *
     * @param pageReqVO 分页查询
     * @return 停车预约分页
     */
    PageResult<ParkReservationDO> getParkReservationPage(ParkReservationPageReqVO pageReqVO);

}
