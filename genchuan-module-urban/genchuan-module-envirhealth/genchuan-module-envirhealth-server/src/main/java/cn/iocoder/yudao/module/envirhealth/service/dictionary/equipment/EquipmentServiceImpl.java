package cn.iocoder.yudao.module.envirhealth.service.dictionary.equipment;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.equipment.vo.EquipmentPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.equipment.vo.EquipmentSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.EquipmentDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.dictionary.EquipmentMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.EQUIPMENT_NOT_EXISTS;

/**
 * 设备 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class EquipmentServiceImpl implements EquipmentService {

    @Resource
    private EquipmentMapper equipmentMapper;

    @Override
    public Long createEquipment(EquipmentSaveReqVO createReqVO) {
        // 插入
        EquipmentDO equipment = BeanUtils.toBean(createReqVO, EquipmentDO.class);
        equipmentMapper.insert(equipment);
        // 返回
        return equipment.getId();
    }

    @Override
    public void updateEquipment(EquipmentSaveReqVO updateReqVO) {
        // 校验存在
        validateEquipmentExists(updateReqVO.getId());
        // 更新
        EquipmentDO updateObj = BeanUtils.toBean(updateReqVO, EquipmentDO.class);
        equipmentMapper.updateById(updateObj);
    }

    @Override
    public void deleteEquipment(Long id) {
        // 校验存在
        validateEquipmentExists(id);
        // 删除
        equipmentMapper.deleteById(id);
    }

    private void validateEquipmentExists(Long id) {
        if (equipmentMapper.selectById(id) == null) {
            throw exception(EQUIPMENT_NOT_EXISTS);
        }
    }

    @Override
    public EquipmentDO getEquipment(Long id) {
        return equipmentMapper.selectById(id);
    }

    @Override
    public PageResult<EquipmentDO> getEquipmentPage(EquipmentPageReqVO pageReqVO) {
        return equipmentMapper.selectPage(pageReqVO);
    }

    @Override
    public List<OptionVO> getEquipmentOptions() {

        List<EquipmentDO> list;
        list = equipmentMapper.selectList(
                new LambdaQueryWrapperX<EquipmentDO>()
                        .eq(EquipmentDO::getDeleted, 0)
                        .orderByDesc(EquipmentDO::getId)
        );
        // 将DO转换为下拉框VO（label=name，value=id）
        return CollectionUtils.convertList(list, equipmentDO -> {
            OptionVO vo = new OptionVO();
            vo.setLabel(equipmentDO.getName());
            vo.setValue(equipmentDO.getSysEquipmentId());
            return vo;
        });
    }
}