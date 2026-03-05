package cn.iocoder.yudao.module.facility.service.syswarn;

import cn.hutool.core.util.RandomUtil;
import cn.iocoder.yudao.module.facility.controller.admin.syswarn.vo.SysWarnPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.syswarn.vo.SysWarnRespVO;
import cn.iocoder.yudao.module.facility.controller.admin.syswarn.vo.SysWarnSaveReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.syswarn.vo.SysWarnUpdateReqVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.road.monitor.MonitorDO;
import cn.iocoder.yudao.module.facility.dal.dataobject.road.roadfacility.RoadFacilityDO;
import cn.iocoder.yudao.module.facility.dal.dataobject.sysdevice.SysDeviceDO;
import cn.iocoder.yudao.module.facility.dal.dataobject.syswarn.SysWarnDO;
import cn.iocoder.yudao.module.facility.dal.mysql.road.monitor.MonitorMapper;
import cn.iocoder.yudao.module.facility.dal.mysql.road.roadfacility.RoadFacilityMapper;
import cn.iocoder.yudao.module.facility.dal.mysql.sysdevice.SysDeviceMapper;
import cn.iocoder.yudao.module.facility.dal.mysql.syswarn.SysWarnMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
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
    private MonitorMapper roadMonitorMapper;

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
        RoadFacilityDO facility = roadFacilityMapper.selectById(createReqVO.getFacilityId());
        if (facility == null) {
            throw exception(500,"设施不存在");
        }
        createReqVO.setFacilityName(facility.getRoadName());
        createReqVO.setFacilityCode(facility.getRoadCode());


        // ================== 3. 校验并补齐设备信息 ==================
        //3.验证关联设备id不为null，并通过关联id获取其code
        if (createReqVO.getDeviceId() == null) {
            throw exception(500,"设备ID不能为空");
        }
        SysDeviceDO device = sysDeviceMapper.selectById(createReqVO.getDeviceId());
        if (device == null) {
            throw exception(500,"设备不存在");
        }
        createReqVO.setDeviceCode(device.getDeviceCode());


        // ================== 4. 校验并补齐监测数据 ==================
        //4.验证关联监测实时数据ID不为null，并通过关联id获取其code
        if (createReqVO.getMonitorId() == null) {
            throw exception(500,"监测数据ID不能为空");
        }
        //TODO 需要将其进行抽象，不然现在通用预警只能使用 道路预警
        MonitorDO monitor = roadMonitorMapper.selectById(createReqVO.getMonitorId());
        if (monitor == null) {
            throw exception(500,"监测数据不存在");
        }
        createReqVO.setMonitorCode(monitor.getMonitorCode());
        // ================== 5. 预警状态 / 派单状态 ==================
        createReqVO.setStatus("待处置");
        createReqVO.setAssignStatus("未派单");

        // ================== 6. 所属设施类型 ==================
        createReqVO.setFacilityType("道路");

        // ================== 7. 触发时间 ==================
        // 使用当前时间
        createReqVO.setTriggerTime(LocalDateTime.now());

        //8.根据监测id获取预警类型、超标指标名称、超标数值、超标阈值数值
        // ================== 8. 根据监测数据判断预警类型 ==================
        // TODO 后续考虑monitor抽取公共方法

        if (monitor.getPotholeNum() != null
                && monitor.getPotholeNumThreshold() != null
                && monitor.getPotholeNum().compareTo(monitor.getPotholeNumThreshold()) > 0) {

            createReqVO.setType("坑洼数量超标");
            createReqVO.setOverIndex("坑洼数量");
            createReqVO.setOverValue(monitor.getPotholeNum());
            createReqVO.setThresholdValue(monitor.getPotholeNumThreshold());

        } else if (monitor.getCrackLength() != null
                && monitor.getCrackLengthThreshold() != null
                && monitor.getCrackLength().compareTo(monitor.getCrackLengthThreshold()) > 0) {

            createReqVO.setType("裂缝长度超标");
            createReqVO.setOverIndex("裂缝长度");
            createReqVO.setOverValue(monitor.getCrackLength());
            createReqVO.setThresholdValue(monitor.getCrackLengthThreshold());

        } else if (monitor.getRoadTemp() != null
                && monitor.getRoadTempThreshold() != null
                && monitor.getRoadTemp().compareTo(monitor.getRoadTempThreshold()) > 0) {

            createReqVO.setType("路面温度超标");
            createReqVO.setOverIndex("路面温度");
            createReqVO.setOverValue(monitor.getRoadTemp());
            createReqVO.setThresholdValue(monitor.getRoadTempThreshold());

        } else if (monitor.getTrafficFlow() != null
                && monitor.getTrafficFlowThreshold() != null
                && monitor.getTrafficFlow().compareTo(monitor.getTrafficFlowThreshold()) > 0) {

            createReqVO.setType("交通流量超标");
            createReqVO.setOverIndex("交通流量");
            createReqVO.setOverValue(monitor.getTrafficFlow());
            createReqVO.setThresholdValue(monitor.getTrafficFlowThreshold());
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
