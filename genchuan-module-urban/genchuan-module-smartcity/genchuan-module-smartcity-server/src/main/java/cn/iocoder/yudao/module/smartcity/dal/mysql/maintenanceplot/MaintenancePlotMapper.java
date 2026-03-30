package cn.iocoder.yudao.module.smartcity.dal.mysql.maintenanceplot;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.maintenanceplot.MaintenancePlotDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.maintenanceplot.vo.*;

/**
 * 养护地块 Mapper
 *
 * @author zcq
 */
@Mapper
public interface MaintenancePlotMapper extends BaseMapperX<MaintenancePlotDO> {

    default PageResult<MaintenancePlotDO> selectPage(MaintenancePlotPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MaintenancePlotDO>()
                .likeIfPresent(MaintenancePlotDO::getPlotName, reqVO.getPlotName())
                .eqIfPresent(MaintenancePlotDO::getGreeningType, reqVO.getGreeningType())
                .betweenIfPresent(MaintenancePlotDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MaintenancePlotDO::getId));
    }

}