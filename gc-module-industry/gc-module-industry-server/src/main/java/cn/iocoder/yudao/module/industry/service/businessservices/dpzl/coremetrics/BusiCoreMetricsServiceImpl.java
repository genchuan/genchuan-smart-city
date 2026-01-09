package cn.iocoder.yudao.module.industry.service.businessservices.dpzl.coremetrics;

import cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.coremetrics.vo.BusiCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.coremetrics.vo.BusiCoreMetricsRespVO;
import cn.iocoder.yudao.module.industry.dal.mysql.businessservices.dpzl.coremetrics.BusiCoreMetricsMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

@Service
@Validated
public class BusiCoreMetricsServiceImpl implements BusiCoreMetricsService {

    @Resource
    private BusiCoreMetricsMapper BusiCoreMetricsMapper;

    @Override
    public BusiCoreMetricsRespVO getKeyIndicatorsData(BusiCoreMetricsQueryReqVO queryReqVO) {
        BusiCoreMetricsRespVO respVO = new BusiCoreMetricsRespVO();

        // 1. 审批办结率
        respVO.setApprovalCompleteRate(
                BusiCoreMetricsMapper.selectLatestApprovalCompleteRate(queryReqVO.getStatCycle())
        );

        // 2. 政策兑现率
        respVO.setPolFulfillRate(
                BusiCoreMetricsMapper.selectLatestPolFulfillRate(queryReqVO.getStatCycle())
        );

        // 3. 企业满意度
        respVO.setEntSatisfy(
                BusiCoreMetricsMapper.selectLatestEntSatisfy(queryReqVO.getStatCycle())
        );

        // 4. 诉求超期率
        respVO.setAppealOverdueRate(
                BusiCoreMetricsMapper.selectLatestAppealOverdueRate(queryReqVO.getStatCycle())
        );

        // 5. 各区域审批办结率对比
        respVO.setRegionApprovalRates(
                BusiCoreMetricsMapper.selectRegionApprovalRates(queryReqVO.getStatCycle())
        );

        // 6. 近30天政策兑现趋势
        respVO.setPolFulfillTrends(
                BusiCoreMetricsMapper.selectPolFulfillTrendsLast30Days()
        );

        return respVO;
    }
}