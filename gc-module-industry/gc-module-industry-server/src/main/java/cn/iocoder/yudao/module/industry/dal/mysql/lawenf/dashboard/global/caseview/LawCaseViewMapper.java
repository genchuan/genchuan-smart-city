package cn.iocoder.yudao.module.industry.dal.mysql.lawenf.dashboard.global.caseview;

import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.caseview.vo.LawCaseViewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.caseview.vo.LawCaseViewRespVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 执法案件总览 Mapper
 * 用于提供案件类型分布、案件来源分布、办理进度分布及超期案件统计等查询。
 * 对应首页总览图表数据来源。
 * 入参：LawCaseViewQueryReqVO（区域、时间范围、案件筛选条件等）
 * 出参：封装在 LawCaseViewRespVO 的内部静态类中。
 * author: lxs
 */
@Mapper
public interface LawCaseViewMapper {

        /**
         * 查询案件类型分布统计
         *
         * @param reqVO 查询条件（区域、时间、筛选条件）
         * @return 案件类型统计列表
         */
        List<LawCaseViewRespVO.CaseTypeCount> selectCaseTypeCount(LawCaseViewQueryReqVO reqVO);


        /**
         * 查询案件来源分布统计
         *
         * @param reqVO 查询条件（区域、时间、筛选条件）
         * @return 案件来源统计列表
         */
        List<LawCaseViewRespVO.CaseSourceCount> selectCaseSourceCount(LawCaseViewQueryReqVO reqVO);


        /**
         * 查询案件办理进度分布统计
         *
         * @param reqVO 查询条件（区域、时间、筛选条件）
         * @return 办理进度统计列表
         */
        List<LawCaseViewRespVO.ProgressStageCount> selectProgressStageCount(LawCaseViewQueryReqVO reqVO);


        /**
         * 查询超期未办结案件数量
         *
         * @param reqVO 查询条件（区域、时间、筛选条件）
         * @return 超期案件数
         */
        Integer selectOverdueCaseCount(LawCaseViewQueryReqVO reqVO);

}
