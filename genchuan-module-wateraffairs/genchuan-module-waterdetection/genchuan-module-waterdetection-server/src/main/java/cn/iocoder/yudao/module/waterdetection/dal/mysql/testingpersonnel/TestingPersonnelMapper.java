package cn.iocoder.yudao.module.waterdetection.dal.mysql.testingpersonnel;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.testingpersonnel.TestingPersonnelDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.testingpersonnel.vo.*;

/**
 * 检测人员信息管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface TestingPersonnelMapper extends BaseMapperX<TestingPersonnelDO> {

    default PageResult<TestingPersonnelDO> selectPage(TestingPersonnelPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TestingPersonnelDO>()
                .eqIfPresent(TestingPersonnelDO::getStaffNo, reqVO.getStaffNo())
                .likeIfPresent(TestingPersonnelDO::getStaffName, reqVO.getStaffName())
                .eqIfPresent(TestingPersonnelDO::getPosition, reqVO.getPosition())
                .eqIfPresent(TestingPersonnelDO::getCertificateNo, reqVO.getCertificateNo())
                .eqIfPresent(TestingPersonnelDO::getTrainingRecord, reqVO.getTrainingRecord())
                .eqIfPresent(TestingPersonnelDO::getAgencyCode, reqVO.getAgencyCode())
                .betweenIfPresent(TestingPersonnelDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TestingPersonnelDO::getId));
    }

}