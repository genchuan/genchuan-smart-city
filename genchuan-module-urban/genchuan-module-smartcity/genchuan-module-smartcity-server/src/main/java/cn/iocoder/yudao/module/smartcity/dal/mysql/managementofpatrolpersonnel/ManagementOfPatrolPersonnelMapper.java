package cn.iocoder.yudao.module.smartcity.dal.mysql.managementofpatrolpersonnel;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.managementofpatrolpersonnel.ManagementOfPatrolPersonnelDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.managementofpatrolpersonnel.vo.*;

/**
 * 巡査人员管理 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface ManagementOfPatrolPersonnelMapper extends BaseMapperX<ManagementOfPatrolPersonnelDO> {

    default PageResult<ManagementOfPatrolPersonnelDO> selectPage(ManagementOfPatrolPersonnelPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ManagementOfPatrolPersonnelDO>()
                .likeIfPresent(ManagementOfPatrolPersonnelDO::getFullName, reqVO.getFullName())
                .eqIfPresent(ManagementOfPatrolPersonnelDO::getDepartment, reqVO.getDepartment())
                .eqIfPresent(ManagementOfPatrolPersonnelDO::getPosition, reqVO.getPosition())
                .betweenIfPresent(ManagementOfPatrolPersonnelDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ManagementOfPatrolPersonnelDO::getId));
    }

}