package cn.iocoder.yudao.module.industry.service.health.dashboard.global.overview;


import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.overview.vo.HealthOverviewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.overview.vo.HealthOverviewRespVO;

/**
 * 卫健全域数据概览 Service 接口
 *
 * @author lxs
 */
public interface HealthOverviewService {

        /**
         * 查询卫健全域数据概览
         *
         * @param healthOverviewQueryReqVO 查询条件
         * @return 查询结果
         */
        HealthOverviewRespVO getHealthOverview(HealthOverviewQueryReqVO healthOverviewQueryReqVO);
}
