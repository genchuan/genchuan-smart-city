package cn.iocoder.yudao.module.smartcity.dal.mysql.maintenancepersonnel;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.maintenancepersonnel.MaintenancePersonnelDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.maintenancepersonnel.vo.*;

/**
 * 养护人员 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface MaintenancePersonnelMapper extends BaseMapperX<MaintenancePersonnelDO> {

    default PageResult<MaintenancePersonnelDO> selectPage(MaintenancePersonnelPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MaintenancePersonnelDO>()
                .eqIfPresent(MaintenancePersonnelDO::getPersonnelId, reqVO.getPersonnelId())
                .likeIfPresent(MaintenancePersonnelDO::getPersonnelName, reqVO.getPersonnelName())
                .betweenIfPresent(MaintenancePersonnelDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MaintenancePersonnelDO::getId));
    }

}