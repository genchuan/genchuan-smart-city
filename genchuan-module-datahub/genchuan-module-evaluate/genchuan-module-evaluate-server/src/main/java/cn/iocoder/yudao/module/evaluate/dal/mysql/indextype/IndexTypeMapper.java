package cn.iocoder.yudao.module.evaluate.dal.mysql.indextype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.indextype.vo.IndexTypePageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.indextype.IndexTypeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 指标类型字典 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface IndexTypeMapper extends BaseMapperX<IndexTypeDO> {

    default PageResult<IndexTypeDO> selectPage(IndexTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<IndexTypeDO>()
                .eqIfPresent(IndexTypeDO::getTypeId, reqVO.getTypeId())
                .likeIfPresent(IndexTypeDO::getName, reqVO.getName())
                .eqIfPresent(IndexTypeDO::getCode, reqVO.getCode())
                .eqIfPresent(IndexTypeDO::getDesc, reqVO.getDesc())
                .betweenIfPresent(IndexTypeDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(IndexTypeDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(IndexTypeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(IndexTypeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(IndexTypeDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(IndexTypeDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(IndexTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(IndexTypeDO::getId));
    }

}