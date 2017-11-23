package ${package_name}.service;

import ${package_name}.entity.${table_name};
import java.util.List;
import java.util.Map;

/**
* 描述：${table_name} 服务实现层接口
*/
public interface I${table_name}Service {

    /**
    * 描述：根据Id获取
    * @param id
    */
    ${table_name} get(Long id);

    /**
    * 描述：根据Ids批量获取
    * @param ids
    * @Return List<${table_name}>
        */
    List<${table_name}> getAll(Long[] ids);

    /**
    * 描述：查询${table_name}对象
    * @param params
    * @Return List<${table_name}>
    */
    List<${table_name}> query(Map<String, Object> params);

    /**
    * 描述：保存${table_name}对象
    * @param  ${table_name?uncap_first}
    * @Return ${table_name}
    */
    ${table_name} add(${table_name} ${table_name?uncap_first});

    /**
    * 描述：批量保存${table_name}对象
    * @param ${table_name?uncap_first}List
    * @Return List<${table_name}>
    */
    List<${table_name}> addAll(List<${table_name}> ${table_name?uncap_first}List);

    /**
    * 描述：更新${table_name}对象
    * @param ${table_name?uncap_first}
    * @Return ${table_name}
    */
    ${table_name} update(${table_name} ${table_name?uncap_first});

    /**
    * 描述：批量更新${table_name}对象
    * @param ${table_name?uncap_first}List
    * @Return List<${table_name}>
    */
    List<${table_name}> updateAll(List<${table_name}> ${table_name?uncap_first}List);

    /**
    * 描述：根据Id删除
    * @param id
    */
    void delete(Long id);

    /**
    * 描述：根据Id数组批量删除
    * @param ids
    */
    void deleteAll(Long[] ids);

    /**
    * 描述：校验${table_name}对象
    * @param ${table_name?uncap_first}
    * @Return Boolean
    */
    Boolean validate(${table_name} ${table_name?uncap_first});

    /**
    * 描述：批量校验${table_name}对象
    * @param ${table_name?uncap_first}List
    * @Return Boolean
    */
    Boolean validateAll(List<${table_name}> ${table_name?uncap_first}List);

}