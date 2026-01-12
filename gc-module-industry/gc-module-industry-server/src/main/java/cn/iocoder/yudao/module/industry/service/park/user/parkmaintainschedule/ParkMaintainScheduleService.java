package cn.iocoder.yudao.module.industry.service.park.user.parkmaintainschedule;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmaintainschedule.vo.ParkMaintainSchedulePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmaintainschedule.vo.ParkMaintainScheduleSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkmaintainschedule.ParkMaintainScheduleDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 运维排班 Service 接口
 *
 * @author lxs
 */
public interface ParkMaintainScheduleService {

    /**
     * 创建运维排班
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkMaintainSchedule(@Valid ParkMaintainScheduleSaveReqVO createReqVO);

    /**
     * 更新运维排班
     *
     * @param updateReqVO 更新信息
     */
    void updateParkMaintainSchedule(@Valid ParkMaintainScheduleSaveReqVO updateReqVO);

    /**
     * 删除运维排班
     *
     * @param id 编号
     */
    void deleteParkMaintainSchedule(Long id);

    /**
     * 获得运维排班
     *
     * @param id 编号
     * @return 运维排班
     */
    ParkMaintainScheduleDO getParkMaintainSchedule(Long id);

    /**
     * 获得运维排班分页
     *
     * @param pageReqVO 分页查询
     * @return 运维排班分页
     */
    PageResult<ParkMaintainScheduleDO> getParkMaintainSchedulePage(ParkMaintainSchedulePageReqVO pageReqVO);

}
