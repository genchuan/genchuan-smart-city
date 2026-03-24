package cn.iocoder.yudao.module.facility.service.manhole.manholeconfig;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholeconfig.vo.ManholeConfigPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholeconfig.vo.ManholeConfigReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholeconfig.vo.ManholeConfigSaveReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholeconfig.vo.ManholeCoverConfigPageRespVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.manholeconfig.ManholeConfigDO;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.manholecover.ManholeCoverDO;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.manholemonitor.ManholeMonitorDO;
import cn.iocoder.yudao.module.facility.dal.dataobject.sysdevice.SysDeviceDO;
import cn.iocoder.yudao.module.facility.dal.mysql.manhole.manholeconfig.ManholeConfigMapper;
import cn.iocoder.yudao.module.facility.dal.mysql.manhole.manholecover.ManholeCoverMapper;
import cn.iocoder.yudao.module.facility.dal.mysql.manhole.manholemonitor.ManholeMonitorMapper;
import cn.iocoder.yudao.module.facility.dal.mysql.sysdevice.SysDeviceMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.facility.enums.ErrorCodeConstants.MANHOLE_CONFIG_NOT_EXISTS;

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
    @Transactional(rollbackFor = Exception.class)
    public void saveConfig(ManholeConfigReqVO configVO) {

        validateManholeConfigExists(configVO.getId());

        LambdaQueryWrapperX<ManholeCoverDO> lambdaQueryWrapperX = new LambdaQueryWrapperX();
        lambdaQueryWrapperX.eq(ManholeCoverDO::getCoverNo,configVO.getCoverNo());
        ManholeCoverDO rs = manholeCoverMapper.selectOne(lambdaQueryWrapperX);
        // 1. 唯一性校验
        if (rs != null) {
            throw exception("井盖编号已存在！");
        }

        LambdaQueryWrapperX<SysDeviceDO> sysDeviceDOLambdaQueryWrapperX = new LambdaQueryWrapperX();
        sysDeviceDOLambdaQueryWrapperX.eq(SysDeviceDO::getDeviceCode,configVO.getDeviceCode());
        SysDeviceDO sysDeviceDO  = sysDeviceMapper.selectOne(sysDeviceDOLambdaQueryWrapperX);

        if (sysDeviceDO != null) {
            throw exception("设备编号号已存在！");
        }

        // 2. 阈值合理性校验
        if (configVO.getTiltAngleThreshold().compareTo(BigDecimal.ZERO) <= 0
                || configVO.getTiltAngleThreshold().compareTo(BigDecimal.valueOf(90)) >= 0) {
            throw exception("倾斜角度阈值需在0-90度之间！");
        }

        // 3. 保存井盖基础信息
        ManholeCoverDO cover = new ManholeCoverDO();
        cover.setCoverNo(configVO.getCoverNo());
        cover.setRoadId(configVO.getRoadId());
        if (configVO.getId() == null) {
            manholeCoverMapper.insert(cover);
        } else {
            cover.setId(configVO.getId());
            manholeCoverMapper.updateById(cover);
        }

        // 4. 保存监测配置
        ManholeConfigDO config = new ManholeConfigDO();
        config.setCoverId(cover.getId());
        config.setCollectFrequency(configVO.getCollectFrequency());
        config.setTiltAngleThreshold(configVO.getTiltAngleThreshold());
        if (configVO.getConfigId() == null) {
            manholeConfigMapper.insert(config);
        } else {
            config.setId(configVO.getConfigId());
            manholeConfigMapper.updateById(config);
        }

        // 5. 保存监测主表
        ManholeMonitorDO monitor = new ManholeMonitorDO();
        monitor.setCoverId(cover.getId());
        monitor.setDeviceId(configVO.getDeviceId());
        monitor.setStaffId(configVO.getStaffId());
        monitor.setRiskLevelId(configVO.getRiskLevelId());
        if (configVO.getId() == null) {
            monitorMapper.insert(monitor);
        } else {
            monitor.setId(configVO.getId());
            monitorMapper.updateById(monitor);
        }
    }

    @Override
    public PageResult<ManholeCoverConfigPageRespVO> getConfigPage(String coverId, Integer configStatus, String tenantId, Integer pageNo, Integer pageSize) {
        IPage<ManholeCoverConfigPageRespVO> mpPage = new Page<>(pageNo, pageSize);
        IPage<ManholeCoverConfigPageRespVO> result = manholeConfigMapper.selectConfigPage(mpPage, coverId, configStatus, tenantId);
        return new PageResult<>(result.getRecords(), result.getTotal());
    }

}