package cn.iocoder.yudao.module.waterdetection.service.warningmodelvalidation;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.warningmodelvalidation.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.warningmodelvalidation.WarningModelValidationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.warningmodelvalidation.WarningModelValidationMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 预警模型校验 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class WarningModelValidationServiceImpl implements WarningModelValidationService {

    @Resource
    private WarningModelValidationMapper warningModelValidationMapper;

    @Override
    public Long createWarningModelValidation(WarningModelValidationSaveReqVO createReqVO) {
        // 插入
        WarningModelValidationDO warningModelValidation = BeanUtils.toBean(createReqVO, WarningModelValidationDO.class);
        warningModelValidationMapper.insert(warningModelValidation);
        // 返回
        return warningModelValidation.getId();
    }

    @Override
    public void updateWarningModelValidation(WarningModelValidationSaveReqVO updateReqVO) {
        // 校验存在
        validateWarningModelValidationExists(updateReqVO.getId());
        // 更新
        WarningModelValidationDO updateObj = BeanUtils.toBean(updateReqVO, WarningModelValidationDO.class);
        warningModelValidationMapper.updateById(updateObj);
    }

    @Override
    public void deleteWarningModelValidation(Long id) {
        // 校验存在
        validateWarningModelValidationExists(id);
        // 删除
        warningModelValidationMapper.deleteById(id);
    }

    private void validateWarningModelValidationExists(Long id) {
        if (warningModelValidationMapper.selectById(id) == null) {
            throw exception(WARNING_MODEL_VALIDATION_NOT_EXISTS);
        }
    }

    @Override
    public WarningModelValidationDO getWarningModelValidation(Long id) {
        return warningModelValidationMapper.selectById(id);
    }

    @Override
    public PageResult<WarningModelValidationDO> getWarningModelValidationPage(WarningModelValidationPageReqVO pageReqVO) {
        return warningModelValidationMapper.selectPage(pageReqVO);
    }

}