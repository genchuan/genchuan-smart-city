package cn.iocoder.yudao.module.industry.service.emergency.dashboard.global.coremetrics;


import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.coremetrics.vo.EmergCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.coremetrics.vo.EmergCoreMetricsRespVO;
import cn.iocoder.yudao.module.industry.dal.mysql.emergency.dashboard.global.coremetrics.EmergCoreMetricsMapper;
import cn.iocoder.yudao.module.industry.framework.util.StatCycleUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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

    @Override
    public EmergCoreMetricsRespVO getEmergCoreMetrics(EmergCoreMetricsQueryReqVO emergCoreMetricsQueryReqVO) {


        return emergCoreMetricsMapper.getEmergCoreMetrics(emergCoreMetricsQueryReqVO);
    }


}
