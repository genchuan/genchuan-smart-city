package cn.iocoder.yudao.module.stationresource.service.stationresource.rulecontrol.timepermission;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.timepermission.vo.TimePermissionPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.timepermission.vo.TimePermissionRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.timepermission.vo.TimePermissionSaveReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.timepermission.vo.ops.ImportRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.timepermission.vo.ops.TimePermissionCreateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.timepermission.vo.ops.TimePermissionUpdateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.timepermission.vo.statistics.TimePermissionChartRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.timepermission.TimePermissionDO;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 时段权限 Service 接口
 *
 * @author 亘川智城
 */
public interface TimePermissionService {

    /**
     * 创建时段权限
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTimePermission(@Valid TimePermissionSaveReqVO createReqVO);

    /**
     * 更新时段权限
     *
     * @param updateReqVO 更新信息
     */
    void updateTimePermission(@Valid TimePermissionSaveReqVO updateReqVO);

    /**
     * 删除时段权限
     *
     * @param id 编号
     */
    void deleteTimePermission(Long id);

    /**
    * 批量删除时段权限
    *
    * @param ids 编号
    */
    void deleteTimePermissionListByIds(List<Long> ids);

    /**
     * 获得时段权限
     *
     * @param id 编号
     * @return 时段权限
     */
    TimePermissionDO getTimePermission(Long id);

    /**
     * 获得时段权限分页
     *
     * @param pageReqVO 分页查询
     * @return 时段权限分页
     */
    PageResult<TimePermissionRespVO> getTimePermissionPage(TimePermissionPageReqVO pageReqVO);

    void addTimePermission(TimePermissionCreateReqVO createReqVO);

    ImportRespVO importTimePermission(MultipartFile file, boolean updateSupport);

    /**
     * 批量生效
     * @param ids 规则 ID 列表
     */
    void enableTimePermission(List<Long> ids);

    /**
     * 批量禁用
     * @param ids 规则 ID 列表
     */
    void disableTimePermission(List<Long> ids);

    void myUpdateTimePermission(TimePermissionUpdateReqVO updateReqVO);

    TimePermissionChartRespVO getTimePermissionChart();
}
