package kr.co.songjava.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/example/parameter")
public class ExampleParameterController {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * parameter 받는 방법
     *
     * @param id
     * @param code
     * @param model
     */
    @GetMapping("/example1")
    public void example1(@RequestParam String id, @RequestParam String code, Model model) {

        model.addAttribute("id", id);
        model.addAttribute("code", code);
    }

    /**
     * map 을 활용한 parameter 받는 방법
     *
     * @param paraMap
     * @param model
     */
    @GetMapping("/example2")
    public void example2(@RequestParam Map<String, Object> paraMap, Model model) {

        model.addAttribute("paraMap", paraMap);
    }

    /**
     * class 를 활용한 parameter 받는 방법
     *
     * @param parameter
     * @param model
     */
    @GetMapping("/example3")
    public void example3(ExampleParameter parameter, Model model) {

        model.addAttribute("parameter", parameter);
    }

    /**
     * @PathVariable 를 활용한 parameter 받는 방법
     *
     * @param id
     * @param model
     */
    @GetMapping("/example4/{id}/{code}")
    public String example4(@PathVariable String id, @PathVariable String code, Model model) {

        model.addAttribute("id", id);
        model.addAttribute("code", code);

        return "/example/parameter/example4";
    }

    /**
     * String[] 배열을 받는 방법
     *
     * @param ids
     * @param model
     * @return
     */
    @GetMapping("/example5")
    public String example5(@RequestParam String[] ids, Model model) {

        model.addAttribute("ids", ids);

        return "/example/parameter/example5";
    }

    /**
     * json 받는 방법
     */
    @GetMapping("/example6/form")
    public void form() {

    }

    /**
     * json 받는 방법
     *
     * @param requestBody
     * @return
     */
    @PostMapping("/example6/saveData")
    @ResponseBody
    public Map<String, Object> example6(@RequestBody ExampleRequestBodyUser requestBody) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        resultMap.put("result", true);

        logger.info("requestBody : {}", requestBody);

        return resultMap;
    }
}
