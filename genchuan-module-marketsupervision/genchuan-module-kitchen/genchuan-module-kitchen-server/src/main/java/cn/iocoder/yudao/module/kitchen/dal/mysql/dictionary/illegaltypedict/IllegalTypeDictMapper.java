package cn.iocoder.yudao.module.kitchen.dal.mysql.dictionary.illegaltypedict;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.illegaltypedict.vo.IllegalTypeDictPageReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.dictionary.illegaltypedict.IllegalTypeDictDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 违规类型字典 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface IllegalTypeDictMapper extends BaseMapperX<IllegalTypeDictDO> {

    default PageResult<IllegalTypeDictDO> selectPage(IllegalTypeDictPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<IllegalTypeDictDO>()
                .eqIfPresent(IllegalTypeDictDO::getTypeCategory, reqVO.getTypeCategory())
                .eqIfPresent(IllegalTypeDictDO::getTypeCode, reqVO.getTypeCode())
                .likeIfPresent(IllegalTypeDictDO::getTypeName, reqVO.getTypeName())
                .eqIfPresent(IllegalTypeDictDO::getIllegalBehaviorDescription, reqVO.getIllegalBehaviorDescription())
                .eqIfPresent(IllegalTypeDictDO::getAlarmDeviceDescription, reqVO.getAlarmDeviceDescription())
                .eqIfPresent(IllegalTypeDictDO::getSort, reqVO.getSort())
                .betweenIfPresent(IllegalTypeDictDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(IllegalTypeDictDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(IllegalTypeDictDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(IllegalTypeDictDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(IllegalTypeDictDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(IllegalTypeDictDO::getId));
    }

}
