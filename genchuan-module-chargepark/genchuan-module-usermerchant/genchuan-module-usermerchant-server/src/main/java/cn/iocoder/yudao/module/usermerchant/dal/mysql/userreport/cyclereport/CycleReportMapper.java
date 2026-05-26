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

//    default PageResult<CycleReportDO> selectPage(CycleReportPageReqVO reqVO) {
//        return selectPage(reqVO, new LambdaQueryWrapperX<CycleReportDO>()
//                .eqIfPresent(CycleReportDO::getReportCycle, reqVO.getReportCycle())
//                .betweenIfPresent(CycleReportDO::getStatStartTime, reqVO.getStatStartTime())
//                .betweenIfPresent(CycleReportDO::getStatEndTime, reqVO.getStatEndTime())
//                .eqIfPresent(CycleReportDO::getReportStatus, reqVO.getReportStatus())
//                .orderByDesc(CycleReportDO::getId));
//    }

    default PageResult<CycleReportDO> selectPage(CycleReportPageReqVO reqVO) {
        LambdaQueryWrapperX<CycleReportDO> wrapper = new LambdaQueryWrapperX<>();
        // 其他筛选条件
        wrapper.eqIfPresent(CycleReportDO::getReportCycle, reqVO.getReportCycle())
                .eqIfPresent(CycleReportDO::getReportStatus, reqVO.getReportStatus())
                .orderByDesc(CycleReportDO::getId);

        // 时间段包含筛选：记录的时间段完全在筛选的时间段内
        LocalDateTime filterStart = reqVO.getStatStartTime();
        LocalDateTime filterEnd = reqVO.getStatEndTime();
        if (filterStart != null && filterEnd != null) {
            wrapper.ge(CycleReportDO::getStatStartTime, filterStart)
                    .le(CycleReportDO::getStatEndTime, filterEnd);
        } else if (filterStart != null) {
            wrapper.ge(CycleReportDO::getStatStartTime, filterStart);
        } else if (filterEnd != null) {
            wrapper.le(CycleReportDO::getStatEndTime, filterEnd);
        }

        return selectPage(reqVO, wrapper);
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