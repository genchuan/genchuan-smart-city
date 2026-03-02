package cn.iocoder.yudao.module.evaluate.service.subject;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subject.vo.SubjectPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subject.vo.SubjectRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subject.vo.SubjectSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.subject.SubjectDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.subject.SubjectMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.SUBJECT_NOT_EXISTS;

/**
 * 评价主体 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class SubjectServiceImpl implements SubjectService {

    @Resource
    private SubjectMapper subjectMapper;

    @Override
    public Long createSubject(SubjectSaveReqVO createReqVO) {
        // 插入
        SubjectDO subject = BeanUtils.toBean(createReqVO, SubjectDO.class);
        subjectMapper.insert(subject);

        // 返回
        return subject.getId();
    }

    @Override
    public void updateSubject(SubjectSaveReqVO updateReqVO) {
        // 校验存在
        validateSubjectExists(updateReqVO.getId());
        // 更新
        SubjectDO updateObj = BeanUtils.toBean(updateReqVO, SubjectDO.class);
        subjectMapper.updateById(updateObj);
    }

    @Override
    public void deleteSubject(Long id) {
        // 校验存在
        validateSubjectExists(id);
        // 删除
        subjectMapper.deleteById(id);
    }

    @Override
        public void deleteSubjectListByIds(List<Long> ids) {
        // 删除
        subjectMapper.deleteByIds(ids);
        }


    private void validateSubjectExists(Long id) {
        if (subjectMapper.selectById(id) == null) {
            throw exception(SUBJECT_NOT_EXISTS);
        }
    }

    @Override
    public SubjectDO getSubject(Long id) {
        return subjectMapper.selectById(id);
    }

    @Override
    public PageResult<SubjectDO> getSubjectPage(SubjectPageReqVO pageReqVO) {
        return subjectMapper.selectPage(pageReqVO);
    }
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
    @Override
    public PageResult<SubjectRespVO> getSubjectJoinPage(SubjectPageReqVO reqVO) {
        return subjectMapper.selectSubjectJoinPage(reqVO);
    }
}