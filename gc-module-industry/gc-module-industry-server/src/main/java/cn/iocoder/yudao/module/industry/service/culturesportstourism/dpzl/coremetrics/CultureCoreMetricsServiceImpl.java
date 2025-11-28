package cn.iocoder.yudao.module.industry.service.culturesportstourism.dpzl.coremetrics;

import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.coreindicators.vo.CultureCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.coreindicators.vo.CultureCoreMetricsRespVO;
import cn.iocoder.yudao.module.industry.dal.mysql.culturesportstourism.dpzl.coremetrics.CultureCoreMetricsMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class CultureCoreMetricsServiceImpl implements CultureCoreMetricsService {

    @Resource
    private CultureCoreMetricsMapper CultureCoreMetricsMapper;

    @Override
    public CultureCoreMetricsRespVO getCoreIndicators(CultureCoreMetricsQueryReqVO queryVO) {
        // 直接查询数据库，不使用缓存
        CultureCoreMetricsRespVO result = new CultureCoreMetricsRespVO();
        // 查询文旅资源总数
        result.setTotalSceneCount(CultureCoreMetricsMapper.selectTotalSceneCount(queryVO.getTimeCycle()));
        // 查询当日客流峰值
        result.setMaxCount(CultureCoreMetricsMapper.selectMaxCount(queryVO.getTimeCycle()));
        // 查询投诉办结率
        result.setCompleteRate(CultureCoreMetricsMapper.selectCompleteRate(queryVO.getTimeCycle()));
        // 查询设施完好率
        result.setFacilityGoodRate(CultureCoreMetricsMapper.selectFacilityGoodRate(queryVO.getTimeCycle()));
        // 查询活动开展数
        result.setNewSceneCount(CultureCoreMetricsMapper.selectNewSceneCount(queryVO.getTimeCycle()));

        return result;
    }
}