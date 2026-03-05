package cn.iocoder.yudao.module.evaluate.dal.mysql.cycletype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.cycletype.vo.CycleTypePageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.cycletype.CycleTypeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 周期类型字典 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface CycleTypeMapper extends BaseMapperX<CycleTypeDO> {

    default PageResult<CycleTypeDO> selectPage(CycleTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CycleTypeDO>()
                .eqIfPresent(CycleTypeDO::getTypeId, reqVO.getTypeId())
                .likeIfPresent(CycleTypeDO::getName, reqVO.getName())
                .eqIfPresent(CycleTypeDO::getCode, reqVO.getCode())
                .eqIfPresent(CycleTypeDO::getDesc, reqVO.getDesc())
                .betweenIfPresent(CycleTypeDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(CycleTypeDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(CycleTypeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(CycleTypeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(CycleTypeDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(CycleTypeDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(CycleTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CycleTypeDO::getId));
    }

}