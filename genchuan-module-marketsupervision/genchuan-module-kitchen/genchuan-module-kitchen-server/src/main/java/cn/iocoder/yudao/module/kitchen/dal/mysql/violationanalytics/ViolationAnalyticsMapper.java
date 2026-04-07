package cn.iocoder.yudao.module.kitchen.dal.mysql.violationanalytics;

import cn.iocoder.yudao.module.kitchen.controller.admin.violationanalytics.vo.page.ViolationAnalyticsPageReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.violationanalytics.vo.page.ViolationAnalyticsPageResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ViolationAnalyticsMapper {
    // 查询列表
    List<ViolationAnalyticsPageResp> selectViolationAnalyticsList(@Param("req") ViolationAnalyticsPageReq req);

    // 查询总数
    Long selectViolationAnalyticsCount(@Param("req") ViolationAnalyticsPageReq req);
}
