package cn.iocoder.yudao.module.smartcity.dal.mysql.classificationofguideinformation;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.classificationofguideinformation.ClassificationOfGuideInformationDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.classificationofguideinformation.vo.*;

/**
 * 指南信息分类 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface ClassificationOfGuideInformationMapper extends BaseMapperX<ClassificationOfGuideInformationDO> {

    default PageResult<ClassificationOfGuideInformationDO> selectPage(ClassificationOfGuideInformationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ClassificationOfGuideInformationDO>()
                .eqIfPresent(ClassificationOfGuideInformationDO::getApplications, reqVO.getApplications())
                .eqIfPresent(ClassificationOfGuideInformationDO::getApplicationScenarios, reqVO.getApplicationScenarios())
                .betweenIfPresent(ClassificationOfGuideInformationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ClassificationOfGuideInformationDO::getId));
    }

}