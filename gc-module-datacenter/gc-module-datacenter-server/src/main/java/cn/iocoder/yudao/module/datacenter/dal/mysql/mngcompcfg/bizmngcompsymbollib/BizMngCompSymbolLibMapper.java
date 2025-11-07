package cn.iocoder.yudao.module.datacenter.dal.mysql.mngcompcfg.bizmngcompsymbollib;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompsymbollib.vo.BizMngCompSymbolLibPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.bizmngcompsymbollib.BizMngCompSymbolLibDO;
import org.apache.ibatis.annotations.Mapper;

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