package cn.iocoder.yudao.module.industry.service.lawenf.dashboard.global.forceview;


import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.forceview.vo.LawForceViewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.forceview.vo.LawForceViewRespVO;

/**
 * 执法力量分布视图 Service 接口
 *
 * @author lxs
 */
public interface LawForceViewService {

        /**
         * 查询执法力量分布视图
         *
         * @param lawForceViewQueryReqVO 查询条件
         * @return 查询结果
         */
        LawForceViewRespVO getLawForceView(LawForceViewQueryReqVO lawForceViewQueryReqVO);
}
