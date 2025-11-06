package cn.iocoder.yudao.module.datacenter.dal.mysql.device.rule.devicecatrule;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.devicecatrule.vo.DeviceCatRulePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.rule.devicecatrule.DeviceCatRuleDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备分类规则配置 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface DeviceCatRuleMapper extends BaseMapperX<DeviceCatRuleDO> {

    default PageResult<DeviceCatRuleDO> selectPage(DeviceCatRulePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeviceCatRuleDO>()
                .eqIfPresent(DeviceCatRuleDO::getCatRuleId, reqVO.getCatRuleId())
                .eqIfPresent(DeviceCatRuleDO::getParentCatRuleId, reqVO.getParentCatRuleId())
                .eqIfPresent(DeviceCatRuleDO::getCatLevel, reqVO.getCatLevel())
                .eqIfPresent(DeviceCatRuleDO::getCatCode, reqVO.getCatCode())
                .likeIfPresent(DeviceCatRuleDO::getCatName, reqVO.getCatName())
                .eqIfPresent(DeviceCatRuleDO::getCatDesc, reqVO.getCatDesc())
                .eqIfPresent(DeviceCatRuleDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(DeviceCatRuleDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(DeviceCatRuleDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .eqIfPresent(DeviceCatRuleDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DeviceCatRuleDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DeviceCatRuleDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DeviceCatRuleDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DeviceCatRuleDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceCatRuleDO::getId));
    }

}