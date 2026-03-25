package cn.iocoder.yudao.module.facility.service.manhole.manholeconfig;

import cn.hutool.core.util.IdUtil;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholeconfig.vo.*;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.manholeconfig.ManholeConfigDO;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.manholecover.ManholeCoverDO;
import cn.iocoder.yudao.module.facility.dal.mysql.manhole.manholeconfig.ManholeConfigMapper;
import cn.iocoder.yudao.module.facility.dal.mysql.manhole.manholecover.ManholeCoverMapper;
import cn.iocoder.yudao.module.facility.dal.mysql.manhole.manholemonitor.ManholeMonitorMapper;
import cn.iocoder.yudao.module.facility.dal.mysql.sysdevice.SysDeviceMapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.facility.enums.ErrorCodeConstants.*;

/**
 * 窨井盖监测配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ManholeConfigServiceImpl implements ManholeConfigService {

    @Resource
    private ManholeConfigMapper manholeConfigMapper;

    @Resource
    private ManholeCoverMapper manholeCoverMapper;

    @Resource
    private ManholeMonitorMapper monitorMapper;

    @Resource
    private SysDeviceMapper sysDeviceMapper;

    @Override
    public Long createManholeConfig(ManholeConfigSaveReqVO createReqVO) {
        // 插入
        ManholeConfigDO manholeConfig = BeanUtils.toBean(createReqVO, ManholeConfigDO.class);
        manholeConfigMapper.insert(manholeConfig);
        // 返回
        return manholeConfig.getId();
    }

    @Override
    public void updateManholeConfig(ManholeConfigSaveReqVO updateReqVO) {
        // 校验存在
        validateManholeConfigExists(updateReqVO.getId());
        // 更新
        ManholeConfigDO updateObj = BeanUtils.toBean(updateReqVO, ManholeConfigDO.class);
        manholeConfigMapper.updateById(updateObj);
    }

    @Override
    public void deleteManholeConfig(Long id) {
        // 校验存在
        validateManholeConfigExists(id);
        // 删除
        manholeConfigMapper.deleteById(id);
    }

    private void validateManholeConfigExists(Long id) {
        if (manholeConfigMapper.selectById(id) == null) {
            throw exception(MANHOLE_CONFIG_NOT_EXISTS);
        }
    }

    @Override
    public ManholeConfigDO getManholeConfig(Long id) {
        return manholeConfigMapper.selectById(id);
    }

    @Override
    public PageResult<ManholeConfigDO> getManholeConfigPage(ManholeConfigPageReqVO pageReqVO) {
        return manholeConfigMapper.selectPage(pageReqVO);
    }

    /**
     * 保存监测配置（新增/编辑）
     */
