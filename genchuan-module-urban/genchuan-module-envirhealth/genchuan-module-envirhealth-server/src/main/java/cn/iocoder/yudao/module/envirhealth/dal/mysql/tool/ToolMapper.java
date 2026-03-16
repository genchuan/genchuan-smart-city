package cn.iocoder.yudao.module.envirhealth.dal.mysql.tool;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.tool.vo.ToolPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.tool.ToolDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工具字典 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ToolMapper extends BaseMapperX<ToolDO> {

    default PageResult<ToolDO> selectPage(ToolPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ToolDO>()
                .eqIfPresent(ToolDO::getSysToolId, reqVO.getSysToolId())
                .likeIfPresent(ToolDO::getName, reqVO.getName())
                .eqIfPresent(ToolDO::getCode, reqVO.getCode())
                .eqIfPresent(ToolDO::getType, reqVO.getType())
                .eqIfPresent(ToolDO::getSpecification, reqVO.getSpecification())
                .eqIfPresent(ToolDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ToolDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ToolDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ToolDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ToolDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ToolDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(ToolDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ToolDO::getId));
    }

}