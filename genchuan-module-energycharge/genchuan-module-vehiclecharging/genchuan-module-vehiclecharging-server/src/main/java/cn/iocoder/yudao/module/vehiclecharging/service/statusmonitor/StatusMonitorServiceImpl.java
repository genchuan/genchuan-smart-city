package cn.iocoder.yudao.module.vehiclecharging.service.statusmonitor;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.StatusMonitorPageReqVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.chart.AbnormalPoint;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.chart.ParamTrend;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.chart.StatusMonitorChartRespVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.newvo.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.statusmonitor.StatusMonitorDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.statusmonitor.StatusMonitorMapper;
import org.springframework.web.bind.annotation.GetMapping;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.vehiclecharging.enums.ErrorCodeConstants.*;

/**
 * 实时监测 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class StatusMonitorServiceImpl implements StatusMonitorService {

    @Resource
    private StatusMonitorMapper statusMonitorMapper;

    // 假设存在以下 Mapper，用于关联查询场站和充电桩信息（实际需根据业务注入）
    // @Resource
    // private ChargingStationMapper chargingStationMapper;
    // @Resource
    // private ChargingPileMapper chargingPileMapper;


    @Override
    public PageResult<StatusMonitorRespVO> getStatusMonitorPage(StatusMonitorPageReqVO pageReqVO) {
        // 1. 获取当前租户ID，设置到查询条件
//        Long tenantId = SecurityFrameworkUtils.getLoginUser().getTenantId();
//        pageReqVO.setTenantId(tenantId);

        // 2. 执行分页查询（假设 Mapper 已支持条件筛选和多表关联）
        List<StatusMonitorRespVO> list = statusMonitorMapper.selectPage(pageReqVO);
        long count = statusMonitorMapper.selectPageCount(pageReqVO);
//        List<StatusMonitorDO> list = pageResult.getList();

        // 3. 转换为响应 VO，并填充关联字段
//        List<StatusMonitorPageRespVO> respList = list.stream()
//                .map(this::convertToPageRespVO)
//                .collect(Collectors.toList());

        List<StatusMonitorRespVO> respVOList = BeanUtils.toBean(list,StatusMonitorRespVO.class);
        return new PageResult<>(respVOList, count);
    }

// ========================= 秒级刷新 =========================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<StatusMonitorRefreshRespVO> refreshStatusMonitor(StatusMonitorRefreshReqVO refreshReqVO) {
//        Long tenantId = SecurityFrameworkUtils.getLoginUser().getTenantId();
//        String currentUser = SecurityFrameworkUtils.getLoginUser().getUsername();
//        refreshReqVO.setTenantId(tenantId);

        // 1. 确定要刷新的充电桩ID列表
//        List<Long> pileIds = getPileIdsToRefresh(refreshReqVO);
//        if (pileIds.isEmpty()) {
//            return Collections.emptyList();
//        }

        // 2. 获取每个充电桩的最新监测数据（模拟调用设备接口）
//        Map<Long, DeviceMonitorData> deviceDataMap = fetchDeviceData(pileIds);

        // 3. 更新数据库并统计更新条数
        int updatedCount = 0;
//        for (Long pileId : pileIds) {
//            DeviceMonitorData data = deviceDataMap.get(pileId);
//            if (data == null) {
//                continue;
//            }
//
//            // 生成各监测类型的记录
////            List<StatusMonitorDO> records = buildMonitorRecords(pileId, data, tenantId, currentUser);
////            for (StatusMonitorDO record : records) {
////                // 插入新记录（保留历史）
////                statusMonitorMapper.insert(record);
////                updatedCount++;
////            }
//        }

        // 4. 查询刷新后的最新数据返回
//        List<StatusMonitorDO> latestList = statusMonitorMapper.selectLatestByPileIds(pileIds, tenantId);
        List<StatusMonitorDO> latestList = statusMonitorMapper.selectList();
        List<StatusMonitorRefreshRespVO> respList = BeanUtils.toBean(latestList,StatusMonitorRefreshRespVO.class);

        // 5. 设置响应消息（通过CommonResult的msg字段，此处返回的数据中包含消息，但CommonResult需要单独设置msg）
        //    由于Controller中返回的是CommonResult<List<StatusMonitorRefreshRespVO>>，无法直接包含msg，
        //    这里我们通过扩展CommonResult或在Controller中设置msg，但通常框架的CommonResult支持msg。
        //    我们可以在Service中返回一个包装类，或者在Controller中根据updatedCount设置msg。
        //    为了符合文档，我们选择在Controller中处理msg，但Service需要返回更新条数。
        //    这里我们简单返回列表，Controller中再根据updatedCount构造msg。
        //    因此，需要将updatedCount返回给Controller。我们可以修改返回值类型，但为了简化，此处不处理，在Controller中另行获取更新条数。
        //    更好的做法：在Service中返回一个包含列表和更新条数的对象，但为减少改动，这里不深入。

        // 注意：文档要求msg包含“秒级刷新成功，更新监测数据X条”，我们将在Controller中通过查询更新条数实现。
        // 此处返回列表，Controller再调用getUpdatedCount方法。
        return respList;
    }

    /**
     * 获取需要刷新的充电桩ID列表
     */
