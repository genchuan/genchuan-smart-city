package cn.iocoder.yudao.module.data.service.eventinstance;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.data.controller.admin.eventinstance.vo.EventInstancePageReqVO;
import cn.iocoder.yudao.module.data.controller.admin.eventinstance.vo.EventInstanceSaveReqVO;
import cn.iocoder.yudao.module.data.controller.admin.eventinstance.vo.EventInstanceUpdateStatusReqVO;
import cn.iocoder.yudao.module.data.dal.dataobject.eventinstance.EventInstanceDO;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 监测事件实例 Service 接口
 *
 * @author zhucongquan
 */
public interface EventInstanceService {

    /**
     * 创建监测事件实例
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEventInstance(@Valid EventInstanceSaveReqVO createReqVO);

    /**
     * 更新监测事件实例
     *
     * @param updateReqVO 更新信息
     */
    void updateEventInstance(@Valid EventInstanceSaveReqVO updateReqVO);

    /**
     * 删除监测事件实例
     *
     * @param id 编号
     */
    void deleteEventInstance(Long id);

    /**
     * 获得监测事件实例
     *
     * @param id 编号
     * @return 监测事件实例
     */
    EventInstanceDO getEventInstance(Long id);

    /**
     * 获得监测事件实例分页
     *
     * @param pageReqVO 分页查询
     * @return 监测事件实例分页
     */
    PageResult<EventInstanceDO> getEventInstancePage(EventInstancePageReqVO pageReqVO);

    /**
     * 批量更新监测事件实例状态
     *
     * @param updateReqVO 批量更新信息
     * @return 更新成功的记录数
     */
    Integer updateEventInstanceStatusBatch(@Valid EventInstanceUpdateStatusReqVO updateReqVO);

    /**
     * 导入监测事件实例 Excel
     *
     * @param file Excel 文件
     * @return 导入结果提示信息
     * @throws IOException 文件操作异常
     */
    String importEventInstanceExcel(MultipartFile file) throws IOException;

}