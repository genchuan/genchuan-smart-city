package cn.iocoder.yudao.module.vehiclepass.service.passreport.cyclereport;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.tenant.core.context.TenantContextHolder;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.passreport.cyclereport.vo.CycleReportCreateReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.passreport.cyclereport.vo.CycleReportCreateRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.passreport.cyclereport.vo.CycleReportPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.passreport.cyclereport.vo.CycleReportRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.passreport.cyclereport.CycleReportDO;
import cn.iocoder.yudao.module.vehiclepass.dal.mysql.passreport.cyclereport.CycleReportMapper;
import cn.iocoder.yudao.module.vehiclepass.service.passreport.cyclereport.CycleReportService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class CycleReportServiceImpl implements CycleReportService {

    @Resource
    private CycleReportMapper cycleReportMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CycleReportCreateRespVO createCycleReport(CycleReportCreateReqVO req) {
        long startMs = System.currentTimeMillis();
        Long tenantId = TenantContextHolder.getTenantId();

        // 1. 校验场站是否属于当前租户
        cycleReportMapper.selectStationName(req.getStationId());

        // 2. 创建初始记录
        CycleReportDO report = new CycleReportDO();
        report.setReportCycle(req.getReportCycle());
        LocalDateTime startDate = req.getStatStartTimeDate();
        LocalDateTime endDate = req.getStatEndTimeDate();
        report.setStatStartTime(startDate);
        report.setStatEndTime(endDate);
        report.setStationId(req.getStationId());
        report.setReportStatus("生成中");
        cycleReportMapper.insert(report);

        try {
            // 3. 统计指标
            Integer enterCount = cycleReportMapper.selectEnterCount(req.getStationId(), startDate, endDate, tenantId);
            Integer leaveCount = cycleReportMapper.selectLeaveCount(req.getStationId(), startDate, endDate, tenantId);
            Integer parkingCount = cycleReportMapper.selectParkingCount(req.getStationId(), endDate, tenantId);
            BigDecimal identifyRate = cycleReportMapper.selectIdentifySuccessRate(req.getStationId(), startDate, endDate, tenantId);
            BigDecimal checkRate = cycleReportMapper.selectCheckSuccessRate(req.getStationId(), startDate, endDate, tenantId);
            BigDecimal abnormalRate = cycleReportMapper.selectAbnormalHandleRate(req.getStationId(), startDate, endDate, tenantId);
            BigDecimal etcRate = cycleReportMapper.selectEtcPassSuccessRate(req.getStationId(), startDate, endDate, tenantId);

            report.setEnterCount(enterCount != null ? enterCount : 0);
            report.setLeaveCount(leaveCount != null ? leaveCount : 0);
            report.setParkingCount(parkingCount != null ? parkingCount : 0);
            report.setIdentifySuccessRate(identifyRate != null ? identifyRate : BigDecimal.ZERO);
            report.setCheckSuccessRate(checkRate != null ? checkRate : BigDecimal.ZERO);
            report.setAbnormalHandleRate(abnormalRate != null ? abnormalRate : BigDecimal.ZERO);
            report.setEtcPassSuccessRate(etcRate != null ? etcRate : BigDecimal.ZERO);
            report.setReportStatus("已生成");
        } catch (Exception e) {
            log.error("生成周期报表失败，reportId: {}", report.getId(), e);
            report.setReportStatus("生成失败");
        }

        // 4. 计算耗时
        long cost = System.currentTimeMillis() - startMs;
        report.setCreateCost((int) (cost / 1000));

        // 5. 更新报表（只更新统计字段，避免覆盖自动填充字段）
        LambdaUpdateWrapper<CycleReportDO> updateWrapper = Wrappers.<CycleReportDO>lambdaUpdate()
                .set(CycleReportDO::getEnterCount, report.getEnterCount())
                .set(CycleReportDO::getLeaveCount, report.getLeaveCount())
                .set(CycleReportDO::getParkingCount, report.getParkingCount())
                .set(CycleReportDO::getIdentifySuccessRate, report.getIdentifySuccessRate())
                .set(CycleReportDO::getCheckSuccessRate, report.getCheckSuccessRate())
                .set(CycleReportDO::getAbnormalHandleRate, report.getAbnormalHandleRate())
                .set(CycleReportDO::getEtcPassSuccessRate, report.getEtcPassSuccessRate())
                .set(CycleReportDO::getReportStatus, report.getReportStatus())
                .set(CycleReportDO::getCreateCost, report.getCreateCost())
                .eq(CycleReportDO::getId, report.getId());
        cycleReportMapper.update(null, updateWrapper);

        CycleReportCreateRespVO resp = new CycleReportCreateRespVO();
        resp.setId(report.getId());
        resp.setReportStatus(report.getReportStatus());
        return resp;
    }

    @Override
    public PageResult<CycleReportRespVO> getCycleReportPage(CycleReportPageReqVO reqVO) {
        Long tenantId = TenantContextHolder.getTenantId();
        reqVO.setTenantId(tenantId);
        Page<CycleReportDO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());
        IPage<CycleReportDO> result = cycleReportMapper.selectPageJoin(page, reqVO);
        List<CycleReportRespVO> list = BeanUtils.toBean(result.getRecords(), CycleReportRespVO.class);
        return new PageResult<>(list, result.getTotal());
    }

    // 导出方法，复用分页查询
    @Override
    public List<CycleReportRespVO> getCycleReportList(CycleReportPageReqVO pageReqVO) {
        Long tenantId = TenantContextHolder.getTenantId();
        pageReqVO.setTenantId(tenantId);
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        Page<CycleReportDO> page = new Page<>(1, pageReqVO.getPageSize());
        IPage<CycleReportDO> result = cycleReportMapper.selectPageJoin(page, pageReqVO);
        return BeanUtils.toBean(result.getRecords(), CycleReportRespVO.class);
    }
}