package cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagecollection;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.collectiontimeperiod.CollectionTimePeriodPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.CollectionTimePeriodDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 收运时段字典 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface CollectionTimePeriodMapper extends BaseMapperX<CollectionTimePeriodDO> {

    default PageResult<CollectionTimePeriodDO> selectPage(CollectionTimePeriodPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CollectionTimePeriodDO>()
                .eqIfPresent(CollectionTimePeriodDO::getPeriodCode, reqVO.getPeriodCode())
                .likeIfPresent(CollectionTimePeriodDO::getPeriodName, reqVO.getPeriodName())
                .betweenIfPresent(CollectionTimePeriodDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(CollectionTimePeriodDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(CollectionTimePeriodDO::getSort, reqVO.getSort())
                .eqIfPresent(CollectionTimePeriodDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CollectionTimePeriodDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CollectionTimePeriodDO::getId));
    }

}