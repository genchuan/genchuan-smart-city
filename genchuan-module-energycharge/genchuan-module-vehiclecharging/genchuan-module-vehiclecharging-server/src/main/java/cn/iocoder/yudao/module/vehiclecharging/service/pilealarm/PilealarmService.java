package cn.iocoder.yudao.module.vehiclecharging.service.pilealarm;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.pilealarm.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.pilealarm.PilealarmDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 充电桩告警 Service 接口
 *
 * @author 亘川智城
 */
public interface PilealarmService {

    /**
     * 创建充电桩告警
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    String createPilealarm(@Valid PilealarmSaveReqVO createReqVO);

    /**
     * 更新充电桩告警
     *
     * @param updateReqVO 更新信息
     */
    void updatePilealarm(@Valid PilealarmSaveReqVO updateReqVO);

    /**
     * 删除充电桩告警
     *
     * @param id 编号
     */
    void deletePilealarm(String id);

    /**
     * 批量删除充电桩告警
     *
     * @param ids 编号
     */
    void deletePilealarmListByIds(List<String> ids);

    /**
     * 获得充电桩告警
     *
     * @param id 编号
     * @return 充电桩告警
     */
    PilealarmDO getPilealarm(String id);

    /**
     * 获得充电桩告警分页
     *
     * @param pageReqVO 分页查询
     * @return 充电桩告警分页
     */
    PageResult<PilealarmDO> getPilealarmPage(PilealarmPageReqVO pageReqVO);

}