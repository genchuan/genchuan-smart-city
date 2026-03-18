package cn.iocoder.yudao.module.evaluate.dal.mysql.commentstatistic;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.commentstatistic.vo.CommentStatisticPageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.commentstatistic.CommentStatisticDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 巡查巡检统计 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface CommentStatisticMapper extends BaseMapperX<CommentStatisticDO> {

    /**
     * 清空统计表所有数据
     */
    void deleteAll();

    default PageResult<CommentStatisticDO> selectPage(CommentStatisticPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CommentStatisticDO>()
                .eqIfPresent(CommentStatisticDO::getItemId, reqVO.getItemId())
                .eqIfPresent(CommentStatisticDO::getObjectId, reqVO.getObjectId())
                .eqIfPresent(CommentStatisticDO::getCount, reqVO.getCount())
                .eqIfPresent(CommentStatisticDO::getScore, reqVO.getScore())
                .eqIfPresent(CommentStatisticDO::getAddressCoding, reqVO.getAddressCoding())
                .eqIfPresent(CommentStatisticDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(CommentStatisticDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(CommentStatisticDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(CommentStatisticDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(CommentStatisticDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(CommentStatisticDO::getUpdateTime, reqVO.getUpdateTime())
                .eqIfPresent(CommentStatisticDO::getStatus, reqVO.getStatus())
                .orderByDesc(CommentStatisticDO::getId));
    }

    /**
     * 根据 itemId 和 objectId 从巡查巡检表动态统计数量
     *
     * @param itemId 指标项ID
     * @param objectId 评价对象ID
     * @return 统计数量
     */
    Long selectCountByItemIdAndObjectId(@Param("itemId") Long itemId, @Param("objectId") Long objectId);

    /**
     * 根据 systemId、itemId 和 objectId 从巡查巡检表动态统计数量
     *
     * @param systemId 体系ID
     * @param itemId 指标项ID
     * @param objectId 评价对象ID
     * @return 统计数量
     */
    Long selectCountBySystemIdAndItemIdAndObjectId(@Param("systemId") Long systemId, @Param("itemId") Long itemId, @Param("objectId") Long objectId);

}