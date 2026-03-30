package cn.iocoder.yudao.module.waterdetection.dal.mysql.testingcapability;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.testingcapability.TestingCapabilityDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.testingcapability.vo.*;

/**
 * 检测能力及设备管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface TestingCapabilityMapper extends BaseMapperX<TestingCapabilityDO> {

    default PageResult<TestingCapabilityDO> selectPage(TestingCapabilityPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TestingCapabilityDO>()
                .eqIfPresent(TestingCapabilityDO::getAgencyCode, reqVO.getAgencyCode())
                .eqIfPresent(TestingCapabilityDO::getTestableIndicators, reqVO.getTestableIndicators())
                .eqIfPresent(TestingCapabilityDO::getEquipmentModel, reqVO.getEquipmentModel())
                .eqIfPresent(TestingCapabilityDO::getEquipmentNo, reqVO.getEquipmentNo())
                .eqIfPresent(TestingCapabilityDO::getCalibrationRecord, reqVO.getCalibrationRecord())
                .eqIfPresent(TestingCapabilityDO::getEquipmentStatus, reqVO.getEquipmentStatus())
                .betweenIfPresent(TestingCapabilityDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TestingCapabilityDO::getId));
    }

}