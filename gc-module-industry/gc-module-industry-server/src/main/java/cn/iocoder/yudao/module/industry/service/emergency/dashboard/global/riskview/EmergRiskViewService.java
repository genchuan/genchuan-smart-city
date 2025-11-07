package cn.iocoder.yudao.module.industry.service.emergency.dashboard.global.riskview;


import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.riskview.vo.EmergRiskViewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.riskview.vo.EmergRiskViewRespVO;

import java.util.List;

/**
 * 风险分布视图 Service 接口
 *
 * @author lxs
 */
public interface EmergRiskViewService {

    /**
     * 查询风险分布视图
     *
     * @param emergRiskViewQueryReqVO 查询条件
     * @return 查询结果
     */
    List<EmergRiskViewRespVO> listEmergRiskView(EmergRiskViewQueryReqVO emergRiskViewQueryReqVO);
}
