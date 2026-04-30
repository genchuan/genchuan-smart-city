package cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.stationuser;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationuser.vo.StationUserPageReqVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationuser.StationUserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 站点用户 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface StationUserMapper extends BaseMapperX<StationUserDO> {

    default PageResult<StationUserDO> selectPage(StationUserPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<StationUserDO>()
                .likeIfPresent(StationUserDO::getUsername, reqVO.getUsername())
                .eqIfPresent(StationUserDO::getPassword, reqVO.getPassword())
                .likeIfPresent(StationUserDO::getNickname, reqVO.getNickname())
                .eqIfPresent(StationUserDO::getSex, reqVO.getSex())
                .eqIfPresent(StationUserDO::getAvatar, reqVO.getAvatar())
                .eqIfPresent(StationUserDO::getPhone, reqVO.getPhone())
                .eqIfPresent(StationUserDO::getEmail, reqVO.getEmail())
                .eqIfPresent(StationUserDO::getStatus, reqVO.getStatus())
                .eqIfPresent(StationUserDO::getRemark, reqVO.getRemark())
                .eqIfPresent(StationUserDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(StationUserDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(StationUserDO::getCreator, reqVO.getCreator())
                .betweenIfPresent(StationUserDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(StationUserDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(StationUserDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(StationUserDO::getId));
    }

}
