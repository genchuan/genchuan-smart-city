package cn.iocoder.yudao.module.chargepark.marketop.service.couponactivity.packageconfig;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
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
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        packageConfig.setStatus("0");
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
        if (!"0".equals(packageConfig.getStatus())) {
            throw exception(PACKAGE_CONFIG_NOT_EXISTS);
        }
        packageConfig.setStatus("1");
        packageConfig.setAuditTime(LocalDateTime.now());
        packageConfig.setEffectTime(LocalDateTime.now());
        packageConfigMapper.updateById(packageConfig);
    }

    @Override
    public void disable(Long id) {
        PackageConfigDO packageConfig = validateExists(id);
        if (!"1".equals(packageConfig.getStatus())) {
            throw exception(PACKAGE_CONFIG_NOT_EXISTS);
        }
        packageConfig.setStatus("0");
        packageConfigMapper.updateById(packageConfig);
    }

    @Override
    public PackageConfigChartRespVO getChart() {
        PackageConfigChartRespVO respVO = new PackageConfigChartRespVO();

        List<PackageConfigDO> allList = packageConfigMapper.selectList(new LambdaQueryWrapperX<>());

        // enableCount: status为1的数量
        long enableCount = allList.stream().filter(item -> "1".equals(item.getStatus())).count();
        respVO.setEnableCount((int) enableCount);

        // salesCount: 总数
        respVO.setSalesCount(allList.size());

        // typeList: 按type分组统计数量
        Map<String, Long> typeCount = allList.stream()
                .collect(Collectors.groupingBy(
                        item -> item.getType() != null ? item.getType() : "unknown",
                        Collectors.counting()));
        List<PackageConfigChartRespVO.TypeCountItem> typeList = typeCount.entrySet().stream()
                .map(entry -> {
                    PackageConfigChartRespVO.TypeCountItem item = new PackageConfigChartRespVO.TypeCountItem();
                    item.setType(entry.getKey());
                    item.setCount(entry.getValue().intValue());
                    return item;
                })
                .collect(Collectors.toList());
        respVO.setTypeList(typeList);

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
