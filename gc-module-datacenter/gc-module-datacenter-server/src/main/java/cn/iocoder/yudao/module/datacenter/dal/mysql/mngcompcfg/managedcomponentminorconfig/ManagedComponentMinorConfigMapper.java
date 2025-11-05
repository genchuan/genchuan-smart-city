package cn.iocoder.yudao.module.datacenter.dal.mysql.mngcompcfg.managedcomponentminorconfig;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.managedcomponentminorconfig.vo.ManagedComponentMinorConfigPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.managedcomponentminorconfig.ManagedComponentMinorConfigDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理部件小类配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ManagedComponentMinorConfigMapper extends BaseMapperX<ManagedComponentMinorConfigDO> {

    default PageResult<ManagedComponentMinorConfigDO> selectPage(ManagedComponentMinorConfigPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ManagedComponentMinorConfigDO>()
                .eqIfPresent(ManagedComponentMinorConfigDO::getMinorId, reqVO.getMinorId())
                .eqIfPresent(ManagedComponentMinorConfigDO::getMajorId, reqVO.getMajorId())
                .eqIfPresent(ManagedComponentMinorConfigDO::getMinorCode, reqVO.getMinorCode())
                .likeIfPresent(ManagedComponentMinorConfigDO::getMinorName, reqVO.getMinorName())
                .eqIfPresent(ManagedComponentMinorConfigDO::getMinorDesc, reqVO.getMinorDesc())
                .eqIfPresent(ManagedComponentMinorConfigDO::getDeptCode, reqVO.getDeptCode())
                .likeIfPresent(ManagedComponentMinorConfigDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(ManagedComponentMinorConfigDO::getIsExtend, reqVO.getIsExtend())
                .eqIfPresent(ManagedComponentMinorConfigDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(ManagedComponentMinorConfigDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ManagedComponentMinorConfigDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(ManagedComponentMinorConfigDO::getExtField1, reqVO.getExtField1())
                .eqIfPresent(ManagedComponentMinorConfigDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(ManagedComponentMinorConfigDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(ManagedComponentMinorConfigDO::getId));
    }

}