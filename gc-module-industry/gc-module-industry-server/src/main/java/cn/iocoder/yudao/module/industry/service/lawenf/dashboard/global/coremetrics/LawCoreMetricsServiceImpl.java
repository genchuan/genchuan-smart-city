package cn.iocoder.yudao.module.industry.service.lawenf.dashboard.global.coremetrics;


import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.coremetrics.vo.LawCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.coremetrics.vo.LawCoreMetricsRespVO;

import cn.iocoder.yudao.module.industry.dal.mysql.lawenf.dashboard.global.coremetrics.LawCoreMetricsMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 执法核心指标 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class LawCoreMetricsServiceImpl implements LawCoreMetricsService {

    @Resource
    private LawCoreMetricsMapper lawCoreMetricsMapper;

    @Override
    public LawCoreMetricsRespVO getLawCoreMetrics(LawCoreMetricsQueryReqVO lawCoreMetricsQueryReqVO) {
            return lawCoreMetricsMapper.getLawCoreMetrics(lawCoreMetricsQueryReqVO);
    }
}
