package cn.iocoder.yudao.module.park.dal.mysql.park.basicAssociation.itemtype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.itemtype.vo.ItemTypePageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.itemtype.ItemTypeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理事项类别 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface ItemTypeMapper extends BaseMapperX<ItemTypeDO> {

    default PageResult<ItemTypeDO> selectPage(ItemTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ItemTypeDO>()
                .eqIfPresent(ItemTypeDO::getParentTypeId, reqVO.getParentTypeId())
                .eqIfPresent(ItemTypeDO::getItemCode, reqVO.getItemCode())
                .likeIfPresent(ItemTypeDO::getItemName, reqVO.getItemName())
                .eqIfPresent(ItemTypeDO::getBizDomain, reqVO.getBizDomain())
                .eqIfPresent(ItemTypeDO::getProcessConfig, reqVO.getProcessConfig())
                .eqIfPresent(ItemTypeDO::getHandleRoleIds, reqVO.getHandleRoleIds())
                .eqIfPresent(ItemTypeDO::getItemStatus, reqVO.getItemStatus())
                .eqIfPresent(ItemTypeDO::getItemRemark, reqVO.getItemRemark())
                .betweenIfPresent(ItemTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ItemTypeDO::getId));
    }

}