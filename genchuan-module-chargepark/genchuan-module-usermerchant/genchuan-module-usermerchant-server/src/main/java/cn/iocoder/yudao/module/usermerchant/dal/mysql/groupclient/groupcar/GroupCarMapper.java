package cn.iocoder.yudao.module.usermerchant.dal.mysql.groupclient.groupcar;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.groupclient.groupcar.GroupCarDO;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.usermerchant.controller.admin.groupclient.groupcar.vo.*;

/**
 * 集团车辆 Mapper
 *
 * @author 亘川智城
 */
@Mapper
@DS("master")
public interface GroupCarMapper extends BaseMapperX<GroupCarDO> {

    default PageResult<GroupCarDO> selectPage(GroupCarPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GroupCarDO>()
                .eqIfPresent(GroupCarDO::getGroupId, reqVO.getGroupId())
                .eqIfPresent(GroupCarDO::getPlateNo, reqVO.getPlateNo())
                .eqIfPresent(GroupCarDO::getPlateColor, reqVO.getPlateColor())
                .eqIfPresent(GroupCarDO::getCarType, reqVO.getCarType())
                .betweenIfPresent(GroupCarDO::getBindTime, reqVO.getBindTime())
                .eqIfPresent(GroupCarDO::getStatus, reqVO.getStatus())
                .eqIfPresent(GroupCarDO::getAuditorId, reqVO.getAuditorId())
                .betweenIfPresent(GroupCarDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(GroupCarDO::getRemark, reqVO.getRemark())
                .orderByDesc(GroupCarDO::getId));
    }

}