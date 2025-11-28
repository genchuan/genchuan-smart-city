package cn.iocoder.yudao.module.industry.service.culturesportstourism.dpzl.passengerflow;

import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.passengerflow.vo.CulturePasFlowQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.passengerflow.vo.CulturePasFlowRespVO;
import cn.iocoder.yudao.module.industry.dal.mysql.culturesportstourism.dpzl.passengerflow.CulturePasFlowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CulturePasFlowServiceImpl implements CulturePasFlowService {

    private final CulturePasFlowMapper CulturePasFlowMapper;

    @Override
    public CulturePasFlowRespVO getPassengerFlowOverview(CulturePasFlowQueryReqVO queryVO) {
        CulturePasFlowRespVO result = new CulturePasFlowRespVO();

        // 实时客流总量
        result.setTotalRptCount(CulturePasFlowMapper.selectTotalRptCount(queryVO));

        // 客流峰值时段
        result.setMaxHour(CulturePasFlowMapper.selectMaxHour(queryVO));

        // 分时客流趋势
        result.setHourlyTrends(CulturePasFlowMapper.selectHourlyTrends(queryVO));

        // 区域客流分布
        result.setRegionDistributions(CulturePasFlowMapper.selectRegionDistributions(queryVO));

        return result;
    }

    @Override
    public Object getRegionOptions() {
        // 实际实现中需查询sys_area表获取区域层级数据
        // 格式示例: [{code: "110000", name: "北京市", children: [...]}, ...]
        return null;
    }
}