//    @Transactional(rollbackFor = Exception.class)
//    public void saveConfig(ManholeConfigReqVO configVO) {
//
//        validateManholeConfigExists(configVO.getId());
//
//        LambdaQueryWrapperX<ManholeCoverDO> lambdaQueryWrapperX = new LambdaQueryWrapperX();
//        lambdaQueryWrapperX.eq(ManholeCoverDO::getCoverNo,configVO.getCoverNo());
//        ManholeCoverDO rs = manholeCoverMapper.selectOne(lambdaQueryWrapperX);
//        // 1. 唯一性校验
//        if (rs != null) {
//            throw exception("井盖编号已存在！");
//        }
//
//        LambdaQueryWrapperX<SysDeviceDO> sysDeviceDOLambdaQueryWrapperX = new LambdaQueryWrapperX();
//        sysDeviceDOLambdaQueryWrapperX.eq(SysDeviceDO::getDeviceCode,configVO.getDeviceCode());
//        SysDeviceDO sysDeviceDO  = sysDeviceMapper.selectOne(sysDeviceDOLambdaQueryWrapperX);
//
//        if (sysDeviceDO != null) {
//            throw exception("设备编号号已存在！");
//        }
//
//        // 2. 阈值合理性校验
//        if (configVO.getTiltAngleThreshold().compareTo(BigDecimal.ZERO) <= 0
//                || configVO.getTiltAngleThreshold().compareTo(BigDecimal.valueOf(90)) >= 0) {
//            throw exception("倾斜角度阈值需在0-90度之间！");
//        }
//
//        // 3. 保存井盖基础信息
//        ManholeCoverDO cover = new ManholeCoverDO();
//        cover.setCoverNo(configVO.getCoverNo());
//        cover.setRoadId(configVO.getRoadId());
//        if (configVO.getId() == null) {
//            manholeCoverMapper.insert(cover);
//        } else {
//            cover.setId(configVO.getId());
//            manholeCoverMapper.updateById(cover);
//        }
//
//        // 4. 保存监测配置
//        ManholeConfigDO config = new ManholeConfigDO();
//        config.setCoverId(cover.getId());
//        config.setCollectFrequency(configVO.getCollectFrequency());
//        config.setTiltAngleThreshold(configVO.getTiltAngleThreshold());
//        if (configVO.getConfigId() == null) {
//            manholeConfigMapper.insert(config);
//        } else {
//            config.setId(configVO.getConfigId());
//            manholeConfigMapper.updateById(config);
//        }
//
//        // 5. 保存监测主表
//        ManholeMonitorDO monitor = new ManholeMonitorDO();
//        monitor.setCoverId(cover.getId());
//        monitor.setDeviceId(configVO.getDeviceId());
//        monitor.setStaffId(configVO.getStaffId());
//        monitor.setRiskLevelId(configVO.getRiskLevelId());
//        if (configVO.getId() == null) {
//            monitorMapper.insert(monitor);
//        } else {
//            monitor.setId(configVO.getId());
//            monitorMapper.updateById(monitor);
//        }
//    }

    @Override
    public PageResult<ManholeCoverConfigPageRespVO> getConfigPage(String coverId, Integer configStatus, String tenantId, Integer pageNo, Integer pageSize) {
        IPage<ManholeCoverConfigPageRespVO> mpPage = new Page<>(pageNo, pageSize);
        IPage<ManholeCoverConfigPageRespVO> result = manholeConfigMapper.selectConfigPage(mpPage, coverId, configStatus, tenantId);
        return new PageResult<>(result.getRecords(), result.getTotal());
    }

    @Override
    public ManholeCoverConfigDetailRespVO getDetail(Long id, Long tenantId) {
        // 直接调用你自定义 XML 的 SQL → 正确执行关联查询 + 嵌套映射
        ManholeCoverConfigDetailRespVO detail = manholeConfigMapper.selectDetailById(id, tenantId);
        return detail;
    }
    /**
     * 添加监测配置
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult<ManholeCoverConfigAddRespVO> addManholeCoverConfig(ManholeCoverConfigAddReqVO reqVO) {

        LambdaQueryWrapperX<ManholeCoverDO> lq = new LambdaQueryWrapperX();
        lq.eq(ManholeCoverDO::getId, reqVO.getCoverId());
        ManholeCoverDO manholeCoverDO = manholeCoverMapper.selectOne(lq);
        if(manholeCoverDO == null){
            throw exception(MANHOLE_CONFIG_NOT_EXISTS);
        }
        LambdaQueryWrapperX<ManholeConfigDO> lq2 = new LambdaQueryWrapperX();
        lq2.eq(ManholeConfigDO::getCoverId, reqVO.getCoverId());
        ManholeConfigDO manholeConfigDO =manholeConfigMapper.selectOne(lq2);
        if (manholeConfigDO != null){
            throw exception(MANHOLE_CONFIG_EXISTS);
        }
        ManholeConfigDO configDO = new ManholeConfigDO();

        // 1. 主键 & 井盖ID
        configDO.setCoverId(Long.valueOf(reqVO.getCoverId()));

        // 2. 阈值配置
        ManholeCoverConfigAddReqVO.ThresholdConfig threshold = reqVO.getThresholdConfig();
        configDO.setTiltAngleThreshold(BigDecimal.valueOf(threshold.getTiltAngleThreshold()));
        configDO.setOpenDurationThreshold(BigDecimal.valueOf(threshold.getOpenDurationThreshold()));
        configDO.setExtCommon1(String.valueOf(threshold.getDisplacementThreshold()));
        configDO.setExtCommon2(String.valueOf(threshold.getWaterLevelThreshold()));

        // 3. 采集配置
        ManholeCoverConfigAddReqVO.CollectConfig collect = reqVO.getCollectConfig();
        configDO.setCollectFrequency(collect.getCollectFrequency());
        configDO.setOfflineTimeout(collect.getOfflineTimeout());
        configDO.setDataUploadMode(collect.getDataUploadMode() == null ? 0 : collect.getDataUploadMode());

        // 4. 报警配置 → 直接赋值 List，适配你的 DO！
        ManholeCoverConfigAddReqVO.AlarmConfig alarm = reqVO.getAlarmConfig();
        configDO.setAlarmLevel(alarm.getAlarmLevel() == null ? 1 : alarm.getAlarmLevel());
        configDO.setAlarmDelay(alarm.getAlarmDelay() == null ? 30 : alarm.getAlarmDelay());
        configDO.setExtCommon3(alarm.getAlarmType());  // 直接 List<Integer>
        configDO.setExtCommon4(alarm.getAlarmRecipient()); // 直接 List<String>

        // 5. 状态 & 租户
        Integer configStatus = reqVO.getConfigStatus() == null ? 0 : reqVO.getConfigStatus();
        configDO.setConfigStatus(configStatus);
        configDO.setConfigStatusName(configStatus == 0 ? "未生效" : "已生效");
        configDO.setTenantId(Long.valueOf(reqVO.getTenantId()));
        configDO.setChainHash("0x" + IdUtil.randomUUID().replace("-", ""));

        // 6. 保存
        manholeConfigMapper.insert(configDO);

        // 7. 返回
        ManholeCoverConfigAddRespVO respVO = BeanUtils.toBean(configDO, ManholeCoverConfigAddRespVO.class);
        respVO.setConfigId(configDO.getId().toString());
        respVO.setCoverId(reqVO.getCoverId());
        respVO.setTenantId(reqVO.getTenantId());

        return CommonResult.success(respVO);
    }

   /**
     * 编辑窨井盖配置
     *
     * @param reqVO 配置信息
     * @return 配置信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult<ManholeCoverConfigEditRespVO> editManholeCoverConfig(ManholeCoverConfigEditReqVO reqVO) {
        // 1. 校验窨井盖是否存在
        LambdaQueryWrapperX<ManholeCoverDO> coverLq = new LambdaQueryWrapperX<>();
        coverLq.eq(ManholeCoverDO::getId, reqVO.getCoverId());
        ManholeCoverDO manholeCoverDO = manholeCoverMapper.selectOne(coverLq);
        if (manholeCoverDO == null) {
            throw exception(MANHOLE_CONFIG_NOT_EXISTS);
        }

        // 2. 校验配置是否存在
        LambdaQueryWrapperX<ManholeConfigDO> configLq = new LambdaQueryWrapperX<>();
        configLq.eq(ManholeConfigDO::getCoverId, reqVO.getCoverId());
        ManholeConfigDO configDO = manholeConfigMapper.selectOne(configLq);
        if (configDO == null) {
            throw exception(MANHOLE_CONFIG_NOT_EXISTS);
        }

        // 3. 动态更新字段（仅更新传入的非空字段，不覆盖原有数据）
        // 3.1 阈值配置
        ManholeCoverConfigEditReqVO.ThresholdConfig threshold = reqVO.getThresholdConfig();
        if (threshold != null) {
            if (threshold.getTiltAngleThreshold() != null) {
                configDO.setTiltAngleThreshold(BigDecimal.valueOf(threshold.getTiltAngleThreshold()));
            }
            if (threshold.getOpenDurationThreshold() != null) {
                configDO.setOpenDurationThreshold(BigDecimal.valueOf(threshold.getOpenDurationThreshold()));
            }
            if (threshold.getDisplacementThreshold() != null) {
                configDO.setExtCommon1(String.valueOf(threshold.getDisplacementThreshold()));
            }
            if (threshold.getWaterLevelThreshold() != null) {
                configDO.setExtCommon2(String.valueOf(threshold.getWaterLevelThreshold()));
            }
        }

        // 3.2 采集配置
        ManholeCoverConfigEditReqVO.CollectConfig collect = reqVO.getCollectConfig();
        if (collect != null) {
            if (collect.getCollectFrequency() != null) {
                configDO.setCollectFrequency(collect.getCollectFrequency());
            }
            if (collect.getOfflineTimeout() != null) {
                configDO.setOfflineTimeout(collect.getOfflineTimeout());
            }
            if (collect.getDataUploadMode() != null) {
                configDO.setDataUploadMode(collect.getDataUploadMode());
            }
        }

        // 3.3 报警配置（List类型直接赋值，DO已配置Jackson类型处理器）
        ManholeCoverConfigEditReqVO.AlarmConfig alarm = reqVO.getAlarmConfig();
        if (alarm != null) {
            if (alarm.getAlarmLevel() != null) {
                configDO.setAlarmLevel(alarm.getAlarmLevel());
            }
            if (alarm.getAlarmDelay() != null) {
                configDO.setAlarmDelay(alarm.getAlarmDelay());
            }
            if (alarm.getAlarmType() != null) {
                configDO.setExtCommon3(alarm.getAlarmType());
            }
            if (alarm.getAlarmRecipient() != null) {
                configDO.setExtCommon4(alarm.getAlarmRecipient());
            }
        }

        // 3.4 配置状态 + 状态名称
        if (reqVO.getConfigStatus() != null) {
            configDO.setConfigStatus(reqVO.getConfigStatus());
            configDO.setConfigStatusName(reqVO.getConfigStatus() == 0 ? "未生效" : "已生效");
        }

        // 4. 租户一致性校验（可选，和你新增逻辑保持一致）
        configDO.setTenantId(Long.valueOf(reqVO.getTenantId()));
        // 重新生成区块链哈希（和新增逻辑一致）
        configDO.setChainHash("0x" + IdUtil.randomUUID().replace("-", ""));

        // 5. 更新数据库
        manholeConfigMapper.updateById(configDO);

        // 6. 封装返回结果
        ManholeCoverConfigEditRespVO respVO = BeanUtils.toBean(configDO, ManholeCoverConfigEditRespVO.class);
        respVO.setConfigId(configDO.getId());
        respVO.setCoverId(reqVO.getCoverId());
        respVO.setTenantId(reqVO.getTenantId());

        return CommonResult.success(respVO);
    }
    /**
     * 删除窨井盖配置
     *
     * @param configId 配置ID
     * @param reqVO 删除信息
     * @return 删除结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult<String> deleteManholeCoverConfig(String configId, ManholeCoverConfigDeleteReqVO reqVO) {
        // 1. 校验配置是否存在（租户隔离）
        LambdaQueryWrapperX<ManholeConfigDO> queryWrapper = new LambdaQueryWrapperX<>();
        queryWrapper.eq(ManholeConfigDO::getId, Long.valueOf(configId));
        queryWrapper.eq(ManholeConfigDO::getTenantId, Long.valueOf(reqVO.getTenantId()));
        ManholeConfigDO configDO = manholeConfigMapper.selectOne(queryWrapper);

        // 2. 不存在抛异常
        if (configDO == null) {
            throw exception(MANHOLE_CONFIG_NOT_EXISTS);
        }

        // 3. 🔥 核心修复：手动逻辑删除（绕过插件，强制更新deleted=1）
        LambdaUpdateWrapper<ManholeConfigDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(ManholeConfigDO::getId, configId);
        updateWrapper.eq(ManholeConfigDO::getTenantId, reqVO.getTenantId());
        // 强制设置删除标志
        updateWrapper.set(ManholeConfigDO::getDeleted, true);

        // 执行更新
        manholeConfigMapper.update(null, updateWrapper);

        // 4. 返回成功
        return CommonResult.success(configId);
    }

    /**
     * 开始监听
     *
     * @param coverId 井盖ID
     * @param tenantId 租户ID
     * @param operateUserId 操作人ID
     * @return 监听结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult<ManholeMonitorOperateRespVO> startMonitor(String coverId, String tenantId, String operateUserId) {
        Long coverIdLong = Long.valueOf(coverId);
        Long tenantIdLong = Long.valueOf(tenantId);

        // 1. 校验井盖存在
        ManholeCoverDO cover = manholeCoverMapper.selectOne(new LambdaQueryWrapperX<ManholeCoverDO>()
                .eq(ManholeCoverDO::getId, coverIdLong));
        if (cover == null) {
            throw exception(COVER_NOT_EXISTS);
        }

        // 2. 校验配置存在
        ManholeConfigDO config = manholeConfigMapper.selectOne(new LambdaQueryWrapperX<ManholeConfigDO>()
                .eq(ManholeConfigDO::getCoverId, coverIdLong)
                .eq(ManholeConfigDO::getTenantId, tenantIdLong));
        if (config == null) {
            throw exception(MANHOLE_CONFIG_NOT_EXISTS);
        }

        // 3. 启动监测 = 修改配置状态为 已生效(1)
        config.setConfigStatus(1);
        config.setConfigStatusName("已生效");
        config.setUpdater(operateUserId);
        manholeConfigMapper.updateById(config);

        // 4. 组装返回
        ManholeMonitorOperateRespVO resp = new ManholeMonitorOperateRespVO();
        resp.setCoverId(coverId);
        resp.setMonitorStatus(1);
        resp.setOperateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        resp.setTenantId(tenantId);

        return CommonResult.success(resp);
    }

    /**
     * 停止监听
     *
     * @param coverId 井盖ID
     * @param stopReason 停止原因
     * @param tenantId 租户ID
     * @param operateUserId 操作人ID
     * @return 停止结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult<ManholeMonitorOperateRespVO> stopMonitor(String coverId, String stopReason, String tenantId, String operateUserId) {
        Long coverIdLong = Long.valueOf(coverId);
        Long tenantIdLong = Long.valueOf(tenantId);

        // 1. 校验井盖存在
        ManholeCoverDO cover = manholeCoverMapper.selectOne(new LambdaQueryWrapperX<ManholeCoverDO>()
                .eq(ManholeCoverDO::getId, coverIdLong));
        if (cover == null) {
            throw exception(COVER_NOT_EXISTS);
        }

        // 2. 校验配置存在
        ManholeConfigDO config = manholeConfigMapper.selectOne(new LambdaQueryWrapperX<ManholeConfigDO>()
                .eq(ManholeConfigDO::getCoverId, coverIdLong)
                .eq(ManholeConfigDO::getTenantId, tenantIdLong));
        if (config == null) {
            throw exception(MANHOLE_CONFIG_NOT_EXISTS);
        }

        // 3. 核心：停止监测 = 修改配置状态为【已停用(2)】
        config.setConfigStatus(2);
        config.setConfigStatusName("已停用");
        config.setUpdater(operateUserId);
        manholeConfigMapper.updateById(config);

        // 4. 组装返回结果
        ManholeMonitorOperateRespVO resp = new ManholeMonitorOperateRespVO();
        resp.setCoverId(coverId);
        resp.setMonitorStatus(0); // 接口要求：0-已停止
        resp.setOperateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        resp.setTenantId(tenantId);

        return CommonResult.success(resp);
    }
}