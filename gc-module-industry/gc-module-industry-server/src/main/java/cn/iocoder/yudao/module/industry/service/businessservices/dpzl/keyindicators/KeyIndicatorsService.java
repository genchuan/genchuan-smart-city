package cn.iocoder.yudao.module.industry.service.businessservices.dpzl.keyindicators;

import cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.keyindicators.vo.KeyIndicatorsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.keyindicators.vo.KeyIndicatorsRespVO;

public interface KeyIndicatorsService {

    /**
     * 获取关键指标展示数据
     *
     * @param queryReqVO 查询参数
     * @return 关键指标数据
     */
    KeyIndicatorsRespVO getKeyIndicatorsData(KeyIndicatorsQueryReqVO queryReqVO);
}