package cn.iocoder.yudao.module.waterdetection.dal.mysql.waterprotectionarea;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.waterprotectionarea.WaterProtectionAreaDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.waterprotectionarea.vo.*;

/**
 * 水源保护区管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface WaterProtectionAreaMapper extends BaseMapperX<WaterProtectionAreaDO> {

    default PageResult<WaterProtectionAreaDO> selectPage(WaterProtectionAreaPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WaterProtectionAreaDO>()
                .likeIfPresent(WaterProtectionAreaDO::getProtectionLevel, reqVO.getProtectionLevel())
                .eqIfPresent(WaterProtectionAreaDO::getBoundaryRange, reqVO.getBoundaryRange())
                .likeIfPresent(WaterProtectionAreaDO::getSignboardNo, reqVO.getSignboardNo())
                .eqIfPresent(WaterProtectionAreaDO::getSignboardLocation, reqVO.getSignboardLocation())
                .betweenIfPresent(WaterProtectionAreaDO::getInstallTime, reqVO.getInstallTime())
                .eqIfPresent(WaterProtectionAreaDO::getMaintenanceRecord, reqVO.getMaintenanceRecord())
                .eqIfPresent(WaterProtectionAreaDO::getPollutionStatus, reqVO.getPollutionStatus())
                .betweenIfPresent(WaterProtectionAreaDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(WaterProtectionAreaDO::getId));
    }

}