package cn.iocoder.yudao.module.datacenter.dal.mysql.mngcompcfg.managedmajorminorrel;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.managedmajorminorrel.vo.ManagedMajorMinorRelPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.managedmajorminorrel.ManagedMajorMinorRelDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理部件大类小类关联 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ManagedMajorMinorRelMapper extends BaseMapperX<ManagedMajorMinorRelDO> {

    default PageResult<ManagedMajorMinorRelDO> selectPage(ManagedMajorMinorRelPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ManagedMajorMinorRelDO>()
                .eqIfPresent(ManagedMajorMinorRelDO::getRelId, reqVO.getRelId())
                .eqIfPresent(ManagedMajorMinorRelDO::getMajorId, reqVO.getMajorId())
                .eqIfPresent(ManagedMajorMinorRelDO::getMinorId, reqVO.getMinorId())
                .eqIfPresent(ManagedMajorMinorRelDO::getRelStatus, reqVO.getRelStatus())
                .betweenIfPresent(ManagedMajorMinorRelDO::getRelTime, reqVO.getRelTime())
                .betweenIfPresent(ManagedMajorMinorRelDO::getUnrelTime, reqVO.getUnrelTime())
                .eqIfPresent(ManagedMajorMinorRelDO::getOperateUser, reqVO.getOperateUser())
                .eqIfPresent(ManagedMajorMinorRelDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ManagedMajorMinorRelDO::getExtField1, reqVO.getExtField1())
                .eqIfPresent(ManagedMajorMinorRelDO::getExtField2, reqVO.getExtField2())
                .eqIfPresent(ManagedMajorMinorRelDO::getExtField3, reqVO.getExtField3())
                .eqIfPresent(ManagedMajorMinorRelDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(ManagedMajorMinorRelDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(ManagedMajorMinorRelDO::getId));
    }

}