package cn.iocoder.yudao.module.industry.service.lawenf.dashboard.global.caseview;


import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.caseview.vo.LawCaseViewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.caseview.vo.LawCaseViewRespVO;

import java.util.List;

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

        /**
         * 查询执法案件的案件类型列表
         * 主要用于前端筛选条件或下拉项展示
         */
        List<String> getLawCaseViewTypeList();


        /**
         * 查询执法案件的案件来源列表
         * 用于前端筛选条件或统计展示
         */
        List<String> getLawCaseViewSourceList();

}
