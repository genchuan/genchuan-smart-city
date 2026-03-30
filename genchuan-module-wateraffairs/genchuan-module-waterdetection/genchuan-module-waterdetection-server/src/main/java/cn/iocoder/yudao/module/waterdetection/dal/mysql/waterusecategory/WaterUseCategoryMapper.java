package cn.iocoder.yudao.module.waterdetection.dal.mysql.waterusecategory;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.waterusecategory.WaterUseCategoryDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.waterusecategory.vo.*;

/**
 * 用水性质分类管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface WaterUseCategoryMapper extends BaseMapperX<WaterUseCategoryDO> {

    default PageResult<WaterUseCategoryDO> selectPage(WaterUseCategoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WaterUseCategoryDO>()
                .eqIfPresent(WaterUseCategoryDO::getUserCode, reqVO.getUserCode())
                .eqIfPresent(WaterUseCategoryDO::getWaterUseType, reqVO.getWaterUseType())
                .eqIfPresent(WaterUseCategoryDO::getWaterQuota, reqVO.getWaterQuota())
                .betweenIfPresent(WaterUseCategoryDO::getCategoryDate, reqVO.getCategoryDate())
                .betweenIfPresent(WaterUseCategoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(WaterUseCategoryDO::getId));
    }

}