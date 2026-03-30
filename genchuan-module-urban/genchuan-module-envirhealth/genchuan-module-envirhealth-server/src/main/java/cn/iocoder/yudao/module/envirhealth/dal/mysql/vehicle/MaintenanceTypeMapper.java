package cn.iocoder.yudao.module.envirhealth.dal.mysql.vehicle;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.maintenancetype.MaintenanceTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.MaintenanceTypeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 维护类型字典表【通用复用】 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface MaintenanceTypeMapper extends BaseMapperX<MaintenanceTypeDO> {

    default PageResult<MaintenanceTypeDO> selectPage(MaintenanceTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MaintenanceTypeDO>()
                .eqIfPresent(MaintenanceTypeDO::getMaintenanceTypeId, reqVO.getMaintenanceTypeId())
                .likeIfPresent(MaintenanceTypeDO::getMaintenanceName, reqVO.getMaintenanceName())
                .eqIfPresent(MaintenanceTypeDO::getDescription, reqVO.getDescription())
                .eqIfPresent(MaintenanceTypeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(MaintenanceTypeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(MaintenanceTypeDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(MaintenanceTypeDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(MaintenanceTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MaintenanceTypeDO::getId));
    }

}