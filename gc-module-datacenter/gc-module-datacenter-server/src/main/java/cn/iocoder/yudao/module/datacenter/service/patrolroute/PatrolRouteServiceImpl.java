package cn.iocoder.yudao.module.datacenter.service.patrolroute;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.patrolroute.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.patrolroute.PatrolRouteDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.patrolroute.PatrolRouteMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 巡查路线 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class PatrolRouteServiceImpl implements PatrolRouteService {

    @Resource
    private PatrolRouteMapper patrolRouteMapper;

    @Override
    public Long createPatrolRoute(PatrolRouteSaveReqVO createReqVO) {
        // 插入
        PatrolRouteDO patrolRoute = BeanUtils.toBean(createReqVO, PatrolRouteDO.class);
        patrolRouteMapper.insert(patrolRoute);
        // 返回
        return patrolRoute.getId();
    }

    @Override
    public void updatePatrolRoute(PatrolRouteSaveReqVO updateReqVO) {
        // 校验存在
        validatePatrolRouteExists(updateReqVO.getId());
        // 更新
        PatrolRouteDO updateObj = BeanUtils.toBean(updateReqVO, PatrolRouteDO.class);
        patrolRouteMapper.updateById(updateObj);
    }

    @Override
    public void deletePatrolRoute(Long id) {
        // 校验存在
        validatePatrolRouteExists(id);
        // 删除
        patrolRouteMapper.deleteById(id);
    }

    private void validatePatrolRouteExists(Long id) {
        if (patrolRouteMapper.selectById(id) == null) {
            throw exception(PATROL_ROUTE_NOT_EXISTS);
        }
    }

    @Override
    public PatrolRouteDO getPatrolRoute(Long id) {
        return patrolRouteMapper.selectById(id);
    }

    @Override
    public PageResult<PatrolRouteDO> getPatrolRoutePage(PatrolRoutePageReqVO pageReqVO) {
        return patrolRouteMapper.selectPage(pageReqVO);
    }

}