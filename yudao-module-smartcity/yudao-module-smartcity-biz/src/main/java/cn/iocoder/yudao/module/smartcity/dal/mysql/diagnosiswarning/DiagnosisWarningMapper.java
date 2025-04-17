package cn.iocoder.yudao.module.smartcity.dal.mysql.diagnosiswarning;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.diagnosiswarning.DiagnosisWarningDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.diagnosiswarning.vo.*;

/**
 * 故障诊断和预警 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface DiagnosisWarningMapper extends BaseMapperX<DiagnosisWarningDO> {

    default PageResult<DiagnosisWarningDO> selectPage(DiagnosisWarningPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DiagnosisWarningDO>()
                .eqIfPresent(DiagnosisWarningDO::getNameFaultyEquipment, reqVO.getNameFaultyEquipment())
                .eqIfPresent(DiagnosisWarningDO::getFaultEquipmentNumber, reqVO.getFaultEquipmentNumber())
                .betweenIfPresent(DiagnosisWarningDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DiagnosisWarningDO::getId));
    }

}