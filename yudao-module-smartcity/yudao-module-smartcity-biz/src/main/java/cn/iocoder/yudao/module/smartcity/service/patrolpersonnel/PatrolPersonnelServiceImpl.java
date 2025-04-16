package cn.iocoder.yudao.module.smartcity.service.patrolpersonnel;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.patrolpersonnel.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.patrolpersonnel.PatrolPersonnelDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.patrolpersonnel.PatrolPersonnelMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 巡查人员 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class PatrolPersonnelServiceImpl implements PatrolPersonnelService {

    @Resource
    private PatrolPersonnelMapper patrolPersonnelMapper;

    @Override
    public Long createPatrolPersonnel(PatrolPersonnelSaveReqVO createReqVO) {
        // 插入
        PatrolPersonnelDO patrolPersonnel = BeanUtils.toBean(createReqVO, PatrolPersonnelDO.class);
        patrolPersonnelMapper.insert(patrolPersonnel);
        // 返回
        return patrolPersonnel.getId();
    }

    @Override
    public void updatePatrolPersonnel(PatrolPersonnelSaveReqVO updateReqVO) {
        // 校验存在
        validatePatrolPersonnelExists(updateReqVO.getId());
        // 更新
        PatrolPersonnelDO updateObj = BeanUtils.toBean(updateReqVO, PatrolPersonnelDO.class);
        patrolPersonnelMapper.updateById(updateObj);
    }

    @Override
    public void deletePatrolPersonnel(Long id) {
        // 校验存在
        validatePatrolPersonnelExists(id);
        // 删除
        patrolPersonnelMapper.deleteById(id);
    }

    private void validatePatrolPersonnelExists(Long id) {
        if (patrolPersonnelMapper.selectById(id) == null) {
            throw exception(PATROL_PERSONNEL_NOT_EXISTS);
        }
    }

    @Override
    public PatrolPersonnelDO getPatrolPersonnel(Long id) {
        return patrolPersonnelMapper.selectById(id);
    }

    @Override
    public PageResult<PatrolPersonnelDO> getPatrolPersonnelPage(PatrolPersonnelPageReqVO pageReqVO) {
        return patrolPersonnelMapper.selectPage(pageReqVO);
    }

}