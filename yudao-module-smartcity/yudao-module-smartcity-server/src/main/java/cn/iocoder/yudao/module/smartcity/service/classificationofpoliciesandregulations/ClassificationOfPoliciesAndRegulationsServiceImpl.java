package cn.iocoder.yudao.module.smartcity.service.classificationofpoliciesandregulations;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.classificationofpoliciesandregulations.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.classificationofpoliciesandregulations.ClassificationOfPoliciesAndRegulationsDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.classificationofpoliciesandregulations.ClassificationOfPoliciesAndRegulationsMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 政策法规分类 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class ClassificationOfPoliciesAndRegulationsServiceImpl implements ClassificationOfPoliciesAndRegulationsService {

    @Resource
    private ClassificationOfPoliciesAndRegulationsMapper classificationOfPoliciesAndRegulationsMapper;

    @Override
    public Long createClassificationOfPoliciesAndRegulations(ClassificationOfPoliciesAndRegulationsSaveReqVO createReqVO) {
        // 插入
        ClassificationOfPoliciesAndRegulationsDO classificationOfPoliciesAndRegulations = BeanUtils.toBean(createReqVO, ClassificationOfPoliciesAndRegulationsDO.class);
        classificationOfPoliciesAndRegulationsMapper.insert(classificationOfPoliciesAndRegulations);
        // 返回
        return classificationOfPoliciesAndRegulations.getId();
    }

    @Override
    public void updateClassificationOfPoliciesAndRegulations(ClassificationOfPoliciesAndRegulationsSaveReqVO updateReqVO) {
        // 校验存在
        validateClassificationOfPoliciesAndRegulationsExists(updateReqVO.getId());
        // 更新
        ClassificationOfPoliciesAndRegulationsDO updateObj = BeanUtils.toBean(updateReqVO, ClassificationOfPoliciesAndRegulationsDO.class);
        classificationOfPoliciesAndRegulationsMapper.updateById(updateObj);
    }

    @Override
    public void deleteClassificationOfPoliciesAndRegulations(Long id) {
        // 校验存在
        validateClassificationOfPoliciesAndRegulationsExists(id);
        // 删除
        classificationOfPoliciesAndRegulationsMapper.deleteById(id);
    }

    private void validateClassificationOfPoliciesAndRegulationsExists(Long id) {
        if (classificationOfPoliciesAndRegulationsMapper.selectById(id) == null) {
            throw exception(CLASSIFICATION_OF_POLICIES_AND_REGULATIONS_NOT_EXISTS);
        }
    }

    @Override
    public ClassificationOfPoliciesAndRegulationsDO getClassificationOfPoliciesAndRegulations(Long id) {
        return classificationOfPoliciesAndRegulationsMapper.selectById(id);
    }

    @Override
    public PageResult<ClassificationOfPoliciesAndRegulationsDO> getClassificationOfPoliciesAndRegulationsPage(ClassificationOfPoliciesAndRegulationsPageReqVO pageReqVO) {
        return classificationOfPoliciesAndRegulationsMapper.selectPage(pageReqVO);
    }

}