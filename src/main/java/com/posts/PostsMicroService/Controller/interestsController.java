package com.posts.PostsMicroService.Controller;


import com.posts.PostsMicroService.DTO.InterestDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/interest")
public class interestsController {



    @RequestMapping(value = "getInterests",method = RequestMethod.GET)
    public InterestDto getInterests() throws IOException {
        Document document=Jsoup.connect("https://www.careeraddict.com/hobbies-cv-list").get();

        Elements elements=document.select("li strong");
        List<String> intersts=new ArrayList<>();
        for(Element links:elements)
        {
//            System.out.println("link "+links.attr("href"));
            intersts.add(links.text().toString());
            //System.out.println(links.text());
        }

        InterestDto interestDto=new InterestDto();
        interestDto.setInterests(intersts);

        return interestDto;
    }


}
