package cn.iocoder.yudao.module.waterdetection.service.equipmentasset;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.equipmentasset.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.equipmentasset.EquipmentAssetDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 设备资产台账管理 Service 接口
 *
 * @author zcq
 */
public interface EquipmentAssetService {

    /**
     * 创建设备资产台账管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEquipmentAsset(@Valid EquipmentAssetSaveReqVO createReqVO);

    /**
     * 更新设备资产台账管理
     *
     * @param updateReqVO 更新信息
     */
    void updateEquipmentAsset(@Valid EquipmentAssetSaveReqVO updateReqVO);

    /**
     * 删除设备资产台账管理
     *
     * @param id 编号
     */
    void deleteEquipmentAsset(Long id);

    /**
     * 获得设备资产台账管理
     *
     * @param id 编号
     * @return 设备资产台账管理
     */
    EquipmentAssetDO getEquipmentAsset(Long id);

    /**
     * 获得设备资产台账管理分页
     *
     * @param pageReqVO 分页查询
     * @return 设备资产台账管理分页
     */
    PageResult<EquipmentAssetDO> getEquipmentAssetPage(EquipmentAssetPageReqVO pageReqVO);

}