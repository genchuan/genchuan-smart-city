package cn.iocoder.yudao.module.park.dal.mysql.park.basicAssociation.apptype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.apptype.vo.AppTypePageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.apptype.AppTypeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 行业应用类别 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface AppTypeMapper extends BaseMapperX<AppTypeDO> {

    default PageResult<AppTypeDO> selectPage(AppTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AppTypeDO>()
                .eqIfPresent(AppTypeDO::getParentTypeId, reqVO.getParentTypeId())
                .eqIfPresent(AppTypeDO::getAppCode, reqVO.getAppCode())
                .likeIfPresent(AppTypeDO::getAppName, reqVO.getAppName())
                .eqIfPresent(AppTypeDO::getBizDomain, reqVO.getBizDomain())
                .eqIfPresent(AppTypeDO::getFunctionDesc, reqVO.getFunctionDesc())
                .eqIfPresent(AppTypeDO::getAccessPermCode, reqVO.getAccessPermCode())
                .eqIfPresent(AppTypeDO::getAppStatus, reqVO.getAppStatus())
                .eqIfPresent(AppTypeDO::getAppRemark, reqVO.getAppRemark())
                .betweenIfPresent(AppTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AppTypeDO::getId));
    }

}
