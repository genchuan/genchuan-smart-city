package cn.iocoder.yudao.module.industry.service.health.dashboard.global.coremetrics;

import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.coremetrics.vo.HealthCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.coremetrics.vo.HealthCoreMetricsRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.coremetrics.vo.HealthCoreMetricsRespVO.MetricVO;
import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.coremetrics.vo.HealthCoreMetricsRespVO.TrendPointVO;
import cn.iocoder.yudao.module.industry.dal.mysql.health.dashboard.global.coremetrics.HealthCoreMetricsMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Validated
public class HealthCoreMetricsServiceImpl implements HealthCoreMetricsService {

    @Resource
    private HealthCoreMetricsMapper mapper;

    @Override
    public HealthCoreMetricsRespVO getHealthCoreMetrics(HealthCoreMetricsQueryReqVO reqVO) {
        HealthCoreMetricsRespVO respVO = new HealthCoreMetricsRespVO();

        // 最近 N 天，用于生成趋势列表和查询时间范围
        int days = 30;
        LocalDate today = LocalDate.now(); // 当前日期

        // ============================
        // 1. 门诊诊疗人次
        // ============================
        MetricVO outpatient = new MetricVO();

        // 获取门诊当前值
        outpatient.setCurrentValue(mapper.selectOutpatientVolume(reqVO));
        // 根据当前值计算状态，例如“达标/未达标”
        outpatient.setStatus(calcStatus(outpatient.getCurrentValue()));

        // 查询门诊趋势数据（只包含有数据的日期）
        List<TrendPointVO> outpatientTrend = mapper.selectOutpatientTrend(reqVO);
        // 将趋势列表按日期映射成 Map，便于快速查找
        Map<String, TrendPointVO> outpatientMap = outpatientTrend.stream()
                .collect(Collectors.toMap(TrendPointVO::getDate, tp -> tp));

        // 填充最近 N 天的数据，如果某天没有数据，用 0 填充
        List<TrendPointVO> outpatientFullTrend = new ArrayList<>();
        for (int i = days - 1; i >= 0; i--) {
            LocalDate date = today.minusDays(i);
            String dateStr = date.toString(); // yyyy-MM-dd
            TrendPointVO tp = outpatientMap.getOrDefault(dateStr, new TrendPointVO());
            if (tp.getDate() == null) tp.setDate(dateStr);         // 设置日期
            if (tp.getValue() == null) tp.setValue(BigDecimal.ZERO); // 无数据则置 0
            outpatientFullTrend.add(tp);
        }
        outpatient.setTrendList(outpatientFullTrend);
        respVO.setOutpatientVolume(outpatient);

        // ============================
        // 2. 住院人次
        // ============================
        MetricVO inpatient = new MetricVO();

        // 获取住院人次当前值
        inpatient.setCurrentValue(mapper.selectInpatientVolume(reqVO));
        // 计算状态
        inpatient.setStatus(calcStatus(inpatient.getCurrentValue()));

        // 查询住院趋势数据（只包含有数据的日期）
        List<TrendPointVO> inpatientTrend = mapper.selectInpatientTrend(reqVO);
        // 将趋势列表按日期映射成 Map，便于快速查找
        Map<String, TrendPointVO> inpatientMap = inpatientTrend.stream()
                .collect(Collectors.toMap(TrendPointVO::getDate, tp -> tp));

        // 填充最近 N 天的数据，如果某天没有数据，用 0 填充
        List<TrendPointVO> inpatientFullTrend = new ArrayList<>();
        for (int i = days - 1; i >= 0; i--) {
            LocalDate date = today.minusDays(i);
            String dateStr = date.toString();
            TrendPointVO tp = inpatientMap.getOrDefault(dateStr, new TrendPointVO());
            if (tp.getDate() == null) tp.setDate(dateStr);
            if (tp.getValue() == null) tp.setValue(BigDecimal.ZERO);
            inpatientFullTrend.add(tp);
        }
        inpatient.setTrendList(inpatientFullTrend);
        respVO.setInpatientVolume(inpatient);

        // ============================
        // 3. 家庭医生签约率
        // ============================
        MetricVO family = new MetricVO();

        // 获取当前值
        family.setCurrentValue(mapper.selectFamilyDoctorSignRate(reqVO));
        // 计算状态
        family.setStatus(calcStatus(family.getCurrentValue()));

        // 查询趋势数据
        List<TrendPointVO> familyTrend = mapper.selectFamilyDoctorTrend(reqVO);
        // 映射成 Map
        Map<String, TrendPointVO> familyMap = familyTrend.stream()
                .collect(Collectors.toMap(TrendPointVO::getDate, tp -> tp));

        // 填充最近 N 天的数据
        List<TrendPointVO> familyFullTrend = new ArrayList<>();
        for (int i = days - 1; i >= 0; i--) {
            LocalDate date = today.minusDays(i);
            String dateStr = date.toString();
            TrendPointVO tp = familyMap.getOrDefault(dateStr, new TrendPointVO());
            if (tp.getDate() == null) tp.setDate(dateStr);
            if (tp.getValue() == null) tp.setValue(BigDecimal.ZERO);
            familyFullTrend.add(tp);
        }
        family.setTrendList(familyFullTrend);
        respVO.setFamilyDoctorSignRate(family);

        // ============================
        // 4. 疫苗接种完成率
        // ============================
        MetricVO vaccine = new MetricVO();

        // 获取当前值
        vaccine.setCurrentValue(mapper.selectVaccineCompleteRate(reqVO));
        // 计算状态
        vaccine.setStatus(calcStatus(vaccine.getCurrentValue()));

        // 查询趋势数据
        List<TrendPointVO> vaccineTrend = mapper.selectVaccineTrend(reqVO);
        Map<String, TrendPointVO> vaccineMap = vaccineTrend.stream()
                .collect(Collectors.toMap(TrendPointVO::getDate, tp -> tp));

        // 填充最近 N 天的数据
        List<TrendPointVO> vaccineFullTrend = new ArrayList<>();
        for (int i = days - 1; i >= 0; i--) {
            LocalDate date = today.minusDays(i);
            String dateStr = date.toString();
            TrendPointVO tp = vaccineMap.getOrDefault(dateStr, new TrendPointVO());
            if (tp.getDate() == null) tp.setDate(dateStr);
            if (tp.getValue() == null) tp.setValue(BigDecimal.ZERO);
            vaccineFullTrend.add(tp);
        }
        vaccine.setTrendList(vaccineFullTrend);
        respVO.setVaccineCompleteRate(vaccine);

        // ============================
        // 5. 传染病报告及时率
        // ============================
        MetricVO infect = new MetricVO();

        // 获取当前值
        infect.setCurrentValue(mapper.selectInfectRptTimelyRate(reqVO));
        // 计算状态
        infect.setStatus(calcStatus(infect.getCurrentValue()));

        // 查询趋势数据
        List<TrendPointVO> infectTrend = mapper.selectInfectTrend(reqVO);
        Map<String, TrendPointVO> infectMap = infectTrend.stream()
                .collect(Collectors.toMap(TrendPointVO::getDate, tp -> tp));

        // 填充最近 N 天的数据
        List<TrendPointVO> infectFullTrend = new ArrayList<>();
        for (int i = days - 1; i >= 0; i--) {
            LocalDate date = today.minusDays(i);
            String dateStr = date.toString();
            TrendPointVO tp = infectMap.getOrDefault(dateStr, new TrendPointVO());
            if (tp.getDate() == null) tp.setDate(dateStr);
            if (tp.getValue() == null) tp.setValue(BigDecimal.ZERO);
            infectFullTrend.add(tp);
        }
        infect.setTrendList(infectFullTrend);
        respVO.setInfectRptTimelyRate(infect);

        return respVO;
    }

    /**
     * 指标达标状态计算
     */
    private String calcStatus(java.math.BigDecimal value) {
        if (value == null) {
            return "正常";
        }
        if (value.compareTo(new java.math.BigDecimal("90")) >= 0) {
            return "达标";
        }
        return "未达标";
    }
}
