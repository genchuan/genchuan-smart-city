package cn.iocoder.yudao.module.industry.service.lawenf.dashboard.global.caseview;

import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.caseview.vo.LawCaseViewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.caseview.vo.LawCaseViewRespVO;
import cn.iocoder.yudao.module.industry.dal.mysql.lawenf.dashboard.global.caseview.LawCaseViewMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

@Service
@Validated
public class LawCaseViewServiceImpl implements LawCaseViewService {

    @Resource
    private LawCaseViewMapper lawCaseViewMapper;

    @Override
    public LawCaseViewRespVO getLawCaseView(LawCaseViewQueryReqVO reqVO) {
        // 1. 获取前端传入的 timeRange 参数
        String timeRange = reqVO.getTimeRange();

        // 2. 判断 timeRange 是否非空
        if (StringUtils.hasText(timeRange)) {
            LocalDateTime startTime = null;
            LocalDateTime endTime = null;
            LocalDate today = LocalDate.now();

            // 3. 根据 timeRange 值计算起始时间和结束时间
            switch (timeRange.toLowerCase()) {
                case "today":
                    // 3.1 今日: 起始时间 = 当天00:00:00, 结束时间 = 当天23:59:59.999999999
                    startTime = today.atStartOfDay();
                    endTime = today.atTime(LocalTime.MAX);
                    break;
                case "week":
                    // 3.2 本周: 起始时间 = 本周周一00:00:00, 结束时间 = 本周周日23:59:59.999999999
                    startTime = today.with(DayOfWeek.MONDAY).atStartOfDay();
                    endTime = today.with(DayOfWeek.SUNDAY).atTime(LocalTime.MAX);
                    break;
                case "month":
                    // 3.3 本月: 起始时间 = 本月1号00:00:00, 结束时间 = 本月最后一天23:59:59.999999999
                    startTime = today.withDayOfMonth(1).atStartOfDay();
                    endTime = today.withDayOfMonth(today.lengthOfMonth()).atTime(LocalTime.MAX);
                    break;
                default:
                    // 3.4 未知 timeRange: 抛出异常提示不支持的值
                    throw new IllegalArgumentException("不支持的timeRange: " + timeRange);
            }

            // 4. 将计算好的起始时间和结束时间设置回查询对象
            reqVO.setStartTime(startTime);
            reqVO.setEndTime(endTime);
        }

        // 查询各类统计
        List<LawCaseViewRespVO.CaseTypeCount> typeList =
                lawCaseViewMapper.selectCaseTypeCount(reqVO);

        List<LawCaseViewRespVO.CaseSourceCount> sourceList =
                lawCaseViewMapper.selectCaseSourceCount(reqVO);

        List<LawCaseViewRespVO.ProgressStageCount> progressList =
                lawCaseViewMapper.selectProgressStageCount(reqVO);

        Integer overdue = lawCaseViewMapper.selectOverdueCaseCount(reqVO);

        // 避免 null
        LawCaseViewRespVO respVO = new LawCaseViewRespVO();
        respVO.setTypeDistribution(typeList != null ? typeList : Collections.emptyList());
        respVO.setSourceDistribution(sourceList != null ? sourceList : Collections.emptyList());
        respVO.setProgressDistribution(progressList != null ? progressList : Collections.emptyList());
        respVO.setOverdueCaseCount(overdue != null ? overdue : 0);

        return respVO;
    }

    /**
     * 查询执法案件的案件类型列表
     * 通过统计接口获取所有案件类型，并抽取类型字段返回
     */
    @Override
    public List<String> getLawCaseViewTypeList() {
        //直接req放空的，没关系
        LawCaseViewQueryReqVO reqVO = new LawCaseViewQueryReqVO();

        // 调用已有 Mapper 查询案件类型及数量
        List<LawCaseViewRespVO.CaseTypeCount> typeList = lawCaseViewMapper.selectCaseTypeCount(reqVO);

        // 提取案件类型字段，返回 List<String>
        if (typeList == null || typeList.isEmpty()) {
            return Collections.emptyList();
        }

        return typeList.stream()
                .map(LawCaseViewRespVO.CaseTypeCount::getCaseType)
                .distinct() // 去重，防止重复
                .toList();
    }

    /**
     * 查询执法案件的案件来源列表
     * 通过统计接口获取所有案件来源，并抽取来源字段返回
     */
    @Override
    public List<String> getLawCaseViewSourceList() {
        // 构造空查询条件，当前业务不做筛选
        LawCaseViewQueryReqVO reqVO = new LawCaseViewQueryReqVO();

        // 查询案件来源及数量
        List<LawCaseViewRespVO.CaseSourceCount> sourceList = lawCaseViewMapper.selectCaseSourceCount(reqVO);

        // 无数据返回空集合
        if (sourceList == null || sourceList.isEmpty()) {
            return Collections.emptyList();
        }

        // 提取案件来源字段并去重
        return sourceList.stream()
                .map(LawCaseViewRespVO.CaseSourceCount::getCaseSource)
                .distinct()
                .toList();
    }

}
