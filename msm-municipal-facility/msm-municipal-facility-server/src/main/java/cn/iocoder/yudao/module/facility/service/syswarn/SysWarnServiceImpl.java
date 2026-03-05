package cn.iocoder.yudao.module.facility.service.syswarn;

import cn.hutool.core.util.RandomUtil;
import cn.iocoder.yudao.module.facility.controller.admin.syswarn.vo.*;
import cn.iocoder.yudao.module.facility.dal.dataobject.syswarn.SysWarnDO;
import cn.iocoder.yudao.module.facility.dal.mysql.road.roadmonitor.RoadMonitorMapper;
import cn.iocoder.yudao.module.facility.dal.mysql.road.roadfacility.RoadFacilityMapper;
import cn.iocoder.yudao.module.facility.dal.mysql.sysdevice.SysDeviceMapper;
import cn.iocoder.yudao.module.facility.dal.mysql.syswarn.SysWarnMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.facility.enums.ErrorCodeConstants.*;

/**
 * 通用预警 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class SysWarnServiceImpl implements SysWarnService {

    @Resource
    private SysWarnMapper sysWarnMapper;



    @Resource
    private RoadMonitorMapper roadMonitorMapper;

    @Resource
    private RoadFacilityMapper roadFacilityMapper;


    @Resource
    private SysDeviceMapper sysDeviceMapper;
    @Override
    public Long createSysWarn(SysWarnSaveReqVO createReqVO) {
        // ================== 1. 自动生成预警编号 ==================
        // 规则：WARN + 月日(MMdd) + 6位流水号，例如：WARN0304000001
        String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMdd"));
        String prefix = "WARN" + dateStr;

        // 查询当天最大编号（示例，具体 SQL 你可以用 like prefix%） TODO 流水号先简单处理
//        String maxWarnNo = sysWarnMapper.selectMaxWarnNoByPrefix(prefix);
        String nextSeq;
        String maxWarnNo = null;
        if (maxWarnNo == null) {
//            nextSeq = "000001";
            nextSeq = RandomUtil.randomNumbers(6);
        } else {
            String seq = maxWarnNo.substring(maxWarnNo.length() - 6);
            nextSeq = String.format("%06d", Integer.parseInt(seq) + 1);
        }
        createReqVO.setWarnNo(prefix + nextSeq);


        // ================== 2. 校验并补齐设施信息 ==================
        //2.验证关联设施id不为null，并通过关联设施id获取其名称和code TODO 抽象出来
        if (createReqVO.getFacilityId() == null) {
            throw exception(500,"设施ID不能为空");
        }

        if (createReqVO.getFacilityName()==null){
            throw exception(500,"设施名称不存在");
        }
        if (createReqVO.getFacilityCode()==null){
            throw exception(500,"设施编码不存在");
        }

        // ================== 3. 校验并补齐设备信息 ==================
        //3.验证关联设备id，code不为null
        if (createReqVO.getDeviceId() == null) {
            throw exception(500, "设备ID不能为空");
        }

        if (!StringUtils.hasText(createReqVO.getDeviceCode())) {
            throw exception(500, "设备编码不能为空");
        }


        // ================== 4. 校验并补齐监测数据 ==================
        //4.验证关联监测实时数据ID不为null，并通过关联id获取其code
        if (createReqVO.getMonitorId() == null) {
            throw exception(500,"监测数据ID不能为空");
        }
        if (!StringUtils.hasText(createReqVO.getMonitorCode())) {
            throw exception(500, "监测数据编码不能为空");
        }

        // ================== 5. 预警状态 / 派单状态 ==================
        createReqVO.setStatus("待处置");
        createReqVO.setAssignStatus("未派单");

        // ================== 6. 所属设施类型 ==================
        if (createReqVO.getFacilityType() == null) {
            throw exception(500,"设施类型不能为空");
        }

        // ================== 7. 触发时间 ==================
        // 使用当前时间
        createReqVO.setTriggerTime(LocalDateTime.now());

        // ================== 8. 校验预警关键信息完整性 ==================
        // 说明：
        // 1. SysWarnService 不负责预警规则计算、不解析监测数据
        // 2. 预警类型、超标指标、超标值、阈值必须由上游业务（如道路/桥梁预警）提前计算并传入
        // 3. 这里仅校验以下字段是否完整，保证预警记录可独立存储与展示：
        //    - type             ：预警类型（如“坑洼数量超标”）
        //    - overIndex        ：超标指标名称（如“坑洼数量”）
        //    - overValue        ：超标实际值
        //    - thresholdValue   ：超标阈值
        if (!StringUtils.hasText(createReqVO.getType())) {
            throw exception(500, "预警类型不能为空");
        }

        if (!StringUtils.hasText(createReqVO.getOverIndex())) {
            throw exception(500, "超标指标不能为空");
        }

        if (createReqVO.getOverValue() == null) {
            throw exception(500, "超标数值不能为空");
        }

        if (createReqVO.getThresholdValue() == null) {
            throw exception(500, "阈值不能为空");
        }


        // ================== 插入预警表 ==================
        SysWarnDO sysWarn = BeanUtils.toBean(createReqVO, SysWarnDO.class);
        sysWarnMapper.insert(sysWarn);

        // ================== 监测数据回写预警ID ==================
        //TODO 追加到 warning_id_list_str 中（逗号分隔）
//        roadMonitorMapper.appendWarningId(
//                monitor.getId(),
//                sysWarn.getId()
//        );

        return sysWarn.getId();
    }

//    @Override
//    public Long createSysWarn(SysWarnSaveReqVO createReqVO) {
//        // ================== 1. 自动生成预警编号 ==================
//        // 规则：WARN + 月日(MMdd) + 6位流水号，例如：WARN0304000001
//        String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMdd"));
//        String prefix = "WARN" + dateStr;
//
//        // 查询当天最大编号（示例，具体 SQL 你可以用 like prefix%） TODO 流水号先简单处理
////        String maxWarnNo = sysWarnMapper.selectMaxWarnNoByPrefix(prefix);
//        String nextSeq;
//        String maxWarnNo = null;
//        if (maxWarnNo == null) {
////            nextSeq = "000001";
//            nextSeq = RandomUtil.randomNumbers(6);
//        } else {
//            String seq = maxWarnNo.substring(maxWarnNo.length() - 6);
//            nextSeq = String.format("%06d", Integer.parseInt(seq) + 1);
//        }
//        createReqVO.setWarnNo(prefix + nextSeq);
//
//
//        // ================== 2. 校验并补齐设施信息 ==================
//        //2.验证关联设施id不为null，并通过关联设施id获取其名称和code TODO 抽象出来
//        if (createReqVO.getFacilityId() == null) {
//            throw exception(500,"设施ID不能为空");
//        }
//        RoadFacilityDO facility = roadFacilityMapper.selectById(createReqVO.getFacilityId());
//        if (facility == null) {
//            throw exception(500,"设施不存在");
//        }
//        createReqVO.setFacilityName(facility.getRoadName());
//        createReqVO.setFacilityCode(facility.getRoadCode());
//
//
//        // ================== 3. 校验并补齐设备信息 ==================
//        //3.验证关联设备id不为null，并通过关联id获取其code
//        if (createReqVO.getDeviceId() == null) {
//            throw exception(500,"设备ID不能为空");
//        }
//        SysDeviceDO device = sysDeviceMapper.selectById(createReqVO.getDeviceId());
//        if (device == null) {
//            throw exception(500,"设备不存在");
//        }
//        createReqVO.setDeviceCode(device.getDeviceCode());
//
//
//        // ================== 4. 校验并补齐监测数据 ==================
//        //4.验证关联监测实时数据ID不为null，并通过关联id获取其code
//        if (createReqVO.getMonitorId() == null) {
//            throw exception(500,"监测数据ID不能为空");
//        }
//        //TODO 需要将其进行抽象，不然现在通用预警只能使用 道路预警
//        RoadMonitorDO monitor = roadMonitorMapper.selectById(createReqVO.getMonitorId());
//        if (monitor == null) {
//            throw exception(500,"监测数据不存在");
//        }
//        createReqVO.setMonitorCode(monitor.getMonitorCode());
//        // ================== 5. 预警状态 / 派单状态 ==================
//        createReqVO.setStatus("待处置");
//        createReqVO.setAssignStatus("未派单");
//
//        // ================== 6. 所属设施类型 ==================
//        createReqVO.setFacilityType("道路");
//
//        // ================== 7. 触发时间 ==================
//        // 使用当前时间
//        createReqVO.setTriggerTime(LocalDateTime.now());
//
//        //8.根据监测id获取预警类型、超标指标名称、超标数值、超标阈值数值
//        // ================== 8. 根据监测数据判断预警类型 ==================
//        // TODO 后续考虑monitor抽取公共方法
//
//        if (monitor.getPotholeNum() != null
//                && monitor.getPotholeNumThreshold() != null
//                && monitor.getPotholeNum().compareTo(monitor.getPotholeNumThreshold()) > 0) {
//
//            createReqVO.setType("坑洼数量超标");
//            createReqVO.setOverIndex("坑洼数量");
//            createReqVO.setOverValue(monitor.getPotholeNum());
//            createReqVO.setThresholdValue(monitor.getPotholeNumThreshold());
//
//        } else if (monitor.getCrackLength() != null
//                && monitor.getCrackLengthThreshold() != null
//                && monitor.getCrackLength().compareTo(monitor.getCrackLengthThreshold()) > 0) {
//
//            createReqVO.setType("裂缝长度超标");
//            createReqVO.setOverIndex("裂缝长度");
//            createReqVO.setOverValue(monitor.getCrackLength());
//            createReqVO.setThresholdValue(monitor.getCrackLengthThreshold());
//
//        } else if (monitor.getRoadTemp() != null
//                && monitor.getRoadTempThreshold() != null
//                && monitor.getRoadTemp().compareTo(monitor.getRoadTempThreshold()) > 0) {
//
//            createReqVO.setType("路面温度超标");
//            createReqVO.setOverIndex("路面温度");
//            createReqVO.setOverValue(monitor.getRoadTemp());
//            createReqVO.setThresholdValue(monitor.getRoadTempThreshold());
//
//        } else if (monitor.getTrafficFlow() != null
//                && monitor.getTrafficFlowThreshold() != null
//                && monitor.getTrafficFlow().compareTo(monitor.getTrafficFlowThreshold()) > 0) {
//
//            createReqVO.setType("交通流量超标");
//            createReqVO.setOverIndex("交通流量");
//            createReqVO.setOverValue(monitor.getTrafficFlow());
//            createReqVO.setThresholdValue(monitor.getTrafficFlowThreshold());
//        }
//
//
//        // ================== 插入预警表 ==================
//        SysWarnDO sysWarn = BeanUtils.toBean(createReqVO, SysWarnDO.class);
//        sysWarnMapper.insert(sysWarn);
//
//        // ================== 监测数据回写预警ID ==================
//        //TODO 追加到 warning_id_list_str 中（逗号分隔）
////        roadMonitorMapper.appendWarningId(
////                monitor.getId(),
////                sysWarn.getId()
////        );
//
//        return sysWarn.getId();
//    }

    @Override
    public void updateSysWarn(SysWarnUpdateReqVO updateReqVO) {
        // 校验存在
        validateSysWarnExists(updateReqVO.getId());
        // 更新
        SysWarnDO updateObj = BeanUtils.toBean(updateReqVO, SysWarnDO.class);
        sysWarnMapper.updateById(updateObj);
    }

    @Override
    public void deleteSysWarn(Long id) {
        // 校验存在
        validateSysWarnExists(id);
        // 删除
        sysWarnMapper.deleteById(id);
    }

    private void validateSysWarnExists(Long id) {
        if (sysWarnMapper.selectById(id) == null) {
            throw exception(SYS_WARN_NOT_EXISTS);
        }
    }

    @Override
    public SysWarnDO getSysWarn(Long id) {
        return sysWarnMapper.selectById(id);
    }

//    @Override
//    public PageResult<SysWarnDO> getSysWarnPage(SysWarnPageReqVO pageReqVO) {
//        return sysWarnMapper.selectPage(pageReqVO);
//    }

    @Override
    public PageResult<SysWarnRespVO> getSysWarnPage(SysWarnPageReqVO pageReqVO) {
        //强制要求市政设施不能为null
        String facilityType = pageReqVO.getFacilityType();
        if (facilityType==null){
            throw exception(500,"所属市政设施类型不能为null");
        }

        // 1. 查询 List
        List<SysWarnDO> list = sysWarnMapper.getSysWarnPage(pageReqVO);

        // 2. 封装分页（总数为 list.size()，可根据实际分页插件调整）
        PageResult<SysWarnRespVO> voPage = new PageResult<>();
        voPage.setList(list.stream()
                .map(doObj -> BeanUtils.toBean(doObj, SysWarnRespVO.class))
                .toList());
        voPage.setTotal((long) list.size());

        // 3. 计算剩余时间
        voPage.getList().forEach(vo -> vo.setRemainTime(calcRemainTime(vo.getTriggerTime(), vo.getDealLimit())));

        return voPage;
//        // 1. 查询 DO 分页数据
//        PageResult<SysWarnDO> pageResult = sysWarnMapper.getSysWarnPage(pageReqVO);
//
//        if (pageResult==null){
//            return null;
//        }
//
//        // 2. DO -> VO
//        PageResult<SysWarnRespVO> voPage =
//                BeanUtils.toBean(pageResult, SysWarnRespVO.class);
//
//        // 3. 计算剩余时间（非数据库字段）
//        voPage.getList().forEach(vo -> {
//            vo.setRemainTime(
//                    calcRemainTime(vo.getTriggerTime(), vo.getDealLimit())
//            );
//        });
//
//        return voPage;
    }

    @Override
    public Integer batchConfirmInvalid(SysWarnBatchConfirmValidReqVO reqVO) {
        // ================== 1. 请求对象校验 ==================
        if (reqVO == null) {
            throw exception(400, "请求参数不能为空");
        }

        // ================== 2. ID 列表校验 ==================
        List<Long> idList = reqVO.getIdList();
        if (idList == null || idList.isEmpty()) {
            throw exception(400, "预警ID列表不能为空");
        }

        if (idList.stream().anyMatch(Objects::isNull)) {
            throw exception(400, "预警ID列表中不能包含空值");
        }

        //2.调用通用批量更新接口
        SysWarnBatchUpdateReqVO sysWarnBatchUpdateReqVO =new SysWarnBatchUpdateReqVO();
        sysWarnBatchUpdateReqVO.setIdList(idList);
        sysWarnBatchUpdateReqVO.setStatus("无效已归档");

        Integer count = this.batchUpdateSysWarn(sysWarnBatchUpdateReqVO);

        return count;
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer batchUpdateSysWarn(SysWarnBatchUpdateReqVO reqVO) {
        // ================== 1. 基础参数校验 ==================
        if (reqVO == null) {
            throw exception(400, "请求参数不能为空");
        }

        if (reqVO.getIdList() == null || reqVO.getIdList().isEmpty()) {
            throw exception(400, "预警ID列表不能为空");
        }

        if (reqVO.getIdList().stream().anyMatch(Objects::isNull)) {
            throw exception(400, "预警ID列表中不能包含空值");
        }

        // Service 层不做任何字段处理，完全交由 XML 控制
        return sysWarnMapper.batchUpdateByIdList(reqVO);
    }

    @Override
    public Integer confirmValid(ConfirmValidReqVO reqVO) {

        // ================== 1. 参数校验 ==================
        // 确认有效操作必须指定预警ID
        if (reqVO.getId() == null) {
            throw exception(500, "id不能为空");
        }

        //校验数据库存在
        SysWarnDO sysWarnDO = sysWarnMapper.selectById(reqVO.getId());
        if (sysWarnDO==null){
            throw exception(500,"该预警不存在数据库");
        }
        //校验状态是否为“待处置”
        if (!"待处置".equals(sysWarnDO.getStatus())){
            throw exception(500,"预警状态不是待处置");
        }

        // ================== 2. 构造更新对象 ==================
        // 仅更新“确认有效”相关字段，避免误更新其他字段
        SysWarnDO updateDO = new SysWarnDO();
        updateDO.setId(reqVO.getId());

        // 设置预警状态为“有效待派单”
        // 注意：这里后续抽成常量或枚举，避免硬编码
        updateDO.setStatus("有效待派单");

        // 设置人工确认意见
        updateDO.setConfirmOpinion(reqVO.getConfirmOpinion());

        // ================== 3. 执行更新 ==================
        // 根据主键ID更新，返回影响行数（通常为 1）
        return sysWarnMapper.updateById(updateDO);
    }

    @Override
    public Integer confirmInvalid(ConfirmInvalidReqVO reqVO) {

        // ================== 1. 参数校验 ==================
        // 确认有效操作必须指定预警ID
        if (reqVO.getId() == null) {
            throw exception(500, "id不能为空");
        }

        //校验数据库存在
        SysWarnDO sysWarnDO = sysWarnMapper.selectById(reqVO.getId());
        if (sysWarnDO==null){
            throw exception(500,"该预警不存在数据库");
        }
        //校验状态是否为“待处置”
        if (!"待处置".equals(sysWarnDO.getStatus())){
            throw exception(500,"预警状态不是待处置");
        }

        // ================== 2. 构造更新对象 ==================
        // 仅更新“确认有效”相关字段，避免误更新其他字段
        SysWarnDO updateDO = new SysWarnDO();
        updateDO.setId(reqVO.getId());

        // 设置预警状态为“有效待派单”
        // 注意：这里后续抽成常量或枚举，避免硬编码
        updateDO.setStatus("无效已归档");

        // 设置无效原因
         updateDO.setInvalidReason(reqVO.getInvalidReason());

        // ================== 3. 执行更新 ==================
        // 根据主键ID更新，返回影响行数（通常为 1）
        return sysWarnMapper.updateById(updateDO);
    }

    /**
     * 计算预警剩余时间（小时，保留 2 位小数）
     *
     * 规则：
     * (trigger_time + deal_limit) - now
     */
    private String calcRemainTime(LocalDateTime triggerTime, BigDecimal dealLimit) {
        if (triggerTime == null || dealLimit == null) {
            return "--";
        }

        // 触发时间 + 处置时限
        LocalDateTime deadline = triggerTime.plusMinutes(
                dealLimit.multiply(BigDecimal.valueOf(60)).longValue()
        );

        long minutes = Duration.between(LocalDateTime.now(), deadline).toMinutes();
        if (minutes <= 0) {
            return "0";
        }

        return BigDecimal.valueOf(minutes)
                .divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP)
                .toPlainString();
    }
}
