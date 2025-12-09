package cn.iocoder.yudao.module.industry.service.lawenf.dashboard.global.caseview;

import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.caseview.vo.LawCaseViewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.caseview.vo.LawCaseViewRespVO;
import cn.iocoder.yudao.module.industry.dal.mysql.lawenf.dashboard.global.caseview.LawCaseViewMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;

@Service
@Validated
public class LawCaseViewServiceImpl implements LawCaseViewService {

    @Resource
    private LawCaseViewMapper lawCaseViewMapper;

    @Override
    public LawCaseViewRespVO getLawCaseView(LawCaseViewQueryReqVO reqVO) {

        // 查询各类统计
        List<LawCaseViewRespVO.CaseTypeCount> typeList =
                lawCaseViewMapper.selectCaseTypeCount(reqVO);

        List<LawCaseViewRespVO.CaseSourceCount> sourceList =
                lawCaseViewMapper.selectCaseSourceCount(reqVO);

        List<LawCaseViewRespVO.ProgressStageCount> progressList =
                lawCaseViewMapper.selectProgressStageCount(reqVO);

        Integer overdue = lawCaseViewMapper.selectOverdueCaseCount(reqVO);

        // 避免 null
        LawCaseViewRespVO respVO = new LawCaseViewRespVO();
        respVO.setTypeDistribution(typeList != null ? typeList : Collections.emptyList());
        respVO.setSourceDistribution(sourceList != null ? sourceList : Collections.emptyList());
        respVO.setProgressDistribution(progressList != null ? progressList : Collections.emptyList());
        respVO.setOverdueCaseCount(overdue != null ? overdue : 0);

        return respVO;
    }

    @Override
    public List<String> getLawCaseViewTypeList() {
        //直接req放空的，没关系
        LawCaseViewQueryReqVO reqVO = new LawCaseViewQueryReqVO();

        // 调用已有 Mapper 查询案件类型及数量
        List<LawCaseViewRespVO.CaseTypeCount> typeList = lawCaseViewMapper.selectCaseTypeCount(reqVO);

        // 提取案件类型字段，返回 List<String>
        if (typeList == null || typeList.isEmpty()) {
            return Collections.emptyList();
        }

        return typeList.stream()
                .map(LawCaseViewRespVO.CaseTypeCount::getCaseType)
                .distinct() // 去重，防止重复
                .toList();
    }
}
