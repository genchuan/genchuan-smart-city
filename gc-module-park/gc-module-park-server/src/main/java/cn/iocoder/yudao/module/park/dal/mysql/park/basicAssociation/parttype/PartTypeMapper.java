package cn.iocoder.yudao.module.park.dal.mysql.park.basicAssociation.parttype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.parttype.vo.PartTypePageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.parttype.PartTypeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理部件类别 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface PartTypeMapper extends BaseMapperX<PartTypeDO> {

    default PageResult<PartTypeDO> selectPage(PartTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PartTypeDO>()
                .eqIfPresent(PartTypeDO::getParentTypeId, reqVO.getParentTypeId())
                .eqIfPresent(PartTypeDO::getTypeCode, reqVO.getTypeCode())
                .likeIfPresent(PartTypeDO::getTypeName, reqVO.getTypeName())
                .eqIfPresent(PartTypeDO::getTypeDesc, reqVO.getTypeDesc())
                .eqIfPresent(PartTypeDO::getBizDomain, reqVO.getBizDomain())
                .eqIfPresent(PartTypeDO::getTypeStatus, reqVO.getTypeStatus())
                .eqIfPresent(PartTypeDO::getTypeRemark, reqVO.getTypeRemark())
                .betweenIfPresent(PartTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PartTypeDO::getId));
    }

}