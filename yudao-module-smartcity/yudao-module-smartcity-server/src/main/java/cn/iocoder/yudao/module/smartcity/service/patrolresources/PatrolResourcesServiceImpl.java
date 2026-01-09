package cn.iocoder.yudao.module.smartcity.service.patrolresources;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.patrolresources.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.patrolresources.PatrolResourcesDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.patrolresources.PatrolResourcesMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 巡查资源 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class PatrolResourcesServiceImpl implements PatrolResourcesService {

    @Resource
    private PatrolResourcesMapper patrolResourcesMapper;

    @Override
    public Long createPatrolResources(PatrolResourcesSaveReqVO createReqVO) {
        // 插入
        PatrolResourcesDO patrolResources = BeanUtils.toBean(createReqVO, PatrolResourcesDO.class);
        patrolResourcesMapper.insert(patrolResources);
        // 返回
        return patrolResources.getId();
    }

    @Override
    public void updatePatrolResources(PatrolResourcesSaveReqVO updateReqVO) {
        // 校验存在
        validatePatrolResourcesExists(updateReqVO.getId());
        // 更新
        PatrolResourcesDO updateObj = BeanUtils.toBean(updateReqVO, PatrolResourcesDO.class);
        patrolResourcesMapper.updateById(updateObj);
    }

    @Override
    public void deletePatrolResources(Long id) {
        // 校验存在
        validatePatrolResourcesExists(id);
        // 删除
        patrolResourcesMapper.deleteById(id);
    }

    private void validatePatrolResourcesExists(Long id) {
        if (patrolResourcesMapper.selectById(id) == null) {
            throw exception(PATROL_RESOURCES_NOT_EXISTS);
        }
    }

    @Override
    public PatrolResourcesDO getPatrolResources(Long id) {
        return patrolResourcesMapper.selectById(id);
    }

    @Override
    public PageResult<PatrolResourcesDO> getPatrolResourcesPage(PatrolResourcesPageReqVO pageReqVO) {
        return patrolResourcesMapper.selectPage(pageReqVO);
    }

}