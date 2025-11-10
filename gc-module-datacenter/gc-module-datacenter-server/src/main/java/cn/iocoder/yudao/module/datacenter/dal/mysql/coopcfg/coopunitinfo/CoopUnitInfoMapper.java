package cn.iocoder.yudao.module.datacenter.dal.mysql.coopcfg.coopunitinfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopunitinfo.vo.CoopUnitInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.coopcfg.coopunitinfo.CoopUnitInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 联动单位信息 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface CoopUnitInfoMapper extends BaseMapperX<CoopUnitInfoDO> {

    default PageResult<CoopUnitInfoDO> selectPage(CoopUnitInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CoopUnitInfoDO>()
                .eqIfPresent(CoopUnitInfoDO::getUnitId, reqVO.getUnitId())
                .eqIfPresent(CoopUnitInfoDO::getUnitCode, reqVO.getUnitCode())
                .likeIfPresent(CoopUnitInfoDO::getUnitName, reqVO.getUnitName())
                .eqIfPresent(CoopUnitInfoDO::getUnitType, reqVO.getUnitType())
                .eqIfPresent(CoopUnitInfoDO::getRegionCode, reqVO.getRegionCode())
                .likeIfPresent(CoopUnitInfoDO::getRegionName, reqVO.getRegionName())
                .likeIfPresent(CoopUnitInfoDO::getLeaderName, reqVO.getLeaderName())
                .eqIfPresent(CoopUnitInfoDO::getLeaderContact, reqVO.getLeaderContact())
                .eqIfPresent(CoopUnitInfoDO::getContactAddress, reqVO.getContactAddress())
                .eqIfPresent(CoopUnitInfoDO::getCoopScope, reqVO.getCoopScope())
                .eqIfPresent(CoopUnitInfoDO::getUnitStatus, reqVO.getUnitStatus())
                .eqIfPresent(CoopUnitInfoDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(CoopUnitInfoDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(CoopUnitInfoDO::getUpdateUser, reqVO.getUpdateUser())
                .orderByDesc(CoopUnitInfoDO::getId));
    }

}