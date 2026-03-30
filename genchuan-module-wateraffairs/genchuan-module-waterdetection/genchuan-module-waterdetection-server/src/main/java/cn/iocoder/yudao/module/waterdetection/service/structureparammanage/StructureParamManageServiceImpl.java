package cn.iocoder.yudao.module.waterdetection.service.structureparammanage;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.structureparammanage.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.structureparammanage.StructureParamManageDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.structureparammanage.StructureParamManageMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 构建筑物参数管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class StructureParamManageServiceImpl implements StructureParamManageService {

    @Resource
    private StructureParamManageMapper structureParamManageMapper;

    @Override
    public Long createStructureParamManage(StructureParamManageSaveReqVO createReqVO) {
        // 插入
        StructureParamManageDO structureParamManage = BeanUtils.toBean(createReqVO, StructureParamManageDO.class);
        structureParamManageMapper.insert(structureParamManage);
        // 返回
        return structureParamManage.getId();
    }

    @Override
    public void updateStructureParamManage(StructureParamManageSaveReqVO updateReqVO) {
        // 校验存在
        validateStructureParamManageExists(updateReqVO.getId());
        // 更新
        StructureParamManageDO updateObj = BeanUtils.toBean(updateReqVO, StructureParamManageDO.class);
        structureParamManageMapper.updateById(updateObj);
    }

    @Override
    public void deleteStructureParamManage(Long id) {
        // 校验存在
        validateStructureParamManageExists(id);
        // 删除
        structureParamManageMapper.deleteById(id);
    }

    private void validateStructureParamManageExists(Long id) {
        if (structureParamManageMapper.selectById(id) == null) {
            throw exception(STRUCTURE_PARAM_MANAGE_NOT_EXISTS);
        }
    }

    @Override
    public StructureParamManageDO getStructureParamManage(Long id) {
        return structureParamManageMapper.selectById(id);
    }

    @Override
    public PageResult<StructureParamManageDO> getStructureParamManagePage(StructureParamManagePageReqVO pageReqVO) {
        return structureParamManageMapper.selectPage(pageReqVO);
    }

}