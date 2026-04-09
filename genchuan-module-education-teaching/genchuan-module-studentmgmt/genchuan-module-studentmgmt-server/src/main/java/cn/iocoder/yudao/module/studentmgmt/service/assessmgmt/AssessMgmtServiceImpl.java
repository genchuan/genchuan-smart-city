package cn.iocoder.yudao.module.studentmgmt.service.assessmgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.assessmgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.studentinfo.vo.StudentInfoCoreIndexReqVO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.assessmgmt.AssessMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.assessmgmt.AssessMgmtMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.ASSESS_MGMT_NOT_EXISTS;

/**
 * 考评管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class AssessMgmtServiceImpl implements AssessMgmtService {

    @Resource
    private AssessMgmtMapper assessMgmtMapper;

    @Override
    public Long createAssessMgmt(AssessMgmtSaveReqVO createReqVO) {
        // 插入
        AssessMgmtDO assessMgmt = BeanUtils.toBean(createReqVO, AssessMgmtDO.class);
        assessMgmtMapper.insert(assessMgmt);

        // 返回
        return assessMgmt.getId();
    }

    @Override
    public void updateAssessMgmt(AssessMgmtSaveReqVO updateReqVO) {
        // 校验存在
        validateAssessMgmtExists(updateReqVO.getId());
        // 更新
        AssessMgmtDO updateObj = BeanUtils.toBean(updateReqVO, AssessMgmtDO.class);
        assessMgmtMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssessMgmt(Long id) {
        // 校验存在
        validateAssessMgmtExists(id);
        // 删除
        assessMgmtMapper.deleteById(id);
    }

    @Override
        public void deleteAssessMgmtListByIds(List<Long> ids) {
        // 删除
        assessMgmtMapper.deleteByIds(ids);
        }


    private void validateAssessMgmtExists(Long id) {
        if (assessMgmtMapper.selectById(id) == null) {
            throw exception(ASSESS_MGMT_NOT_EXISTS);
        }
    }

    @Override
    public AssessMgmtDO getAssessMgmt(Long id) {
        return assessMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<AssessMgmtDO> getAssessMgmtPage(AssessMgmtPageReqVO pageReqVO) {
        return assessMgmtMapper.selectPage(pageReqVO);
    }

    @Override
    public boolean publishAssessMgmt(AssessMgmtPublishReqVO publishReqVO) {
        AssessMgmtDO assessMgmtDO = assessMgmtMapper.selectById(publishReqVO.getId());
        if (assessMgmtDO != null) {
            assessMgmtDO.setStatus("已发布");
            assessMgmtDO.setPublishTime(LocalDateTime.now());
            assessMgmtMapper.updateById(assessMgmtDO);
            return true;
        }
        return false;
    }

    @Override
    public AssessMgmtChartRespVO chart(AssessMgmtChartReqVO reqVO) {
        AssessMgmtChartRespVO vo = new AssessMgmtChartRespVO();
        String grade = reqVO.getGrade();
        String major = reqVO.getMajor();

        // 1. 卡片数据
        vo.setTotalAssessCount(assessMgmtMapper.selectTotalAssessCount(grade, major, "", ""));
        vo.setPendingPublishCount(assessMgmtMapper.selectTotalAssessCount(grade, major, "未发布", ""));

        // 教室卫生/早操/文明班级/黑板报
        vo.setHygieneScore(assessMgmtMapper.selectAssessScore(grade, major, "已发布", "教室卫生"));
        vo.setMorningExerciseScore(assessMgmtMapper.selectAssessScore(grade, major, "已发布", "早操"));
        vo.setCivilizedScore(assessMgmtMapper.selectAssessScore(grade, major, "已发布", "文明班级"));
        vo.setBlackboardScore(assessMgmtMapper.selectAssessScore(grade, major, "已发布", "黑板报"));

        vo.setTodayPublishCount(assessMgmtMapper.selectTodayPublishCount(grade, major));

        return vo;
    }

    /**
     * 考评类型分布统计
     * @param reqVO
     * @return
     */
    @Override
    public AssessMgmtTypeCountRespVO typeCount(AssessMgmtChartReqVO reqVO) {
        String grade = reqVO.getGrade();
        return assessMgmtMapper.typeCount(grade);
    }

    @Override
    public AssessMgmtCoreIndexReqVO getCoreIndex(StudentInfoCoreIndexReqVO reqVO) {
        LocalDateTime startTime = reqVO.getStartTime();
        LocalDateTime endTime = reqVO.getEndTime();
        return assessMgmtMapper.getCoreIndex(startTime, endTime);
    }

}