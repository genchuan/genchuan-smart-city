package cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagecollection;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.collectionfrequency.CollectionFrequencyPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.CollectionFrequencyDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 收运频次字典 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface CollectionFrequencyMapper extends BaseMapperX<CollectionFrequencyDO> {

    default PageResult<CollectionFrequencyDO> selectPage(CollectionFrequencyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CollectionFrequencyDO>()
                .eqIfPresent(CollectionFrequencyDO::getFrequencyCode, reqVO.getFrequencyCode())
                .likeIfPresent(CollectionFrequencyDO::getFrequencyName, reqVO.getFrequencyName())
                .eqIfPresent(CollectionFrequencyDO::getSort, reqVO.getSort())
                .eqIfPresent(CollectionFrequencyDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CollectionFrequencyDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CollectionFrequencyDO::getId));
    }

}