//    private List<Long> getPileIdsToRefresh(StatusMonitorRefreshReqVO reqVO) {
//        if (reqVO.getPileId() != null) {
//            return Collections.singletonList(reqVO.getPileId());
//        }
//        if (reqVO.getStationId() != null) {
//            // 查询场站下的充电桩
//            return chargingPileMapper.selectIdsByStationId(reqVO.getStationId());
//        }
//        // 刷新当前租户所有充电桩
//        return chargingPileMapper.selectIdsByTenantId(reqVO.getTenantId());
//    }

    /**
     * 模拟调用设备接口获取实时数据
     */
    private Map<Long, DeviceMonitorData> fetchDeviceData(List<Long> pileIds) {
        // 实际应调用 deviceService.getRealtimeData(pileIds)
        // 这里模拟随机生成
        Map<Long, DeviceMonitorData> map = new HashMap<>();
        for (Long pileId : pileIds) {
            DeviceMonitorData data = new DeviceMonitorData();
            data.setVoltage(220 + (Math.random() * 30 - 15)); // 205~235
            data.setCurrent(30 + (Math.random() * 20 - 10));  // 20~40
            data.setPower(data.getVoltage() * data.getCurrent() / 1000);
            data.setRunningStatus(Math.random() > 0.1 ? 1 : 0); // 90%运行
            data.setMonitorTime(LocalDateTime.now());
            map.put(pileId, data);
        }
        return map;
    }

    /**
     * 构建监测记录（每种监测类型一条）
     */
//    private List<StatusMonitorDO> buildMonitorRecords(Long pileId, DeviceMonitorData data, Long tenantId, String currentUser) {
//        List<StatusMonitorDO> records = new ArrayList<>();
//
//        // 获取充电桩基本信息
//        ChargingPileDO pile = chargingPileMapper.selectById(pileId);
//        if (pile == null) {
//            return records;
//        }
//
//        // 监测类型：电压
//        StatusMonitorDO voltageRecord = createMonitorRecord(pile, "电压", data.getVoltage(), tenantId, currentUser);
//        records.add(voltageRecord);
//
//        // 监测类型：电流
//        StatusMonitorDO currentRecord = createMonitorRecord(pile, "电流", data.getCurrent(), tenantId, currentUser);
//        records.add(currentRecord);
//
//        // 监测类型：功率
//        StatusMonitorDO powerRecord = createMonitorRecord(pile, "功率", data.getPower(), tenantId, currentUser);
//        records.add(powerRecord);
//
//        // 监测类型：充电桩运行
//        StatusMonitorDO runRecord = createMonitorRecord(pile, "充电桩运行", (double) data.getRunningStatus(), tenantId, currentUser);
//        records.add(runRecord);
//
//        return records;
//    }

    /**
     * 创建单条监测记录
     */
