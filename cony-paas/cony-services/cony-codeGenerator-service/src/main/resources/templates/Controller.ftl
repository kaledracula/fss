package ${package_name}.controller;

import com.glodon.coca.service.contract.result.Result;
import com.glodon.coca.service.provider.swagger.EnableApiDoc;
import ${package_name}.service.I${table_name}Service;
import ${package_name}.entity.${table_name};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
* 描述：${table_name}控制层
*/
@EnableApiDoc
@RestController
@RequestMapping("/${table_name?uncap_first}")
public class ${table_name}Controller {

    @Autowired
    private I${table_name}Service ${table_name?uncap_first}Service;

    private I${table_name}Service getService() {
        return ${table_name?uncap_first}Service;
    }

    /**
    * 描述：根据Id 查询
    * @param id
    * @Return Result
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result get(@PathVariable("id") Long id) {
        return new Result(getService().get(id));
    }

    /**
    * 描述：根据Ids 批量获取对象
    * @param ids
    * @Return Result
    */
    @RequestMapping(value = "/getAll/{ids}", method = RequestMethod.GET)
    public Result get(@PathVariable("ids") Long[] ids) {
        return new Result(getService().getAll(ids));
    }

    /**
    * 描述：查询${table_name}对象列表
    * @param params
    * @Return Result
    */
    @RequestMapping(value = "/query",method = RequestMethod.POST)
    public Result query(@RequestParam Map<String,Object> params) {
        return new Result(getService().query(params));
    }

    /**
    * 描述：保存${table_name}对象
    * @param ${table_name?uncap_first}
    * @Return Result
    */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@Valid @RequestBody ${table_name} ${table_name?uncap_first}) {
        if(getService().validate(${table_name?uncap_first})) {
            return new Result(getService().add(${table_name?uncap_first}));
        }
        return new Result();
    }

    /**
    * 描述：批量保存${table_name}对象
    * @param ${table_name?uncap_first}List
    * @Return Result
    */
    @RequestMapping(value = "/addAll", method = RequestMethod.POST)
    public Result addAll(@Valid @RequestBody List<${table_name}> ${table_name?uncap_first}List) {
        if(getService().validateAll(${table_name?uncap_first}List)) {
            return new Result(getService().addAll(${table_name?uncap_first}List));
        }
        return new Result();
    }

    /**
    * 描述：更新${table_name}对象
    * @param ${table_name?uncap_first}
    * @Return Result
    */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result update(@RequestBody ${table_name} ${table_name?uncap_first}) {
        return new Result(getService().update(${table_name?uncap_first}));
    }

    /**
    * 描述：批量更新${table_name}对象
    * @param ${table_name?uncap_first}List
    * @Return Result
    */
    @RequestMapping(value = "/updateAll", method = RequestMethod.PUT)
    public Result updateAll(@RequestBody List<${table_name}> ${table_name?uncap_first}List) {
        return new Result(getService().updateAll(${table_name?uncap_first}List));
    }

    /**
    * 描述：根据Id 删除
    * @param id
    * @Return Result
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable("id") Long id) {
        getService().delete(id);
        return new Result();
    }

    /**
    * 描述：根据Id数组批量删除
    * @param ids
    * @Return Result
    */
    @RequestMapping(value = "/deleteAll/{ids}", method = RequestMethod.DELETE)
    public Result deleteAll(@PathVariable("ids") Long[] ids) {
        getService().deleteAll(ids);
        return new Result();
    }


    /**
    * 描述：保存${table_name}对象
    * @param ${table_name?uncap_first}
    * @Return Result
    */
    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public Result validate(@Valid @RequestBody ${table_name} ${table_name?uncap_first}) {
        getService().validate(${table_name?uncap_first});
        return new Result();
    }

    /**
    * 描述：批量校验${table_name}对象
    * @param ${table_name?uncap_first}List
    * @Return Result
    */
    @RequestMapping(value = "/validateAll", method = RequestMethod.POST)
    public Result validateAll(@Valid @RequestBody List<${table_name}> ${table_name?uncap_first}List) {
        getService().validateAll(${table_name?uncap_first}List);
        return new Result();
    }
}