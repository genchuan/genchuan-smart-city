package cn.iocoder.yudao.module.waterdetection.dal.mysql.watersourcemanagement;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.watersourcemanagement.WaterSourceManagementDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.watersourcemanagement.vo.*;

/**
 * 水源类型及属性管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface WaterSourceManagementMapper extends BaseMapperX<WaterSourceManagementDO> {

    default PageResult<WaterSourceManagementDO> selectPage(WaterSourceManagementPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WaterSourceManagementDO>()
                .likeIfPresent(WaterSourceManagementDO::getSourceCode, reqVO.getSourceCode())
                .likeIfPresent(WaterSourceManagementDO::getSourceName, reqVO.getSourceName())
                .eqIfPresent(WaterSourceManagementDO::getSourceType, reqVO.getSourceType())
                .eqIfPresent(WaterSourceManagementDO::getLongitude, reqVO.getLongitude())
                .eqIfPresent(WaterSourceManagementDO::getLatitude, reqVO.getLatitude())
                .eqIfPresent(WaterSourceManagementDO::getAdministrativeRegion, reqVO.getAdministrativeRegion())
                .eqIfPresent(WaterSourceManagementDO::getSourceDescription, reqVO.getSourceDescription())
                .betweenIfPresent(WaterSourceManagementDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(WaterSourceManagementDO::getId));
    }

}