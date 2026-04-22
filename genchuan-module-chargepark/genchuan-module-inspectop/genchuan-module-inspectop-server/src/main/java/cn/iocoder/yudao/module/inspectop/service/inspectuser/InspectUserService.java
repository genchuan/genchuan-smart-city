package cn.iocoder.yudao.module.inspectop.service.inspectuser;

import java.util.*;

import cn.iocoder.yudao.module.inspectop.framework.ImportRespVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.inspectuser.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.inspectuser.InspectUserDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 巡检人员 Service 接口
 *
 * @author zhucongquan
 */
public interface InspectUserService {

    /**
     * 创建巡检人员
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectUser(@Valid InspectUserSaveReqVO createReqVO);

    /**
     * 更新巡检人员
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectUser(@Valid InspectUserSaveReqVO updateReqVO);

    /**
     * 删除巡检人员
     *
     * @param id 编号
     */
    void deleteInspectUser(Long id);

    /**
    * 批量删除巡检人员
    *
    * @param ids 编号
    */
    void deleteInspectUserListByIds(List<Long> ids);

    /**
     * 获得巡检人员
     *
     * @param id 编号
     * @return 巡检人员
     */
    InspectUserDO getInspectUser(Long id);

    /**
     * 获得巡检人员分页
     *
     * @param pageReqVO 分页查询
     * @return 巡检人员分页
     */
    PageResult<InspectUserDO> getInspectUserPage(InspectUserPageReqVO pageReqVO);

    /**
     * 导入巡检人员
     *
     * @param file Excel文件
     * @param updateSupport 是否更新已存在的数据
     * @return 导入结果
     */
    ImportRespVO importInspectUser(MultipartFile file, boolean updateSupport);

    /**
     * 启用巡检人员
     *
     * @param id 人员ID
     */
    void enableInspectUser(Long id);

    /**
     * 禁用巡检人员
     *
     * @param id 人员ID
     */
    void disableInspectUser(Long id);

    /**
     * 获得巡检人员图表统计
     *
     * @return 图表统计结果
     */
    InspectUserChartRespVO getInspectUserChart();
}