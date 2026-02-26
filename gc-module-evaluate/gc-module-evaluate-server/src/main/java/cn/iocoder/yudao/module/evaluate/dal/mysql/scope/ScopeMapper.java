package cn.iocoder.yudao.module.evaluate.dal.mysql.scope;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.scope.vo.ScopePageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.scope.ScopeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 范围字典 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ScopeMapper extends BaseMapperX<ScopeDO> {

    default PageResult<ScopeDO> selectPage(ScopePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ScopeDO>()
                .eqIfPresent(ScopeDO::getScopeId, reqVO.getScopeId())
                .likeIfPresent(ScopeDO::getName, reqVO.getName())
                .eqIfPresent(ScopeDO::getCode, reqVO.getCode())
                .eqIfPresent(ScopeDO::getDesc, reqVO.getDesc())
                .betweenIfPresent(ScopeDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(ScopeDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(ScopeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ScopeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ScopeDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ScopeDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(ScopeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ScopeDO::getId));
    }

}