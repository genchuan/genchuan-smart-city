package cn.iocoder.yudao.module.smartcity.service.classificationofguideinformation;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.classificationofguideinformation.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.classificationofguideinformation.ClassificationOfGuideInformationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.classificationofguideinformation.ClassificationOfGuideInformationMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 指南信息分类 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class ClassificationOfGuideInformationServiceImpl implements ClassificationOfGuideInformationService {

    @Resource
    private ClassificationOfGuideInformationMapper classificationOfGuideInformationMapper;

    @Override
    public Long createClassificationOfGuideInformation(ClassificationOfGuideInformationSaveReqVO createReqVO) {
        // 插入
        ClassificationOfGuideInformationDO classificationOfGuideInformation = BeanUtils.toBean(createReqVO, ClassificationOfGuideInformationDO.class);
        classificationOfGuideInformationMapper.insert(classificationOfGuideInformation);
        // 返回
        return classificationOfGuideInformation.getId();
    }

    @Override
    public void updateClassificationOfGuideInformation(ClassificationOfGuideInformationSaveReqVO updateReqVO) {
        // 校验存在
        validateClassificationOfGuideInformationExists(updateReqVO.getId());
        // 更新
        ClassificationOfGuideInformationDO updateObj = BeanUtils.toBean(updateReqVO, ClassificationOfGuideInformationDO.class);
        classificationOfGuideInformationMapper.updateById(updateObj);
    }

    @Override
    public void deleteClassificationOfGuideInformation(Long id) {
        // 校验存在
        validateClassificationOfGuideInformationExists(id);
        // 删除
        classificationOfGuideInformationMapper.deleteById(id);
    }

    private void validateClassificationOfGuideInformationExists(Long id) {
        if (classificationOfGuideInformationMapper.selectById(id) == null) {
            throw exception(CLASSIFICATION_OF_GUIDE_INFORMATION_NOT_EXISTS);
        }
    }

    @Override
    public ClassificationOfGuideInformationDO getClassificationOfGuideInformation(Long id) {
        return classificationOfGuideInformationMapper.selectById(id);
    }

    @Override
    public PageResult<ClassificationOfGuideInformationDO> getClassificationOfGuideInformationPage(ClassificationOfGuideInformationPageReqVO pageReqVO) {
        return classificationOfGuideInformationMapper.selectPage(pageReqVO);
    }

}