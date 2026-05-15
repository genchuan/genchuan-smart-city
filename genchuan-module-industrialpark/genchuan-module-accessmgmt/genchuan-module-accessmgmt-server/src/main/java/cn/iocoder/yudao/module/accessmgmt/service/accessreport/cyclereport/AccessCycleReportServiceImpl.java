package cn.iocoder.yudao.module.accessmgmt.service.accessreport.cyclereport;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.accessreport.cyclereport.vo.*;
import cn.iocoder.yudao.module.accessmgmt.dal.dataobject.accessreport.cyclereport.AccessCycleReportDO;
import cn.iocoder.yudao.module.accessmgmt.dal.mysql.accessreport.cyclereport.AccessCycleReportMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.accessmgmt.enums.ErrorCodeConstants.*;

/**
 * 通行周期报表 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AccessCycleReportServiceImpl implements AccessCycleReportService {

    @Resource
    private AccessCycleReportMapper accessCycleReportMapper;

    @Override
    public PageResult<AccessCycleReportRespVO> getAccessCycleReportPage(AccessCycleReportPageReqVO pageReqVO) {
        PageResult<AccessCycleReportDO> pageResult = accessCycleReportMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult, AccessCycleReportRespVO.class);
    }

    @Override
    public AccessCycleReportRespVO getAccessCycleReport(Long id) {
        AccessCycleReportDO entity = accessCycleReportMapper.selectById(id);
        if (entity == null) {
            throw exception(ACCESS_CYCLE_REPORT_NOT_EXISTS);
        }
        return BeanUtils.toBean(entity, AccessCycleReportRespVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AccessCycleReportGenerateRespVO generateAccessCycleReport(AccessCycleReportGenerateReqVO reqVO) {
        AccessCycleReportDO entity = new AccessCycleReportDO();
        entity.setCycleType(reqVO.getCycleType());

        // 根据周期类型计算时间范围
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start;
        LocalDateTime end;

        if ("自定义".equals(reqVO.getCycleType())) {
            // 自定义周期使用传入的时间
            start = reqVO.getStartTime() != null ?
                    LocalDateTime.parse(reqVO.getStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : now;
            end = reqVO.getEndTime() != null ?
                    LocalDateTime.parse(reqVO.getEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : now;
        } else {
            // 非自定义周期自动计算
            switch (reqVO.getCycleType()) {
                case "日报":
                    start = now.toLocalDate().atStartOfDay();
                    end = now.toLocalDate().atTime(23, 59, 59);
                    break;
                case "周报":
                    start = now.minusWeeks(1).toLocalDate().atStartOfDay();
                    end = now.toLocalDate().atTime(23, 59, 59);
                    break;
                case "月报":
                    start = now.minusMonths(1).toLocalDate().atStartOfDay();
                    end = now.toLocalDate().atTime(23, 59, 59);
                    break;
                case "季报":
                    start = now.minusMonths(3).toLocalDate().atStartOfDay();
                    end = now.toLocalDate().atTime(23, 59, 59);
                    break;
                case "半年报":
                    start = now.minusMonths(6).toLocalDate().atStartOfDay();
                    end = now.toLocalDate().atTime(23, 59, 59);
                    break;
                case "年报":
                    start = now.minusYears(1).toLocalDate().atStartOfDay();
                    end = now.toLocalDate().atTime(23, 59, 59);
                    break;
                default:
                    start = now.toLocalDate().atStartOfDay();
                    end = now.toLocalDate().atTime(23, 59, 59);
            }
        }

        entity.setStartTime(start);
        entity.setEndTime(end);

        // 生成报表名称
        String reportName = reqVO.getCycleType() + "通行报表_"
                + start.format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "_"
                + end.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        entity.setReportName(reportName);

        // 模拟统计数据
        entity.setTotalPersonAccess(100 + (int) (Math.random() * 900));
        entity.setTotalVisitorArrive(20 + (int) (Math.random() * 180));
        entity.setTotalVehicleAccess(50 + (int) (Math.random() * 450));
        entity.setSpaceUseRate(BigDecimal.valueOf(50.00 + Math.random() * 50.00)
                .setScale(2, java.math.RoundingMode.HALF_UP));
        entity.setPayIncome(BigDecimal.valueOf(500.00 + Math.random() * 9500.00)
                .setScale(2, java.math.RoundingMode.HALF_UP));

        accessCycleReportMapper.insert(entity);

        AccessCycleReportGenerateRespVO respVO = new AccessCycleReportGenerateRespVO();
        respVO.setSuccess(true);
        respVO.setReportId(entity.getId());
        return respVO;
    }

    @Override
    public List<AccessCycleReportRespVO> getAccessCycleReportList(AccessCycleReportPageReqVO pageReqVO) {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<AccessCycleReportDO> pageResult = accessCycleReportMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult.getList(), AccessCycleReportRespVO.class);
    }

    @Override
    public AccessCycleReportChartRespVO getAccessCycleReportChart(Long id) {
        AccessCycleReportDO entity = accessCycleReportMapper.selectById(id);
        if (entity == null) {
            throw exception(ACCESS_CYCLE_REPORT_NOT_EXISTS);
        }

        AccessCycleReportChartRespVO chartVO = new AccessCycleReportChartRespVO();

        // 卡片数据
        AccessCycleReportChartRespVO.CardData cardData = accessCycleReportMapper.selectCardData(id);
        chartVO.setCardData(cardData);

        // 趋势列表
        chartVO.setAccessTrendList(accessCycleReportMapper.selectAccessTrendList(id));
        chartVO.setVehicleTrendList(accessCycleReportMapper.selectVehicleTrendList(id));
        chartVO.setSpaceTrendList(accessCycleReportMapper.selectSpaceTrendList(id));
        chartVO.setAreaCountList(accessCycleReportMapper.selectAreaCountList(id));
        chartVO.setParkCountList(accessCycleReportMapper.selectParkCountList(id));
        chartVO.setPersonTypeList(accessCycleReportMapper.selectPersonTypeList(id));
        chartVO.setVehicleTypeList(accessCycleReportMapper.selectVehicleTypeList(id));
        chartVO.setPayTypeList(accessCycleReportMapper.selectPayTypeList(id));
        chartVO.setPointMapList(accessCycleReportMapper.selectPointMapList(id));
        chartVO.setTrackMapList(accessCycleReportMapper.selectTrackMapList(id));

        return chartVO;
    }

}
