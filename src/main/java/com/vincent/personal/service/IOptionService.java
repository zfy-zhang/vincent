package com.vincent.personal.service;

import com.vincent.personal.modal.vo.OptionVo;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:vincent
 * Date:2018/11/7
 */
public interface IOptionService {
    void insertOption(OptionVo optionVo);

    void insertOption(String name, String value);

    List<OptionVo> getOptions();


    /**
     * 保存一组配置
     *
     * @param options
     */
    void saveOptions(Map<String, String> options);

    OptionVo getOptionByName(String name);
}

