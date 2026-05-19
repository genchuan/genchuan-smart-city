package cn.iocoder.yudao.module.accessmgmt.dal.mysql.faceaccess.facemgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.facemgmt.vo.FaceMgmtChartRespVO;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.facemgmt.vo.FaceMgmtPageReqVO;
import cn.iocoder.yudao.module.accessmgmt.dal.dataobject.faceaccess.facemgmt.FaceMgmtDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

/**
 * 人脸信息 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface FaceMgmtMapper extends BaseMapperX<FaceMgmtDO> {

    /**
     * 分页查询人脸信息，支持按姓名/手机号/企业/区域/权限状态筛选
     */
    default PageResult<FaceMgmtDO> selectPage(FaceMgmtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FaceMgmtDO>()
                .likeIfPresent(FaceMgmtDO::getUserName, reqVO.getUserName())
                .likeIfPresent(FaceMgmtDO::getPhone, reqVO.getPhone())
                .likeIfPresent(FaceMgmtDO::getCompany, reqVO.getCompany())
                .eqIfPresent(FaceMgmtDO::getAccessArea, reqVO.getAccessArea())
                .eqIfPresent(FaceMgmtDO::getAuthStatus, reqVO.getAuthStatus())
                .orderByDesc(FaceMgmtDO::getId));
    }

    /**
     * 统计各状态数量：总数、授权数、过期数、未授权数
     */
    FaceMgmtChartRespVO selectChartStats(@Param("startTime") Long startTime,
                                         @Param("endTime") Long endTime);

    /**
     * 统计各区域授权人数
     */
    List<FaceMgmtChartRespVO.AreaAuthItem> selectAreaAuthList(@Param("startTime") Long startTime,
                                                               @Param("endTime") Long endTime);

    /**
     * 统计各时段通行人数（基于最后通行时间）
     */
    List<FaceMgmtChartRespVO.TimeAccessItem> selectTimeAccessList(@Param("startTime") Long startTime,
                                                                   @Param("endTime") Long endTime);

}
