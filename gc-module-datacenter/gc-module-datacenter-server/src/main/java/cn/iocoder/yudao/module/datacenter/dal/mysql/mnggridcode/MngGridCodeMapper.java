package cn.iocoder.yudao.module.datacenter.dal.mysql.mnggridcode;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mnggridcode.MngGridCodeDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.datacenter.controller.admin.mnggridcode.vo.*;

/**
 * 管理网格编码 Mapper
 *
 * @author zcq
 */
@Mapper
public interface MngGridCodeMapper extends BaseMapperX<MngGridCodeDO> {

    default PageResult<MngGridCodeDO> selectPage(MngGridCodePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MngGridCodeDO>()
                .eqIfPresent(MngGridCodeDO::getMgCodeId, reqVO.getMgCodeId())
                .eqIfPresent(MngGridCodeDO::getMgGridCode, reqVO.getMgGridCode())
                .eqIfPresent(MngGridCodeDO::getMngGridId, reqVO.getMngGridId())
                .eqIfPresent(MngGridCodeDO::getAreaFullCode, reqVO.getAreaFullCode())
                .eqIfPresent(MngGridCodeDO::getSeqCode, reqVO.getSeqCode())
                .betweenIfPresent(MngGridCodeDO::getGenerateTime, reqVO.getGenerateTime())
                .eqIfPresent(MngGridCodeDO::getCodeStatus, reqVO.getCodeStatus())
                .eqIfPresent(MngGridCodeDO::getRemark, reqVO.getRemark())
                .eqIfPresent(MngGridCodeDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(MngGridCodeDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(MngGridCodeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(MngGridCodeDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(MngGridCodeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MngGridCodeDO::getId));
    }

}