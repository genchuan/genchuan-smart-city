package cn.iocoder.yudao.module.smartcity.service.dynamicinformationclassification;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.dynamicinformationclassification.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.dynamicinformationclassification.DynamicInformationClassificationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.dynamicinformationclassification.DynamicInformationClassificationMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 动态信息分类 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class DynamicInformationClassificationServiceImpl implements DynamicInformationClassificationService {

    @Resource
    private DynamicInformationClassificationMapper dynamicInformationClassificationMapper;

    @Override
    public Long createDynamicInformationClassification(DynamicInformationClassificationSaveReqVO createReqVO) {
        // 插入
        DynamicInformationClassificationDO dynamicInformationClassification = BeanUtils.toBean(createReqVO, DynamicInformationClassificationDO.class);
        dynamicInformationClassificationMapper.insert(dynamicInformationClassification);
        // 返回
        return dynamicInformationClassification.getId();
    }

    @Override
    public void updateDynamicInformationClassification(DynamicInformationClassificationSaveReqVO updateReqVO) {
        // 校验存在
        validateDynamicInformationClassificationExists(updateReqVO.getId());
        // 更新
        DynamicInformationClassificationDO updateObj = BeanUtils.toBean(updateReqVO, DynamicInformationClassificationDO.class);
        dynamicInformationClassificationMapper.updateById(updateObj);
    }

    @Override
    public void deleteDynamicInformationClassification(Long id) {
        // 校验存在
        validateDynamicInformationClassificationExists(id);
        // 删除
        dynamicInformationClassificationMapper.deleteById(id);
    }

    private void validateDynamicInformationClassificationExists(Long id) {
        if (dynamicInformationClassificationMapper.selectById(id) == null) {
            throw exception(DYNAMIC_INFORMATION_CLASSIFICATION_NOT_EXISTS);
        }
    }

    @Override
    public DynamicInformationClassificationDO getDynamicInformationClassification(Long id) {
        return dynamicInformationClassificationMapper.selectById(id);
    }

    @Override
    public PageResult<DynamicInformationClassificationDO> getDynamicInformationClassificationPage(DynamicInformationClassificationPageReqVO pageReqVO) {
        return dynamicInformationClassificationMapper.selectPage(pageReqVO);
    }

}