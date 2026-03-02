package cn.iocoder.yudao.module.evaluate.service.subjecttype;

import cn.iocoder.yudao.module.evaluate.controller.admin.sys.subjecttype.vo.SubjectTypePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.subjecttype.vo.SubjectTypeSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.evaluate.dal.dataobject.subjecttype.SubjectTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.evaluate.dal.mysql.subjecttype.SubjectTypeMapper;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 主体类型字典 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class SubjectTypeServiceImpl implements SubjectTypeService {

    @Resource
    private SubjectTypeMapper subjectTypeMapper;

    @Override
    public Long createSubjectType(SubjectTypeSaveReqVO createReqVO) {
        // 插入
        SubjectTypeDO subjectType = BeanUtils.toBean(createReqVO, SubjectTypeDO.class);
        subjectTypeMapper.insert(subjectType);
        // 返回
        return subjectType.getId();
    }

    @Override
    public void updateSubjectType(SubjectTypeSaveReqVO updateReqVO) {
        // 校验存在
        validateSubjectTypeExists(updateReqVO.getId());
        // 更新
        SubjectTypeDO updateObj = BeanUtils.toBean(updateReqVO, SubjectTypeDO.class);
        subjectTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteSubjectType(Long id) {
        // 校验存在
        validateSubjectTypeExists(id);
        // 删除
        subjectTypeMapper.deleteById(id);
    }

    private void validateSubjectTypeExists(Long id) {
        if (subjectTypeMapper.selectById(id) == null) {
            throw exception(SUBJECT_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public SubjectTypeDO getSubjectType(Long id) {
        return subjectTypeMapper.selectById(id);
    }

    @Override
    public PageResult<SubjectTypeDO> getSubjectTypePage(SubjectTypePageReqVO pageReqVO) {
        return subjectTypeMapper.selectPage(pageReqVO);
    }

}