package cn.iocoder.yudao.module.chargepark.carservice.service.findcar;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo.PathPlanPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo.PathPlanSaveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.findcar.PathPlanDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.findcar.PathPlanMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.carservice.enums.ErrorCodeConstants.PATH_PLAN_NOT_EXISTS;

/**
 * 路径规划 Service 实现类
 *
 * @author carservice
 */
@Service
@Validated
public class PathPlanServiceImpl implements PathPlanService {

    @Resource
    private PathPlanMapper pathPlanMapper;

    @Override
    public Long createPathPlan(PathPlanSaveReqVO createReqVO) {
        PathPlanDO pathPlan = BeanUtils.toBean(createReqVO, PathPlanDO.class);
        if (pathPlan.getPlanTime() == null) {
            pathPlan.setPlanTime(LocalDateTime.now());
        }
        pathPlanMapper.insert(pathPlan);
        return pathPlan.getId();
    }

    @Override
    public void updatePathPlan(PathPlanSaveReqVO updateReqVO) {
        validatePathPlanExists(updateReqVO.getId());
        PathPlanDO updateObj = BeanUtils.toBean(updateReqVO, PathPlanDO.class);
        pathPlanMapper.updateById(updateObj);
    }

    @Override
    public void deletePathPlan(Long id) {
        validatePathPlanExists(id);
        pathPlanMapper.deleteById(id);
    }

    @Override
    public void deletePathPlanListByIds(List<Long> ids) {
        pathPlanMapper.deleteByIds(ids);
    }

    private void validatePathPlanExists(Long id) {
        if (pathPlanMapper.selectById(id) == null) {
            throw exception(PATH_PLAN_NOT_EXISTS);
        }
    }

    @Override
    public PathPlanDO getPathPlan(Long id) {
        return pathPlanMapper.selectById(id);
    }

    @Override
    public PageResult<PathPlanDO> getPathPlanPage(PathPlanPageReqVO pageReqVO) {
        return pathPlanMapper.selectPage(pageReqVO);
    }

}
