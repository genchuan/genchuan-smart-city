package cn.iocoder.yudao.module.industry.service.park.pay.parkrealtime;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkrealtime.vo.ParkRealTimePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkrealtime.vo.ParkRealTimeSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkrealtime.ParkRealTimeDO;
import jakarta.validation.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 停车泊位实时状态 Service 接口
 *
 * @author lxs
 */
public interface ParkRealTimeService {

    /**
     * 创建停车泊位实时状态
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkRealTime(@Valid ParkRealTimeSaveReqVO createReqVO);

    /**
     * 更新停车泊位实时状态
     *
     * @param updateReqVO 更新信息
     */
    void updateParkRealTime(@Valid ParkRealTimeSaveReqVO updateReqVO);

    /**
     * 删除停车泊位实时状态
     *
     * @param id 编号
     */
    void deleteParkRealTime(Long id);

    /**
     * 获得停车泊位实时状态
     *
     * @param id 编号
     * @return 停车泊位实时状态
     */
    ParkRealTimeDO getParkRealTime(Long id);

    /**
     * 获得停车泊位实时状态分页
     *
     * @param pageReqVO 分页查询
     * @return 停车泊位实时状态分页
     */
    PageResult<ParkRealTimeDO> getParkRealTimePage(ParkRealTimePageReqVO pageReqVO);

}