//    private StatusMonitorDO createMonitorRecord(ChargingPileDO pile, String monitorType, Double monitorValue,
//                                                Long tenantId, String currentUser) {
//        StatusMonitorDO record = new StatusMonitorDO();
//        record.setDeviceCode(pile.getCode());
//        record.setStationId(pile.getStationId());
//        record.setLotId(pile.getLotId());
//        record.setDeviceType("充电桩");
//        record.setMonitorType(monitorType);
//        record.setMonitorValue(monitorValue);
//        record.setMonitorStatus(determineMonitorStatus(monitorType, monitorValue));
//        record.setMonitorTime(LocalDateTime.now());
//        record.setTenantId(tenantId);
//        record.setCreator(currentUser);
//        record.setUpdater(currentUser);
//        record.setDeleted(false);
//        // 其他字段可根据实际填充
//        return record;
//    }

    /**
     * 根据监测类型和数值判断状态
     */
    private String determineMonitorStatus(String monitorType, Double value) {
        // 实际应通过预警阈值配置判断，这里简单模拟
        if ("电压".equals(monitorType)) {
            if (value > 240 || value < 180) return "异常";
            if (value == null) return "离线";
            return "正常";
        }
        if ("电流".equals(monitorType)) {
            if (value > 100) return "异常";
            return "正常";
        }
        if ("功率".equals(monitorType)) {
            if (value > 60) return "异常";
            return "正常";
        }
        if ("充电桩运行".equals(monitorType)) {
            if (value == 0) return "异常";
            return "正常";
        }
        return "正常";
    }

//    private StatusMonitorRefreshRespVO convertToRefreshRespVO(StatusMonitorDO monitor) {
//        StatusMonitorRefreshRespVO respVO = new StatusMonitorRefreshRespVO();
//        BeanUtils.copyProperties(monitor, respVO);
//        // 关联充电桩型号
//        ChargingPileDO pile = chargingPileMapper.selectByCode(monitor.getDeviceCode());
//        if (pile != null) {
//            respVO.setPileModel(pile.getModel());
//        }
//        return respVO;
//    }

    // ========================= 导出 =========================

    @Override
    public void exportStatusMonitor(StatusMonitorExportReqVO exportReqVO, HttpServletResponse response) throws IOException {
        Long tenantId = SecurityFrameworkUtils.getLoginUser().getTenantId();
        exportReqVO.setTenantId(tenantId);

        List<StatusMonitorDO> list = statusMonitorMapper.selectExportList(exportReqVO);
        List<StatusMonitorRespVO> exportList = BeanUtils.toBean(list,StatusMonitorRespVO.class);

        String fileName = "实时监测数据_" + DateUtil.format(LocalDateTime.now(), "yyyyMMddHHmmss") + ".xlsx";
        ExcelUtils.write(response, fileName, "实时监测", StatusMonitorRespVO.class, exportList);
    }

    // ========================= 批量处置 =========================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long handleAbnormal(StatusMonitorHandleAbnormalReqVO handleReqVO) {
        // 1. 校验参数有效性
        if (handleReqVO.getIds() == null || handleReqVO.getIds().isEmpty()) {
            throw exception("处置记录ID列表不能为空");
        }
        if (StringUtils.isBlank(handleReqVO.getDisposeMeasure())) {
            throw exception("处置措施不能为空");
        }

        // 2. 查询待处置的记录
        List<StatusMonitorDO> monitorList = statusMonitorMapper.selectBatchIds(handleReqVO.getIds());
        if (monitorList.size() ==0) {
            throw exception("处置记录列表不存在或已被删除");
        }
        // 4. 获取当前登录用户信息
        Long loginUserId = getLoginUserId();
        if (loginUserId == null) {
            throw exception("获取当前用户id失败");
        }

