package cn.iocoder.yudao.module.datacenter.dal.mysql.componentcategory;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.componentcategory.ComponentCategoryDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.datacenter.controller.admin.componentcategory.vo.*;

/**
 * 监测部件分类 Mapper
 *
 * @author lll
 */
@Mapper
public interface ComponentCategoryMapper extends BaseMapperX<ComponentCategoryDO> {

    default PageResult<ComponentCategoryDO> selectPage(ComponentCategoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ComponentCategoryDO>()
                .eqIfPresent(ComponentCategoryDO::getCategoryId, reqVO.getCategoryId())
                .eqIfPresent(ComponentCategoryDO::getParentId, reqVO.getParentId())
                .eqIfPresent(ComponentCategoryDO::getLevel, reqVO.getLevel())
                .eqIfPresent(ComponentCategoryDO::getCode, reqVO.getCode())
                .likeIfPresent(ComponentCategoryDO::getName, reqVO.getName())
                .eqIfPresent(ComponentCategoryDO::getDescription, reqVO.getDescription())
                .eqIfPresent(ComponentCategoryDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ComponentCategoryDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(ComponentCategoryDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ComponentCategoryDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(ComponentCategoryDO::getExt1, reqVO.getExt1())
                .eqIfPresent(ComponentCategoryDO::getExt2, reqVO.getExt2())
                .eqIfPresent(ComponentCategoryDO::getExt3, reqVO.getExt3())
                .eqIfPresent(ComponentCategoryDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(ComponentCategoryDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(ComponentCategoryDO::getId));
    }

}