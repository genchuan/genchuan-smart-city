package cn.iocoder.yudao.module.data.dal.mysql.matterinstance;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.data.dal.dataobject.matterinstance.matterInstanceDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.data.controller.admin.matterinstance.vo.*;

/**
 * 管理事项实例 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface matterInstanceMapper extends BaseMapperX<matterInstanceDO> {

    default PageResult<matterInstanceDO> selectPage(matterInstancePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<matterInstanceDO>()
                .eqIfPresent(matterInstanceDO::getMatterInstanceId, reqVO.getMatterInstanceId())
                .likeIfPresent(matterInstanceDO::getName, reqVO.getName())
                .eqIfPresent(matterInstanceDO::getUniqueCode, reqVO.getUniqueCode())
                .eqIfPresent(matterInstanceDO::getCategoryId, reqVO.getCategoryId())
                .likeIfPresent(matterInstanceDO::getCategoryName, reqVO.getCategoryName())
                .eqIfPresent(matterInstanceDO::getParentCategoryId, reqVO.getParentCategoryId())
                .eqIfPresent(matterInstanceDO::getLocation, reqVO.getLocation())
                .eqIfPresent(matterInstanceDO::getGridId, reqVO.getGridId())
                .likeIfPresent(matterInstanceDO::getGridName, reqVO.getGridName())
                .eqIfPresent(matterInstanceDO::getDescription, reqVO.getDescription())
                .eqIfPresent(matterInstanceDO::getStatusId, reqVO.getStatusId())
                .likeIfPresent(matterInstanceDO::getStatusName, reqVO.getStatusName())
                .eqIfPresent(matterInstanceDO::getDeptId, reqVO.getDeptId())
                .likeIfPresent(matterInstanceDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(matterInstanceDO::getAttachmentInfo, reqVO.getAttachmentInfo())
                .eqIfPresent(matterInstanceDO::getPartIds, reqVO.getPartIds())
                .eqIfPresent(matterInstanceDO::getPartCount, reqVO.getPartCount())
                .eqIfPresent(matterInstanceDO::getTimeoutFlag, reqVO.getTimeoutFlag())
                .eqIfPresent(matterInstanceDO::getTimeoutDuration, reqVO.getTimeoutDuration())
                .eqIfPresent(matterInstanceDO::getDealOpinion, reqVO.getDealOpinion())
                .eqIfPresent(matterInstanceDO::getDealBy, reqVO.getDealBy())
                .likeIfPresent(matterInstanceDO::getHandlerName, reqVO.getHandlerName())
                .betweenIfPresent(matterInstanceDO::getDealTime, reqVO.getDealTime())
                .eqIfPresent(matterInstanceDO::getRemark, reqVO.getRemark())
                .eqIfPresent(matterInstanceDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(matterInstanceDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(matterInstanceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(matterInstanceDO::getId));
    }

    /**
     * 根据分类ID列表进行分页查询（用于树形查询）
     *
     * @param reqVO 分页查询条件
     * @param categoryIds 分类ID列表
     * @return 分页结果
     */
    default PageResult<matterInstanceDO> selectPageByCategoryIds(matterInstancePageReqVO reqVO, List<String> categoryIds) {
        LambdaQueryWrapperX<matterInstanceDO> queryWrapper = new LambdaQueryWrapperX<matterInstanceDO>()
                .eqIfPresent(matterInstanceDO::getMatterInstanceId, reqVO.getMatterInstanceId())
                .likeIfPresent(matterInstanceDO::getName, reqVO.getName())
                .eqIfPresent(matterInstanceDO::getUniqueCode, reqVO.getUniqueCode())
                // 关键：按分类ID列表查询
                .in(matterInstanceDO::getCategoryId, categoryIds)
                .likeIfPresent(matterInstanceDO::getCategoryName, reqVO.getCategoryName())
                .eqIfPresent(matterInstanceDO::getParentCategoryId, reqVO.getParentCategoryId())
                .likeIfPresent(matterInstanceDO::getLocation, reqVO.getLocation())
                .eqIfPresent(matterInstanceDO::getGridId, reqVO.getGridId())
                .likeIfPresent(matterInstanceDO::getGridName, reqVO.getGridName())
                .likeIfPresent(matterInstanceDO::getDescription, reqVO.getDescription())
                .eqIfPresent(matterInstanceDO::getStatusId, reqVO.getStatusId())
                .likeIfPresent(matterInstanceDO::getStatusName, reqVO.getStatusName())
                .eqIfPresent(matterInstanceDO::getDeptId, reqVO.getDeptId())
                .likeIfPresent(matterInstanceDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(matterInstanceDO::getAttachmentInfo, reqVO.getAttachmentInfo())
                .eqIfPresent(matterInstanceDO::getPartIds, reqVO.getPartIds())
                .eqIfPresent(matterInstanceDO::getPartCount, reqVO.getPartCount())
                .eqIfPresent(matterInstanceDO::getTimeoutFlag, reqVO.getTimeoutFlag())
                .eqIfPresent(matterInstanceDO::getTimeoutDuration, reqVO.getTimeoutDuration())
                .betweenIfPresent(matterInstanceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(matterInstanceDO::getId);

        return selectPage(reqVO, queryWrapper);
    }

}