package cn.iocoder.yudao.module.industry.service.health.dashboard.global.overview;


import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.overview.vo.HealthOverviewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.overview.vo.HealthOverviewRespVO;

import cn.iocoder.yudao.module.industry.dal.mysql.health.dashboard.global.overview.HealthOverviewMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 卫健全域数据概览 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class HealthOverviewServiceImpl implements HealthOverviewService {

    @Resource
    private HealthOverviewMapper healthOverviewMapper;

    @Override
    public HealthOverviewRespVO getHealthOverview(HealthOverviewQueryReqVO healthOverviewQueryReqVO) {
            return healthOverviewMapper.getHealthOverview(healthOverviewQueryReqVO);
    }
}
