// ServiceItemsServiceImpl.java
package cn.iocoder.yudao.module.industry.service.businessservices.dpzl.serviceitems;

import cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.serviceitems.vo.BusiServiceItemsRespVO;
import cn.iocoder.yudao.module.industry.dal.mysql.businessservices.dpzl.serviceitems.BusiServiceItemsMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Map;

@Service
public class BusiServiceItemsServiceImpl implements BusiServiceItemsService {

    @Resource
    private BusiServiceItemsMapper busiServiceItemsMapper;

    @Override
    public BusiServiceItemsRespVO getServiceItemsOverview() {
        BusiServiceItemsRespVO respVO = new BusiServiceItemsRespVO();

        // 服务事项总数
        respVO.setTotalSvcCount(busiServiceItemsMapper.selectTotalSvcCount());

        // 当日待办量
        respVO.setDailyPendCount(busiServiceItemsMapper.selectDailyPendCount());

        // 事项办结率
        respVO.setSvcCompleteRate(busiServiceItemsMapper.selectSvcCompleteRate());

        // 超期事项数
        respVO.setOverdueSvcCount(busiServiceItemsMapper.selectOverdueSvcCount());

        // 事项类型分布 - 将Map转换为List
        Map<String, Map<String, Object>> typeDistributionMap = busiServiceItemsMapper.selectTypeDistribution();
        respVO.setTypeDistribution(new ArrayList<>(typeDistributionMap.values()));

        // 近24小时事项办理趋势 - 将Map转换为List
        Map<Integer, Map<String, Object>> hourlyTrendMap = busiServiceItemsMapper.selectHourlyTrend();
        respVO.setHourlyTrend(new ArrayList<>(hourlyTrendMap.values()));

        return respVO;
    }

}