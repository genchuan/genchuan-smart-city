package cn.iocoder.yudao.module.smartcity.dal.mysql.parkinglotinformation;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.parkinglotinformation.ParkingLotInformationDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.parkinglotinformation.vo.*;

/**
 * 停车场信息管理 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface ParkingLotInformationMapper extends BaseMapperX<ParkingLotInformationDO> {

    default PageResult<ParkingLotInformationDO> selectPage(ParkingLotInformationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkingLotInformationDO>()
                .likeIfPresent(ParkingLotInformationDO::getName, reqVO.getName())
                .betweenIfPresent(ParkingLotInformationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ParkingLotInformationDO::getId));
    }

}