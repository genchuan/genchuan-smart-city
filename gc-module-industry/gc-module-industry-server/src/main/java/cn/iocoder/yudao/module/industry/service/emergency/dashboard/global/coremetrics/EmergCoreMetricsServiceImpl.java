package cn.iocoder.yudao.module.industry.service.emergency.dashboard.global.coremetrics;


import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.coremetrics.vo.EmergCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.coremetrics.vo.EmergCoreMetricsRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.coremetrics.vo.TimeValuePoint;
import cn.iocoder.yudao.module.industry.dal.mysql.emergency.dashboard.global.coremetrics.EmergCoreMetricsMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 应急核心指标 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class EmergCoreMetricsServiceImpl implements EmergCoreMetricsService {

    @Resource
    private EmergCoreMetricsMapper emergCoreMetricsMapper;

//    @Override
//    public EmergCoreMetricsRespVO getEmergCoreMetrics(EmergCoreMetricsQueryReqVO emergCoreMetricsQueryReqVO) {
//
//        EmergCoreMetricsRespVO respVO= emergCoreMetricsMapper.getEmergCoreMetrics(emergCoreMetricsQueryReqVO);
////        respVO.setUpdateTime(emergCoreMetricsMapper.getEmergCoreMetricsUpdateTime(emergCoreMetricsQueryReqVO));
//        return respVO;
//    }

    @Override
    public EmergCoreMetricsRespVO getEmergCoreMetrics(EmergCoreMetricsQueryReqVO reqVO) {

        EmergCoreMetricsRespVO respVO=new EmergCoreMetricsRespVO();
        //获取总的信息,缺少趋势数据
        respVO=emergCoreMetricsMapper.getEmergCoreMetrics(reqVO);
        // 趋势展示天数，默认为7天
        int trendDays =6;

        // 一. 应急事件办结率信息
        // 1.查询指定时间区间内已有的办结率趋势数据
        List<TimeValuePoint> dbList = emergCoreMetricsMapper.getEmergEventCompleteRateTrendList(reqVO);
        // 2. 补充缺失日期并生成按顺序排列的完整趋势列表
        //    （1）如果某天没有数据，则默认填充 BigDecimal.ZERO
        //    （2）默认展示最近 7 天的趋势，如果 reqVO 中指定了时间区间，则使用指定区间
        List<TimeValuePoint> trendList = buildTrendList(reqVO, dbList, trendDays); // 默认最近7天
        // 3. 将趋势列表回填到响应对象中
        respVO.getEmergEventCompleteRate().setTrendList(trendList);

        //二、预警准确率信息
        // 1.查询指定时间区间内已有的办结率趋势数据
        List<TimeValuePoint> dbList2 = emergCoreMetricsMapper.getEarlyWarnAccRateTrendList(reqVO);
        // 2. 补充缺失日期并生成按顺序排列的完整趋势列表
        //    （1）如果某天没有数据，则默认填充 BigDecimal.ZERO
        //    （2）默认展示最近 7 天的趋势，如果 reqVO 中指定了时间区间，则使用指定区间
        List<TimeValuePoint> trendList2 = buildTrendList(reqVO, dbList2, trendDays); // 默认最近7天
        // 3. 将趋势列表回填到响应对象中
        respVO.getEarlyWarnAccRate().setTrendList(trendList2);

        // 三、资源调用率信息
        // 1.查询指定时间区间内已有的资源调用率趋势数据
        List<TimeValuePoint> dbList3 = emergCoreMetricsMapper.getResUseRateTrendList(reqVO);
        // 2. 补充缺失日期并生成按顺序排列的完整趋势列表
        //    （1）如果某天没有数据，则默认填充 BigDecimal.ZERO
        //    （2）默认展示最近 7 天的趋势，如果 reqVO 中指定了时间区间，则使用指定区间
        List<TimeValuePoint> trendList3 = buildTrendList(reqVO, dbList3, trendDays); // 默认最近7天
        // 3. 将趋势列表回填到响应对象中
        respVO.getResUseRate().setTrendList(trendList3);

        // 四、风险整改率信息
        // 1.查询指定时间区间内已有的风险整改率趋势数据
        List<TimeValuePoint> dbList4 = emergCoreMetricsMapper.getRiskRectifyRateTrendList(reqVO);
        // 2. 补充缺失日期并生成按顺序排列的完整趋势列表
        //    （1）如果某天没有数据，则默认填充 BigDecimal.ZERO
        //    （2）默认展示最近 7 天的趋势，如果 reqVO 中指定了时间区间，则使用指定区间
        List<TimeValuePoint> trendList4 = buildTrendList(reqVO, dbList4, trendDays); // 默认最近7天
        // 3. 将趋势列表回填到响应对象中
        respVO.getRiskRectifyRate().setTrendList(trendList4);


        return respVO;

    }

    /**
     * 构建完整的趋势列表（按日期顺序，缺失日期补零）
     *
     * @param reqVO       查询条件，包含开始时间和结束时间
     * @param dbList      已存在的趋势数据
     * @param trendDays 趋势展示天数
     * @return 按日期顺序排列的完整趋势列表
     */
    private List<TimeValuePoint> buildTrendList(EmergCoreMetricsQueryReqVO reqVO,
                                                List<TimeValuePoint> dbList,
                                                int trendDays) {
        // 将已有数据dbList转换成 Map，方便按日期快速查找
        Map<LocalDate, TimeValuePoint> dbMap = dbList.stream()
                .collect(Collectors.toMap(TimeValuePoint::getDate, tvp -> tvp));

        // 计算结束日期，如果有筛选时间就用，否则为今天
        LocalDate endDate = reqVO.getEndTime() != null ? reqVO.getEndTime().toLocalDate() : LocalDate.now();
        // 计算起始日期=结束日期往前默认天数
        LocalDate startDate = endDate.minusDays(trendDays);
        // 按天遍历日期范围，生成完整列表
        List<TimeValuePoint> finalTrendList = new java.util.ArrayList<>();
        LocalDate current = startDate;
        while (!current.isAfter(endDate)) {
            // 如果已有数据则使用已有数据，否则补充默认值 0
            TimeValuePoint tvp = dbMap.getOrDefault(current, new TimeValuePoint(current, BigDecimal.ZERO));
            finalTrendList.add(tvp);
            current = current.plusDays(1);
        }
        return finalTrendList;
    }


}
