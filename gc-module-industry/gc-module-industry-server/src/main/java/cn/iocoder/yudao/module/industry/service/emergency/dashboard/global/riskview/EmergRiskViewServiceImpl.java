package cn.iocoder.yudao.module.industry.service.emergency.dashboard.global.riskview;


import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.riskview.vo.EmergRiskViewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.riskview.vo.EmergRiskViewRespVO;

import cn.iocoder.yudao.module.industry.dal.mysql.emergency.dashboard.global.riskview.EmergRiskViewMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 风险分布视图 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class EmergRiskViewServiceImpl implements EmergRiskViewService {

    @Resource
    private EmergRiskViewMapper emergRiskViewMapper;

    @Override
    public List<EmergRiskViewRespVO> listEmergRiskView(EmergRiskViewQueryReqVO emergRiskViewQueryReqVO) {
        return emergRiskViewMapper.listEmergRiskView(emergRiskViewQueryReqVO);
    }
}
