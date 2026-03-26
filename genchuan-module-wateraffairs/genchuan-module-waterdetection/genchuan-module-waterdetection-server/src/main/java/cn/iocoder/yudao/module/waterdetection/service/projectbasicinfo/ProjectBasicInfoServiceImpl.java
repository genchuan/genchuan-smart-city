package cn.iocoder.yudao.module.waterdetection.service.projectbasicinfo;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.projectbasicinfo.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.projectbasicinfo.ProjectBasicInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.projectbasicinfo.ProjectBasicInfoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 工程基本信息管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class ProjectBasicInfoServiceImpl implements ProjectBasicInfoService {

    @Resource
    private ProjectBasicInfoMapper projectBasicInfoMapper;

    @Override
    public Long createProjectBasicInfo(ProjectBasicInfoSaveReqVO createReqVO) {
        // 插入
        ProjectBasicInfoDO projectBasicInfo = BeanUtils.toBean(createReqVO, ProjectBasicInfoDO.class);
        projectBasicInfoMapper.insert(projectBasicInfo);
        // 返回
        return projectBasicInfo.getId();
    }

    @Override
    public void updateProjectBasicInfo(ProjectBasicInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateProjectBasicInfoExists(updateReqVO.getId());
        // 更新
        ProjectBasicInfoDO updateObj = BeanUtils.toBean(updateReqVO, ProjectBasicInfoDO.class);
        projectBasicInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteProjectBasicInfo(Long id) {
        // 校验存在
        validateProjectBasicInfoExists(id);
        // 删除
        projectBasicInfoMapper.deleteById(id);
    }

    private void validateProjectBasicInfoExists(Long id) {
        if (projectBasicInfoMapper.selectById(id) == null) {
            throw exception(PROJECT_BASIC_INFO_NOT_EXISTS);
        }
    }

    @Override
    public ProjectBasicInfoDO getProjectBasicInfo(Long id) {
        return projectBasicInfoMapper.selectById(id);
    }

    @Override
    public PageResult<ProjectBasicInfoDO> getProjectBasicInfoPage(ProjectBasicInfoPageReqVO pageReqVO) {
        return projectBasicInfoMapper.selectPage(pageReqVO);
    }

}