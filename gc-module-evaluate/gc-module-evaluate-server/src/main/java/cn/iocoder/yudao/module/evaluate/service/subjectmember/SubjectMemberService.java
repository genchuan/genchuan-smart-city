package cn.iocoder.yudao.module.evaluate.service.subjectmember;

import java.util.*;

import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subjectmember.vo.SubjectMemberPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subjectmember.vo.SubjectMemberSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.subjectmember.SubjectMemberDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 评价主体成员 Service 接口
 *
 * @author 芋道源码
 */
public interface SubjectMemberService {

    /**
     * 创建评价主体成员
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSubjectMember(@Valid SubjectMemberSaveReqVO createReqVO);

    /**
     * 更新评价主体成员
     *
     * @param updateReqVO 更新信息
     */
    void updateSubjectMember(@Valid SubjectMemberSaveReqVO updateReqVO);

    /**
     * 删除评价主体成员
     *
     * @param id 编号
     */
    void deleteSubjectMember(Long id);

    /**
    * 批量删除评价主体成员
    *
    * @param ids 编号
    */
    void deleteSubjectMemberListByIds(List<Long> ids);

    /**
     * 获得评价主体成员
     *
     * @param id 编号
     * @return 评价主体成员
     */
    SubjectMemberDO getSubjectMember(Long id);

    /**
     * 获得评价主体成员分页
     *
     * @param pageReqVO 分页查询
     * @return 评价主体成员分页
     */
    PageResult<SubjectMemberDO> getSubjectMemberPage(SubjectMemberPageReqVO pageReqVO);

}