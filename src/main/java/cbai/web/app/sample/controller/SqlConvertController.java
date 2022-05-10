package cbai.web.app.toolbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cbai.util.sqlconvert.SqlConverterAbstract;
import cbai.web.app.toolbox.services.SqlConvertService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("toolbox")
@RestController
public class SqlConvertController {

    @Autowired
    private SqlConvertService sqlConvertService;

    @PostMapping(value = "/convert", params = "selMod=sqlConvert")
    public String convert(@RequestParam(value = "srcValue", required = true) String srcValue, @RequestParam(value = "projectId") String projectId) {
        String output = "";
        try {
            log.debug("input:" + srcValue);

            SqlConverterAbstract sqlConverter = sqlConvertService.loadSqlConverter(projectId);
            output = sqlConverter.convert(srcValue);
        } catch (Exception e) {
            log.error("sqlConvert", e);
            output = e.getMessage();
        }
        log.debug("output:" + output);
        return output;
    }
}
