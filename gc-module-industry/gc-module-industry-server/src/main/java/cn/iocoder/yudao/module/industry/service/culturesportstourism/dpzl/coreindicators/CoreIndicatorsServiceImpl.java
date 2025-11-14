package cn.iocoder.yudao.module.industry.service.culturesportstourism.dpzl.coreindicators;

import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.coreindicators.vo.CoreIndicatorsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.coreindicators.vo.CoreIndicatorsRespVO;
import cn.iocoder.yudao.module.industry.dal.mysql.culturesportstourism.dpzl.coreindicators.CoreIndicatorsMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class CoreIndicatorsServiceImpl implements CoreIndicatorsService {

    @Resource
    private CoreIndicatorsMapper coreIndicatorsMapper;

    @Override
    public CoreIndicatorsRespVO getCoreIndicators(CoreIndicatorsQueryReqVO queryVO) {
        // 直接查询数据库，不使用缓存
        CoreIndicatorsRespVO result = new CoreIndicatorsRespVO();
        // 查询文旅资源总数
        result.setTotalSceneCount(coreIndicatorsMapper.selectTotalSceneCount(queryVO.getTimeCycle()));
        // 查询当日客流峰值
        result.setMaxCount(coreIndicatorsMapper.selectMaxCount(queryVO.getTimeCycle()));
        // 查询投诉办结率
        result.setCompleteRate(coreIndicatorsMapper.selectCompleteRate(queryVO.getTimeCycle()));
        // 查询设施完好率
        result.setFacilityGoodRate(coreIndicatorsMapper.selectFacilityGoodRate(queryVO.getTimeCycle()));
        // 查询活动开展数
        result.setNewSceneCount(coreIndicatorsMapper.selectNewSceneCount(queryVO.getTimeCycle()));

        return result;
    }
}