package cn.iocoder.yudao.module.waterdetection.dal.mysql.testresult;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.testresult.TestResultDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.testresult.vo.*;

/**
 * 检测结果录入 Mapper
 *
 * @author zcq
 */
@Mapper
public interface TestResultMapper extends BaseMapperX<TestResultDO> {

    default PageResult<TestResultDO> selectPage(TestResultPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TestResultDO>()
                .eqIfPresent(TestResultDO::getSampleCode, reqVO.getSampleCode())
                .eqIfPresent(TestResultDO::getTestIndicator, reqVO.getTestIndicator())
                .eqIfPresent(TestResultDO::getTestValue, reqVO.getTestValue())
                .eqIfPresent(TestResultDO::getUnit, reqVO.getUnit())
                .eqIfPresent(TestResultDO::getTestMethod, reqVO.getTestMethod())
                .eqIfPresent(TestResultDO::getTestOperator, reqVO.getTestOperator())
                .betweenIfPresent(TestResultDO::getTestTime, reqVO.getTestTime())
                .eqIfPresent(TestResultDO::getEquipmentCode, reqVO.getEquipmentCode())
                .betweenIfPresent(TestResultDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TestResultDO::getId));
    }

}