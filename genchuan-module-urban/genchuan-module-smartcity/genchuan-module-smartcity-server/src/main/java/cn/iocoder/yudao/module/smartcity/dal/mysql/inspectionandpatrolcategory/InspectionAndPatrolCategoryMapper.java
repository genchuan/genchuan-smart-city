package cn.iocoder.yudao.module.smartcity.dal.mysql.inspectionandpatrolcategory;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectionandpatrolcategory.InspectionAndPatrolCategoryDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.inspectionandpatrolcategory.vo.*;

/**
 * 巡查巡检类 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface InspectionAndPatrolCategoryMapper extends BaseMapperX<InspectionAndPatrolCategoryDO> {

    default PageResult<InspectionAndPatrolCategoryDO> selectPage(InspectionAndPatrolCategoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectionAndPatrolCategoryDO>()
                .likeIfPresent(InspectionAndPatrolCategoryDO::getNumber, reqVO.getNumber())
                .betweenIfPresent(InspectionAndPatrolCategoryDO::getInspectionAndPatrolTime, reqVO.getInspectionAndPatrolTime())
                .likeIfPresent(InspectionAndPatrolCategoryDO::getPatrolPersonnel, reqVO.getPatrolPersonnel())
                .eqIfPresent(InspectionAndPatrolCategoryDO::getPatrolInspectionArea, reqVO.getPatrolInspectionArea())
                .betweenIfPresent(InspectionAndPatrolCategoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectionAndPatrolCategoryDO::getId));
    }

}