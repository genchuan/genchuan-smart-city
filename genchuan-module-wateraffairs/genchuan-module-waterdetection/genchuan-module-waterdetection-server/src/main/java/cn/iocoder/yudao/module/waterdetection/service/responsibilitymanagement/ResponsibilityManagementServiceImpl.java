package cn.iocoder.yudao.module.waterdetection.service.responsibilitymanagement;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.responsibilitymanagement.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.responsibilitymanagement.ResponsibilityManagementDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.responsibilitymanagement.ResponsibilityManagementMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 责任单位及责任人管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class ResponsibilityManagementServiceImpl implements ResponsibilityManagementService {

    @Resource
    private ResponsibilityManagementMapper responsibilityManagementMapper;

    @Override
    public Long createResponsibilityManagement(ResponsibilityManagementSaveReqVO createReqVO) {
        // 插入
        ResponsibilityManagementDO responsibilityManagement = BeanUtils.toBean(createReqVO, ResponsibilityManagementDO.class);
        responsibilityManagementMapper.insert(responsibilityManagement);
        // 返回
        return responsibilityManagement.getId();
    }

    @Override
    public void updateResponsibilityManagement(ResponsibilityManagementSaveReqVO updateReqVO) {
        // 校验存在
        validateResponsibilityManagementExists(updateReqVO.getId());
        // 更新
        ResponsibilityManagementDO updateObj = BeanUtils.toBean(updateReqVO, ResponsibilityManagementDO.class);
        responsibilityManagementMapper.updateById(updateObj);
    }

    @Override
    public void deleteResponsibilityManagement(Long id) {
        // 校验存在
        validateResponsibilityManagementExists(id);
        // 删除
        responsibilityManagementMapper.deleteById(id);
    }

    private void validateResponsibilityManagementExists(Long id) {
        if (responsibilityManagementMapper.selectById(id) == null) {
            throw exception(RESPONSIBILITY_MANAGEMENT_NOT_EXISTS);
        }
    }

    @Override
    public ResponsibilityManagementDO getResponsibilityManagement(Long id) {
        return responsibilityManagementMapper.selectById(id);
    }

    @Override
    public PageResult<ResponsibilityManagementDO> getResponsibilityManagementPage(ResponsibilityManagementPageReqVO pageReqVO) {
        return responsibilityManagementMapper.selectPage(pageReqVO);
    }

}