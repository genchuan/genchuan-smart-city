package cn.iocoder.yudao.module.studentmgmt.service.studyup;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.BaseChartReqVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.ChartCountVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.studyup.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.studyup.StudyUpDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.studyup.StudyUpMapper;
import cn.iocoder.yudao.module.studentmgmt.enums.StudyUpStatusEnum;
import com.alibaba.fastjson.JSONObject;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.STUDY_UP_NOT_EXISTS;

/**
 * 升学管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class StudyUpServiceImpl implements StudyUpService {

    @Resource
    private StudyUpMapper studyUpMapper;

    @Override
    public Long createStudyUp(StudyUpSaveReqVO createReqVO) {
        // 插入
        StudyUpDO studyUp = BeanUtils.toBean(createReqVO, StudyUpDO.class);
        studyUpMapper.insert(studyUp);

        // 返回
        return studyUp.getId();
    }

    @Override
    public void updateStudyUp(StudyUpSaveReqVO updateReqVO) {
        // 校验存在
        validateStudyUpExists(updateReqVO.getId());
        // 更新
        StudyUpDO updateObj = BeanUtils.toBean(updateReqVO, StudyUpDO.class);
        studyUpMapper.updateById(updateObj);
    }

    @Override
    public void deleteStudyUp(Long id) {
        // 校验存在
        validateStudyUpExists(id);
        // 删除
        studyUpMapper.deleteById(id);
    }

    @Override
    public void deleteStudyUpListByIds(List<Long> ids) {
        // 删除
        studyUpMapper.deleteByIds(ids);
    }


    private StudyUpDO validateStudyUpExists(Long id) {
        StudyUpDO studyUpDO = studyUpMapper.selectById(id);
        if (studyUpDO == null) {
            throw exception(STUDY_UP_NOT_EXISTS);
        }
        return studyUpDO;
    }

    @Override
    public StudyUpDO getStudyUp(Long id) {
        return studyUpMapper.selectById(id);
    }

    @Override
    public PageResult<StudyUpDO> getStudyUpPage(StudyUpPageReqVO pageReqVO) {
        return studyUpMapper.selectPage(pageReqVO);
    }
    @Override
    public PageResult<StudyUpRespVO> getStudyUpJoinPage(StudyUpPageReqVO pageReqVO) {
        return studyUpMapper.selectJoinPage(pageReqVO);
    }

    @Override
    public StudyUpQueryRespVO query(StudyUpQueryReqVO reqVO) {
        return studyUpMapper.selectByStudentId(reqVO.getStudentId());
    }

    @Override
    public Boolean select(StudyUpSelectReqVO reqVO) {
        // 校验存在
        StudyUpDO studyUp = validateStudyUpExists(reqVO.getId());
        studyUp.setSchoolName(reqVO.getSchoolName());
        studyUp.setSchoolType(reqVO.getSchoolType());
        studyUp.setMajor(reqVO.getMajor());
        studyUp.setStatus(StudyUpStatusEnum.PENDING_PLAN.getStatus());
        String loginUserNickname = SecurityFrameworkUtils.getLoginUserNickname();
        studyUp.setUpdater(loginUserNickname);
        studyUp.setUpdateTime(LocalDateTime.now());
        // 更新
        int i = studyUpMapper.updateById(studyUp);
        return i > 0;
    }

    @Override
    public Boolean plan(StudyUpPlanReqVO reqVO) {
        // 校验存在
        StudyUpDO studyUp = validateStudyUpExists(reqVO.getId());
        studyUp.setPlanContent(reqVO.getPlanContent());
        studyUp.setPlanTime(reqVO.getPlanTime());
        studyUp.setStatus(StudyUpStatusEnum.PLANNED.getStatus());
        String loginUserNickname = SecurityFrameworkUtils.getLoginUserNickname();
        studyUp.setUpdater(loginUserNickname);
        studyUp.setUpdateTime(LocalDateTime.now());
        // 更新
        int i = studyUpMapper.updateById(studyUp);
        return i > 0;
    }

    @Override
    public Boolean record(StudyUpRecordReqVO reqVO) {
        // 校验存在
        StudyUpDO studyUp = validateStudyUpExists(reqVO.getId());
        studyUp.setRemark(reqVO.getRemark());
        studyUp.setRecordTime(reqVO.getRecordTime());
        studyUp.setStatus(StudyUpStatusEnum.PLANNED.getStatus());
        String loginUserNickname = SecurityFrameworkUtils.getLoginUserNickname();
        studyUp.setUpdater(loginUserNickname);
        studyUp.setUpdateTime(LocalDateTime.now());
        // 更新
        int i = studyUpMapper.updateById(studyUp);
        return i > 0;
    }

    @Override
    public StudyUpChartRespVO chart(BaseChartReqVO reqVO) {
        StudyUpChartRespVO vo = new StudyUpChartRespVO();

        // 1. 卡片数据
        LocalDateTime[] timeRange = reqVO.getTimeRange();
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (null != timeRange) {
            startTime = timeRange[0];
            endTime = timeRange[1];
        }
        // 1. 卡片数据
        vo = studyUpMapper.selectTotalCount(startTime, endTime,
                StudyUpStatusEnum.PENDING_PLAN.getStatus(),
                StudyUpStatusEnum.PLANNED.getStatus()
        );
        // 如果统计为空，则设置为0
        if (vo == null) {
            vo = new StudyUpChartRespVO();
        }
        if (vo.getPlannedStudent() == null) {
            vo.setPlannedStudent(0);
        }
        if (vo.getTotalStudent() == null) {
            vo.setTotalStudent(0);
        }
        if (vo.getWaitPlanStudent() == null) {
            vo.setWaitPlanStudent(0);
        }

        //近一周报名趋势数据，包含日期及对应报名数

        List<JSONObject> schoolTopCountList = studyUpMapper.selectSchoolTopCount(startTime, endTime);
        vo.setSchoolTopCount(schoolTopCountList);
        return vo;
    }

    @Override
    public StudyUpStudyCountRespVO studyCount(BaseChartReqVO reqVO) {
        StudyUpStudyCountRespVO vo = new StudyUpStudyCountRespVO();

        // 1. 卡片数据
        LocalDateTime[] timeRange = reqVO.getTimeRange();
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (null != timeRange) {
            startTime = timeRange[0];
            endTime = timeRange[1];
        }
        // 1. 卡片数据
        List<ChartCountVO> intentionDistributionList = studyUpMapper.selectIntentionDistributionList(startTime, endTime);
        List<ChartCountVO> schoolTypeDistributionList = studyUpMapper.selectSchoolTypeDistributionList(startTime, endTime);

        vo.setIntentionDistribution(intentionDistributionList);
        vo.setSchoolTypeDistribution(schoolTypeDistributionList);
        return vo;
    }

}