package cn.iocoder.yudao.module.park.dal.mysql.park.user.supplier;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.user.supplier.vo.SupplierPageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.supplier.SupplierDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 供应商 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface SupplierMapper extends BaseMapperX<SupplierDO> {

    default PageResult<SupplierDO> selectPage(SupplierPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SupplierDO>()
                .likeIfPresent(SupplierDO::getSupplierName, reqVO.getSupplierName())
                .eqIfPresent(SupplierDO::getContactPerson, reqVO.getContactPerson())
                .eqIfPresent(SupplierDO::getContactPhone, reqVO.getContactPhone())
                .eqIfPresent(SupplierDO::getAddress, reqVO.getAddress())
                .eqIfPresent(SupplierDO::getBusinessScope, reqVO.getBusinessScope())
                .eqIfPresent(SupplierDO::getQualification, reqVO.getQualification())
                .eqIfPresent(SupplierDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(SupplierDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(SupplierDO::getRemark, reqVO.getRemark())
                .eqIfPresent(SupplierDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(SupplierDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(SupplierDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(SupplierDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(SupplierDO::getId));
    }

}
