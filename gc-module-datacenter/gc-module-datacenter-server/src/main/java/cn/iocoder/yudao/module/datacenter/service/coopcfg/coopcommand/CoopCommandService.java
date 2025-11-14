package cn.iocoder.yudao.module.datacenter.service.coopcfg.coopcommand;

import cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopcommand.vo.CoopCommandPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopcommand.vo.CoopCommandSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.coopcfg.coopcommand.CoopCommandDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 联动指令 Service 接口
 *
 * @author 亘川智城
 */
public interface CoopCommandService {

    /**
     * 创建联动指令
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCoopCommand(@Valid CoopCommandSaveReqVO createReqVO);

    /**
     * 更新联动指令
     *
     * @param updateReqVO 更新信息
     */
    void updateCoopCommand(@Valid CoopCommandSaveReqVO updateReqVO);

    /**
     * 删除联动指令
     *
     * @param id 编号
     */
    void deleteCoopCommand(Long id);

    /**
     * 获得联动指令
     *
     * @param id 编号
     * @return 联动指令
     */
    CoopCommandDO getCoopCommand(Long id);

    /**
     * 获得联动指令分页
     *
     * @param pageReqVO 分页查询
     * @return 联动指令分页
     */
    PageResult<CoopCommandDO> getCoopCommandPage(CoopCommandPageReqVO pageReqVO);

}