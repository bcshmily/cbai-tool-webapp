package cbai.web.app.toolbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cbai.util.EntityUtil;
import cbai.util.StringUtils;
import cbai.util.morphem.MorphemHelper;
import cbai.web.app.common.services.NameConvertService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("toolbox")
@RestController
public class NameConvertController {

    @Autowired
    private NameConvertService nameConvertService;

    @PostMapping(value = "/convert", params = "selMod=nameConvert")
    public String convert(@RequestParam String srcValue, @RequestParam String selSubMod, @RequestParam(value = "projectId") String projectId) {
        String output = "";
        try {
            log.debug("input:" + srcValue);
            String[] lines = srcValue.replaceAll("\r", "").split("\n");
            StringBuilder sb = new StringBuilder();
            MorphemHelper morphemHelper = nameConvertService.loadMorphemHelper(projectId);
            for (String line : lines) {
                line = line.trim();
                if (StringUtils.isEmpty(line)) {
                    sb.append("\n");
                } else {
                    if (sb.length() > 0) {
                        sb.append("\n");
                    }
                    String roma = morphemHelper.getRomaFromKanji(line);
                    if ("java".equals(selSubMod)) {
                        sb.append(EntityUtil.getJavaNameFromDbName(roma, true));
                    } else {
                        sb.append(roma);
                    }
                }
            }
            output = sb.toString();
        } catch (Exception e) {
            log.error("nameConvert", e);
            output = e.getMessage();
        }
        log.debug("output:" + output);
        return output;
    }
}
