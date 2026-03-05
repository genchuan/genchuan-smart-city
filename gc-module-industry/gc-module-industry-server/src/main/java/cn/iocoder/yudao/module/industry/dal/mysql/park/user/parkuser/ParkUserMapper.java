package cn.iocoder.yudao.module.industry.dal.mysql.park.user.parkuser;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;

import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkuser.vo.ParkUserPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkuser.ParkUserDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 停车系统用户 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkUserMapper extends BaseMapperX<ParkUserDO> {

    default PageResult<ParkUserDO> selectPage(ParkUserPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkUserDO>()
                .eqIfPresent(ParkUserDO::getUserAccount, reqVO.getUserAccount())
                .eqIfPresent(ParkUserDO::getUserPhone, reqVO.getUserPhone())
                .eqIfPresent(ParkUserDO::getUserType, reqVO.getUserType())
                .eqIfPresent(ParkUserDO::getIdCard, reqVO.getIdCard())
                .likeIfPresent(ParkUserDO::getEnterpriseName, reqVO.getEnterpriseName())
                .eqIfPresent(ParkUserDO::getEnterpriseCode, reqVO.getEnterpriseCode())
                .eqIfPresent(ParkUserDO::getGovDepartment, reqVO.getGovDepartment())
                .eqIfPresent(ParkUserDO::getCertStatus, reqVO.getCertStatus())
                .eqIfPresent(ParkUserDO::getWalletBalance, reqVO.getWalletBalance())
                .eqIfPresent(ParkUserDO::getFreezeBalance, reqVO.getFreezeBalance())
                .eqIfPresent(ParkUserDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ParkUserDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkUserDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkUserDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkUserDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkUserDO::getExtCommon4, reqVO.getExtCommon4())
                .eqIfPresent(ParkUserDO::getRemark, reqVO.getRemark())
                .orderByDesc(ParkUserDO::getId));
    }

}
