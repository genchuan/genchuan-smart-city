package cn.iocoder.yudao.module.smartcity.dal.mysql.classificationofpoliciesandregulations;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.classificationofpoliciesandregulations.ClassificationOfPoliciesAndRegulationsDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.classificationofpoliciesandregulations.vo.*;

/**
 * 政策法规分类 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface ClassificationOfPoliciesAndRegulationsMapper extends BaseMapperX<ClassificationOfPoliciesAndRegulationsDO> {

    default PageResult<ClassificationOfPoliciesAndRegulationsDO> selectPage(ClassificationOfPoliciesAndRegulationsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ClassificationOfPoliciesAndRegulationsDO>()
                .eqIfPresent(ClassificationOfPoliciesAndRegulationsDO::getRegulatoryCategory, reqVO.getRegulatoryCategory())
                .eqIfPresent(ClassificationOfPoliciesAndRegulationsDO::getIsArea, reqVO.getIsArea())
                .eqIfPresent(ClassificationOfPoliciesAndRegulationsDO::getTheme, reqVO.getTheme())
                .betweenIfPresent(ClassificationOfPoliciesAndRegulationsDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ClassificationOfPoliciesAndRegulationsDO::getId));
    }

}