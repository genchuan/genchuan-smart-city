package cn.iocoder.yudao.module.envirhealth.dal.mysql.river;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.cleaningtype.CleaningTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.river.CleaningTypeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 保洁类型字典表 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CleaningTypeMapper extends BaseMapperX<CleaningTypeDO> {

    default PageResult<CleaningTypeDO> selectPage(CleaningTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CleaningTypeDO>()
                .eqIfPresent(CleaningTypeDO::getCleaningTypeId, reqVO.getCleaningTypeId())
                .likeIfPresent(CleaningTypeDO::getCleaningName, reqVO.getCleaningName())
                .eqIfPresent(CleaningTypeDO::getDescription, reqVO.getDescription())
                .eqIfPresent(CleaningTypeDO::getStatus, reqVO.getStatus())
                .eqIfPresent(CleaningTypeDO::getSort, reqVO.getSort())
                .eqIfPresent(CleaningTypeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(CleaningTypeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(CleaningTypeDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(CleaningTypeDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(CleaningTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CleaningTypeDO::getId));
    }

}