package cn.iocoder.yudao.module.smartcity.dal.mysql.experienceinformationinput;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.experienceinformationinput.ExperienceInformationInputDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.experienceinformationinput.vo.*;

/**
 * 经验信息录入 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface ExperienceInformationInputMapper extends BaseMapperX<ExperienceInformationInputDO> {

    default PageResult<ExperienceInformationInputDO> selectPage(ExperienceInformationInputPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ExperienceInformationInputDO>()
                .eqIfPresent(ExperienceInformationInputDO::getExperienceTheme, reqVO.getExperienceTheme())
                .eqIfPresent(ExperienceInformationInputDO::getIsArea, reqVO.getIsArea())
                .betweenIfPresent(ExperienceInformationInputDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ExperienceInformationInputDO::getId));
    }

}