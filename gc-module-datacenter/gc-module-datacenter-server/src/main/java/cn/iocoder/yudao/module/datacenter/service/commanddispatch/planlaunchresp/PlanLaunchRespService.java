package cn.iocoder.yudao.module.datacenter.service.commanddispatch.planlaunchresp;

import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.planlaunchresp.vo.PlanLaunchRespPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.planlaunchresp.vo.PlanLaunchRespSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.commanddispatch.planlaunchresp.PlanLaunchRespDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 预案启动响应 Service 接口
 *
 * @author 亘川智城
 */
public interface PlanLaunchRespService {

    /**
     * 创建预案启动响应
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPlanLaunchResp(@Valid PlanLaunchRespSaveReqVO createReqVO);

    /**
     * 更新预案启动响应
     *
     * @param updateReqVO 更新信息
     */
    void updatePlanLaunchResp(@Valid PlanLaunchRespSaveReqVO updateReqVO);

    /**
     * 删除预案启动响应
     *
     * @param id 编号
     */
    void deletePlanLaunchResp(Long id);

    /**
     * 获得预案启动响应
     *
     * @param id 编号
     * @return 预案启动响应
     */
    PlanLaunchRespDO getPlanLaunchResp(Long id);

    /**
     * 获得预案启动响应分页
     *
     * @param pageReqVO 分页查询
     * @return 预案启动响应分页
     */
    PageResult<PlanLaunchRespDO> getPlanLaunchRespPage(PlanLaunchRespPageReqVO pageReqVO);

}