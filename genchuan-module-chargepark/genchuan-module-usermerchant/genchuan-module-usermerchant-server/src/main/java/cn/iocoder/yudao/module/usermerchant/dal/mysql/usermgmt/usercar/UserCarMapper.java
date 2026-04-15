package cn.iocoder.yudao.module.usermerchant.dal.mysql.usermgmt.usercar;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.usermgmt.usercar.UserCarDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.usercar.vo.*;

/**
 * 用户车辆 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface UserCarMapper extends BaseMapperX<UserCarDO> {

    default PageResult<UserCarDO> selectPage(UserCarPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UserCarDO>()
                .eqIfPresent(UserCarDO::getUserId, reqVO.getUserId())
                .eqIfPresent(UserCarDO::getPlateNo, reqVO.getPlateNo())
                .eqIfPresent(UserCarDO::getPlateColor, reqVO.getPlateColor())
                .eqIfPresent(UserCarDO::getCarType, reqVO.getCarType())
                .betweenIfPresent(UserCarDO::getBindTime, reqVO.getBindTime())
                .eqIfPresent(UserCarDO::getStatus, reqVO.getStatus())
                .eqIfPresent(UserCarDO::getAuditorId, reqVO.getAuditorId())
                .betweenIfPresent(UserCarDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(UserCarDO::getRemark, reqVO.getRemark())
                .orderByDesc(UserCarDO::getId));
    }

}