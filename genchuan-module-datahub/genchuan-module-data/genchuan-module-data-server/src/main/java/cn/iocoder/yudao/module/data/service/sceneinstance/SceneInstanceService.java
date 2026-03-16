package cn.iocoder.yudao.module.data.service.sceneinstance;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.data.controller.admin.sceneinstance.vo.SceneInstancePageReqVO;
import cn.iocoder.yudao.module.data.controller.admin.sceneinstance.vo.SceneInstanceSaveReqVO;
import cn.iocoder.yudao.module.data.controller.admin.sceneinstance.vo.SceneInstanceUpdateStatusReqVO;
import cn.iocoder.yudao.module.data.dal.dataobject.sceneinstance.SceneInstanceDO;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 应用场景实例 Service 接口
 *
 * @author zhucongquan
 */
public interface SceneInstanceService {

    /**
     * 创建应用场景实例
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSceneInstance(@Valid SceneInstanceSaveReqVO createReqVO);

    /**
     * 更新应用场景实例
     *
     * @param updateReqVO 更新信息
     */
    void updateSceneInstance(@Valid SceneInstanceSaveReqVO updateReqVO);

    /**
     * 删除应用场景实例
     *
     * @param id 编号
     */
    void deleteSceneInstance(Long id);

    /**
     * 获得应用场景实例
     *
     * @param id 编号
     * @return 应用场景实例
     */
    SceneInstanceDO getSceneInstance(Long id);

    /**
     * 获得应用场景实例分页
     *
     * @param pageReqVO 分页查询
     * @return 应用场景实例分页
     */
    PageResult<SceneInstanceDO> getSceneInstancePage(SceneInstancePageReqVO pageReqVO);

    /**
     * 批量更新应用场景实例状态
     *
     * @param updateReqVO 批量更新信息
     * @return 更新成功的记录数
     */
    Integer updateSceneInstanceStatusBatch(@Valid SceneInstanceUpdateStatusReqVO updateReqVO);

    /**
     * 导入应用场景实例 Excel
     *
     * @param file Excel 文件
     * @return 导入结果提示信息
     * @throws IOException 文件操作异常
     */
    String importSceneInstanceExcel(MultipartFile file) throws IOException;

}