package cn.iocoder.yudao.module.waterdetection.dal.mysql.gb5749standard;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.gb5749standard.Gb5749StandardDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.gb5749standard.vo.*;

/**
 * 《生活饮用水卫生标准》GB 5749-2022标准 Mapper
 *
 * @author 朱聪权
 */
@Mapper
public interface Gb5749StandardMapper extends BaseMapperX<Gb5749StandardDO> {

    default PageResult<Gb5749StandardDO> selectPage(Gb5749StandardPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<Gb5749StandardDO>()
                .likeIfPresent(Gb5749StandardDO::getItemName, reqVO.getItemName())
                .eqIfPresent(Gb5749StandardDO::getLimitValue, reqVO.getLimitValue())
                .eqIfPresent(Gb5749StandardDO::getItemOrder, reqVO.getItemOrder())
                .orderByDesc(Gb5749StandardDO::getId));
    }

}