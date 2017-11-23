package ${package_name}.service.impl;

import ${package_name}.entity.${table_name};
import ${package_name}.mapper.${table_name}Mapper;
import com.glodon.coca.data.common.exception.DataException;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;


/**
* 描述：${table_name} 服务实现层
*/
@Service
@Transactional
public class ${table_name}Service implements I${table_name}Service {

    @Autowired
    private ${table_name}Mapper ${table_name?uncap_first}Mapper;

    @Override
    @Transactional(readOnly = true)
    public ${table_name} get(Long id)  {
        ${table_name} ${table_name?uncap_first} = ${table_name?uncap_first}Mapper.selectByPrimaryKey(id);
        if(${table_name?uncap_first} == null)  {
            throw new DataNotFoundException();
        }
        return ${table_name?uncap_first};
    }

    /**
    * 描述：根据Ids批量获取
    * @param ids
    * @Return List<${table_name}>
    */
    @Override
    @Transactional(readOnly = true)
    public List<${table_name}> getAll(Long[] ids) {
        Map<String,Object> param = new HashMap<>();
        param.put("ids",ids);
        return this.query(param);
    }

    /**
    * 描述：查询${table_name}对象
    * @param params
    * @Return List<${table_name}>
    */
    @Override
    @Transactional(readOnly = true)
    public List<${table_name}> query(Map<String, Object> params) {
        return null;
    }

    /**
    * 描述：保存${table_name}对象
    * @param ${table_name?uncap_first}
    * @Return ${table_name}
    */
    @Override
    public ${table_name} add(${table_name} ${table_name?uncap_first}) {
        ${table_name?uncap_first}Mapper.insertSelective(${table_name?uncap_first});
        return ${table_name?uncap_first}Mapper.selectByPrimaryKey(${table_name?uncap_first}.getId());
    }

    /**
    * 描述：批量保存${table_name}对象
    * @param ${table_name?uncap_first}List
    * @Return List<${table_name}>
    */
    @Override
    public List<${table_name}> addAll(List<${table_name}> ${table_name?uncap_first}List) {
        List<${table_name}>  ${table_name?uncap_first}s = new ArrayList<>();
        for(${table_name} ${table_name?uncap_first} : ${table_name?uncap_first}List) {
            ${table_name?uncap_first}s.add(this.add(${table_name?uncap_first}));
        }
        return ${table_name?uncap_first}s;
    }

    /**
    * 描述：更新${table_name}对象
    * @param ${table_name?uncap_first}
    * @Return ${table_name}
    */
    @Override
    public ${table_name} update(${table_name} ${table_name?uncap_first}) {
        if(${table_name?uncap_first}Mapper.updateByPrimaryKeySelective(${table_name?uncap_first}) != 1) {
            this.get(${table_name?uncap_first}.getId())
            throw new DataException("修改信息失败，请重新修改！");
        }
        return this.get(${table_name?uncap_first}.getId());
    }

    /**
    * 描述：批量更新${table_name}对象
    * @param ${table_name?uncap_first}List
    * @Return List<${table_name}>
    */
    @Override
    public List<${table_name}> updateAll(List<${table_name}> ${table_name?uncap_first}List) {
        List<${table_name}>  ${table_name?uncap_first}s = new ArrayList<>();
        for(${table_name} ${table_name?uncap_first} : ${table_name?uncap_first}List) {
            ${table_name?uncap_first}s.add(this.update(${table_name?uncap_first}));
        }
        return ${table_name?uncap_first}s;
    }

    /**
    * 描述：根据Id删除
    * @param id
    */
    @Override
    public void delete(Long id) {
        ${table_name?uncap_first}Mapper.deleteByPrimaryKey(id);
    }

    /**
    * 描述：根据Id数组批量删除
    * @param ids
    */
    @Override
    public void deleteAll(Long[] ids) {
        Arrays.asList(ids).stream().forEach(x -> this.delete(x));
    }

    /**
    * 描述：校验实体数据
    *
    * @param ${table_name?uncap_first} 指定要校验的对象
    * @return 校验通过与否
    */
    @Override
    @Transactional(readOnly = true)
    public Boolean validate(${table_name} ${table_name?uncap_first}) {
        if (${table_name?uncap_first} != null) {
            String result = doValidate(${table_name?uncap_first});
            if (!StringUtils.isEmpty(result)) {
                throw new DataException(result);
            }
        }
        return true;
    }

    /**
    * 描述：批量校验${table_name}对象
    * @param ${table_name?uncap_first}List
    * @Return Boolean
    */
    @Override
    @Transactional(readOnly = true)
    public Boolean validateAll(List<${table_name}> ${table_name?uncap_first}List) {
        if(${table_name?uncap_first}List != null) {
            StringBuilder result = new StringBuilder();
            for(${table_name} ${table_name?uncap_first} : ${table_name?uncap_first}List) {
                if(${table_name?uncap_first} != null) {
                    result.append(doValidate(${table_name?uncap_first}));
                }
            }
            if(!StringUtils.isEmpty(result.toString())) {
                throw new DataException(result.toString());
            }
        }
        return true;
    }

    /**
    * 校验实体的具体实现。
    *
    * @param ${table_name?uncap_first} 指定要校验的对象
    * @return 校验结果
    */
    private String doValidate(${table_name} ${table_name?uncap_first}) {
        return "";
    }
}