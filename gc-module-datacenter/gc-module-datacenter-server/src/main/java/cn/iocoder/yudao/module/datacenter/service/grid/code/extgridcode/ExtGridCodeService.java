package cn.iocoder.yudao.module.datacenter.service.grid.code.extgridcode;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.extgridcode.vo.ExtGridCodePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.extgridcode.vo.ExtGridCodeSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.code.extgridcode.ExtGridCodeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 扩展网格编码 Service 接口
 *
 * @author zcq
 */
public interface ExtGridCodeService {

    /**
     * 创建扩展网格编码
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createExtGridCode(@Valid ExtGridCodeSaveReqVO createReqVO);

    /**
     * 更新扩展网格编码
     *
     * @param updateReqVO 更新信息
     */
    void updateExtGridCode(@Valid ExtGridCodeSaveReqVO updateReqVO);

    /**
     * 删除扩展网格编码
     *
     * @param id 编号
     */
    void deleteExtGridCode(Long id);

    /**
     * 获得扩展网格编码
     *
     * @param id 编号
     * @return 扩展网格编码
     */
    ExtGridCodeDO getExtGridCode(Long id);

    /**
     * 获得扩展网格编码分页
     *
     * @param pageReqVO 分页查询
     * @return 扩展网格编码分页
     */
    PageResult<ExtGridCodeDO> getExtGridCodePage(ExtGridCodePageReqVO pageReqVO);

}