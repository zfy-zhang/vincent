package com.vincent.personal.service;

import com.github.pagehelper.PageInfo;
import com.vincent.personal.modal.vo.AttachVo;

/**
 * Created with IDEA
 * author:vincent
 * Date:2018/11/7
 */
public interface IAttachService {
    /**
     * 分页查询附件
     *
     * @param page
     * @param limit
     * @return
     */
    PageInfo<AttachVo> getAttachs(Integer page, Integer limit);

    /**
     * 保存附件
     *
     * @param fname
     * @param fkey
     * @param ftype
     * @param author
     */
    void save(String fname, String fkey, String ftype, Integer author);

    /**
     * 根据附件Id,查询附件
     *
     * @param id
     * @return
     */
    AttachVo selectById(Integer id);

    /**
     * 删除附件
     *
     * @param id
     */
    void deleteById(Integer id);
}
