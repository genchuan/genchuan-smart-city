package cn.iocoder.yudao.module.datacenter.dal.mysql.grid.code.extgridcode;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.extgridcode.vo.ExtGridCodePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.code.extgridcode.ExtGridCodeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 扩展网格编码 Mapper
 *
 * @author zcq
 */
@Mapper
public interface ExtGridCodeMapper extends BaseMapperX<ExtGridCodeDO> {

    default PageResult<ExtGridCodeDO> selectPage(ExtGridCodePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ExtGridCodeDO>()
                .eqIfPresent(ExtGridCodeDO::getExtCodeId, reqVO.getExtCodeId())
                .eqIfPresent(ExtGridCodeDO::getExtGridCode, reqVO.getExtGridCode())
                .eqIfPresent(ExtGridCodeDO::getExtGridId, reqVO.getExtGridId())
                .eqIfPresent(ExtGridCodeDO::getAreaId, reqVO.getAreaId())
                .eqIfPresent(ExtGridCodeDO::getAreaFullCode, reqVO.getAreaFullCode())
                .eqIfPresent(ExtGridCodeDO::getSeqCode, reqVO.getSeqCode())
                .eqIfPresent(ExtGridCodeDO::getTypeCode, reqVO.getTypeCode())
                .betweenIfPresent(ExtGridCodeDO::getGenerateTime, reqVO.getGenerateTime())
                .eqIfPresent(ExtGridCodeDO::getCodeStatus, reqVO.getCodeStatus())
                .eqIfPresent(ExtGridCodeDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ExtGridCodeDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(ExtGridCodeDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(ExtGridCodeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ExtGridCodeDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(ExtGridCodeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ExtGridCodeDO::getId));
    }

}