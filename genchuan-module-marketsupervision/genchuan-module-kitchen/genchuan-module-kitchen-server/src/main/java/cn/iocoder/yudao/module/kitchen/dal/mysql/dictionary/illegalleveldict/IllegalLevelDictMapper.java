package cn.iocoder.yudao.module.kitchen.dal.mysql.dictionary.illegalleveldict;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.illegalleveldict.vo.IllegalLevelDictPageReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.dictionary.illegalleveldict.IllegalLevelDictDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 违规等级字典 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface IllegalLevelDictMapper extends BaseMapperX<IllegalLevelDictDO> {

    default PageResult<IllegalLevelDictDO> selectPage(IllegalLevelDictPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<IllegalLevelDictDO>()
                .eqIfPresent(IllegalLevelDictDO::getLevelCode, reqVO.getLevelCode())
                .likeIfPresent(IllegalLevelDictDO::getLevelName, reqVO.getLevelName())
                .eqIfPresent(IllegalLevelDictDO::getSort, reqVO.getSort())
                .betweenIfPresent(IllegalLevelDictDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(IllegalLevelDictDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(IllegalLevelDictDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(IllegalLevelDictDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(IllegalLevelDictDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(IllegalLevelDictDO::getId));
    }

}
