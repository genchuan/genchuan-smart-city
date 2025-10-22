package cn.iocoder.yudao.module.datacenter.dal.mysql.bizmngcompsymbollib;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.bizmngcompsymbollib.BizMngCompSymbolLibDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.datacenter.controller.admin.bizmngcompsymbollib.vo.*;

/**
 * 管理部件图示符号库 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface BizMngCompSymbolLibMapper extends BaseMapperX<BizMngCompSymbolLibDO> {

    default PageResult<BizMngCompSymbolLibDO> selectPage(BizMngCompSymbolLibPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BizMngCompSymbolLibDO>()
                .eqIfPresent(BizMngCompSymbolLibDO::getSymbolLibId, reqVO.getSymbolLibId())
                .likeIfPresent(BizMngCompSymbolLibDO::getSymbolName, reqVO.getSymbolName())
                .eqIfPresent(BizMngCompSymbolLibDO::getSymbolPath, reqVO.getSymbolPath())
                .eqIfPresent(BizMngCompSymbolLibDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(BizMngCompSymbolLibDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(BizMngCompSymbolLibDO::getId));
    }

}