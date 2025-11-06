package cn.iocoder.yudao.module.industry.dal.mysql.emergency.dashboard.global.riskview;


import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.riskview.vo.EmergRiskViewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.riskview.vo.EmergRiskViewRespVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 风险分布视图 Mapper
 *
 * @author lxs
 */
@Mapper
public interface EmergRiskViewMapper {

    /**
     * 查询风险分布视图
     *
     * @param emergRiskViewQueryReqVO 查询参数
     * @return EmergRiskViewRespVO 结果
     */
    List<EmergRiskViewRespVO> listEmergRiskView(EmergRiskViewQueryReqVO emergRiskViewQueryReqVO);

}
