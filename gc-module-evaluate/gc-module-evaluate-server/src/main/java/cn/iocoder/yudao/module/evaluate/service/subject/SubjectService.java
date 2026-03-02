package cn.iocoder.yudao.module.evaluate.service.subject;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subject.vo.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.subject.SubjectDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 评价主体 Service 接口
 *
 * @author 芋道源码
 */
public interface SubjectService {

    /**
     * 创建评价主体
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSubject(@Valid SubjectSaveReqVO createReqVO);

    /**
     * 更新评价主体
     *
     * @param updateReqVO 更新信息
     */
    void updateSubject(@Valid SubjectSaveReqVO updateReqVO);

    /**
     * 删除评价主体
     *
     * @param id 编号
     */
    void deleteSubject(Long id);

    /**
    * 批量删除评价主体
    *
    * @param ids 编号
    */
    void deleteSubjectListByIds(List<Long> ids);

    /**
     * 获得评价主体
     *
     * @param id 编号
     * @return 评价主体
     */
    SubjectDO getSubject(Long id);

    PageResult<SubjectDO> getSubjectPage(SubjectPageReqVO pageReqVO);

    // -------------------------- 新增联表查询 Service 方法 --------------------------
//    /**
//     * 联表分页查询（列表页展示用，含关联表名称字段）
//     * @param pageReqVO 分页+筛选参数
//     * @return 含联表字段的分页结果
//     */
//    @Override
//    public PageResult<SubjectRespVO> getSubjectPageWithJoin(SubjectPageReqVO pageReqVO) {
//        // 复用原有筛选条件逻辑
//        LambdaQueryWrapperX<SubjectDO> queryWrapper = new LambdaQueryWrapperX<SubjectDO>()
//                .eqIfPresent(SubjectDO::getSubjectId, pageReqVO.getSubjectId())
//                .likeIfPresent(SubjectDO::getName, pageReqVO.getName()) // 支持模糊查询
//                .eqIfPresent(SubjectDO::getCode, pageReqVO.getCode())
//                .eqIfPresent(SubjectDO::getSubjectTypeId, pageReqVO.getSubjectTypeId())
//                .eqIfPresent(SubjectDO::getContactId, pageReqVO.getContactId())
//                .eqIfPresent(SubjectDO::getStatusId, pageReqVO.getStatusId())
//                .betweenIfPresent(SubjectDO::getBizCreateTime, pageReqVO.getBizCreateTime())
//                .betweenIfPresent(SubjectDO::getCreateTime, pageReqVO.getCreateTime());
//
//        // 执行联表分页查询
//        Page<SubjectRespVO> page = subjectMapper.selectSubjectPageWithJoin(
//                Page.of(pageReqVO.getPageNo(), pageReqVO.getPageSize()), queryWrapper);
//        return new PageResult<>(page.getRecords(), page.getTotal());
//    }
//
//    /**
//     * 联表查询主体详情（含成员列表）
//     * @param detailReqVO 主体ID参数
//     * @return 完整详情（人工主体含成员列表）
//     */
//    @Override
//    public SubjectRespVO getSubjectDetailWithJoin(SubjectDetailReqVO detailReqVO) {
//        // 1. 查询主体基础联表信息
//        SubjectRespVO subjectDetail = subjectMapper.selectSubjectDetailWithJoin(detailReqVO.getSubjectId());
//        if (subjectDetail == null) {
//            throw exception(SUBJECT_NOT_EXISTS);
//        }
//
//        // 2. 若为人工主体，查询成员列表（通过主体类型名称判断，或查询sys_subject_type确认）
//        if ("人工主体".equals(subjectDetail.getSubjectTypeName())) {
//            List<SubjectRespVO.SubjectMemberRespVO> memberList = subjectMapper.selectSubjectMemberList(detailReqVO.getSubjectId());
//            subjectDetail.setMemberList(memberList);
//        }
//
//        return subjectDetail;
//    }
//
//    /**
//     * 查询评价主体统计指标
//     * @return 统计结果（总数量、人工/系统主体数、启用数）
//     */
//    @Override
//    public SubjectStatRespVO getSubjectStat() {
//        return subjectMapper.selectSubjectStat();
//    }
    PageResult<SubjectRespVO> getSubjectJoinPage(SubjectPageReqVO reqVO);
    // 新增联表查询接口方法
//    PageResult<SubjectRespVO> getSubjectPageWithJoin(SubjectPageReqVO pageReqVO);
//    SubjectRespVO getSubjectDetailWithJoin(SubjectDetailReqVO detailReqVO);
//    SubjectStatRespVO getSubjectStat();
}