package cn.iocoder.yudao.module.smartcity.service.classificationofexperienceinformation;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.classificationofexperienceinformation.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.classificationofexperienceinformation.ClassificationOfExperienceInformationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.classificationofexperienceinformation.ClassificationOfExperienceInformationMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 经验信息分类 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class ClassificationOfExperienceInformationServiceImpl implements ClassificationOfExperienceInformationService {

    @Resource
    private ClassificationOfExperienceInformationMapper classificationOfExperienceInformationMapper;

    @Override
    public Long createClassificationOfExperienceInformation(ClassificationOfExperienceInformationSaveReqVO createReqVO) {
        // 插入
        ClassificationOfExperienceInformationDO classificationOfExperienceInformation = BeanUtils.toBean(createReqVO, ClassificationOfExperienceInformationDO.class);
        classificationOfExperienceInformationMapper.insert(classificationOfExperienceInformation);
        // 返回
        return classificationOfExperienceInformation.getId();
    }

    @Override
    public void updateClassificationOfExperienceInformation(ClassificationOfExperienceInformationSaveReqVO updateReqVO) {
        // 校验存在
        validateClassificationOfExperienceInformationExists(updateReqVO.getId());
        // 更新
        ClassificationOfExperienceInformationDO updateObj = BeanUtils.toBean(updateReqVO, ClassificationOfExperienceInformationDO.class);
        classificationOfExperienceInformationMapper.updateById(updateObj);
    }

    @Override
    public void deleteClassificationOfExperienceInformation(Long id) {
        // 校验存在
        validateClassificationOfExperienceInformationExists(id);
        // 删除
        classificationOfExperienceInformationMapper.deleteById(id);
    }

    private void validateClassificationOfExperienceInformationExists(Long id) {
        if (classificationOfExperienceInformationMapper.selectById(id) == null) {
            throw exception(CLASSIFICATION_OF_EXPERIENCE_INFORMATION_NOT_EXISTS);
        }
    }

    @Override
    public ClassificationOfExperienceInformationDO getClassificationOfExperienceInformation(Long id) {
        return classificationOfExperienceInformationMapper.selectById(id);
    }

    @Override
    public PageResult<ClassificationOfExperienceInformationDO> getClassificationOfExperienceInformationPage(ClassificationOfExperienceInformationPageReqVO pageReqVO) {
        return classificationOfExperienceInformationMapper.selectPage(pageReqVO);
    }

}