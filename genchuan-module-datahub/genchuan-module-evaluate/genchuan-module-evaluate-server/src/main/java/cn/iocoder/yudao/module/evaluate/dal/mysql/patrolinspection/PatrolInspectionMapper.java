package cn.iocoder.yudao.module.evaluate.dal.mysql.patrolinspection;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.patrolinspection.vo.PatrolInspectionPageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.patrolinspection.PatrolInspectionDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 巡查巡检 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface PatrolInspectionMapper extends BaseMapperX<PatrolInspectionDO> {

    default PageResult<PatrolInspectionDO> selectPage(PatrolInspectionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PatrolInspectionDO>()
                .eqIfPresent(PatrolInspectionDO::getUserId, reqVO.getUserId())
                .eqIfPresent(PatrolInspectionDO::getSystemId, reqVO.getSystemId())
                .eqIfPresent(PatrolInspectionDO::getObjectId, reqVO.getObjectId())
                .eqIfPresent(PatrolInspectionDO::getItemId, reqVO.getItemId())
                .eqIfPresent(PatrolInspectionDO::getCategoryId, reqVO.getCategoryId())
                .eqIfPresent(PatrolInspectionDO::getDetails, reqVO.getDetails())
                .eqIfPresent(PatrolInspectionDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(PatrolInspectionDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(PatrolInspectionDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(PatrolInspectionDO::getExtCommon4, reqVO.getExtCommon4())
                .eqIfPresent(PatrolInspectionDO::getCreator, reqVO.getCreator())
                .eqIfPresent(PatrolInspectionDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(PatrolInspectionDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(PatrolInspectionDO::getUpdateTime, reqVO.getUpdateTime())
                .eqIfPresent(PatrolInspectionDO::getStatus, reqVO.getStatus())
                .eqIfPresent(PatrolInspectionDO::getImage, reqVO.getImage())
                .eqIfPresent(PatrolInspectionDO::getAddressCoding, reqVO.getAddressCoding())
                .orderByDesc(PatrolInspectionDO::getId));
    }

    /**
     * 查询去重后的 item_id 和 object_id 组合列表（用于分页）
     */
    List<PatrolInspectionDO> selectPatrolGroupList(@Param("itemId") Long itemId,
                                                  @Param("objectId") Long objectId,
                                                  @Param("addressCoding") String addressCoding,
                                                  @Param("pageSize") Integer pageSize,
                                                  @Param("offset") Long offset);

    /**
     * 统计去重后的 item_id 和 object_id 组合总数
     */
    Long selectPatrolGroupCount(@Param("itemId") Long itemId,
                                @Param("objectId") Long objectId,
                                @Param("addressCoding") String addressCoding);

}