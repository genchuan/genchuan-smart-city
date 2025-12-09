package cn.iocoder.yudao.module.industry.service.lawenf.dashboard.global.overview;


import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.overview.vo.*;

import cn.iocoder.yudao.module.industry.dal.mysql.lawenf.dashboard.global.overview.LawOverviewMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 执法全域数据概览 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class LawOverviewServiceImpl implements LawOverviewService {

    @Resource
    private LawOverviewMapper lawOverviewMapper;

    @Override
    public LawOverviewRespVO getLawOverview(LawOverviewQueryReqVO lawOverviewQueryReqVO) {
        // 1. 获取前端传入的 timeRange 参数
        String timeRange = lawOverviewQueryReqVO.getTimeRange();

        // 2. 判断 timeRange 是否非空
        if (StringUtils.hasText(timeRange)) {
            LocalDateTime startTime = null;
            LocalDateTime endTime = null;
            LocalDate today = LocalDate.now();

            // 3. 根据 timeRange 值计算起始时间和结束时间
            switch (timeRange.toLowerCase()) {
                case "today":
                    // 3.1 今日: 起始时间 = 当天00:00:00, 结束时间 = 当天23:59:59.999999999
                    startTime = today.atStartOfDay();
                    endTime = today.atTime(LocalTime.MAX);
                    break;
                case "week":
                    // 3.2 本周: 起始时间 = 本周周一00:00:00, 结束时间 = 本周周日23:59:59.999999999
                    startTime = today.with(DayOfWeek.MONDAY).atStartOfDay();
                    endTime = today.with(DayOfWeek.SUNDAY).atTime(LocalTime.MAX);
                    break;
                case "month":
                    // 3.3 本月: 起始时间 = 本月1号00:00:00, 结束时间 = 本月最后一天23:59:59.999999999
                    startTime = today.withDayOfMonth(1).atStartOfDay();
                    endTime = today.withDayOfMonth(today.lengthOfMonth()).atTime(LocalTime.MAX);
                    break;
                default:
                    // 3.4 未知 timeRange: 抛出异常提示不支持的值
                    throw new IllegalArgumentException("不支持的timeRange: " + timeRange);
            }

            // 4. 将计算好的起始时间和结束时间设置回查询对象
            lawOverviewQueryReqVO.setStartTime(startTime);
            lawOverviewQueryReqVO.setEndTime(endTime);
        }

        // 5. 后续查询逻辑
        return lawOverviewMapper.getLawOverview(lawOverviewQueryReqVO);
    }

    /**
     * 获取执法全域数据中涉及的行政区信息
     * 主要用于大屏行政区下拉筛选
     */
    @Override
    public List<LawOverviewArea> getLawOverviewArea() {
        // 调用 Mapper 查询行政区列表
        return lawOverviewMapper.getLawOverviewArea();
    }

    /**
     * 获取行政区域列表
     *
     * @param areaReqVO 查询条件对象，包含上级行政区编码 regionShortCode
     * @return 返回行政区列表，每个对象包含名称和地区码，按地区码升序排列
     */
    @Override
    public List<AreaRespVO> getAllArea(AreaReqVO areaReqVO) {
        return lawOverviewMapper.getAllArea(areaReqVO);
    }

}
