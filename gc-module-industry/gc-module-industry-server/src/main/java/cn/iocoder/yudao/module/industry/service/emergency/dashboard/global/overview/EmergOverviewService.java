package cn.iocoder.yudao.module.industry.service.emergency.dashboard.global.overview;

import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.overview.vo.EmergOverviewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.overview.vo.EmergOverviewRespVO;

/**
 * 应急全域数据概览 Service 接口
 *
 * @author lxs
 */
public interface EmergOverviewService {
    /**
     * 创建预警告警规则配置
     *
     * @param emergOverviewQueryReqVO 查询信息
     * @return 应急全域数据概览
     */
    EmergOverviewRespVO getEmergOverview(EmergOverviewQueryReqVO emergOverviewQueryReqVO);
}
