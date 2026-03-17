package cn.iocoder.yudao.module.envirhealth.dal.mysql.dictionary.garbagetype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.garbagetype.vo.GarbageTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.garbagetype.GarbageTypeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 垃圾品类字典 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface GarbageTypeMapper extends BaseMapperX<GarbageTypeDO> {

    default PageResult<GarbageTypeDO> selectPage(GarbageTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GarbageTypeDO>()
                .eqIfPresent(GarbageTypeDO::getSysGarbageTypeId, reqVO.getSysGarbageTypeId())
                .likeIfPresent(GarbageTypeDO::getName, reqVO.getName())
                .eqIfPresent(GarbageTypeDO::getCode, reqVO.getCode())
                .eqIfPresent(GarbageTypeDO::getStatus, reqVO.getStatus())
                .eqIfPresent(GarbageTypeDO::getRemark, reqVO.getRemark())
                .eqIfPresent(GarbageTypeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(GarbageTypeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(GarbageTypeDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(GarbageTypeDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(GarbageTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GarbageTypeDO::getId));
    }

}