package cn.iocoder.yudao.module.data.service.monitorinstance;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.data.controller.admin.monitorinstance.vo.MonitorInstancePageReqVO;
import cn.iocoder.yudao.module.data.controller.admin.monitorinstance.vo.MonitorInstanceSaveReqVO;
import cn.iocoder.yudao.module.data.controller.admin.monitorinstance.vo.MonitorInstanceUpdateStatusReqVO;
import cn.iocoder.yudao.module.data.dal.dataobject.monitorinstance.MonitorInstanceDO;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 监测部件实例 Service 接口
 *
 * @author zhucongquan
 */
public interface MonitorInstanceService {

    /**
     * 创建监测部件实例
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMonitorInstance(@Valid MonitorInstanceSaveReqVO createReqVO);

    /**
     * 更新监测部件实例
     *
     * @param updateReqVO 更新信息
     */
    void updateMonitorInstance(@Valid MonitorInstanceSaveReqVO updateReqVO);

    /**
     * 删除监测部件实例
     *
     * @param id 编号
     */
    void deleteMonitorInstance(Long id);

    /**
     * 获得监测部件实例
     *
     * @param id 编号
     * @return 监测部件实例
     */
    MonitorInstanceDO getMonitorInstance(Long id);

    /**
     * 获得监测部件实例分页
     *
     * @param pageReqVO 分页查询
     * @return 监测部件实例分页
     */
    PageResult<MonitorInstanceDO> getMonitorInstancePage(MonitorInstancePageReqVO pageReqVO);

    /**
     * 批量更新监测部件实例的运行状态
     *
     * @param updateReqVO 批量更新信息
     * @return 更新成功的记录数
     */
    Integer updateMonitorInstanceStatusBatch(@Valid MonitorInstanceUpdateStatusReqVO updateReqVO);

    /**
     * 导入监测部件实例 Excel
     *
     * @param file Excel 文件
     * @return 导入结果提示信息
     */
    String importMonitorInstanceExcel(MultipartFile file) throws IOException;
}