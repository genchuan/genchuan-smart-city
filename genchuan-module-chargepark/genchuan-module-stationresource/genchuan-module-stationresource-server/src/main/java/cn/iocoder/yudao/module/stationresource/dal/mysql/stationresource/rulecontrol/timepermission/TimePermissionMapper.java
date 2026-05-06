package cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.rulecontrol.timepermission;

import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.timepermission.vo.TimePermissionPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.timepermission.vo.TimePermissionRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.timepermission.vo.statistics.TimePermissionChartRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.timepermission.TimePermissionDO;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 时段权限 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface TimePermissionMapper extends BaseMapperX<TimePermissionDO> {

    Page<TimePermissionRespVO> getPage(Page<TimePermissionRespVO> page, @Param("pageReqVO") TimePermissionPageReqVO pageReqVO);

    /**
     * 查询总使用次数
     */
    Integer selectTotalUseCount();

    /**
     * 按月分组统计使用次数
     */
    List<TimePermissionChartRespVO.UseLine> selectUseCountGroupByMonth();
}
