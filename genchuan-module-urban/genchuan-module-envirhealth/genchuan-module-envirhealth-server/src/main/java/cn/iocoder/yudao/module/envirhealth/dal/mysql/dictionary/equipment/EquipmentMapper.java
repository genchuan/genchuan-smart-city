package cn.iocoder.yudao.module.envirhealth.dal.mysql.dictionary.equipment;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.equipment.vo.EquipmentPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.equipment.EquipmentDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface EquipmentMapper extends BaseMapperX<EquipmentDO> {

    default PageResult<EquipmentDO> selectPage(EquipmentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EquipmentDO>()
                .eqIfPresent(EquipmentDO::getSysEquipmentId, reqVO.getSysEquipmentId())
                .likeIfPresent(EquipmentDO::getName, reqVO.getName())
                .eqIfPresent(EquipmentDO::getCode, reqVO.getCode())
                .eqIfPresent(EquipmentDO::getType, reqVO.getType())
                .eqIfPresent(EquipmentDO::getModel, reqVO.getModel())
                .eqIfPresent(EquipmentDO::getSpecification, reqVO.getSpecification())
                .eqIfPresent(EquipmentDO::getMaintenanceCycle, reqVO.getMaintenanceCycle())
                .eqIfPresent(EquipmentDO::getStatus, reqVO.getStatus())
                .eqIfPresent(EquipmentDO::getRemark, reqVO.getRemark())
                .eqIfPresent(EquipmentDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EquipmentDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(EquipmentDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(EquipmentDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(EquipmentDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EquipmentDO::getId));
    }

}