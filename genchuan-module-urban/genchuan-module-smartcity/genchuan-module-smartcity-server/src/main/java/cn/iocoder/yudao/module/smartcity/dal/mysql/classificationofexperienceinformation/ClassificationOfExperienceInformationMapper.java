package cn.iocoder.yudao.module.smartcity.dal.mysql.classificationofexperienceinformation;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.classificationofexperienceinformation.ClassificationOfExperienceInformationDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.classificationofexperienceinformation.vo.*;

/**
 * 经验信息分类 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface ClassificationOfExperienceInformationMapper extends BaseMapperX<ClassificationOfExperienceInformationDO> {

    default PageResult<ClassificationOfExperienceInformationDO> selectPage(ClassificationOfExperienceInformationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ClassificationOfExperienceInformationDO>()
                .eqIfPresent(ClassificationOfExperienceInformationDO::getSector, reqVO.getSector())
                .eqIfPresent(ClassificationOfExperienceInformationDO::getApplicationScenarios, reqVO.getApplicationScenarios())
                .betweenIfPresent(ClassificationOfExperienceInformationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ClassificationOfExperienceInformationDO::getId));
    }

}