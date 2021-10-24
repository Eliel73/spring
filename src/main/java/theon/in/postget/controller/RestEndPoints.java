package theon.in.postget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController // @RestController is an annotation that is used to define that the controller is providing REST services with JSON type response.
public class RestEndPoints {

    @Value("${default.course.name}")  // @Value is an annotation that is used to set variable's value using a properties file.
    private String name;

    @Value("${default.course.chapterCount}")
    private int chapterCount;

    @Autowired //@Autowired is an annotation that allows us to instantiate a class without necessarily using a setter or constructor, which makes it much easier to use the concept of Dependency Injection (DI).
    private CourseConfig config;

    @RequestMapping("/course") //@RequestMapping is an annotation that is used to set the url path. By default, the method is GET.
    public Course getEndpoint(@RequestParam(name="name", defaultValue = "String boot", required = false) String name,
                              @RequestParam(name="chapterCount", defaultValue = "5", required = false) int chapterCount){
        return new Course(name, chapterCount);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/course") //It's used to set the url path, although the method (POST, GET, DELETE, PUT) is specific, in this case POST
    public String postEndpoint(@RequestBody Course course){
        return String.format("You save: %s with %d chapters", config.getName(), config.getChapterCount());
    }

}
