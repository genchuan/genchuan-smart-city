package cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.rescue;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo.RescueInfoPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.rescue.RescueInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 救援信息 Mapper
 *
 * @author carservice
 */
@Mapper
public interface RescueInfoMapper extends BaseMapperX<RescueInfoDO> {

    default PageResult<RescueInfoDO> selectPage(RescueInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RescueInfoDO>()
                .eqIfPresent(RescueInfoDO::getUserId, reqVO.getUserId())
                .inIfPresent(RescueInfoDO::getUserId, reqVO.getUserIds())
                .eqIfPresent(RescueInfoDO::getRescueType, reqVO.getRescueType())
                .eqIfPresent(RescueInfoDO::getStatus, reqVO.getStatus())
                .eqIfPresent(RescueInfoDO::getArchiveStatus, reqVO.getArchiveStatus())
                .likeIfPresent(RescueInfoDO::getLocationName, reqVO.getLocationName())
                .betweenIfPresent(RescueInfoDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(RescueInfoDO::getDispatchTime, reqVO.getDispatchTime())
                .betweenIfPresent(RescueInfoDO::getFinishTime, reqVO.getFinishTime())
                .orderByDesc(RescueInfoDO::getId));
    }

    /**
     * 查询所有 rescue_info 涉及的去重 user_id 列表(用于昵称模糊搜索时,先确定候选用户范围)
     */
    @Select("SELECT DISTINCT user_id FROM rescue_info WHERE deleted = 0 AND user_id IS NOT NULL")
    List<Long> selectDistinctUserIds();

}
