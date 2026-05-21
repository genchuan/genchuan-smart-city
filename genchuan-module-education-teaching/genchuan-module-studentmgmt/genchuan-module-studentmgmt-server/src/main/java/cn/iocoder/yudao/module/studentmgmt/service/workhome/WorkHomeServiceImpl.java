package cn.iocoder.yudao.module.studentmgmt.service.workhome;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.workhome.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.assessmgmt.AssessMgmtMapper;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.behaviormgmt.BehaviorMgmtMapper;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.fundsystem.FundSystemMapper;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.honormgmt.HonorMgmtMapper;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.mentalmgmt.MentalMgmtMapper;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.studentinfo.StudentInfoMapper;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.violatemgmt.ViolateMgmtMapper;
import cn.iocoder.yudao.module.studentmgmt.enums.ViolaateStatusEnum;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 学工报 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class WorkHomeServiceImpl implements WorkHomeService {
    @Resource
    private StudentInfoMapper studentInfoMapper;
    @Resource
    private HonorMgmtMapper honorMgmtMapper;
    @Resource
    private AssessMgmtMapper assessMgmtMapper;
    @Resource
    private BehaviorMgmtMapper behaviorMgmtMapper;
    @Resource
    private ViolateMgmtMapper violateMgmtMapper;
    @Resource
    private MentalMgmtMapper mentalMgmtMapper;
    @Resource
    private FundSystemMapper fundSystemMapper;

    @Override
    public PageResult<WorkHomeRespVO> getWorkHomePage(WorkHomePageReqVO pageReqVO) {

        Long total = studentInfoMapper.selectWorkHomePageTotal(pageReqVO);

        Integer pageNo = pageReqVO.getPageNo();
        Integer pageSize = pageReqVO.getPageSize();
        Integer offset = (pageNo-1) * pageSize;
        pageReqVO.setPageNo(offset);

        List<WorkHomeRespVO> list = studentInfoMapper.selectWorkHomePage(pageReqVO);
        return new PageResult<>(list, total);
    }

    @Override
    public WorkHomeChartRespVO chart(WorkHomeChartReqVO reqVO) {
        String className = reqVO.getClassName();
        String grade = reqVO.getGrade();
        LocalDateTime startTime = reqVO.getStartTime();
        LocalDateTime endTime = reqVO.getEndTime();
        WorkHomeChartRespVO vo = new WorkHomeChartRespVO();
        vo.setTotalStudent(studentInfoMapper.selectTotalStudent(startTime, endTime, className, grade));
        vo.setTotalHonor(honorMgmtMapper.selectTotalHonor(startTime, endTime, className, grade));
        vo.setTotalAssess(assessMgmtMapper.selectTotalAssess(startTime, endTime, className, grade));
        vo.setTotalViolate(violateMgmtMapper.selectTotalViolate(startTime, endTime, className, grade));
        vo.setTotalMental(mentalMgmtMapper.selectTotalMental(startTime, endTime, className, grade));
        vo.setTotalFund(fundSystemMapper.selectTotalFund(startTime, endTime, className, grade));
        vo.setUnhandledViolate(violateMgmtMapper.selectUnhandledViolate(startTime, endTime, className, grade, ViolaateStatusEnum.VIOLATE_MGMT_VIOLATE_STATUS_PENDING.getStatus()));
        // TODO : 待处理预警数
        vo.setUnhandledWarn(violateMgmtMapper.selectUnhandledViolate(startTime, endTime, className, grade, ViolaateStatusEnum.VIOLATE_MGMT_VIOLATE_STATUS_WARN.getStatus()));
        return vo;
    }

    @Override
    public List<WorkHomeDimensionCountRespVO> dimensionCount(WorkHomeChartReqVO reqVO) {
        String className = reqVO.getClassName();
        String grade = reqVO.getGrade();
        LocalDateTime startTime = reqVO.getStartTime();
        LocalDateTime endTime = reqVO.getEndTime();

        WorkHomeChartRespVO chartRespVO = new WorkHomeChartRespVO();
        chartRespVO.setTotalHonor(honorMgmtMapper.selectTotalHonor(startTime, endTime, className, grade));
        chartRespVO.setTotalAssess(assessMgmtMapper.selectTotalAssess(startTime, endTime, className, grade));
        chartRespVO.setTotalViolate(violateMgmtMapper.selectTotalViolate(startTime, endTime, className, grade));
        chartRespVO.setTotalMental(mentalMgmtMapper.selectTotalMental(startTime, endTime, className, grade));
        chartRespVO.setTotalFund(fundSystemMapper.selectTotalFund(startTime, endTime, className, grade));

        Integer totalBehavior = behaviorMgmtMapper.selectTotalBehavior(startTime, endTime, className, grade);

        List<WorkHomeDimensionCountRespVO> list = new ArrayList();
        WorkHomeDimensionCountRespVO honor = new WorkHomeDimensionCountRespVO();
        honor.setDimension("荣誉");
        honor.setCount(chartRespVO.getTotalHonor());
        list.add(honor);
        WorkHomeDimensionCountRespVO assess = new WorkHomeDimensionCountRespVO();
        assess.setDimension("考评");
        assess.setCount(chartRespVO.getTotalAssess());
        list.add(assess);
        WorkHomeDimensionCountRespVO behavior = new WorkHomeDimensionCountRespVO();
        behavior.setDimension("行为");
        behavior.setCount(totalBehavior);
        list.add(behavior);
        WorkHomeDimensionCountRespVO violate = new WorkHomeDimensionCountRespVO();
        violate.setDimension("违纪");
        violate.setCount(chartRespVO.getTotalViolate());
        list.add(violate);
        WorkHomeDimensionCountRespVO mental = new WorkHomeDimensionCountRespVO();
        mental.setDimension("心理");
        mental.setCount(chartRespVO.getTotalMental());
        list.add(mental);
        WorkHomeDimensionCountRespVO fund = new WorkHomeDimensionCountRespVO();
        fund.setDimension("资助");
        fund.setCount(chartRespVO.getTotalFund());
        list.add(fund);

        return list;
    }

    @Override
    public List<WorkHomeScoreAnalysisRespVO> scoreAnalysis(WorkHomeScoreAnalysisReqVO reqVO) {
        //className (string): 班级名称。
        //healthScore (decimal): 教室卫生得分。
        //exerciseScore (decimal): 早操得分。
        //civilizedScore (decimal): 文明班级得分。
        //blackboardScore (decimal): 黑板报得分。
        //totalScore (decimal): 综合总分。
        List<WorkHomeScoreAnalysisRespVO> list = assessMgmtMapper.selectScoreAnalysis(reqVO.getGrade(), reqVO.getCycle());
        return list;
    }

    @Override
    public List<WorkHomeCoreIndexRespVO> coreIndex(WorkHomeCoreIndexReqVO reqVO) {
        List<WorkHomeCoreIndexRespVO> list = new ArrayList();
        List<JSONObject> voilateList = violateMgmtMapper.selectTotalCountByDate(reqVO.getStartTime(), reqVO.getEndTime(), reqVO.getCycle());
        List<JSONObject> honorList = honorMgmtMapper.selectTotalCountByDate(reqVO.getStartTime(), reqVO.getEndTime(), reqVO.getCycle());
        List<JSONObject> assessList = assessMgmtMapper.selectTotalCountByDate(reqVO.getStartTime(), reqVO.getEndTime(), reqVO.getCycle());
        for (JSONObject voilate : voilateList) {
            String date = voilate.getString("date");
            Integer count = voilate.getInteger("count");
            WorkHomeCoreIndexRespVO vo = new WorkHomeCoreIndexRespVO();
            vo.setDate(date);
            vo.setViolateCount(count);

            boolean haveHonor = false;
            for (JSONObject honor : honorList) {
                if (date.equals(honor.getString("date"))) {
                    vo.setHonorCount(honor.getInteger("count"));
                    haveHonor = true;
                }
            }
            if (!haveHonor) {
                vo.setHonorCount(0);
            }
            boolean haveAssess = false;
            for (JSONObject assess : assessList) {
                if (date.equals(assess.getString("date"))) {
                    vo.setAssessCount(assess.getInteger("count"));
                    haveAssess = true;
                }
            }
            if (!haveAssess) {
                vo.setAssessCount(0);
            }
            list.add(vo);
        }

        for (JSONObject honor : honorList) {
            String date = honor.getString("date");
            boolean haveVoilate = false;
            WorkHomeCoreIndexRespVO vo = null;

            if (list != null && list.size() > 0) {
                for (WorkHomeCoreIndexRespVO tempVo : list) {
                    if (date.equals(tempVo.getDate())) {
                        haveVoilate = true;
                        vo = tempVo;
                        break;
                    }
                }
                if (!haveVoilate) {
                    vo = new WorkHomeCoreIndexRespVO();
                    vo.setDate(date);
                    vo.setHonorCount(honor.getInteger("count"));
                    vo.setViolateCount(0);
                    vo.setAssessCount(0);
                    list.add(vo);
                }
                else {
                    vo.setHonorCount(honor.getInteger("count"));
                    Integer assessCount = vo.getAssessCount();
                    if (assessCount == null) {
                        vo.setAssessCount(0);
                    }
                }
            }
            else{
                vo = new WorkHomeCoreIndexRespVO();
                vo.setDate(date);
                vo.setHonorCount(honor.getInteger("count"));
                vo.setViolateCount(0);
                vo.setAssessCount(0);
            }
        }
        for (JSONObject assess : assessList) {
            String date = assess.getString("date");
            boolean haveDate = false;
            WorkHomeCoreIndexRespVO vo = null;
            if (list != null && list.size() > 0) {
                for (WorkHomeCoreIndexRespVO tempVo : list) {
                    if (date.equals(tempVo.getDate())) {
                        haveDate = true;
                        vo = tempVo;
                        break;
                    }
                }
            }
            if (!haveDate) {
                vo = new WorkHomeCoreIndexRespVO();
                vo.setDate(date);
                vo.setHonorCount(0);
                vo.setViolateCount(0);
                vo.setAssessCount(assess.getInteger("count"));
                list.add(vo);
            }
            else {
                vo.setAssessCount(assess.getInteger("count"));
                Integer honorCount = vo.getHonorCount();
                if (honorCount == null) {
                    vo.setHonorCount(0);
                }
                Integer violateCount = vo.getViolateCount();
                if (violateCount == null) {
                    vo.setViolateCount(0);
                }
            }
        }
        return list;
    }

}