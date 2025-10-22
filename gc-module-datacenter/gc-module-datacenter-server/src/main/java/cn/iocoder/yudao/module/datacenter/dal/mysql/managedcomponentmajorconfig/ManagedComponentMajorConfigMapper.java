package cn.iocoder.yudao.module.datacenter.dal.mysql.managedcomponentmajorconfig;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.managedcomponentmajorconfig.ManagedComponentMajorConfigDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.datacenter.controller.admin.managedcomponentmajorconfig.vo.*;

/**
 * 管理部件大类配置表 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ManagedComponentMajorConfigMapper extends BaseMapperX<ManagedComponentMajorConfigDO> {

    default PageResult<ManagedComponentMajorConfigDO> selectPage(ManagedComponentMajorConfigPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ManagedComponentMajorConfigDO>()
                .eqIfPresent(ManagedComponentMajorConfigDO::getMajorId, reqVO.getMajorId())
                .eqIfPresent(ManagedComponentMajorConfigDO::getMajorCode, reqVO.getMajorCode())
                .likeIfPresent(ManagedComponentMajorConfigDO::getMajorName, reqVO.getMajorName())
                .eqIfPresent(ManagedComponentMajorConfigDO::getMajorDesc, reqVO.getMajorDesc())
                .eqIfPresent(ManagedComponentMajorConfigDO::getSortNum, reqVO.getSortNum())
                .eqIfPresent(ManagedComponentMajorConfigDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(ManagedComponentMajorConfigDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ManagedComponentMajorConfigDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(ManagedComponentMajorConfigDO::getExtField1, reqVO.getExtField1())
                .eqIfPresent(ManagedComponentMajorConfigDO::getExtField2, reqVO.getExtField2())
                .eqIfPresent(ManagedComponentMajorConfigDO::getExtField3, reqVO.getExtField3())
                .eqIfPresent(ManagedComponentMajorConfigDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(ManagedComponentMajorConfigDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(ManagedComponentMajorConfigDO::getId));
    }

}