package cn.iocoder.yudao.module.data.dal.mysql.matterinstance;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.data.controller.admin.matterinstance.vo.MatterInstancePageReqVO;
import cn.iocoder.yudao.module.data.dal.dataobject.matterinstance.MatterInstanceDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 管理事项实例 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface MatterInstanceMapper extends BaseMapperX<MatterInstanceDO> {

    default PageResult<MatterInstanceDO> selectPage(MatterInstancePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MatterInstanceDO>()
                .eqIfPresent(MatterInstanceDO::getMatterInstanceId, reqVO.getMatterInstanceId())
                .likeIfPresent(MatterInstanceDO::getName, reqVO.getName())
                .eqIfPresent(MatterInstanceDO::getUniqueCode, reqVO.getUniqueCode())
                .eqIfPresent(MatterInstanceDO::getCategoryId, reqVO.getCategoryId())
                .likeIfPresent(MatterInstanceDO::getCategoryName, reqVO.getCategoryName())
                .eqIfPresent(MatterInstanceDO::getParentCategoryId, reqVO.getParentCategoryId())
                .eqIfPresent(MatterInstanceDO::getLocation, reqVO.getLocation())
                .eqIfPresent(MatterInstanceDO::getGridId, reqVO.getGridId())
                .likeIfPresent(MatterInstanceDO::getGridName, reqVO.getGridName())
                .eqIfPresent(MatterInstanceDO::getDescription, reqVO.getDescription())
                .eqIfPresent(MatterInstanceDO::getStatusId, reqVO.getStatusId())
                .likeIfPresent(MatterInstanceDO::getStatus, reqVO.getStatus())
                .eqIfPresent(MatterInstanceDO::getDeptId, reqVO.getDeptId())
                .likeIfPresent(MatterInstanceDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(MatterInstanceDO::getAttachmentInfo, reqVO.getAttachmentInfo())
                .eqIfPresent(MatterInstanceDO::getPartIds, reqVO.getPartIds())
                .eqIfPresent(MatterInstanceDO::getPartCount, reqVO.getPartCount())
                .eqIfPresent(MatterInstanceDO::getTimeoutFlag, reqVO.getTimeoutFlag())
                .eqIfPresent(MatterInstanceDO::getTimeoutDuration, reqVO.getTimeoutDuration())
                .eqIfPresent(MatterInstanceDO::getDealOpinion, reqVO.getDealOpinion())
                .eqIfPresent(MatterInstanceDO::getDealBy, reqVO.getDealBy())
                .likeIfPresent(MatterInstanceDO::getHandler, reqVO.getHandler())
                .betweenIfPresent(MatterInstanceDO::getDealTime, reqVO.getDealTime())
                .eqIfPresent(MatterInstanceDO::getRemark, reqVO.getRemark())
                .eqIfPresent(MatterInstanceDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(MatterInstanceDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(MatterInstanceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MatterInstanceDO::getId));
    }

    /**
     * 根据分类ID列表进行分页查询（用于树形查询）
     *
     * @param reqVO 分页查询条件
     * @param categoryIds 分类ID列表
     * @return 分页结果
     */
    default PageResult<MatterInstanceDO> selectPageByCategoryIds(MatterInstancePageReqVO reqVO, List<String> categoryIds) {
        LambdaQueryWrapperX<MatterInstanceDO> queryWrapper = new LambdaQueryWrapperX<MatterInstanceDO>()
                .eqIfPresent(MatterInstanceDO::getMatterInstanceId, reqVO.getMatterInstanceId())
                .likeIfPresent(MatterInstanceDO::getName, reqVO.getName())
                .eqIfPresent(MatterInstanceDO::getUniqueCode, reqVO.getUniqueCode())
                // 关键：按分类ID列表查询
                .in(MatterInstanceDO::getCategoryId, categoryIds)
                .likeIfPresent(MatterInstanceDO::getCategoryName, reqVO.getCategoryName())
                .eqIfPresent(MatterInstanceDO::getParentCategoryId, reqVO.getParentCategoryId())
                .likeIfPresent(MatterInstanceDO::getLocation, reqVO.getLocation())
                .eqIfPresent(MatterInstanceDO::getGridId, reqVO.getGridId())
                .likeIfPresent(MatterInstanceDO::getGridName, reqVO.getGridName())
                .likeIfPresent(MatterInstanceDO::getDescription, reqVO.getDescription())
                .eqIfPresent(MatterInstanceDO::getStatusId, reqVO.getStatusId())
                .likeIfPresent(MatterInstanceDO::getStatus, reqVO.getStatus())
                .eqIfPresent(MatterInstanceDO::getDeptId, reqVO.getDeptId())
                .likeIfPresent(MatterInstanceDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(MatterInstanceDO::getAttachmentInfo, reqVO.getAttachmentInfo())
                .eqIfPresent(MatterInstanceDO::getPartIds, reqVO.getPartIds())
                .eqIfPresent(MatterInstanceDO::getPartCount, reqVO.getPartCount())
                .eqIfPresent(MatterInstanceDO::getTimeoutFlag, reqVO.getTimeoutFlag())
                .eqIfPresent(MatterInstanceDO::getTimeoutDuration, reqVO.getTimeoutDuration())
                .betweenIfPresent(MatterInstanceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MatterInstanceDO::getId);

        return selectPage(reqVO, queryWrapper);
    }

}