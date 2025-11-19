package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.files.inspectrecarch;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectrecarch.vo.InspectRecArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.files.inspectrecarch.InspectRecArchDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 巡查巡检记录档案 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectRecArchMapper extends BaseMapperX<InspectRecArchDO> {

    default PageResult<InspectRecArchDO> selectPage(InspectRecArchPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectRecArchDO>()
                .eqIfPresent(InspectRecArchDO::getArchId, reqVO.getArchId())
                .eqIfPresent(InspectRecArchDO::getArchCode, reqVO.getArchCode())
                .likeIfPresent(InspectRecArchDO::getArchName, reqVO.getArchName())
                .eqIfPresent(InspectRecArchDO::getArchType, reqVO.getArchType())
                .eqIfPresent(InspectRecArchDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(InspectRecArchDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(InspectRecArchDO::getArchCycle, reqVO.getArchCycle())
                .eqIfPresent(InspectRecArchDO::getIncludeRecCount, reqVO.getIncludeRecCount())
                .eqIfPresent(InspectRecArchDO::getArchAttachUrls, reqVO.getArchAttachUrls())
                .eqIfPresent(InspectRecArchDO::getArchUser, reqVO.getArchUser())
                .betweenIfPresent(InspectRecArchDO::getArchTime, reqVO.getArchTime())
                .eqIfPresent(InspectRecArchDO::getArchStatus, reqVO.getArchStatus())
                .betweenIfPresent(InspectRecArchDO::getDestroyTime, reqVO.getDestroyTime())
                .eqIfPresent(InspectRecArchDO::getArchDesc, reqVO.getArchDesc())
                .eqIfPresent(InspectRecArchDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectRecArchDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectRecArchDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectRecArchDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectRecArchDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectRecArchDO::getId));
    }

}