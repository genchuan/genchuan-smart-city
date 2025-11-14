package cn.iocoder.yudao.module.industry.service.culturesportstourism.dpzl.passengerflow;

import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.passengerflow.vo.PassengerFlowQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.passengerflow.vo.PassengerFlowRespVO;
import cn.iocoder.yudao.module.industry.dal.mysql.culturesportstourism.dpzl.passengerflow.PassengerFlowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PassengerFlowServiceImpl implements PassengerFlowService {

    private final PassengerFlowMapper passengerFlowMapper;

    @Override
    public PassengerFlowRespVO getPassengerFlowOverview(PassengerFlowQueryReqVO queryVO) {
        PassengerFlowRespVO result = new PassengerFlowRespVO();

        // 实时客流总量
        result.setTotalRptCount(passengerFlowMapper.selectTotalRptCount(queryVO));

        // 客流峰值时段
        result.setMaxHour(passengerFlowMapper.selectMaxHour(queryVO));

        // 分时客流趋势
        result.setHourlyTrends(passengerFlowMapper.selectHourlyTrends(queryVO));

        // 区域客流分布
        result.setRegionDistributions(passengerFlowMapper.selectRegionDistributions(queryVO));

        return result;
    }

    @Override
    public Object getRegionOptions() {
        // 实际实现中需查询sys_area表获取区域层级数据
        // 格式示例: [{code: "110000", name: "北京市", children: [...]}, ...]
        return null;
    }
}