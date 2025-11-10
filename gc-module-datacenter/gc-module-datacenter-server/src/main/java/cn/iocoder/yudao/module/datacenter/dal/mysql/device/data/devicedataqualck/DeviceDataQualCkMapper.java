package cn.iocoder.yudao.module.datacenter.dal.mysql.device.data.devicedataqualck;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicedataqualck.vo.DeviceDataQualCkPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.data.devicedataqualck.DeviceDataQualCkDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备数据质量检查 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface DeviceDataQualCkMapper extends BaseMapperX<DeviceDataQualCkDO> {

    default PageResult<DeviceDataQualCkDO> selectPage(DeviceDataQualCkPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeviceDataQualCkDO>()
                .eqIfPresent(DeviceDataQualCkDO::getQualCkId, reqVO.getQualCkId())
                .eqIfPresent(DeviceDataQualCkDO::getDeviceId, reqVO.getDeviceId())
                .likeIfPresent(DeviceDataQualCkDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(DeviceDataQualCkDO::getDeviceCode, reqVO.getDeviceCode())
                .eqIfPresent(DeviceDataQualCkDO::getCkBatch, reqVO.getCkBatch())
                .betweenIfPresent(DeviceDataQualCkDO::getCkTime, reqVO.getCkTime())
                .eqIfPresent(DeviceDataQualCkDO::getCkItem, reqVO.getCkItem())
                .eqIfPresent(DeviceDataQualCkDO::getCkResult, reqVO.getCkResult())
                .eqIfPresent(DeviceDataQualCkDO::getErrorDesc, reqVO.getErrorDesc())
                .eqIfPresent(DeviceDataQualCkDO::getRectifySugg, reqVO.getRectifySugg())
                .eqIfPresent(DeviceDataQualCkDO::getCkUser, reqVO.getCkUser())
                .eqIfPresent(DeviceDataQualCkDO::getRectifyStatus, reqVO.getRectifyStatus())
                .betweenIfPresent(DeviceDataQualCkDO::getRectifyTime, reqVO.getRectifyTime())
                .eqIfPresent(DeviceDataQualCkDO::getRectifyUser, reqVO.getRectifyUser())
                .eqIfPresent(DeviceDataQualCkDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DeviceDataQualCkDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DeviceDataQualCkDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DeviceDataQualCkDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DeviceDataQualCkDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceDataQualCkDO::getId));
    }

}