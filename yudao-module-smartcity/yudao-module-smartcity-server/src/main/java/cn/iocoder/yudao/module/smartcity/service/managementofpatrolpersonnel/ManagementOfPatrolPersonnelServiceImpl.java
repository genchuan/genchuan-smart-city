package cn.iocoder.yudao.module.smartcity.service.managementofpatrolpersonnel;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.managementofpatrolpersonnel.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.managementofpatrolpersonnel.ManagementOfPatrolPersonnelDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.managementofpatrolpersonnel.ManagementOfPatrolPersonnelMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 巡査人员管理 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class ManagementOfPatrolPersonnelServiceImpl implements ManagementOfPatrolPersonnelService {

    @Resource
    private ManagementOfPatrolPersonnelMapper managementOfPatrolPersonnelMapper;

    @Override
    public Long createManagementOfPatrolPersonnel(ManagementOfPatrolPersonnelSaveReqVO createReqVO) {
        // 插入
        ManagementOfPatrolPersonnelDO managementOfPatrolPersonnel = BeanUtils.toBean(createReqVO, ManagementOfPatrolPersonnelDO.class);
        managementOfPatrolPersonnelMapper.insert(managementOfPatrolPersonnel);
        // 返回
        return managementOfPatrolPersonnel.getId();
    }

    @Override
    public void updateManagementOfPatrolPersonnel(ManagementOfPatrolPersonnelSaveReqVO updateReqVO) {
        // 校验存在
        validateManagementOfPatrolPersonnelExists(updateReqVO.getId());
        // 更新
        ManagementOfPatrolPersonnelDO updateObj = BeanUtils.toBean(updateReqVO, ManagementOfPatrolPersonnelDO.class);
        managementOfPatrolPersonnelMapper.updateById(updateObj);
    }

    @Override
    public void deleteManagementOfPatrolPersonnel(Long id) {
        // 校验存在
        validateManagementOfPatrolPersonnelExists(id);
        // 删除
        managementOfPatrolPersonnelMapper.deleteById(id);
    }

    private void validateManagementOfPatrolPersonnelExists(Long id) {
        if (managementOfPatrolPersonnelMapper.selectById(id) == null) {
            throw exception(MANAGEMENT_OF_PATROL_PERSONNEL_NOT_EXISTS);
        }
    }

    @Override
    public ManagementOfPatrolPersonnelDO getManagementOfPatrolPersonnel(Long id) {
        return managementOfPatrolPersonnelMapper.selectById(id);
    }

    @Override
    public PageResult<ManagementOfPatrolPersonnelDO> getManagementOfPatrolPersonnelPage(ManagementOfPatrolPersonnelPageReqVO pageReqVO) {
        return managementOfPatrolPersonnelMapper.selectPage(pageReqVO);
    }

}