package theon.in.postget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController // It's used to
public class RestEndPoints {

    @Value("${default.course.name}")
    private String name;

    @Value("${default.course.chapterCount}")
    private int chapterCount;

    @Autowired
    private CourseConfig config;

    @RequestMapping("/course")
    public Course getEndpoint(@RequestParam(name="name", defaultValue = "String boot", required = false) String name,
                              @RequestParam(name="chapterCount", defaultValue = "5", required = false) int chapterCount){
        return new Course(name, chapterCount);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/course")
    public String postEndpoint(@RequestBody Course course){
        return String.format("You save: %s with %d chapters", config.getName(), config.getChapterCount());
    }

}
