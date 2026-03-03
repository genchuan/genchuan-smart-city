package cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.alarmtype.AlarmTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.AlarmTypeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预警类型字典 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface AlarmTypeMapper extends BaseMapperX<AlarmTypeDO> {

    default PageResult<AlarmTypeDO> selectPage(AlarmTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AlarmTypeDO>()
                .eqIfPresent(AlarmTypeDO::getAlarmTypeId, reqVO.getAlarmTypeId())
                .likeIfPresent(AlarmTypeDO::getAlarmName, reqVO.getAlarmName())
                .eqIfPresent(AlarmTypeDO::getDescription, reqVO.getDescription())
                .eqIfPresent(AlarmTypeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AlarmTypeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(AlarmTypeDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(AlarmTypeDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(AlarmTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AlarmTypeDO::getId));
    }

}