package cn.iocoder.yudao.module.evaluate.dal.mysql.objecttype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.objecttype.vo.ObjectTypePageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.objecttype.ObjectTypeDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 对象类型字典 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ObjectTypeMapper extends BaseMapperX<ObjectTypeDO> {

    default PageResult<ObjectTypeDO> selectPage(ObjectTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ObjectTypeDO>()
                .eqIfPresent(ObjectTypeDO::getTypeId, reqVO.getTypeId())
                .likeIfPresent(ObjectTypeDO::getName, reqVO.getName())
                .eqIfPresent(ObjectTypeDO::getCode, reqVO.getCode())
                .eqIfPresent(ObjectTypeDO::getDesc, reqVO.getDesc())
                .betweenIfPresent(ObjectTypeDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(ObjectTypeDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(ObjectTypeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ObjectTypeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ObjectTypeDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ObjectTypeDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(ObjectTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ObjectTypeDO::getId));
    }

    /**
     * 批量查询对象类型名称（使用 typeId 查询）
     */
    @Select("<script>" +
            "SELECT type_id, name FROM sys_object_type WHERE deleted = 0 AND type_id IN " +
            "<foreach collection='typeIds' item='typeId' open='(' separator=',' close=')'>" +
            "#{typeId}" +
            "</foreach>" +
            "</script>")
    List<ObjectTypeDO> selectByTypeIds(@Param("typeIds") List<String> typeIds);

}
