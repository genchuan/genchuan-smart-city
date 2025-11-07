package cn.iocoder.yudao.module.datacenter.dal.mysql.resschedmanage.emerdeviceinfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerdeviceinfo.vo.EmerDeviceInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emerdeviceinfo.EmerDeviceInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 救援设备信息 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface EmerDeviceInfoMapper extends BaseMapperX<EmerDeviceInfoDO> {

    default PageResult<EmerDeviceInfoDO> selectPage(EmerDeviceInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EmerDeviceInfoDO>()
                .eqIfPresent(EmerDeviceInfoDO::getDeviceId, reqVO.getDeviceId())
                .eqIfPresent(EmerDeviceInfoDO::getDeviceCode, reqVO.getDeviceCode())
                .likeIfPresent(EmerDeviceInfoDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(EmerDeviceInfoDO::getCatId, reqVO.getCatId())
                .likeIfPresent(EmerDeviceInfoDO::getCatName, reqVO.getCatName())
                .eqIfPresent(EmerDeviceInfoDO::getApplyRegionCode, reqVO.getApplyRegionCode())
                .likeIfPresent(EmerDeviceInfoDO::getApplyRegionName, reqVO.getApplyRegionName())
                .eqIfPresent(EmerDeviceInfoDO::getDeviceModel, reqVO.getDeviceModel())
                .eqIfPresent(EmerDeviceInfoDO::getDeviceCount, reqVO.getDeviceCount())
                .eqIfPresent(EmerDeviceInfoDO::getAvailableDeviceCount, reqVO.getAvailableDeviceCount())
                .eqIfPresent(EmerDeviceInfoDO::getDeviceStatus, reqVO.getDeviceStatus())
                .eqIfPresent(EmerDeviceInfoDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(EmerDeviceInfoDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(EmerDeviceInfoDO::getUpdateUser, reqVO.getUpdateUser())
                .orderByDesc(EmerDeviceInfoDO::getId));
    }

}