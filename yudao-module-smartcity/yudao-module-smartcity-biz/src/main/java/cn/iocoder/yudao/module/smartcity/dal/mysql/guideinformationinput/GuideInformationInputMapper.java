package cn.iocoder.yudao.module.smartcity.dal.mysql.guideinformationinput;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.guideinformationinput.GuideInformationInputDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.guideinformationinput.vo.*;

/**
 * 指南信息录入 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface GuideInformationInputMapper extends BaseMapperX<GuideInformationInputDO> {

    default PageResult<GuideInformationInputDO> selectPage(GuideInformationInputPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GuideInformationInputDO>()
                .likeIfPresent(GuideInformationInputDO::getGuideName, reqVO.getGuideName())
                .eqIfPresent(GuideInformationInputDO::getPublishingUnit, reqVO.getPublishingUnit())
                .betweenIfPresent(GuideInformationInputDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GuideInformationInputDO::getId));
    }

}