package cn.iocoder.yudao.module.industry.service.park.guidance.parkguidance;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.guidance.parkguidance.vo.ParkGuidancePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.guidance.parkguidance.vo.ParkGuidanceSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.guidance.parkguidance.ParkGuidanceDO;
import jakarta.validation.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 停车诱导服务 Service 接口
 *
 * @author lxs
 */
public interface ParkGuidanceService {

    /**
     * 创建停车诱导服务
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkGuidance(@Valid ParkGuidanceSaveReqVO createReqVO);

    /**
     * 更新停车诱导服务
     *
     * @param updateReqVO 更新信息
     */
    void updateParkGuidance(@Valid ParkGuidanceSaveReqVO updateReqVO);

    /**
     * 删除停车诱导服务
     *
     * @param id 编号
     */
    void deleteParkGuidance(Long id);

    /**
     * 获得停车诱导服务
     *
     * @param id 编号
     * @return 停车诱导服务
     */
    ParkGuidanceDO getParkGuidance(Long id);

    /**
     * 获得停车诱导服务分页
     *
     * @param pageReqVO 分页查询
     * @return 停车诱导服务分页
     */
    PageResult<ParkGuidanceDO> getParkGuidancePage(ParkGuidancePageReqVO pageReqVO);

}
