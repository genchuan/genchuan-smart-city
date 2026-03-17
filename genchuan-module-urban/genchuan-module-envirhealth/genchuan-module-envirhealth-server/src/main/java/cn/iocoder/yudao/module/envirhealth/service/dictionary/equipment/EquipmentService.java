package cn.iocoder.yudao.module.envirhealth.service.dictionary.equipment;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.equipment.vo.EquipmentPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.equipment.vo.EquipmentSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.equipment.EquipmentDO;
import jakarta.validation.Valid;

/**
 * 设备 Service 接口
 *
 * @author 芋道源码
 */
public interface EquipmentService {

    /**
     * 创建设备
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEquipment(@Valid EquipmentSaveReqVO createReqVO);

    /**
     * 更新设备
     *
     * @param updateReqVO 更新信息
     */
    void updateEquipment(@Valid EquipmentSaveReqVO updateReqVO);

    /**
     * 删除设备
     *
     * @param id 编号
     */
    void deleteEquipment(Long id);

    /**
     * 获得设备
     *
     * @param id 编号
     * @return 设备
     */
    EquipmentDO getEquipment(Long id);

    /**
     * 获得设备分页
     *
     * @param pageReqVO 分页查询
     * @return 设备分页
     */
    PageResult<EquipmentDO> getEquipmentPage(EquipmentPageReqVO pageReqVO);

}