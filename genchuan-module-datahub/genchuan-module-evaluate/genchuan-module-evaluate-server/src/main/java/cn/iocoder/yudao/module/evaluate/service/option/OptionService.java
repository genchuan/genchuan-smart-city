package cn.iocoder.yudao.module.evaluate.service.option;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.option.vo.OptionPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.option.vo.OptionSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.option.OptionDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 选项 Service 接口
 *
 * @author 芋道源码
 */
public interface OptionService {

    /**
     * 创建选项
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOption(@Valid OptionSaveReqVO createReqVO);

    /**
     * 更新选项
     *
     * @param updateReqVO 更新信息
     */
    void updateOption(@Valid OptionSaveReqVO updateReqVO);

    /**
     * 删除选项
     *
     * @param id 编号
     */
    void deleteOption(Long id);

    /**
    * 批量删除选项
    *
    * @param ids 编号
    */
    void deleteOptionListByIds(List<Long> ids);

    /**
     * 获得选项
     *
     * @param id 编号
     * @return 选项
     */
    OptionDO getOption(Long id);

    /**
     * 获得选项分页
     *
     * @param pageReqVO 分页查询
     * @return 选项分页
     */
    PageResult<OptionDO> getOptionPage(OptionPageReqVO pageReqVO);

}