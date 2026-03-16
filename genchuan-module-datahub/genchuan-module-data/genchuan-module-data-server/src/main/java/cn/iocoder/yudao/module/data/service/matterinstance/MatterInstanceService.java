package cn.iocoder.yudao.module.data.service.matterinstance;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.data.controller.admin.matterinstance.vo.MatterInstancePageReqVO;
import cn.iocoder.yudao.module.data.controller.admin.matterinstance.vo.MatterInstanceSaveReqVO;
import cn.iocoder.yudao.module.data.controller.admin.matterinstance.vo.MatterInstanceUpdateStatusNameReqVO;
import cn.iocoder.yudao.module.data.dal.dataobject.matterinstance.MatterInstanceDO;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 管理事项实例 Service 接口
 *
 * @author zhucongquan
 */
public interface MatterInstanceService {

    /**
     * 创建管理事项实例
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long creatematterInstance(@Valid MatterInstanceSaveReqVO createReqVO);

    /**
     * 更新管理事项实例
     *
     * @param updateReqVO 更新信息
     */
    void updatematterInstance(@Valid MatterInstanceSaveReqVO updateReqVO);

    /**
     * 批量更新事项实例的状态名称
     *
     * @param updateReqVO 包含ID列表和目标状态名称
     * @return 成功更新的记录数量
     */
    Integer updateInstanceStatusNameBatch(@Valid MatterInstanceUpdateStatusNameReqVO updateReqVO);

    /**
     * 删除管理事项实例
     *
     * @param id 编号
     */
    void deletematterInstance(Long id);

    /**
     * 获得管理事项实例
     *
     * @param id 编号
     * @return 管理事项实例
     */
    MatterInstanceDO getmatterInstance(Long id);

    /**
     * 获得管理事项实例分页
     *
     * @param pageReqVO 分页查询
     * @return 管理事项实例分页
     */
    PageResult<MatterInstanceDO> getmatterInstancePage(MatterInstancePageReqVO pageReqVO);

    /**
     * 获取指定分类节点下的所有子分类ID（用于树形查询过滤）
     *
     * @param parentCategoryId 父分类ID
     * @param includeSelf 是否包含父分类自身
     * @return 子分类ID列表
     */
    List<String> getSubCategoryIdsForTree(String parentCategoryId, boolean includeSelf);

    /**
     * 导入管理事项实例 Excel
     *
     * @param file Excel 文件
     * @return 导入结果提示信息
     */
    String importMatterInstanceExcel(MultipartFile file) throws IOException;



}