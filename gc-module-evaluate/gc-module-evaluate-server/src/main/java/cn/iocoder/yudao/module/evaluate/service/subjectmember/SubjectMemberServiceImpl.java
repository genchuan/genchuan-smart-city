package cn.iocoder.yudao.module.evaluate.service.subjectmember;

import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subjectmember.vo.SubjectMemberPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subjectmember.vo.SubjectMemberSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.subjectmember.SubjectMemberDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.evaluate.dal.mysql.subjectmember.SubjectMemberMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.*;

/**
 * 评价主体成员 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class SubjectMemberServiceImpl implements SubjectMemberService {

    @Resource
    private SubjectMemberMapper subjectMemberMapper;

    @Override
    public Long createSubjectMember(SubjectMemberSaveReqVO createReqVO) {
        // 插入
        SubjectMemberDO subjectMember = BeanUtils.toBean(createReqVO, SubjectMemberDO.class);
        subjectMemberMapper.insert(subjectMember);

        // 返回
        return subjectMember.getId();
    }

    @Override
    public void updateSubjectMember(SubjectMemberSaveReqVO updateReqVO) {
        // 校验存在
        validateSubjectMemberExists(updateReqVO.getId());
        // 更新
        SubjectMemberDO updateObj = BeanUtils.toBean(updateReqVO, SubjectMemberDO.class);
        subjectMemberMapper.updateById(updateObj);
    }

    @Override
    public void deleteSubjectMember(Long id) {
        // 校验存在
        validateSubjectMemberExists(id);
        // 删除
        subjectMemberMapper.deleteById(id);
    }

    @Override
        public void deleteSubjectMemberListByIds(List<Long> ids) {
        // 删除
        subjectMemberMapper.deleteByIds(ids);
        }


    private void validateSubjectMemberExists(Long id) {
        if (subjectMemberMapper.selectById(id) == null) {
            throw exception(SUBJECT_MEMBER_NOT_EXISTS);
        }
    }

    @Override
    public SubjectMemberDO getSubjectMember(Long id) {
        return subjectMemberMapper.selectById(id);
    }

    @Override
    public PageResult<SubjectMemberDO> getSubjectMemberPage(SubjectMemberPageReqVO pageReqVO) {
        return subjectMemberMapper.selectPage(pageReqVO);
    }

}