package cn.iocoder.yudao.module.envirhealth.service.vehicle.maintenancetype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.maintenancetype.MaintenanceTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.maintenancetype.MaintenanceTypeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.CheckResultDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.MaintenanceTypeDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.vehicle.MaintenanceTypeMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.MAINTENANCE_TYPE_NOT_EXISTS;

/**
 * 维护类型字典表【通用复用】 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MaintenanceTypeServiceImpl implements MaintenanceTypeService {

    @Resource
    private MaintenanceTypeMapper maintenanceTypeMapper;

    @Override
    public Long createMaintenanceType(MaintenanceTypeSaveReqVO createReqVO) {
        // 插入
        MaintenanceTypeDO maintenanceType = BeanUtils.toBean(createReqVO, MaintenanceTypeDO.class);
        maintenanceTypeMapper.insert(maintenanceType);
        // 返回
        return maintenanceType.getId();
    }

    @Override
    public void updateMaintenanceType(MaintenanceTypeSaveReqVO updateReqVO) {
        // 校验存在
        validateMaintenanceTypeExists(updateReqVO.getId());
        // 更新
        MaintenanceTypeDO updateObj = BeanUtils.toBean(updateReqVO, MaintenanceTypeDO.class);
        maintenanceTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteMaintenanceType(Long id) {
        // 校验存在
        validateMaintenanceTypeExists(id);
        // 删除
        maintenanceTypeMapper.deleteById(id);
    }

    private void validateMaintenanceTypeExists(Long id) {
        if (maintenanceTypeMapper.selectById(id) == null) {
            throw exception(MAINTENANCE_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public MaintenanceTypeDO getMaintenanceType(Long id) {
        return maintenanceTypeMapper.selectById(id);
    }

    @Override
    public PageResult<MaintenanceTypeDO> getMaintenanceTypePage(MaintenanceTypePageReqVO pageReqVO) {
        return maintenanceTypeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<OptionVO> getMaintenanceTypeOptions() {

        List<MaintenanceTypeDO> list;
        list = maintenanceTypeMapper.selectList(
                new LambdaQueryWrapperX<MaintenanceTypeDO>()
                        .eq(MaintenanceTypeDO::getDeleted, 0)
                        .orderByDesc(MaintenanceTypeDO::getId)
        );
        // 将DO转换为下拉框VO（label=name，value=id）
        return CollectionUtils.convertList(list, maintenanceTypeDO -> {
            OptionVO vo = new OptionVO();
            vo.setLabel(maintenanceTypeDO.getMaintenanceName());
            vo.setValue(maintenanceTypeDO.getMaintenanceTypeId());
            return vo;
        });
    }
}