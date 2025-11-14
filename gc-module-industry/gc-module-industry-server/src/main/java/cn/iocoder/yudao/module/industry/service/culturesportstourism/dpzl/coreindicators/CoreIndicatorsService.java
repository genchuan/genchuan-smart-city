package cn.iocoder.yudao.module.industry.service.culturesportstourism.dpzl.coreindicators;

import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.coreindicators.vo.CoreIndicatorsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.coreindicators.vo.CoreIndicatorsRespVO;

public interface CoreIndicatorsService {

    /**
     * 获取文旅核心指标数据
     * @param queryVO 查询参数（包含时间周期）
     * @return 核心指标数据
     */
    CoreIndicatorsRespVO getCoreIndicators(CoreIndicatorsQueryReqVO queryVO);
}