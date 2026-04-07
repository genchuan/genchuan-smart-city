package cn.iocoder.yudao.module.kitchen.service.violationanalytics;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.kitchen.controller.admin.violationanalytics.vo.page.ViolationAnalyticsPageReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.violationanalytics.vo.page.ViolationAnalyticsPageResp;
import cn.iocoder.yudao.module.kitchen.dal.mysql.violationanalytics.ViolationAnalyticsMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
public class ViolationAnalyticsServiceImpl implements ViolationAnalyticsService{
    @Resource
    private ViolationAnalyticsMapper violationAnalyticsMapper;


    @Override
    public PageResult<ViolationAnalyticsPageResp> getViolationAnalyticsPage(ViolationAnalyticsPageReq req) {
        // 1. 计算日/周/月 开始结束时间
        LocalDate now = LocalDate.now();
        LocalDate beginTime;
        LocalDate endTime = now;
        String period = req.getStatisticPeriod();

        if ("日".equals(period)) {
            beginTime = now;
        } else if ("周".equals(period)) {
            beginTime = now.with(DayOfWeek.MONDAY); // 周一
        } else if ("月".equals(period)) {
            beginTime = now.withDayOfMonth(1);     // 月初
        } else {
            beginTime = now;
        }
        req.setBeginTime(beginTime);
        req.setEndTime(endTime);

        // ========== 分页拆成两步 ========== TODO
        // 2. 第一步：查询列表 List
        List<ViolationAnalyticsPageResp> list = violationAnalyticsMapper.selectViolationAnalyticsList(req);

        // 3. 第二步：查询总数 total
        Long total = violationAnalyticsMapper.selectViolationAnalyticsCount(req);

        // 回填统计维度与时间
        list.forEach(resp -> {
            resp.setStatisticPeriod(period);
            resp.setBeginTime(beginTime);
            resp.setEndTime(endTime);
        });

        // 4. 组装分页返回
        return new PageResult<>(list, total);
    }

}
