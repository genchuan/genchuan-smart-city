package cn.iocoder.yudao.module.usermerchant.dal.mysql.userreport.cyclereport;

import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.usermerchant.controller.admin.userreport.cyclereport.vo.CycleReportPageReqVO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.userreport.cyclereport.CycleReportDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 周期报表存储 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface CycleReportMapper extends BaseMapperX<CycleReportDO> {

    default PageResult<CycleReportDO> selectPage(CycleReportPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CycleReportDO>()
                .eqIfPresent(CycleReportDO::getReportCycle, reqVO.getReportCycle())
                .betweenIfPresent(CycleReportDO::getStatStartTime, reqVO.getStatStartTime())
                .betweenIfPresent(CycleReportDO::getStatEndTime, reqVO.getStatEndTime())
                .eqIfPresent(CycleReportDO::getReportStatus, reqVO.getReportStatus())
                .orderByDesc(CycleReportDO::getId));
    }

    List<Map<String, Object>> selectUserGrowthTrend(LocalDateTime start, LocalDateTime end, String granularity);

    List<Map<String, Object>> selectPlateAuthTrend(LocalDateTime start, LocalDateTime end, String granularity);

    List<Map<String, Object>> selectRechargeAmountTrend(LocalDateTime start, LocalDateTime end, String granularity);

    List<Map<String, Object>> selectSendCouponTrend(LocalDateTime start, LocalDateTime end, String granularity);

    List<Map<String, Object>> selectUserTypeDistribution(LocalDateTime start, LocalDateTime end);

    List<Map<String, Object>> selectCarTypeDistribution(LocalDateTime start, LocalDateTime end);

    List<Map<String, Object>> selectMerchantTypeDistribution(LocalDateTime start, LocalDateTime end);

    List<Map<String, Object>> selectGroupTypeDistribution(LocalDateTime start, LocalDateTime end);

    List<Map<String, Object>> selectCreditLevelDistribution(LocalDateTime start, LocalDateTime end);

    List<Map<String, Object>> selectMemberLevelDistribution(LocalDateTime start, LocalDateTime end);
}