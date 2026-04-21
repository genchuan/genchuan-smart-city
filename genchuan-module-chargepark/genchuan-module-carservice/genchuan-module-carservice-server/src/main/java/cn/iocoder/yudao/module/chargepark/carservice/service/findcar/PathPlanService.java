package cn.iocoder.yudao.module.chargepark.carservice.service.findcar;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo.PathPlanPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo.PathPlanSaveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.findcar.PathPlanDO;

import java.util.List;

/**
 * 路径规划 Service 接口
 *
 * @author carservice
 */
public interface PathPlanService {

    Long createPathPlan(PathPlanSaveReqVO createReqVO);

    void updatePathPlan(PathPlanSaveReqVO updateReqVO);

    void deletePathPlan(Long id);

    void deletePathPlanListByIds(List<Long> ids);

    PathPlanDO getPathPlan(Long id);

    PageResult<PathPlanDO> getPathPlanPage(PathPlanPageReqVO pageReqVO);

}
