package cn.iocoder.yudao.module.industry.service.culturesportstourism.dpzl.coremetrics;

import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.coreindicators.vo.CultureCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.coreindicators.vo.CultureCoreMetricsRespVO;
import cn.iocoder.yudao.module.industry.dal.mysql.culturesportstourism.dpzl.coremetrics.CultureCoreMetricsMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class CultureCoreMetricsServiceImpl implements CultureCoreMetricsService {

    @Resource
    private CultureCoreMetricsMapper cultureCoreMetricsMapper;

    @Override
    public CultureCoreMetricsRespVO getCoreIndicators(CultureCoreMetricsQueryReqVO queryVO) {
        // 空值判断，默认 recent7
        if (queryVO.getTimeCycle() == null || queryVO.getTimeCycle().isBlank()) {
            queryVO.setTimeCycle("recent7");
        }

        // 解析时间周期
        LocalDateTime now = LocalDateTime.now();
        switch (queryVO.getTimeCycle()) {
            case "today":
                queryVO.setStartTime(now.toLocalDate().atStartOfDay());
                queryVO.setEndTime(now.toLocalDate().atTime(23, 59, 59));
                break;
            case "yesterday":
                LocalDateTime yesterday = now.minusDays(1);
                queryVO.setStartTime(yesterday.toLocalDate().atStartOfDay());
                queryVO.setEndTime(yesterday.toLocalDate().atTime(23, 59, 59));
                break;
            case "recent7":
                queryVO.setStartTime(now.minusDays(6).toLocalDate().atStartOfDay()); // 包含今天
                queryVO.setEndTime(now.toLocalDate().atTime(23, 59, 59));
                break;
            case "recent30":
                queryVO.setStartTime(now.minusDays(29).toLocalDate().atStartOfDay());
                queryVO.setEndTime(now.toLocalDate().atTime(23, 59, 59));
                break;
            default:
                // 如果输入非法，默认 recent7
                queryVO.setStartTime(now.minusDays(6).toLocalDate().atStartOfDay());
                queryVO.setEndTime(now.toLocalDate().atTime(23, 59, 59));
                break;
        }

        // 直接查询数据库
        CultureCoreMetricsRespVO result = new CultureCoreMetricsRespVO();
        // 查询文旅资源总数
        result.setTotalSceneCount(cultureCoreMetricsMapper.selectTotalSceneCount(queryVO));
        // 查询当日客流峰值
        result.setMaxCount(cultureCoreMetricsMapper.selectMaxCount(queryVO));
        // 查询投诉办结率
        result.setCompleteRate(cultureCoreMetricsMapper.selectCompleteRate(queryVO));
        // 查询设施完好率
        result.setFacilityGoodRate(cultureCoreMetricsMapper.selectFacilityGoodRate(queryVO));
        // 查询活动开展数
        result.setNewSceneCount(cultureCoreMetricsMapper.selectNewSceneCount(queryVO));

        return result;
    }
}
