package cn.iocoder.yudao.module.park.dal.mysql.park.basicAssociation.monitorparttype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.monitorparttype.vo.monitorPartTypePageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.monitorparttype.monitorPartTypeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 监测部件类别 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface monitorPartTypeMapper extends BaseMapperX<monitorPartTypeDO> {

    default PageResult<monitorPartTypeDO> selectPage(monitorPartTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<monitorPartTypeDO>()
                .eqIfPresent(monitorPartTypeDO::getParentTypeId, reqVO.getParentTypeId())
                .eqIfPresent(monitorPartTypeDO::getTypeCode, reqVO.getTypeCode())
                .likeIfPresent(monitorPartTypeDO::getTypeName, reqVO.getTypeName())
                .eqIfPresent(monitorPartTypeDO::getMonitorIndices, reqVO.getMonitorIndices())
                .eqIfPresent(monitorPartTypeDO::getDataType, reqVO.getDataType())
                .eqIfPresent(monitorPartTypeDO::getCollectionCycle, reqVO.getCollectionCycle())
                .eqIfPresent(monitorPartTypeDO::getBizDomain, reqVO.getBizDomain())
                .eqIfPresent(monitorPartTypeDO::getTypeStatus, reqVO.getTypeStatus())
                .eqIfPresent(monitorPartTypeDO::getTypeRemark, reqVO.getTypeRemark())
                .betweenIfPresent(monitorPartTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(monitorPartTypeDO::getId));
    }

}