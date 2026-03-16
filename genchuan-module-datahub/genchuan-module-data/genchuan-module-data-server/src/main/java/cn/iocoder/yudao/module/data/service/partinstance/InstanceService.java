package cn.iocoder.yudao.module.data.service.partinstance;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.data.controller.admin.partinstance.vo.InstancePageReqVO;
import cn.iocoder.yudao.module.data.controller.admin.partinstance.vo.InstanceSaveReqVO;
import cn.iocoder.yudao.module.data.controller.admin.partinstance.vo.InstanceUpdateStatusReqVO;
import cn.iocoder.yudao.module.data.dal.dataobject.partinstance.InstanceDO;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 管理部件实例 Service 接口
 *
 * @author zhucongquan
 */
public interface InstanceService {

    /**
     * 创建管理部件实例
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInstance(@Valid InstanceSaveReqVO createReqVO);

    /**
     * 更新管理部件实例
     *
     * @param updateReqVO 更新信息
     */
    void updateInstance(@Valid InstanceSaveReqVO updateReqVO);

    /**
     * 删除管理部件实例
     *
     * @param id 编号
     */
    void deleteInstance(Long id);

    /**
     * 获得管理部件实例
     *
     * @param id 编号
     * @return 管理部件实例
     */
    InstanceDO getInstance(Long id);

    /**
     * 获得管理部件实例分页
     *
     * @param pageReqVO 分页查询
     * @return 管理部件实例分页
     */
    PageResult<InstanceDO> getInstancePage(InstancePageReqVO pageReqVO);

    /**
     * 根据分类ID获得管理部件实例列表
     *
     * @param categoryId 分类ID
     * @return 管理部件实例列表
     */
    List<InstanceDO> getInstanceListByCategoryId(String categoryId);

    /**
     * 导入管理部件实例 Excel
     *
     * @param file Excel 文件
     * @return 导入结果提示信息
     */
    String importInstanceExcel(MultipartFile file) throws IOException;

    /**
     * 批量更新管理部件实例的运行状态
     *
     * @param updateReqVO 批量更新信息
     * @return 更新成功的记录数
     */
    Integer updateInstanceStatusBatch(@Valid InstanceUpdateStatusReqVO updateReqVO);

}