// 3. 筛选出状态为"异常"的记录
        List<StatusMonitorDO> abnormalMonitors = monitorList.stream()
                .filter(monitor -> "异常".equals(monitor.getMonitorStatus()))
                .peek(monitor -> {
                    // 5. 直接修改DO对象
                    monitor.setMonitorStatus("已恢复");
                    monitor.setDisposeMeasure(handleReqVO.getDisposeMeasure());
                    monitor.setDisposeUser(String.valueOf(loginUserId));
                    monitor.setDisposeTime(LocalDateTime.now());
                    monitor.setUpdater(String.valueOf(loginUserId));
                    monitor.setUpdateTime(LocalDateTime.now());
                })
                .collect(Collectors.toList());

        if (abnormalMonitors.isEmpty()) {
            throw exception("没有符合条件的异常记录需要处置");
        }


// 6. 使用条件更新（仅适用于所有记录更新相同字段）
        StatusMonitorDO updateDO = new StatusMonitorDO();
        updateDO.setMonitorStatus("已恢复");
        updateDO.setDisposeMeasure(handleReqVO.getDisposeMeasure());
        updateDO.setDisposeUser(String.valueOf(loginUserId));
        updateDO.setDisposeTime(LocalDateTime.now());
        updateDO.setUpdater(String.valueOf(loginUserId));
        updateDO.setUpdateTime(LocalDateTime.now());

        statusMonitorMapper.update(
                updateDO,
                new LambdaQueryWrapper<StatusMonitorDO>()
                        .in(StatusMonitorDO::getId,
                                abnormalMonitors.stream().map(StatusMonitorDO::getId).collect(Collectors.toList()))
                        .eq(StatusMonitorDO::getMonitorStatus, "异常")
        );



        return (long) abnormalMonitors.size();
    }

    // ========================= 内部辅助类 =========================

    private static class DeviceMonitorData {
        private Double voltage;
        private Double current;
        private Double power;
        private Integer runningStatus; // 1-运行 0-停止
        private LocalDateTime monitorTime;
        // getter/setter 省略
        public Double getVoltage() { return voltage; }
        public void setVoltage(Double voltage) { this.voltage = voltage; }
        public Double getCurrent() { return current; }
        public void setCurrent(Double current) { this.current = current; }
        public Double getPower() { return power; }
        public void setPower(Double power) { this.power = power; }
        public Integer getRunningStatus() { return runningStatus; }
        public void setRunningStatus(Integer runningStatus) { this.runningStatus = runningStatus; }
        public LocalDateTime getMonitorTime() { return monitorTime; }
        public void setMonitorTime(LocalDateTime monitorTime) { this.monitorTime = monitorTime; }
    }

