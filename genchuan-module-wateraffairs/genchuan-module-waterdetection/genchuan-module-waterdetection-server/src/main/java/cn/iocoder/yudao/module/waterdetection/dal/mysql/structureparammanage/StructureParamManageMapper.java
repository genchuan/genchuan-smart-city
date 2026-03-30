package cn.iocoder.yudao.module.waterdetection.dal.mysql.structureparammanage;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.structureparammanage.StructureParamManageDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.structureparammanage.vo.*;

/**
 * 构建筑物参数管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface StructureParamManageMapper extends BaseMapperX<StructureParamManageDO> {

    default PageResult<StructureParamManageDO> selectPage(StructureParamManagePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<StructureParamManageDO>()
                .likeIfPresent(StructureParamManageDO::getStructureName, reqVO.getStructureName())
                .eqIfPresent(StructureParamManageDO::getStructureType, reqVO.getStructureType())
                .eqIfPresent(StructureParamManageDO::getLength, reqVO.getLength())
                .eqIfPresent(StructureParamManageDO::getWidth, reqVO.getWidth())
                .eqIfPresent(StructureParamManageDO::getDepth, reqVO.getDepth())
                .eqIfPresent(StructureParamManageDO::getEffectiveVolume, reqVO.getEffectiveVolume())
                .betweenIfPresent(StructureParamManageDO::getConstructionTime, reqVO.getConstructionTime())
                .betweenIfPresent(StructureParamManageDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(StructureParamManageDO::getId));
    }

}