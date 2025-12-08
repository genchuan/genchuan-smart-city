package cn.iocoder.yudao.module.datacenter.dal.mysql.appscenecategory;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.appscenecategory.AppSceneCategoryDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.datacenter.controller.admin.appscenecategory.vo.*;

/**
 * 应用场景分类配置 Mapper
 *
 * @author zcq
 */
@Mapper
public interface AppSceneCategoryMapper extends BaseMapperX<AppSceneCategoryDO> {

    default PageResult<AppSceneCategoryDO> selectPage(AppSceneCategoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AppSceneCategoryDO>()
                .eqIfPresent(AppSceneCategoryDO::getSceneCatCode, reqVO.getSceneCatCode())
                .likeIfPresent(AppSceneCategoryDO::getSceneCatName, reqVO.getSceneCatName())
                .eqIfPresent(AppSceneCategoryDO::getCatLevel, reqVO.getCatLevel())
                .eqIfPresent(AppSceneCategoryDO::getParentCatId, reqVO.getParentCatId())
                .likeIfPresent(AppSceneCategoryDO::getParentCatName, reqVO.getParentCatName())
                .eqIfPresent(AppSceneCategoryDO::getSceneCatDesc, reqVO.getSceneCatDesc())
                .eqIfPresent(AppSceneCategoryDO::getIsExt, reqVO.getIsExt())
                .eqIfPresent(AppSceneCategoryDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(AppSceneCategoryDO::getRelCompCatId, reqVO.getRelCompCatId())
                .likeIfPresent(AppSceneCategoryDO::getRelCompCatName, reqVO.getRelCompCatName())
                .eqIfPresent(AppSceneCategoryDO::getRelEvtCatId, reqVO.getRelEvtCatId())
                .likeIfPresent(AppSceneCategoryDO::getRelEvtCatName, reqVO.getRelEvtCatName())
                .eqIfPresent(AppSceneCategoryDO::getCreateUserBiz, reqVO.getCreateUserBiz())
                .eqIfPresent(AppSceneCategoryDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(AppSceneCategoryDO::getUpdateUserBiz, reqVO.getUpdateUserBiz())
                .eqIfPresent(AppSceneCategoryDO::getUpdateTimeBiz, reqVO.getUpdateTimeBiz())
                .eqIfPresent(AppSceneCategoryDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(AppSceneCategoryDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(AppSceneCategoryDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AppSceneCategoryDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(AppSceneCategoryDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(AppSceneCategoryDO::getMenuIds, reqVO.getMenuIds())
                .orderByDesc(AppSceneCategoryDO::getId));
    }

    /**
     * 查询所有应用场景分类配置
     *
     * @return 所有应用场景分类配置列表
     */
    default List<AppSceneCategoryDO> selectList() {
        return selectList(new LambdaQueryWrapperX<AppSceneCategoryDO>()
                .orderByAsc(AppSceneCategoryDO::getCatLevel)
                .orderByAsc(AppSceneCategoryDO::getId));
    }

}