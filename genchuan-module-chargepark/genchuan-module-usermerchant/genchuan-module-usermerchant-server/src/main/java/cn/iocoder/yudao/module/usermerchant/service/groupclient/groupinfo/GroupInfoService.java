package cn.iocoder.yudao.module.usermerchant.service.groupclient.groupinfo;

import java.util.*;

import jakarta.validation.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.groupclient.groupinfo.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.groupclient.groupinfo.GroupInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import jakarta.validation.constraints.NotEmpty;

/**
 * 集团信息 Service 接口
 *
 * @author 亘川智城
 */
public interface GroupInfoService {

    /**
     * 创建集团信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Boolean createGroupInfo(@Valid GroupInfoCreateReqVO createReqVO);

    /**
     * 更新集团信息
     *
     * @param updateReqVO 更新信息
     */
    void updateGroupInfo(@Valid GroupInfoUpdateReqVO updateReqVO);

    /**
     * 删除集团信息
     *
     * @param id 编号
     */
    void deleteGroupInfo(Long id);

    /**
    * 批量删除集团信息
    *
    * @param ids 编号
    */
    void deleteGroupInfoListByIds(List<Long> ids);

    /**
     * 获得集团信息
     *
     * @param id 编号
     * @return 集团信息
     */
    GroupInfoDO getGroupInfo(Long id);

    /**
     * 获得集团信息分页
     *
     * @param pageReqVO 分页查询
     * @return 集团信息分页
     */
    PageResult<GroupInfoDO> getGroupInfoPage(GroupInfoPageReqVO pageReqVO);

    /**
     * 导入集团信息
     *
     * @param list 集团信息
     */
    Boolean importGroups(List<GroupInfoImportExcelVO> list, Boolean updateSupport);

    /**
     * 批量审核集团
     *
     * @param reqVO 更新信息
     */
    void batchUpdateGroupInfo(@Valid GroupInfoAuditReqVO reqVO);

    /**
     * 批量启用/禁用集团信息
     *
     * @param ids 编号
     */
    void updateGroupStatus(@NotEmpty(message = "集团ID列表不能为空") List<Long> ids, String status);

    /**
     * 集团信息统计可视化
     *
     * @param chartReqVO 时间范围
     * @return 统计信息
     */
    GroupInfoChartRespVO getGroupInfoChart(@Valid GroupInfoChartReqVO chartReqVO);
}