package cn.iocoder.yudao.module.datacenter.dal.mysql.device.rule.devicerelrule;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.devicerelrule.vo.DeviceRelRulePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.rule.devicerelrule.DeviceRelRuleDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备关联规则配置 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface DeviceRelRuleMapper extends BaseMapperX<DeviceRelRuleDO> {

    default PageResult<DeviceRelRuleDO> selectPage(DeviceRelRulePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeviceRelRuleDO>()
                .eqIfPresent(DeviceRelRuleDO::getRelRuleId, reqVO.getRelRuleId())
                .eqIfPresent(DeviceRelRuleDO::getDeviceCatId, reqVO.getDeviceCatId())
                .likeIfPresent(DeviceRelRuleDO::getDeviceCatName, reqVO.getDeviceCatName())
                .eqIfPresent(DeviceRelRuleDO::getRelObjType, reqVO.getRelObjType())
                .eqIfPresent(DeviceRelRuleDO::getRelObjId, reqVO.getRelObjId())
                .likeIfPresent(DeviceRelRuleDO::getRelObjName, reqVO.getRelObjName())
                .eqIfPresent(DeviceRelRuleDO::getIsRequired, reqVO.getIsRequired())
                .eqIfPresent(DeviceRelRuleDO::getRelCkRule, reqVO.getRelCkRule())
                .eqIfPresent(DeviceRelRuleDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(DeviceRelRuleDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(DeviceRelRuleDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .eqIfPresent(DeviceRelRuleDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DeviceRelRuleDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DeviceRelRuleDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DeviceRelRuleDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DeviceRelRuleDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceRelRuleDO::getId));
    }

}