package cn.iocoder.yudao.module.smartcity.dal.mysql.componentinformation;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.componentinformation.ComponentInformationDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.componentinformation.vo.*;

/**
 * 部件信息 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface ComponentInformationMapper extends BaseMapperX<ComponentInformationDO> {

    default PageResult<ComponentInformationDO> selectPage(ComponentInformationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ComponentInformationDO>()
                .eqIfPresent(ComponentInformationDO::getPartNumber, reqVO.getPartNumber())
                .likeIfPresent(ComponentInformationDO::getComponentName, reqVO.getComponentName())
                .eqIfPresent(ComponentInformationDO::getPartType, reqVO.getPartType())
                .eqIfPresent(ComponentInformationDO::getServiceLife, reqVO.getServiceLife())
                .eqIfPresent(ComponentInformationDO::getRelatedEventRecords, reqVO.getRelatedEventRecords())
                .betweenIfPresent(ComponentInformationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ComponentInformationDO::getId));
    }

}