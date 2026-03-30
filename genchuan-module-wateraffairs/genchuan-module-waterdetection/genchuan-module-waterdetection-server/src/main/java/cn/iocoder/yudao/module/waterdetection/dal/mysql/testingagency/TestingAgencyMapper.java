package cn.iocoder.yudao.module.waterdetection.dal.mysql.testingagency;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.testingagency.TestingAgencyDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.testingagency.vo.*;

/**
 * 检测机构资质管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface TestingAgencyMapper extends BaseMapperX<TestingAgencyDO> {

    default PageResult<TestingAgencyDO> selectPage(TestingAgencyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TestingAgencyDO>()
                .eqIfPresent(TestingAgencyDO::getAgencyCode, reqVO.getAgencyCode())
                .likeIfPresent(TestingAgencyDO::getAgencyName, reqVO.getAgencyName())
                .eqIfPresent(TestingAgencyDO::getCertificateNo, reqVO.getCertificateNo())
                .eqIfPresent(TestingAgencyDO::getTestingScope, reqVO.getTestingScope())
                .betweenIfPresent(TestingAgencyDO::getValidDate, reqVO.getValidDate())
                .eqIfPresent(TestingAgencyDO::getIssuingAuthority, reqVO.getIssuingAuthority())
                .betweenIfPresent(TestingAgencyDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TestingAgencyDO::getId));
    }

}