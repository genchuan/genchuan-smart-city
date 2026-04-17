package cn.iocoder.yudao.module.chargepark.marketop.service.couponactivity.packageconfig;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.packageconfig.vo.PackageConfigChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.packageconfig.vo.PackageConfigCreateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.packageconfig.vo.PackageConfigPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.packageconfig.vo.PackageConfigUpdateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.PackageConfigDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.couponactivity.PackageConfigMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.ErrorCodeConstants.*;

@Service
@Validated
public class PackageConfigServiceImpl implements PackageConfigService {

    @Resource
    private PackageConfigMapper packageConfigMapper;

    @Override
    public PageResult<PackageConfigDO> getPage(PackageConfigPageReqVO reqVO) {
        return packageConfigMapper.selectPage(reqVO);
    }

    @Override
    public PackageConfigDO get(Long id) {
        return packageConfigMapper.selectById(id);
    }

    @Override
    public Long create(PackageConfigCreateReqVO reqVO) {
        // 校验名称唯一
        validateNameUnique(null, reqVO.getName());
        PackageConfigDO packageConfig = BeanUtils.toBean(reqVO, PackageConfigDO.class);
        packageConfig.setStatus("未生效");
        packageConfig.setSaleCount(0);
        packageConfigMapper.insert(packageConfig);
        return packageConfig.getId();
    }

    @Override
    public void update(PackageConfigUpdateReqVO reqVO) {
        validateExists(reqVO.getId());
        PackageConfigDO updateObj = BeanUtils.toBean(reqVO, PackageConfigDO.class);
        packageConfigMapper.updateById(updateObj);
    }

    @Override
    public void enable(Long id) {
        PackageConfigDO packageConfig = validateExists(id);
        if (!"未生效".equals(packageConfig.getStatus())) {
            throw exception(PACKAGE_CONFIG_NOT_EXISTS);
        }
        packageConfig.setStatus("已生效");
        packageConfig.setAuditTime(LocalDateTime.now());
        packageConfig.setEffectTime(LocalDateTime.now());
        packageConfigMapper.updateById(packageConfig);
    }

    @Override
    public void disable(Long id) {
        PackageConfigDO packageConfig = validateExists(id);
        if (!"已生效".equals(packageConfig.getStatus())) {
            throw exception(PACKAGE_CONFIG_NOT_EXISTS);
        }
        packageConfig.setStatus("未生效");
        packageConfigMapper.updateById(packageConfig);
    }

    @Override
    public PackageConfigChartRespVO getChart(String timeRange) {
        // TODO: 实现图表统计逻辑，暂时返回空数据
        PackageConfigChartRespVO respVO = new PackageConfigChartRespVO();
        respVO.setEnableCount(0);
        respVO.setSalesCount(0);
        respVO.setTypeList(new ArrayList<>());
        return respVO;
    }

    private PackageConfigDO validateExists(Long id) {
        PackageConfigDO packageConfig = packageConfigMapper.selectById(id);
        if (packageConfig == null) {
            throw exception(PACKAGE_CONFIG_NOT_EXISTS);
        }
        return packageConfig;
    }

    private void validateNameUnique(Long id, String name) {
        PackageConfigDO existing = packageConfigMapper.selectOne(PackageConfigDO::getName, name);
        if (existing != null && !existing.getId().equals(id)) {
            throw exception(PACKAGE_CONFIG_NAME_EXISTS);
        }
    }

}
