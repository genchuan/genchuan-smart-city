package cn.iocoder.yudao.module.studentmgmt.service.assessmgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.assessmgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.assessmgmt.AssessMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.assessmgmt.AssessMgmtMapper;
import cn.iocoder.yudao.module.studentmgmt.enums.AssessStatusEnum;
import cn.iocoder.yudao.module.studentmgmt.enums.AssessTypeEnum;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.service.impl.DiffParseFunction;
import com.mzt.logapi.starter.annotation.LogRecord;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.ASSESS_MGMT_NOT_EXISTS;
import static cn.iocoder.yudao.module.studentmgmt.enums.LogRecordConstants.*;

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
    @LogRecord(type = STUDENT_ASSESS_TYPE, subType = STUDENT_ASSESS_CREATE_SUB_TYPE, bizNo = "{{#assessMgmt.id}}",
            success = STUDENT_ASSESS_CREATE_SUCCESS)
    public Long createAssessMgmt(AssessMgmtSaveReqVO createReqVO) {
        // 插入
        AssessMgmtDO assessMgmt = BeanUtils.toBean(createReqVO, AssessMgmtDO.class);
        assessMgmtMapper.insert(assessMgmt);

        // 记录操作日志上下文
        LogRecordContext.putVariable("assessMgmt", assessMgmt);
        LogRecordContext.putVariable("className", assessMgmt.getClassName());

        // 返回
        return assessMgmt.getId();
    }

    @Override
    @LogRecord(type = STUDENT_ASSESS_TYPE, subType = STUDENT_ASSESS_UPDATE_SUB_TYPE, bizNo = "{{#assessMgmt.id}}",
            success = STUDENT_ASSESS_UPDATE_SUCCESS)
    public void updateAssessMgmt(AssessMgmtSaveReqVO updateReqVO) {
        // 校验存在
        AssessMgmtDO assessMgmtDO = validateAssessMgmtExists(updateReqVO.getId());
        // 更新
        AssessMgmtDO updateObj = BeanUtils.toBean(updateReqVO, AssessMgmtDO.class);

        // 记录操作日志上下文
        LogRecordContext.putVariable(DiffParseFunction.OLD_OBJECT, BeanUtils.toBean(assessMgmtDO, AssessMgmtSaveReqVO.class));
        LogRecordContext.putVariable("assessMgmt", assessMgmtDO);

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


    private AssessMgmtDO validateAssessMgmtExists(Long id) {
        AssessMgmtDO assessMgmtDO = assessMgmtMapper.selectById(id);
        if ( assessMgmtDO == null) {
            throw exception(ASSESS_MGMT_NOT_EXISTS);
        }
        return assessMgmtDO;
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
    @LogRecord(type = STUDENT_ASSESS_TYPE, subType = STUDENT_ASSESS_PUBLISH_SUB_TYPE, bizNo = "{{#assessMgmt.id}}",
            success = STUDENT_ASSESS_PUBLISH_SUCCESS)
    public boolean publishAssessMgmt(AssessMgmtPublishReqVO publishReqVO) {
        AssessMgmtDO assessMgmtDO = assessMgmtMapper.selectById(publishReqVO.getId());
        if (assessMgmtDO != null) {
            assessMgmtDO.setStatus("1");
            assessMgmtDO.setPublishTime(LocalDateTime.now());
            assessMgmtMapper.updateById(assessMgmtDO);
            // 记录操作日志上下文
            LogRecordContext.putVariable("assessMgmt", assessMgmtDO);

            return true;
        }
        return false;
    }

    @Override
    public AssessMgmtChartRespVO chart(AssessMgmtChartReqVO reqVO) {
        AssessMgmtChartRespVO vo = new AssessMgmtChartRespVO();

        String cycle = reqVO.getCycle();

        // 1. 卡片数据
        //totalCount (integer): 本期考评总记录数。
        vo.setTotalCount(assessMgmtMapper.selectTotalAssessCount(cycle,  "", ""));
        // avgScore (decimal): 本期班级平均得分。
        vo.setAvgScore(assessMgmtMapper.selectAvgScore(cycle, AssessStatusEnum.PUBLISHED.getStatus()));
        // topRankClass (string): 本期排名第一的班级。
        vo.setTopRankClass(assessMgmtMapper.selectTopRankClass(cycle,AssessStatusEnum.PUBLISHED.getStatus()));
        // assessTypeCount (object): 各考评类型的记录数统计，key 为考评类型编码，value 为数量。
        vo.setAssessTypeCount(assessMgmtMapper.selectAssessTypeCount(cycle,AssessStatusEnum.PUBLISHED.getStatus()));
        // statusCount (object): 各状态的记录数统计，key 为状态编码，value 为数量。
        vo.setStatusCount(assessMgmtMapper.selectStatusCount(cycle, AssessStatusEnum.PUBLISHED.getStatus()));

////        vo.setPendingPublishCount(assessMgmtMapper.selectTotalAssessCount(cycle, AssessStatusEnum.UN_PUBLISH.getStatus(), ""));
//
//        // 教室卫生/早操/文明班级/黑板报
//        vo.setHygieneScore(assessMgmtMapper.selectAssessScore(cycle, AssessStatusEnum.PUBLISHED.getStatus(), AssessTypeEnum.CLASS_CLEAN.getStatus()));
//        vo.setMorningExerciseScore(assessMgmtMapper.selectAssessScore(cycle, AssessStatusEnum.PUBLISHED.getStatus(), AssessTypeEnum.MORNING_EXERCISE.getStatus()));
//        vo.setCivilizedScore(assessMgmtMapper.selectAssessScore(cycle, AssessStatusEnum.PUBLISHED.getStatus(), AssessTypeEnum.CIVILIZED_CLASS.getStatus()));
//        vo.setBlackboardScore(assessMgmtMapper.selectAssessScore(cycle, AssessStatusEnum.PUBLISHED.getStatus(), AssessTypeEnum.BLACKBOARD.getStatus()));
//
//        vo.setTodayPublishCount(assessMgmtMapper.selectTodayPublishCount(cycle));

        return vo;
    }

    /**
     * 考评类型分布统计
     * @param reqVO
     * @return
     */
    @Override
    public List<AssessMgmtDimensionScoreRespVO> dimensionScore(AssessMgmtChartReqVO reqVO) {
        String cycle = reqVO.getCycle();
        return assessMgmtMapper.dimensionScore(cycle);
    }

    @Override
    public List<AssessMgmtCycleTrendRespVO> cycleTrend(AssessMgmtCycleTrendReqVO reqVO) {
        LocalDateTime startTime = reqVO.getStartTime();
        LocalDateTime endTime = reqVO.getEndTime();

        String className = reqVO.getClassName();
        List<AssessMgmtCycleTrendRespVO> assessMgmtCycleTrendRespVOS = assessMgmtMapper.cycleTrend(className, startTime, endTime);
        // 按create_time时间的升序排序，并将周期名称按顺序改成第1、2、3、4、5、6、7、8、9、10、11、12 周/月/学期等
//        assessMgmtCycleTrendRespVOS.sort(Comparator.comparing(AssessMgmtCycleTrendRespVO::getCreateTime));
        int i = 1;
        String tempName = assessMgmtCycleTrendRespVOS.get(0).getCycleName();
        List<AssessMgmtCycleTrendRespVO> list = new ArrayList<>();
        for (AssessMgmtCycleTrendRespVO assessMgmtCycleTrendRespVO :assessMgmtCycleTrendRespVOS) {
            if (!tempName.equals(assessMgmtCycleTrendRespVO.getCycleName())) {
                i = 1;
                tempName = assessMgmtCycleTrendRespVO.getCycleName();
            }
            assessMgmtCycleTrendRespVO.setCycleName("第" + (i) + assessMgmtCycleTrendRespVO.getCycleName());
            list.add(assessMgmtCycleTrendRespVO);
            i++;
        }

        return list;
    }

}