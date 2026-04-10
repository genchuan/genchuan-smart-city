package cn.iocoder.yudao.module.kitchen.dal.mysql.vrv.dictionary.bizdicttype;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.kitchen.controller.admin.vrv.dictionary.bizdicttype.vo.BizDictTypePageReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.vrv.dictionary.bizdicttype.BizDictTypeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 业务字典分类 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface BizDictTypeMapper extends BaseMapperX<BizDictTypeDO> {

    default PageResult<BizDictTypeDO> selectPage(BizDictTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BizDictTypeDO>()
                .eqIfPresent(BizDictTypeDO::getUniCode, reqVO.getUniCode())
                .likeIfPresent(BizDictTypeDO::getName, reqVO.getName())
                .eqIfPresent(BizDictTypeDO::getSort, reqVO.getSort())
                .eqIfPresent(BizDictTypeDO::getDescription, reqVO.getDescription())
                .eqIfPresent(BizDictTypeDO::getRemark, reqVO.getRemark())
                .eqIfPresent(BizDictTypeDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(BizDictTypeDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(BizDictTypeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(BizDictTypeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(BizDictTypeDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(BizDictTypeDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(BizDictTypeDO::getId));
    }

}
