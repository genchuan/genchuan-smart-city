package cn.iocoder.yudao.module.datacenter.dal.mysql.device.rule.deviceattrrule;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.deviceattrrule.vo.DeviceAttrRulePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.rule.deviceattrrule.DeviceAttrRuleDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备属性规则配置 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface DeviceAttrRuleMapper extends BaseMapperX<DeviceAttrRuleDO> {

    default PageResult<DeviceAttrRuleDO> selectPage(DeviceAttrRulePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeviceAttrRuleDO>()
                .eqIfPresent(DeviceAttrRuleDO::getAttrRuleId, reqVO.getAttrRuleId())
                .eqIfPresent(DeviceAttrRuleDO::getDeviceCatId, reqVO.getDeviceCatId())
                .likeIfPresent(DeviceAttrRuleDO::getDeviceCatName, reqVO.getDeviceCatName())
                .likeIfPresent(DeviceAttrRuleDO::getAttrName, reqVO.getAttrName())
                .eqIfPresent(DeviceAttrRuleDO::getAttrCode, reqVO.getAttrCode())
                .eqIfPresent(DeviceAttrRuleDO::getDataType, reqVO.getDataType())
                .eqIfPresent(DeviceAttrRuleDO::getFieldLength, reqVO.getFieldLength())
                .eqIfPresent(DeviceAttrRuleDO::getIsRequired, reqVO.getIsRequired())
                .eqIfPresent(DeviceAttrRuleDO::getUnit, reqVO.getUnit())
                .eqIfPresent(DeviceAttrRuleDO::getValueRange, reqVO.getValueRange())
                .eqIfPresent(DeviceAttrRuleDO::getDefaultValue, reqVO.getDefaultValue())
                .eqIfPresent(DeviceAttrRuleDO::getAttrDesc, reqVO.getAttrDesc())
                .eqIfPresent(DeviceAttrRuleDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(DeviceAttrRuleDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(DeviceAttrRuleDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .eqIfPresent(DeviceAttrRuleDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DeviceAttrRuleDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DeviceAttrRuleDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DeviceAttrRuleDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DeviceAttrRuleDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceAttrRuleDO::getId));
    }

}