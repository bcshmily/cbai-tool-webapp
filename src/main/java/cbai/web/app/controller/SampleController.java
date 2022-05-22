package cbai.web.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cbai.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("sample")
@Slf4j
public class SampleController {

    @GetMapping("/")
    public String index(Model model) {
        return "sample/index";
    }

    @PostMapping(value = { "/convert" })
    @ResponseBody
    public String convert(@RequestParam String srcValue) {
        String output = "";
        try {
            log.debug("input:" + srcValue);
            String[] lines = srcValue.replaceAll("\r", "").split("\n");
            StringBuilder sb = new StringBuilder();
            for (String line : lines) {
                line = line.trim();
                if (StringUtils.isEmpty(line)) {
                    sb.append("\n");
                    continue;
                }
                sb.append(line).append("\n");
            }
            output = sb.toString();
        } catch (Exception e) {
            log.error("sample", e);
            output = e.getMessage();
        }
        log.debug("output:" + output);
        return output;

    }
}
