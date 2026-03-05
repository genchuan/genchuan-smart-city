package cn.iocoder.yudao.module.envirhealth.dal.mysql.park;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.park.vo.greentype.GreenTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.park.GreenTypeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 绿化品类字典表【通用复用】 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface GreenTypeMapper extends BaseMapperX<GreenTypeDO> {

    default PageResult<GreenTypeDO> selectPage(GreenTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GreenTypeDO>()
                .eqIfPresent(GreenTypeDO::getGreenTypeId, reqVO.getGreenTypeId())
                .likeIfPresent(GreenTypeDO::getGreenName, reqVO.getGreenName())
                .eqIfPresent(GreenTypeDO::getMaintenanceRequire, reqVO.getMaintenanceRequire())
                .eqIfPresent(GreenTypeDO::getDescription, reqVO.getDescription())
                .eqIfPresent(GreenTypeDO::getStatus, reqVO.getStatus())
                .eqIfPresent(GreenTypeDO::getSort, reqVO.getSort())
                .eqIfPresent(GreenTypeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(GreenTypeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(GreenTypeDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(GreenTypeDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(GreenTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GreenTypeDO::getId));
    }

}