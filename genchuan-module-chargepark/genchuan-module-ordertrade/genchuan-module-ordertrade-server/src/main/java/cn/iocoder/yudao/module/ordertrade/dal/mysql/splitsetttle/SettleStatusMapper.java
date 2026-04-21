package cn.iocoder.yudao.module.ordertrade.dal.mysql.splitsetttle;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.splitsetttle.vo.SettleStatusPageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.splitsetttle.SettleStatusDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SettleStatusMapper extends BaseMapperX<SettleStatusDO> {

    default PageResult<SettleStatusDO> selectPage(SettleStatusPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SettleStatusDO>()
                .eqIfPresent(SettleStatusDO::getBillId, reqVO.getBillId())
                .eqIfPresent(SettleStatusDO::getStatus, reqVO.getStatus())
                .orderByDesc(SettleStatusDO::getId));
    }

    @Select("SELECT status, COUNT(*) AS count FROM settle_status WHERE deleted = 0 GROUP BY status")
    List<Map<String, Object>> selectGroupByStatus();

    @Select("SELECT COUNT(*) FROM settle_status WHERE deleted = 0 AND status = 'normal'")
    Long selectNormalCount();

    @Select("SELECT COUNT(*) FROM settle_status WHERE deleted = 0")
    Long selectTotalCount();

    @Select("SELECT COUNT(*) FROM settle_status WHERE deleted = 0 AND status = 'abnormal'")
    Long selectAbnormalCount();
}
