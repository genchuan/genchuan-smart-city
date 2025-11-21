package cn.iocoder.yudao.module.datacenter.service.operationguide.systemfunctionguide;

import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.systemfunctionguide.vo.SystemFunctionGuidePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.systemfunctionguide.vo.SystemFunctionGuideSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.operationguide.systemfunctionguide.SystemFunctionGuideDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.operationguide.systemfunctionguide.SystemFunctionGuideMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 系统功能指南 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class SystemFunctionGuideServiceImpl implements SystemFunctionGuideService {

    @Resource
    private SystemFunctionGuideMapper systemFunctionGuideMapper;

    @Override
    public Long createSystemFunctionGuide(SystemFunctionGuideSaveReqVO createReqVO) {
        // 插入
        SystemFunctionGuideDO systemFunctionGuide = BeanUtils.toBean(createReqVO, SystemFunctionGuideDO.class);
        systemFunctionGuideMapper.insert(systemFunctionGuide);
        // 返回
        return systemFunctionGuide.getId();
    }

    @Override
    public void updateSystemFunctionGuide(SystemFunctionGuideSaveReqVO updateReqVO) {
        // 校验存在
        validateSystemFunctionGuideExists(updateReqVO.getId());
        // 更新
        SystemFunctionGuideDO updateObj = BeanUtils.toBean(updateReqVO, SystemFunctionGuideDO.class);
        systemFunctionGuideMapper.updateById(updateObj);
    }

    @Override
    public void deleteSystemFunctionGuide(Long id) {
        // 校验存在
        validateSystemFunctionGuideExists(id);
        // 删除
        systemFunctionGuideMapper.deleteById(id);
    }

    private void validateSystemFunctionGuideExists(Long id) {
        if (systemFunctionGuideMapper.selectById(id) == null) {
            throw exception(SYSTEM_FUNCTION_GUIDE_NOT_EXISTS);
        }
    }

    @Override
    public SystemFunctionGuideDO getSystemFunctionGuide(Long id) {
        return systemFunctionGuideMapper.selectById(id);
    }

    @Override
    public PageResult<SystemFunctionGuideDO> getSystemFunctionGuidePage(SystemFunctionGuidePageReqVO pageReqVO) {
        return systemFunctionGuideMapper.selectPage(pageReqVO);
    }

}