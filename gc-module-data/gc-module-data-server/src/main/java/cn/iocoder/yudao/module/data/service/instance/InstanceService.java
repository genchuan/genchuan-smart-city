package cn.iocoder.yudao.module.data.service.instance;

import java.io.IOException;
import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.data.controller.admin.instance.vo.*;
import cn.iocoder.yudao.module.data.dal.dataobject.instance.InstanceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.web.multipart.MultipartFile;

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

}