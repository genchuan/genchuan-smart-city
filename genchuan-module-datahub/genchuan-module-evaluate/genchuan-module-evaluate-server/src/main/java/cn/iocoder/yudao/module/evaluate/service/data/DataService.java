package cn.iocoder.yudao.module.evaluate.service.data;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.data.vo.DataPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.data.vo.DataSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.data.DataDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 上报数据 Service 接口
 *
 * @author 芋道源码
 */
public interface DataService {

    /**
     * 创建上报数据
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createData(@Valid DataSaveReqVO createReqVO);

    /**
     * 更新上报数据
     *
     * @param updateReqVO 更新信息
     */
    void updateData(@Valid DataSaveReqVO updateReqVO);

    /**
     * 删除上报数据
     *
     * @param id 编号
     */
    void deleteData(Long id);

    /**
    * 批量删除上报数据
    *
    * @param ids 编号
    */
    void deleteDataListByIds(List<Long> ids);

    /**
     * 获得上报数据
     *
     * @param id 编号
     * @return 上报数据
     */
    DataDO getData(Long id);

    /**
     * 获得上报数据分页
     *
     * @param pageReqVO 分页查询
     * @return 上报数据分页
     */
    PageResult<DataDO> getDataPage(DataPageReqVO pageReqVO);

}