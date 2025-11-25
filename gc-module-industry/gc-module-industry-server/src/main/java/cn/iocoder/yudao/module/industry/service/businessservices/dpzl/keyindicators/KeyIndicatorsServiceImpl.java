package cn.iocoder.yudao.module.industry.service.businessservices.dpzl.keyindicators;

import cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.keyindicators.vo.KeyIndicatorsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.keyindicators.vo.KeyIndicatorsRespVO;
import cn.iocoder.yudao.module.industry.dal.mysql.businessservices.dpzl.keyindicators.KeyIndicatorsMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

@Service
@Validated
public class KeyIndicatorsServiceImpl implements KeyIndicatorsService {

    @Resource
    private KeyIndicatorsMapper keyIndicatorsMapper;

    @Override
    public KeyIndicatorsRespVO getKeyIndicatorsData(KeyIndicatorsQueryReqVO queryReqVO) {
        KeyIndicatorsRespVO respVO = new KeyIndicatorsRespVO();

        // 1. 审批办结率
        respVO.setApprovalCompleteRate(
                keyIndicatorsMapper.selectLatestApprovalCompleteRate(queryReqVO.getStatCycle())
        );

        // 2. 政策兑现率
        respVO.setPolFulfillRate(
                keyIndicatorsMapper.selectLatestPolFulfillRate(queryReqVO.getStatCycle())
        );

        // 3. 企业满意度
        respVO.setEntSatisfy(
                keyIndicatorsMapper.selectLatestEntSatisfy(queryReqVO.getStatCycle())
        );

        // 4. 诉求超期率
        respVO.setAppealOverdueRate(
                keyIndicatorsMapper.selectLatestAppealOverdueRate(queryReqVO.getStatCycle())
        );

        // 5. 各区域审批办结率对比
        respVO.setRegionApprovalRates(
                keyIndicatorsMapper.selectRegionApprovalRates(queryReqVO.getStatCycle())
        );

        // 6. 近30天政策兑现趋势
        respVO.setPolFulfillTrends(
                keyIndicatorsMapper.selectPolFulfillTrendsLast30Days()
        );

        return respVO;
    }
}