package cn.iocoder.yudao.module.envirhealth.dal.mysql.area;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.area.AreaDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.envirhealth.controller.admin.area.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 区域编码 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface AreaMapper extends BaseMapperX<AreaDO> {

    default PageResult<AreaDO> selectPage(AreaPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AreaDO>()
                .eqIfPresent(AreaDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(AreaDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(AreaDO::getParentCode, reqVO.getParentCode())
                .eqIfPresent(AreaDO::getLevel, reqVO.getLevel())
                .eqIfPresent(AreaDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AreaDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(AreaDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(AreaDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(AreaDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AreaDO::getId));
    }

    @Select("SELECT * FROM sys_area WHERE area_code = #{areaCode} LIMIT 1")
    AreaDO selectByAreaCode(@Param("areaCode") String areaCode);
}