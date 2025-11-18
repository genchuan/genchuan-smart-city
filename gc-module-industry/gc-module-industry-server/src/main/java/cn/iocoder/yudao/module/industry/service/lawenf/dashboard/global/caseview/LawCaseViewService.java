package cn.iocoder.yudao.module.industry.service.lawenf.dashboard.global.caseview;


import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.caseview.vo.LawCaseViewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.caseview.vo.LawCaseViewRespVO;

/**
 * 执法案件总览 Service 接口
 *
 * @author lxs
 */
public interface LawCaseViewService {

        /**
         * 查询执法案件总览
         *
         * @param lawCaseViewQueryReqVO 查询条件
         * @return 查询结果
         */
        LawCaseViewRespVO getLawCaseView(LawCaseViewQueryReqVO lawCaseViewQueryReqVO);
}