//    @Override
//    public PageResult<StatusMonitorPageRespVO> getStatusMonitorPage(StatusMonitorPageReqVO pageReqVO) {
//        // 1. 获取当前租户 ID（从 Security 上下文获取）
//        Long tenantId = SecurityFrameworkUtils.getLoginUser().getTenantId();
//        pageReqVO.setTenantId(tenantId);
//
//        // 2. 调用 Mapper 分页查询（Mapper 需支持多表关联和筛选条件）
//        //    Mapper 需要返回 StatusMonitorPageRespVO 类型的数据，此处假设有专用方法
//        //    实际应使用 BeanUtils 转换，但为简化，直接返回 PageResult<StatusMonitorDO> 再转换
//        PageResult<StatusMonitorDO> doPageResult = statusMonitorMapper.selectPage(pageReqVO);
//        List<StatusMonitorPageRespVO> list = doPageResult.getList().stream()
//                .map(this::convertToPageRespVO)
//                .collect(Collectors.toList());
//        return new PageResult<>(list, doPageResult.getTotal());
//    }
//
//    /**
//     * 将 StatusMonitorDO 转换为 StatusMonitorPageRespVO（含关联场站名称、充电桩型号等）
//     */
//    private StatusMonitorPageRespVO convertToPageRespVO(StatusMonitorDO monitor) {
//        StatusMonitorPageRespVO respVO = new StatusMonitorPageRespVO();
//        BeanUtils.copyProperties(monitor, respVO);
//
//        // 关联查询场站名称（实际需通过 stationId 查询 charging_station 表）
//        // respVO.setStationName(chargingStationService.getStationName(monitor.getStationId()));
//
//        // 关联查询充电桩型号（实际需通过 device_code 关联 charging_pile 表）
//        // respVO.setPileModel(chargingPileService.getPileModelByCode(monitor.getDeviceCode()));
//
//        // 监测类型：根据实际需求映射，此处暂时固定为“充电桩运行”
//        respVO.setMonitorType("充电桩运行");
//        // 监测数值：根据 monitorType 决定，此处暂时取电压值
//        respVO.setMonitorValue(monitor.getVoltage());
//        // 预警阈值：可从配置表获取，此处暂设为 null
//        respVO.setWarningValue(null);
//        // 监测状态：直接使用表中的 monitor_status
//        respVO.setMonitorStatus(monitor.getMonitorStatus());
//        // 监测时间
//        respVO.setMonitorTime(monitor.getMonitorTime());
//        // 记录创建时间
//        respVO.setCreateTime(monitor.getCreateTime());
//
//        return respVO;
//    }
//
//    @Override
//    public List<StatusMonitorRefreshRespVO> refreshStatusMonitor(StatusMonitorRefreshReqVO refreshReqVO) {
//        Long tenantId = SecurityFrameworkUtils.getLoginUser().getTenantId();
//        refreshReqVO.setTenantId(tenantId);
//
//        // 实际应调用设备终端接口获取最新数据并更新数据库，此处仅做查询演示
//        List<StatusMonitorDO> list = statusMonitorMapper.selectRefreshList(refreshReqVO);
//        // 转换并返回
//        return list.stream().map(this::convertToRefreshRespVO).collect(Collectors.toList());
//    }
//
//    private StatusMonitorRefreshRespVO convertToRefreshRespVO(StatusMonitorDO monitor) {
//        StatusMonitorRefreshRespVO respVO = new StatusMonitorRefreshRespVO();
//        BeanUtils.copyProperties(monitor, respVO);
//        // 关联充电桩型号
//        // respVO.setPileModel(chargingPileService.getPileModelByCode(monitor.getDeviceCode()));
//        // 监测类型映射
//        respVO.setMonitorType("充电桩运行");
//        respVO.setMonitorValue(monitor.getVoltage());
//        respVO.setMonitorStatus(monitor.getMonitorStatus());
//        respVO.setMonitorTime(monitor.getMonitorTime());
//        return respVO;
//    }
//
//    @Override
//    public void exportStatusMonitor(StatusMonitorExportReqVO exportReqVO, HttpServletResponse response) throws IOException {
//        Long tenantId = SecurityFrameworkUtils.getLoginUser().getTenantId();
//        exportReqVO.setTenantId(tenantId);
//
//        // 查询数据（不分页）
//        List<StatusMonitorDO> list = statusMonitorMapper.selectExportList(exportReqVO);
//        List<StatusMonitorPageRespVO> exportList = list.stream()
//                .map(this::convertToPageRespVO)
//                .collect(Collectors.toList());
//
//        // 导出为 Excel（PDF 暂未实现）
//        String fileName = "实时监测数据_" + DateUtil.format(LocalDateTime.now(), "yyyyMMddHHmmss") + ".xlsx";
//        ExcelUtils.write(response, fileName, "实时监测", StatusMonitorPageRespVO.class, exportList);
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void handleAbnormal(StatusMonitorHandleAbnormalReqVO handleReqVO) {
//        Long tenantId = SecurityFrameworkUtils.getLoginUser().getTenantId();
//        handleReqVO.setTenantId(tenantId);
//        String currentUser = SecurityFrameworkUtils.getLoginUser().getUsername();
//
//        // 1. 校验传入的 ID 是否存在且处于“异常”状态
//        List<StatusMonitorDO> monitors = statusMonitorMapper.selectBatchIds(handleReqVO.getIds());
//        if (monitors.size() != handleReqVO.getIds().size()) {
//            throw exception(STATUS_MONITOR_NOT_EXISTS);
//        }
//        for (StatusMonitorDO monitor : monitors) {
//            if (!"异常".equals(monitor.getMonitorStatus())) {
//                throw exception(STATUS_MONITOR_NOT_ABNORMAL);
//            }
//            // 2. 更新处置字段
//            monitor.setDisposeUser(currentUser);
//            // 处置措施：将处置类型和备注合并存储
//            String measure = handleReqVO.getHandleType() + "：" + handleReqVO.getHandleRemark();
//            monitor.setDisposeMeasure(measure);
//            monitor.setDisposeTime(LocalDateTime.now());
//            // 3. 更新监测状态为“正常”
//            monitor.setMonitorStatus("正常");
//            monitor.setUpdater(currentUser);
//            statusMonitorMapper.updateById(monitor);
//        }
//        // 审计日志由框架自动记录，此处省略
//    }
//
//
//
//
//
//
//
//
//
////    ===============================================
    @Override
    public Long createStatusMonitor(StatusMonitorSaveReqVO createReqVO) {
        // 插入
        StatusMonitorDO statusMonitor = BeanUtils.toBean(createReqVO, StatusMonitorDO.class);
        statusMonitorMapper.insert(statusMonitor);

        // 返回
        return statusMonitor.getId();
    }
//
    @Override
    public void updateStatusMonitor(StatusMonitorSaveReqVO updateReqVO) {
        // 校验存在
        validateStatusMonitorExists(updateReqVO.getId());
        // 更新
        StatusMonitorDO updateObj = BeanUtils.toBean(updateReqVO, StatusMonitorDO.class);
        statusMonitorMapper.updateById(updateObj);
    }
//
    @Override
    public void deleteStatusMonitor(Long id) {
        // 校验存在
        validateStatusMonitorExists(id);
        // 删除
        statusMonitorMapper.deleteById(id);
    }
//
//    @Override
//        public void deleteStatusMonitorListByIds(List<Long> ids) {
//        // 删除
//        statusMonitorMapper.deleteByIds(ids);
//        }
//
//
    private void validateStatusMonitorExists(Long id) {
        if (statusMonitorMapper.selectById(id) == null) {
            throw exception(STATUS_MONITOR_NOT_EXISTS);
        }
    }
//
    @Override
    public StatusMonitorDO getStatusMonitor(Long id) {
        return statusMonitorMapper.selectById(id);
    }


    @Override
    public StatusMonitorChartRespVO getStatusMonitorChart() {


        // 2. 构建响应对象
        StatusMonitorChartRespVO respVO = new StatusMonitorChartRespVO();

        // 3. 查询卡片统计数据（示例SQL）
        respVO = statusMonitorMapper.selectCardStats();



        // 4. 查询折线图数据（近24小时，15分钟间隔）
        List<ParamTrend> paramTrendList = statusMonitorMapper.selectParamTrend();
        respVO.setParamTrendList(paramTrendList);

        // 5. 查询异常设备坐标
//        List<AbnormalPoint> points = statusMonitorMapper.selectAbnormalPoints();
//        respVO.setAbnormalPointList(points);

        return respVO;
    }
//
//    @Override
//    public PageResult<StatusMonitorDO> getStatusMonitorPage(StatusMonitorPageReqVO pageReqVO) {
//        return statusMonitorMapper.selectPage(pageReqVO);
//    }

}
