package cn.iocoder.yudao.module.vehiclepass.dal.mysql.leavemgmt.paycheck;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.paycheck.vo.PayCheckPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.paycheck.vo.PayCheckRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.paycheck.vo.PayCheckChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.paycheck.vo.PayCheckChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.leavemgmt.paycheck.PayCheckDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 缴费核验 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface PayCheckMapper extends BaseMapperX<PayCheckDO> {

    default PageResult<PayCheckDO> selectPage(PayCheckPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PayCheckDO>()
                .eqIfPresent(PayCheckDO::getPlateNo, reqVO.getPlateNo())
                .eqIfPresent(PayCheckDO::getParkFee, reqVO.getParkFee())
                .eqIfPresent(PayCheckDO::getStatus, reqVO.getStatus())
                .eqIfPresent(PayCheckDO::getStationId, reqVO.getStationId())
                .eqIfPresent(PayCheckDO::getCheckUserId, reqVO.getCheckUserId())
                .eqIfPresent(PayCheckDO::getCheckResult, reqVO.getCheckResult())
                .eqIfPresent(PayCheckDO::getRemark, reqVO.getRemark())
                .eqIfPresent(PayCheckDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(PayCheckDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(PayCheckDO::getCreator, reqVO.getCreator())
                .eqIfPresent(PayCheckDO::getUpdater, reqVO.getUpdater())
                .orderByDesc(PayCheckDO::getId));
    }

    IPage<PayCheckRespVO> selectPageJoin(Page<?> page, @Param("reqVO") PayCheckPageReqVO reqVO);

    /**
     * 统计核验成功率趋势
     *
     * @param reqVO 查询参数
     * @return 趋势数据
     */
    List<PayCheckChartRespVO.CheckSuccessTrend> selectChartTrend(@Param("reqVO") PayCheckChartReqVO reqVO);

    /**
     * 统计核验成功率（总体）
     *
     * @param reqVO 查询参数
     * @return 成功率
     */
    Double selectCheckSuccessRate(@Param("reqVO") PayCheckChartReqVO reqVO);

    /**
     * 统计平均核验时长（小时）
     *
     * @param reqVO 查询参数
     * @return 平均时长
     */
    Double selectAvgCheckDuration(@Param("reqVO") PayCheckChartReqVO reqVO);

}