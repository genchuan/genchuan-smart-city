package cn.iocoder.yudao.module.industry.service.health.dashboard.global.medinst;


import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.medinst.vo.MedInstQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.medinst.vo.MedInstRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.medinst.vo.TreatTrendVO;

import java.util.List;

/**
 * 医疗机构分布视图 Service 接口
 *
 * @author lxs
 */
public interface MedInstService {

        /**
         * 查询医疗机构分布视图
         *
         * @param medInstQueryReqVO 查询条件
         * @return 查询结果
         */
        MedInstRespVO getMedInst(MedInstQueryReqVO medInstQueryReqVO);

        List<TreatTrendVO> getInstTrend(Long orgId);
}
