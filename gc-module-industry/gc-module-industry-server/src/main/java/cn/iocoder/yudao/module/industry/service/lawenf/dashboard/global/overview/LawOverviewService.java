package cn.iocoder.yudao.module.industry.service.lawenf.dashboard.global.overview;


import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.overview.vo.LawOverviewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.overview.vo.LawOverviewRespVO;

/**
 * 执法全域数据概览 Service 接口
 *
 * @author lxs
 */
public interface LawOverviewService {

        /**
         * 查询执法全域数据概览
         *
         * @param lawOverviewQueryReqVO 查询条件
         * @return 查询结果
         */
        LawOverviewRespVO getLawOverview(LawOverviewQueryReqVO lawOverviewQueryReqVO);
}
