package cn.iocoder.yudao.module.datacenter.dal.mysql.resschedmanage.emervehicleinfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emervehicleinfo.vo.EmerVehicleInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emervehicleinfo.EmerVehicleInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 救援车辆信息 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface EmerVehicleInfoMapper extends BaseMapperX<EmerVehicleInfoDO> {

    default PageResult<EmerVehicleInfoDO> selectPage(EmerVehicleInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EmerVehicleInfoDO>()
                .eqIfPresent(EmerVehicleInfoDO::getVehicleId, reqVO.getVehicleId())
                .eqIfPresent(EmerVehicleInfoDO::getVehicleCode, reqVO.getVehicleCode())
                .likeIfPresent(EmerVehicleInfoDO::getVehicleName, reqVO.getVehicleName())
                .eqIfPresent(EmerVehicleInfoDO::getCatId, reqVO.getCatId())
                .likeIfPresent(EmerVehicleInfoDO::getCatName, reqVO.getCatName())
                .eqIfPresent(EmerVehicleInfoDO::getApplyRegionCode, reqVO.getApplyRegionCode())
                .likeIfPresent(EmerVehicleInfoDO::getApplyRegionName, reqVO.getApplyRegionName())
                .eqIfPresent(EmerVehicleInfoDO::getVehicleModel, reqVO.getVehicleModel())
                .eqIfPresent(EmerVehicleInfoDO::getVehicleCount, reqVO.getVehicleCount())
                .eqIfPresent(EmerVehicleInfoDO::getAvailableVehicleCount, reqVO.getAvailableVehicleCount())
                .eqIfPresent(EmerVehicleInfoDO::getVehicleStatus, reqVO.getVehicleStatus())
                .eqIfPresent(EmerVehicleInfoDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(EmerVehicleInfoDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(EmerVehicleInfoDO::getUpdateUser, reqVO.getUpdateUser())
                .orderByDesc(EmerVehicleInfoDO::getId));
    }

}